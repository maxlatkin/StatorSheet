package ru.data.assignment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	
	private static final Logger LOG = LoggerFactory.getLogger(Config.class);
	public static final String DB_NOTE_URL = "db.noteUrl";
	public static final String DB_STO_URL = "db.stoUrl";
	public static final String DB_MECH_CALC_RESULTS = "db.MCRUrl";
	public static final String DB_USERNAME = "db.username";
	public static final String DB_PASSWORD = "db.password";
	private static Properties properties = new Properties();
	
	private Config() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static String getProperty(String name) {
		if (properties.isEmpty()) {
			try (InputStream propertiesFile = Config.class.getResourceAsStream("/wnc.properties")) {
				properties.load(propertiesFile);
			} catch (IOException e) {
				LOG.error("Error loading wnc.properties", e);
			}
		}
		return properties.getProperty(name);
	}
}
