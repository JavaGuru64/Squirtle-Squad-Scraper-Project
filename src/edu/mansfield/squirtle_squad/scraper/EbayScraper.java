package edu.mansfield.squirtle_squad.scraper;

import java.io.IOException;
import java.util.ArrayList;

import edu.mansfield.squirtle_squad.model.Item;

/*
 * 
 * Figured that it would be best passing the html into the class's
 * constructor and then all the data can be returned nicely. May
 * Require the an implementation of the data formatter to be 
 * functional. And you will definitely have to build the item class 
 * that is in the model package. If I think of anything else I will 
 * add it later. Remember to keep things generic.
 *
 */

public class EbayScraper extends Scraper{
	
	// JSoup Document doc is a variable of Scraper
	
	public EbayScraper(String url) throws IOException{
		super(url);
	}
	
	public int getItemCount(){
		
		/*
		 *  TODO Return the number of items listed above the item listings
		 *  
		 *  This will be used to know whether the URL needs to be updated to
		 *  narrow the search.
		 *  
		 */
		return 0;
	}
	
	public ArrayList<Item> getItemsListed(){
		ArrayList<Item> itemsOnPage = new ArrayList<Item>(200);
		
		/*
		 * TODO Return an ArrayList of the items listed on the webpage
		 * 
		 * Please keep the default size of the ArrayList at 200
		 * 
		 */
		
		return itemsOnPage;
	}
}
