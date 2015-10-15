package edu.mansfield.squirtle_squad.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


// Thought abstraction was appropriate, may be overkill.
public abstract class Scraper {

	// I think this is correct
	protected Document doc;
	
	public Scraper(String url) throws IOException {
		boolean bubbles = true;
		while(bubbles){
			try {
				doc = Jsoup.connect(url).get();
				bubbles = false;
			}
			catch(Exception e){
				
			}
		}
	}

}
