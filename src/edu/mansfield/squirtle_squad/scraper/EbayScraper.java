package edu.mansfield.squirtle_squad.scraper;
 
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 


import java.io.IOException;
import java.util.ArrayList;
 
/**
 * Example program to list links from a URL.
 */
public class EbayScraper {
    public static void main(String[] args) throws IOException {
       String url = "http://www.ebay.com/sch/allcategories/all-categories";
        ArrayList<String> cat = new ArrayList<String>();
 
        Document doc = Jsoup.connect(url).get();
  /* TODO, foreach instead of forloop because select returns an arraylist of elements.
    
    
   */
       /* for(int i=0;i<=199;i++)
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
        
        
        for(Element e: doc.select("a.ch[href]"))
        {
        //Element e = doc.select("a.ch[href]").get(0);
        String url1 = e.attr("href");
        cat.add(url1);
        
        }
 
    }
}