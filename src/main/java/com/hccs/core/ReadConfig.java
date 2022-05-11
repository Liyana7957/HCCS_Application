package com.hccs.core;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties prop;
	
	
	public void loadPropertyFile(String path) throws Exception {
		FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir") + path);
		prop = new Properties();
		prop.load(fileInput);
	}
	
	public String returnDataFromFile(String path, String value) throws Exception {
		loadPropertyFile(path);
		return prop.getProperty(value);
	}
}
