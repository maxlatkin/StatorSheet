package ru.data;

import java.io.IOException;
import java.util.Base64;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentsList {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocumentsList.class);
	
	private DocumentsList() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void assignDataToDataStoreByDoc(String number) {
		try {
			Elements rows = getTrElements();
			Elements cols = null;
			if (rows != null) {
				for (int i = 1; i < rows.size(); i++) {
					cols = rows.get(i).select("TD");
					if (number.equalsIgnoreCase(cols.get(0).text())) {
						DataStore.setExtDiam(Double.parseDouble(cols.get(2).text()));
						DataStore.setIntDiam(Double.parseDouble(cols.get(3).text()));
						DataStore.setTotalSlotHght(Double.parseDouble(cols.get(4).text()));
						DataStore.setSlotWdth(Double.parseDouble(cols.get(5).text()));
						DataStore.setSlotQty(Integer.parseInt(cols.get(6).text()));
						DataStore.setSlotStep(Integer.parseInt(cols.get(7).text()));
						DataStore.setSegmRolling(cols.get(8).text());
					}
				}
			}
		} catch (NullPointerException e) {
			LOG.error("Error in assignDocumentDataTo()", e);
		}
	}
	
	private static Elements getTrElements() {
		try {
			Document document = getDocument();
			if (document != null) {
				Element table = document.select("TABLE").get(0);
				return table.select("TR");
			}
		} catch (NullPointerException e) {
			LOG.error("Error in getTrElements()", e);
		}
		return null;
	}

	private static Document getDocument() {
		try {
			return getConnection().get();
		} catch (NullPointerException | IOException e) {
			LOG.error("Error receiving document", e);
		}
		return null;
	}

	private static Connection getConnection() {
		String url = Config.getProperty(Config.DB_URL);
		String username = Config.getProperty(Config.DB_USERNAME);
		String password = Config.getProperty(Config.DB_PASSWORD);
		String login = username + ":" + password;
		String base64login = new String(Base64.getEncoder().encode(login.getBytes()));
		return Jsoup.connect(url).header("Authorization", "Basic " + base64login);
	}

}
