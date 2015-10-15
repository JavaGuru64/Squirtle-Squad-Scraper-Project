package edu.mansfield.squirtle_squad.controller;

import edu.mansfield.squirtle_squad.model.Item;
import edu.mansfield.squirtle_squad.scraper.EbayScraper;
import edu.mansfield.squirtle_squad.scraper.Ebay_Cat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


/* 
 * http://www.ebay.com/sch/i.html?_png=1&rt=nc&_mPrRngCbx=1&_udlo=0&_udhi=100
 */

/*
 * http://www.ebay.com/sch/i.html?_from=R40&_sacat=0&_png=1&_nkw=all+items&_pgn=16&_skc=3000&rt=nc
 */


public class EbayScanController implements WebScanner {
	
	final int ITEMS_PER_PAGE = 10000;
	
	private ArrayList<String> categories;
	private int scanLength; 
	private int scanPercent;
	private int percentIncrementPerCategory;
	private int timeSinceLastPercentIncrease;
	private int timeRemainingEstimate;
	private Hashtable<Long, Item> items;
	
	
	public EbayScanController() {
		initializeScan();
	}


	public void scan() {
		
		String url;
		EbayScraper scraper;
//		String itemPage;
		int numberOfItemsInCategory;
		int itemsDownloaded;
		int minPrice;
		int maxPrice;
		int priceIncrement;
		
		try{
		// Step through the catagories and scrape ALL THE DATUM!!!
		for(String categoryURL: categories){
			url = categoryURL;
			itemsDownloaded = 0;
			minPrice = 0;
			maxPrice = 10;
			priceIncrement = maxPrice - minPrice;
			
			scraper = new EbayScraper(url);
			numberOfItemsInCategory = scraper.getItemCount();
			
			items = new Hashtable<Long, Item>(2*numberOfItemsInCategory);
			
			System.out.println("Downloading: " + categoryURL);
			
			// Begin the scraping process for the current category
			do{
				String priceRange = "";
				int maxPriceDividor = 0;
				
				scraper = new EbayScraper(url);
				while(scraper.getItemCount() > 10000){
					maxPriceDividor++;
					
					
					maxPrice = (minPrice + priceIncrement)/maxPriceDividor;
					if(maxPrice <= minPrice){
						maxPrice = minPrice + 10;
						break;
					}
					
					priceRange = "&_mPrRngCbx=1&_udlo=" + minPrice + "&_udhi=" + maxPrice + "&rt=nc";
					System.out.println("Price Range:" + minPrice + "-" + maxPrice);
					String testString = url + "?_png=1" + priceRange;
					System.out.println("New URL: " + testString);
					scraper = new EbayScraper(testString);
					System.out.println("Adjusting Price: " + scraper.getItemCount() + " Items found");
				}
				priceIncrement = Math.abs(2*(maxPrice - minPrice));
				minPrice = maxPrice;
				
				System.out.println("Num Items In Price Range: " + scraper.getItemCount());
				
//				for(int i = 0; i < scraper.getItemCount()/ITEMS_PER_PAGE; i++){
//					
//					itemPage = "?_png=1&_pgn=" + (i+1) + "&_skc=" + i*ITEMS_PER_PAGE;
//					scraper = new EbayScraper(url + itemPage + priceRange);
					scraper = new EbayScraper(url + "?_png=1" + priceRange);
					for(Item item: scraper.getItemsListed()){
						
						 if(!items.containsKey(item.getId())){
							 items.put(item.getId(), item);
						 }
					}
					itemsDownloaded += scraper.getItemCount();
					System.out.println("Downloaded: " + url + priceRange);
					System.out.println("Items Downloaded: " + itemsDownloaded);
//				}
				
			// WE WON'T STOP UNTIL ALL YOUR DATUM IS BELONG TO US.
			}while(itemsDownloaded < numberOfItemsInCategory);
			
			System.out.println("Category " + categoryURL + " is done.");
			// -TODO increment scan percent and update time for scan
		}
		}catch(IOException e){
			e.printStackTrace();
			// -TODO Fail nice
		}
	}

	private boolean initializeScan(){
		try {
			categories = Ebay_Cat.getAllCategories();
			scanLength = categories.size();
			scanPercent = 0;
			
		} catch (IOException e) {
			e.printStackTrace();
			/*
			 *  -TODO exit scan and throw message box exclaiming failure may 
			 *        want to make a delegate
			 */
			return false;
		}
		return true;
	}
	
	public static void main(String[] args){
		EbayScanController ebay = new EbayScanController();
		ebay.scan();
	}
}
