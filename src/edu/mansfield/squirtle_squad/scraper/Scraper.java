package edu.mansfield.squirtle_squad.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.mansfield.squirtle_squad.delegates.WebScannerDelegate;

// Thought abstraction was appropriate, may be overkill.
public abstract class Scraper {

	private final int TIME_OUT = 100;

	// I think this is correct
	protected Document doc;

	public Scraper(WebScannerDelegate source, String url) {
		boolean noConnection = true;
		int i = 1;

		while (noConnection) {
			try {
				doc = Jsoup.connect(url).get();
				noConnection = false;
			} catch (IOException connectionFailure) {
				if (i++ > TIME_OUT) {
					noConnection = false;
				}
			}
		}
	}

}
