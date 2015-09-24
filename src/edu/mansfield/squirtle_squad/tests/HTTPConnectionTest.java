package edu.mansfield.squirtle_squad.tests;

import edu.mansfield.squirtle_squad.network.HTTPConnection;

import java.io.IOException;

public class HTTPConnectionTest {

	public static void main(String[] args) {
		
		// URL for the first 200 items on the all items search.
		String url = "http://www.ebay.com/sch/i.html?_nkw=all+items&_pgn=1&_ipg=200&_skc=0&rt=nc";
		
		try{
			String webpage = HTTPConnection.htmlFromUrl(url);
			System.out.println(webpage);
		}
		catch (IOException e){
			System.out.println("Oops, something went wrong... Well wronger than usual");
		}

	}

}
