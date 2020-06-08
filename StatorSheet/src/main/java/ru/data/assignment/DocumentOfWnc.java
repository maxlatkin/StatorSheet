package ru.data.assignment;

import java.io.IOException;
import java.util.Base64;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DocumentOfWnc {
	private static final Logger LOG = LoggerFactory.getLogger(DocumentOfWnc.class);
	protected DocumentTypes type;
	
	public void assignVarsToDataStore(String number) {
		try {
			Elements rows = getTrElements();
			Elements cols = null;
			if (rows != null) {
				for (int i = 1; i < rows.size(); i++) {
					cols = rows.get(i).select("TD");
					if (number.equalsIgnoreCase(cols.get(0).text())) {
						setDataFromColsToDataStore(cols);
						break;
					}
				}
			}
			LOG.info("Data assigned");
		} catch (Exception e) {
			LOG.error("Error in assignVarsToDataStore()", e);
		}	
	}
	
	protected abstract void setDataFromColsToDataStore(Elements cols);
	
	protected Elements getTrElements() {
		try {
			Document document = getConnection().get();
			if (document != null) {
				Element table = document.select("TABLE").get(0);
				Elements elements = table.select("TR");
				if (LOG.isInfoEnabled()) {
					LOG.info("JSP elements received in {}", type.name());
				}
				return elements;
			}
		} catch (NullPointerException | IOException e) {
			LOG.error("Error in getTrElements()", e);
		}
		return null;
	}

	private Connection getConnection() {
		String url = Config.getProperty(getUrlOfDocument());
		String username = Config.getProperty(Config.DB_USERNAME);
		String password = Config.getProperty(Config.DB_PASSWORD);
		String login = username + ":" + password;
		String base64login = new String(Base64.getEncoder().encode(login.getBytes()));
		Connection connection = Jsoup.connect(url).header("Authorization", "Basic " + base64login);
		if (LOG.isInfoEnabled()) {
			LOG.info("JSP connection received in {}", type.name());
		}
		return connection;
	}
	private String getUrlOfDocument() {
		String url = null;
		switch (type) {
		case CALC_AND_WIND_NOTE:
			url = Config.DB_NOTE_URL;
			break;
		case STO:
			url = Config.DB_STO_URL;
			break;
		case MECH_CALC_RESULTS:
			url = Config.DB_MECH_CALC_RESULTS;
			break;
		default:
			throw new IllegalArgumentException("Wrong document type:" + type);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("URL of JSP received in {}", type.name());
		}
		return url;
	}
}
