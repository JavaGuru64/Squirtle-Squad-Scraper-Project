package edu.mansfield.squirtle_squad.controller;

import edu.mansfield.squirtle_squad.database.DatabaseInteractions;
import edu.mansfield.squirtle_squad.delegates.ScanDelegate;
import edu.mansfield.squirtle_squad.delegates.WebScannerDelegate;
import edu.mansfield.squirtle_squad.model.Item;
import edu.mansfield.squirtle_squad.scraper.EbayScraper;
import edu.mansfield.squirtle_squad.scraper.Ebay_Cat;
import edu.mansfield.squirtle_squad.scraper.Scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/* 
 * http://www.ebay.com/sch/i.html?_png=1&rt=nc&_mPrRngCbx=1&_udlo=0&_udhi=100
 */

/*
 * http://www.ebay.com/sch/i.html?_from=R40&_sacat=0&_png=1&_nkw=all+items&_pgn=16&_skc=3000&rt=nc
 */


public class EbayScanController extends ScanController implements WebScannerDelegate {
	
	final int ITEMS_PER_PAGE = 10000;
	
	private ArrayList<String> categories;
	private int scanLength; 
//	private int scanPercent;
    private int percentIncrementPerCategory;
	private ScanDelegate delegate; 
	private boolean isCanceled;
	private Connection dbConnect;
	
	public EbayScanController(ScanDelegate delegate) {
		this.delegate = delegate;
		initializeScan();
	}
	
	public EbayScanController() {
		initializeScan();
	}


	public void scan() {
		try{
			ArrayList<Thread> threads = new ArrayList<Thread>();
			EbayScanController referenceToThis = this;
			for(String categoryURL: categories){
				Thread aThread = new Thread(new Runnable(){
					@Override
					public void run(){
						try{
							scanCategory(new String(categoryURL));
						}
						catch(IOException exception){
							exception.printStackTrace();
						}
						delegate.incrementScanPercentage(referenceToThis, percentIncrementPerCategory);
					}
				}, categoryURL);
				
				aThread.start();
				
				threads.add(aThread);
			}
			
			boolean threadsAreAlive;
			do{
				threadsAreAlive = false;
				
				for(Thread thread: threads){
					//System.out.println(thread.getName()+ ": " + (thread.isAlive()?" is alive": " is dead"));
					if(thread.isAlive()){
						threadsAreAlive = true;
					}
				}
				Thread.sleep(1000);
			}while(threadsAreAlive);
		}catch(Exception e){
			e.printStackTrace();
			// -TODO Fail nice
		}
	}
	
	public void scanCategory(String categoryURL) throws IOException{
		EbayScraper scraper;
		int numberOfItemsInCategory;
		int itemsDownloaded;
		int minPrice;
		int maxPrice;
		int priceIncrement;
		
		String url = categoryURL;
		itemsDownloaded = 0;
		minPrice = 0;
		maxPrice = 10;
		priceIncrement = maxPrice - minPrice;
		
		scraper = new EbayScraper(this, url);
		numberOfItemsInCategory = scraper.getItemCount();
		delegate.setStatusText(this, "Downloading: " + categoryURL);
		
		// Begin the scraping process for the current category
		do{
			String priceRange = "";
			int maxPriceDividor = 0;
			
			scraper = new EbayScraper(this, url);
			while(scraper.getItemCount() > 10000 &&  maxPrice < 1000000000 && !isCanceled){
				maxPriceDividor++;
				
				maxPrice = (minPrice + priceIncrement)/maxPriceDividor;
				if(maxPrice <= minPrice){
					maxPrice = minPrice + 10;
					delegate.setStatusText(this, "Something Weird Happened!");
					break;
				}
				
				priceRange = "&_mPrRngCbx=1&_udlo=" + minPrice + "&_udhi=" + maxPrice + "&rt=nc";
				
				String testString = url + "?_png=1" + priceRange;
				scraper = new EbayScraper(this, testString);
				delegate.setStatusText(this, "Adjusting Price Range:" + minPrice + "-" + maxPrice
						+ " | " + scraper.getItemCount() + " Items in range");
			}
			
			priceIncrement = Math.abs(2*(maxPrice - minPrice));
			minPrice = maxPrice;
			
			scraper = new EbayScraper(this, url + "?_png=1" + priceRange);
			delegate.setStatusText(this, "Downloading: " + url + "?_png=1" + priceRange);
			pushItemsToDataBase(scraper.getItemsListed());
			itemsDownloaded += scraper.getItemCount();
			
		// WE WON'T STOP UNTIL ALL YOUR DATUM IS BELONG TO US.
		}while(itemsDownloaded < numberOfItemsInCategory && !isCanceled);
	}

	protected boolean initializeScan(){
		try {
			categories = Ebay_Cat.getAllCategories();
			scanLength = categories.size();
			percentIncrementPerCategory = 10000/scanLength;
			DatabaseInteractions dbInteract = new DatabaseInteractions();
			dbConnect = dbInteract.dbConnect();
			//System.out.println(scanLength);
			//scanPercent = 0;
			
		} catch (IOException e) {
			e.printStackTrace();
			showAlertErrorBox("Error Code: " + e.getClass(), e.getLocalizedMessage());
			/*
			 *  -TODO exit scan and throw message box exclaiming failure may 
			 *        want to make a delegate
			 */
			return false;
		}
		return true;
	}
	
	public void alertConnectionTimeOut(Scraper source){
		showAlertErrorBox("Connection Timeout", "Your connection timed out.\n"
				+ "Please, check to make sure you are connected to the internet and try again.");
		//-TODO Alert User of connection timeout. Fail Happy!!!
	}
	
	private boolean pushItemsToDataBase(Collection<Item> items){
		
		for(Item item: items){
			DatabaseInteractions dbInteract = new DatabaseInteractions();
			try {
				//System.out.println(item);
				dbInteract.addOrUpdateData(dbConnect, item);
			} catch (SQLException e) {
				e.printStackTrace();
				// Perhaps
				return false;
			}
		}
		
		return true;
	}
	
	private void showAlertErrorBox(String error, String errorMessage){
		JOptionPane.showMessageDialog(null, errorMessage, error, JOptionPane.ERROR_MESSAGE);
	}
	
	public void kill(){
		isCanceled = true;
	}
}
