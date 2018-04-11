package com.java.resource.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	public static String getPropertyValue(String key) {

		Properties prop = new Properties();
		InputStream input = null;
		String value="";

		try {

			input = new FileInputStream("C://Users//aashishprasad//Workspaces//MyEclipse 8.6//DigiLocker//src//com//java//resource//property//config.properties");

			// load a properties file
			prop.load(input);
			value=prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;

	}
}
