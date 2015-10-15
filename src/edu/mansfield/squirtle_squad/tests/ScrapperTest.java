package edu.mansfield.squirtle_squad.tests;
 
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.mansfield.squirtle_squad.model.Item;
import edu.mansfield.squirtle_squad.utilities.DataFormatter;

import java.io.IOException;
import java.util.ArrayList;
 
/**
 * Example program to list links from a URL.
 */
public class ScrapperTest {
    public static void main(String[] args) throws IOException {
       String url = "http://www.ebay.com/sch/Soda-/36/i.html?_ipg=200&rt=nc&LH_BIN=1";
        ArrayList<Item> Items = new ArrayList<Item>();
        Boolean auc = false;
        long time = 0;
        Document doc = Jsoup.connect(url).get();
  /* TODO, foreach instead of forloop because select returns an arraylist of elements.*/
    
    

       for(int i=0;i<=199;i++)
        {
    	double price = DataFormatter.doubleFromPriceString("" + doc.select("li.lvprice").get(i).text());
    	//System.out.println(i + " " + price);
        String title = "" + doc.select("a.vip").get(i).text();
        //System.out.println(i + " " + title);
        Element e = doc.select("li[listingid]").get(i);  
        long listingId = Long.parseLong(e.attr("listingid"));
        //System.out.println(i + " " + listingId);
        Elements elements = doc.select("span[timems]");
        if(elements.size() > 0){
           Element e2 = doc.select("span[timems]").get(i);
       	   time = DataFormatter.longTimeFromString(e2.attr("timems"));
       	   auc = true;
        }
        else{
        	time = 0;
        }
        //System.out.println(i + " " + time);
        Items.add(new Item(listingId,title,price,time,auc));
        
        }
       for(Item it: Items){
         System.out.println(it.toString());
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