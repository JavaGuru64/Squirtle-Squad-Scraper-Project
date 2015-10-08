package edu.mansfield.squirtle_squad.scraper;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ebay_Cat {
	public static ArrayList<String> getAllCategories() throws IOException {
		Document doc = Jsoup.connect(
				"http://www.ebay.com/sch/allcategories/all-categories").get();
		ArrayList<String> cat = new ArrayList<String>();
		for (Element e : doc.select("a.ch[href]")) {
			String url1 = e.attr("href");
			cat.add(url1);

		}
		return cat;

	}

}
