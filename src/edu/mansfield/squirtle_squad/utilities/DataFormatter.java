package edu.mansfield.squirtle_squad.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFormatter {
	public static double doubleFromPriceString(String priceString) {
		try {
			priceString = priceString.replaceAll(",", "");
			Pattern p = Pattern.compile(".*\\$(\\d+\\.\\d+).*");
			Matcher matches = p.matcher(priceString);
			matches.find();
			priceString = matches.group(1);
			return Double.parseDouble(priceString);
		} catch (Exception e) {
			System.out.println("A price String of " + priceString + " Did it");
			e.printStackTrace();
		}
		return 0;
	}

	public static long longTimeFromString(String timeString) {
		try {
			return Long.parseLong(timeString);
		} catch (NumberFormatException e) {

		}
		return 0;
	}
}
