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
	
	final int ITEMS_PER_PAGE = 200;
	
	private ArrayList<String> categories;
	private int scanLength; 
	private int scanPercent;
	private int percentIncrementPerCategory;
	private int timeSinceLastPercentIncrease;
	private int timeRemainingEstimate;
	private Hashtable<String, Item> items;
	
	
	public EbayScanController() {
		initializeScan();
	}


	public void scan() {
		
		String url;
		EbayScraper scraper;
		String itemPage;
		int numberOfItemsInCategory;
		int itemsDownloaded;
		int minPrice;
		int maxPrice;
		int priceIncrement;
		
		// Step through the catagories and scrape ALL THE DATUM!!!
		for(String categoryURL: categories){
			url = categoryURL;
			itemsDownloaded = 0;
			minPrice = 0;
			maxPrice = 100;
			priceIncrement = maxPrice - minPrice;
			
			scraper = new EbayScraper(url);
			numberOfItemsInCategory = scraper.getItemCount();
			
			items = new Hashtable<String, Item>(2*numberOfItemsInCategory);
			
			// Begin the scraping process for the current category
			do{
				String priceRange = "";
				while(scraper.getItemCount() > 10000){
					int maxPriceDividor = 0;
					maxPriceDividor++;
					maxPrice = (minPrice + (priceIncrement)/maxPriceDividor);
					priceRange = "?_png=1&rt=nc&_mPrRngCbx=1&_udlo=" + minPrice + "&_udhi=" + maxPrice;
					String testString = url + priceRange;
					scraper = new EbayScraper(testString);
				}
				priceIncrement = 2*(maxPrice - minPrice);
				minPrice = maxPrice;
				

				
				for(int i = 0; i < scraper.getItemCount()/ITEMS_PER_PAGE; i++){
					
					itemPage = "?_png=1&_pgn=" + (i+1) + "&_skc=" + i*ITEMS_PER_PAGE + "&rt=nc";
					scraper = new EbayScraper(url + itemPage + priceRange);
					
					for(Item item: scraper.getItemsListed()){
						itemsDownloaded++;
						 if(!items.containsKey(item.getID())){
							 items.put(item.getID(), item);
						 }
					}
				}
				
			// WE WON'T STOP UNTIL ALL YOUR DATUM IS BELONG TO US.
			}while(itemsDownloaded < numberOfItemsInCategory);
			
			// -TODO increment scan percent and update time for scan
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
}
