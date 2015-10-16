package edu.mansfield.squirtle_squad.delegates;

import edu.mansfield.squirtle_squad.scraper.Scraper;

public interface WebScannerDelegate {
	public void AlertConnectionTimeOut(Scraper source);
}
