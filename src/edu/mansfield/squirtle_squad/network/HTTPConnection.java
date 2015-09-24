package edu.mansfield.squirtle_squad.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTTPConnection {

	/*
	 *	Right now this constructor is useless we may want to add functionality to
	 *  our networking later, but for now all we need is a static method. 
	 *
	 */
	HTTPConnection(){
		
	}
	
	/*
	 *  Decided it would be better to throw the exception then deal with it here,
	 *  because depending on the context of how or when we are using it we may 
	 *  want to handle an exception differently.
	 *  
	 */
	public static String htmlFromUrl(String url) throws IOException {
		
		URLConnection htmlStream = new URL(url).openConnection();
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(htmlStream.getInputStream()));
		
		StringBuilder webpage = new StringBuilder("");
		
		String temporaryVariableThatWouldBePerfectForAnEmojii;
		
		while((temporaryVariableThatWouldBePerfectForAnEmojii = inputStream.readLine()) != null){
			webpage.append(temporaryVariableThatWouldBePerfectForAnEmojii);
		}
		
		return webpage.toString();
	}
	
	public static String htmlWithNewlinesFromUrl(String url) throws IOException {
		
		URLConnection htmlStream = new URL(url).openConnection();
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(htmlStream.getInputStream()));
		
		StringBuilder webpage = new StringBuilder("");
		
		String temporaryVariableThatWouldBePerfectForAnEmojii;
		
		while((temporaryVariableThatWouldBePerfectForAnEmojii = inputStream.readLine()) != null){
			webpage.append(temporaryVariableThatWouldBePerfectForAnEmojii + "\n");
		}
		
		return webpage.toString();
	}
}
