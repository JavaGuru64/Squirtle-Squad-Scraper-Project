package edu.mansfield.squirtle_squad.scraper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.mansfield.squirtle_squad.model.Item;
import edu.mansfield.squirtle_squad.utilities.DataFormatter;

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
		try{
			return Integer.parseInt(doc.select("span.listingscnt").text().replaceAll("\\D", ""));
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public ArrayList<Item> getItemsListed(){
		ArrayList<Item> itemsOnPage = new ArrayList<Item>(200);
		Element e;
		Elements es;
		
		try{
			e = doc.select("ul#ListViewInner").get(0);
			es = e.select("li[listingid]");
		}
		catch(Exception exception){
			return itemsOnPage;
		}
		
		//int i=0;
		
		
		for(Element el: es){
			//System.out.println(++i);
			long id = Long.parseLong(el.attr("listingid"));
			String title = el.select("a.vip").get(0).text();
			double price = DataFormatter.doubleFromPriceString(el.select("li.lvprice").get(0).text());
			long time = 0;
			boolean isAuction = false;
			try{
				Element elm = el.select("span.tme").get(0).select("span[timems]").get(0);
				time = Long.parseLong(elm.attr("timems"));
				isAuction = true;
			}
			catch(IndexOutOfBoundsException exception){
				
			}
			
			itemsOnPage.add(new Item(id, title, price, time, isAuction));
		}
		
		return itemsOnPage;
	}
	
	public static void main(String[] args) throws IOException{
		EbayScraper scraper = new EbayScraper("http://www.ebay.com/sch/Antiquities-/37903/i.html?_mPrRngCbx=1&_udlo=0&_udhi=16&_pgn=11&_skc=2000&rt=nc");
		int i=0;
		for(Item item: scraper.getItemsListed()){
			System.out.println(++i);
			System.out.println(item.toString());
		}
	}
}
