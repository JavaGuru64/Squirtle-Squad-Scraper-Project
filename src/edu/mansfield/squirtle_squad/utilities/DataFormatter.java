package edu.mansfield.squirtle_squad.utilities;

public class DataFormatter {
	public static double doubleFromPriceString(String priceString){
		try{
			return Double.parseDouble(priceString.substring(1));
		}
		catch(NumberFormatException e){
			
		}
		return 0;
	}
}
