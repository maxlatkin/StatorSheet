package ru.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	
	private static final Logger LOG = LoggerFactory.getLogger(Config.class);
	public static final String DB_URL = "db.url";
	public static final String DB_USERNAME = "db.username";
	public static final String DB_PASSWORD = "db.password";
	private static Properties properties = new Properties();
	
	private Config() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static String getProperty(String name) {
		if (properties.isEmpty()) {
			try (InputStream is = Config.class.getResourceAsStream("/wnc.properties")) {
				properties.load(is);
			} catch (IOException e) {
				LOG.error("Error loading wnc .properties", e);
			}
		}
		return properties.getProperty(name);
	}
}
