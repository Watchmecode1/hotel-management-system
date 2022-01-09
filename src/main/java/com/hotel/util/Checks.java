package com.hotel.util;

public class Checks {

	public static boolean isDigit(String string) {
		return !string.matches("[^0-9.]++");
	}
	
	/**
	 * a method to check if a string can be parsed as double
	 * @param string is the string to check
	 * @return true if the string can be parsed as double
	 */
	public static boolean isDouble(String string)
	{
		for (int i = 0; i < string.length(); i++) if (!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.') return false;

		return true;
	}
}