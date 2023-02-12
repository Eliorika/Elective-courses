package ru.rsreu.electivecourses.propertiesmanagers;

import java.util.ResourceBundle;

public class DBConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("dbconfig");

	private DBConfigurationManager() {}
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
