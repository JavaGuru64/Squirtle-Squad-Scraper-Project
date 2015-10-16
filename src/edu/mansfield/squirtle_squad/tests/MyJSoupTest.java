package edu.mansfield.squirtle_squad.tests;

import java.io.IOException;
//import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import edu.mansfield.squirtle_squad.model.Item;

public class MyJSoupTest {
	public static void main(String[] args) throws IOException {
		String url = "http://www.ebay.com/sch/Soda-/36/i.html?&_ipg=200&rt=nc";
		//ArrayList<Item> Items = new ArrayList<Item>();

		Document doc = Jsoup.connect(url).get();
		  /* TODO, foreach instead of forloop because select returns an arraylist of elements.*/
		    
		    
		Integer.parseInt(doc.select("span.listingscnt").text().replaceAll("\\D", ""));
		
//		Element e = doc.select("ul#ListViewInner").get(0);
//		Elements es = e.select("li[listingid]");
//		int i=0;
//		for(Element el: es){
//			System.out.println(++i);
//			System.out.println(el.attr("listingid"));
//			System.out.println(el.select("a.vip").get(0).text());
//			System.out.println(el.select("li.lvprice").get(0).text());
//			try{
//				System.out.println(el.select("span.tme").get(0).select("span[timems]").get(0).attr("timems"));
//				System.out.println("Is Auction");
//			}
//			catch(IndexOutOfBoundsException exception){
//				System.out.println("Is Not Auction");
//			}
//			System.out.println();
//		}
//		
		
//		for(Element e: es){
//			System.out.println(e.select("li.listingid").text());
//		}
	}

}
