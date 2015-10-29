package edu.mansfield.squirtle_squad.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import edu.mansfield.squirtle_squad.database.DatabaseInteractions;

public class FileHandler {

	static final String DIRECTORY = "/EbayScraper";
	static final String SCRAPER_FILE = "/Scraper.db";
	static final String TIMESTAMP_FILE = "/TimeStamp";
	
	private static String homeDir = System.getProperty("user.home");
	
	public static String getTimeStampFilePath(){
		return homeDir + DIRECTORY + TIMESTAMP_FILE;
	}

	public static String getScraperFilePath(){
		return homeDir + DIRECTORY + SCRAPER_FILE;
	}
	
	public static void setUpAppDirectory(){
		File dir = new File(homeDir + DIRECTORY);
		if(!dir.exists()){
			while(!dir.mkdir());
		}
		
		File timestamp = new File(dir.getAbsolutePath() + TIMESTAMP_FILE);
		try {
			timestamp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File scraperDB = new File(dir.getAbsolutePath() + SCRAPER_FILE);
		try {
			if(scraperDB.createNewFile()){
				DatabaseInteractions db = new DatabaseInteractions();
				Connection conn = db.dbConnect();
				db.makeTable(conn);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
