package ru.rsreu.electivecourses.propertiesmanagers;

import java.util.ResourceBundle;

public class QueryManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("query");

	private QueryManager() {}
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
