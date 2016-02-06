package com.wikipediaTest.AutomationFramework.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
 * Utility class to access the data from the file
 */
public class TestUtil {

	public static Properties loadProperties(String propertyFilePath) {

		Properties prop = new Properties();
		InputStream fileInput = null;

		try {
			fileInput = new FileInputStream(propertyFilePath);
			prop.load(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

		finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}

		}

		return prop;

	}

}
