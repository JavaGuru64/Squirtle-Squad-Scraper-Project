package edu.mansfield.squirtle_squad.scraper;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Example program to list links from a URL.
 */
public class Ebay_Scraper {
    public static void main(String[] args) throws IOException {
        String url = "http://www.ebay.com/sch/i.html?_from=R40&_sacat=0&_ipg=200&_nkw=all+items&rt=nc&LH_BIN=1";

        Document doc = Jsoup.connect(url).get();

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

    }
}