package edu.mansfield.squirtle_squad.scraper;

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
<<<<<<< HEAD

public class EbayScraper extends Scraper{
	
	// String webpage is a variable of Scraper
	
	public EbayScraper(String webpage){
		super(webpage);
	}
	
	public int getItemCount(){
		
		/*
		 *  TODO Return the number of items listed above the item listings
		 *  
		 *  This will be used to know whether the URL needs to be updated to
		 *  narrow the search.
		 *  
		 */
		webpage = "Blah";
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
=======
public class EbayScraper {
    public static void main(String[] args) throws IOException {
       String url = "http://www.ebay.com/sch/Soda-/36/i.html?LH_Auction=1&_ipg=200&rt=nc";
        ArrayList<String> cat = new ArrayList<String>();
 
        Document doc = Jsoup.connect(url).get();
  /* TODO, foreach instead of forloop because select returns an arraylist of elements.
    
    
   */
        /*
       for(int i=0;i<=199;i++)
        {
    	String price = "" + doc.select("li.lvprice").get(i).text();
    	
    	System.out.println(i + " " + price);
        }
        
        for(int i=0;i<=199;i++)
        {
        String title = "" + doc.select("a.vip").get(i).text();
        System.out.println(i + " " + title);
        }
        
        for(int i=0;i<=199;i++)
        {
        Element e = doc.select("li[listingid]").get(i);  
        String listingId = e.attr("listingid");
        System.out.println(i + " " + listingId);
        }
        */
        for(int i=0;i<=199;i++)
        {
        Element e = doc.select("span[timems]").get(i);  
        long time = (Long.parseLong(e.attr("timems"))/1000);
        System.out.println(i + " " + time);
        }
     
        
        /*     
        for(Element e: doc.select("a.ch[href]"))
        {
        //Element e = doc.select("a.ch[href]").get(0);
        String url1 = e.attr("href");
        cat.add(url1);
        }
        */
    }
}
>>>>>>> origin/master
