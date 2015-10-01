package edu.mansfield.squirtle_squad.scraper;


// Thought abstraction was appropriate, may be overkill.
public abstract class Scraper {

	// I think this is correct
	protected String webpage;
	
	public Scraper(String webpage) {
		this.webpage = webpage;
	}

}
