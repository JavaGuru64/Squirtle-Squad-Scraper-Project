package edu.mansfield.squirtle_squad.scraper;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ebay_Cat {
	
	// -TODO add a timeout to this thing so we don't fail over a dropped packet
	public static ArrayList<String> getAllCategories() throws IOException {
		Document doc = Jsoup.connect(
				"http://www.ebay.com/sch/allcategories/all-categories").get();
		ArrayList<String> cat = new ArrayList<String>();
		for (Element e : doc.select("a.ch[href]")) {
			String url1 = e.attr("href");
			if(!url1.endsWith("/i.html")){
				url1 = url1 + "/i.html";
			}
			
			cat.add(url1);

		}
		return cat;

	}
	
	public static void main(String[] args){
		try {
			for(String s: Ebay_Cat.getAllCategories()){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
