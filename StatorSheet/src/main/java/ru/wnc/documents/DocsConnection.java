package ru.wnc.documents;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocsConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocsConnection.class);
	private DocumentTypes type;
	private Elements noteElements;
	private Elements stoElements;
	private Elements resultsElements;
	private static DocsConnection instance;
    
	private DocsConnection() {}
	
	public static DocsConnection getInstance(DocumentTypes type) {
		if (instance == null) {
			instance = new DocsConnection();
		}
		instance.type = type;
		return instance;
	}
	
	public Elements getTrElems() {
		return getElemsByType();
	}

	public void setTrElemsByNumberFilter(String numberFilter) {
		try {
			Document document = getConnection(numberFilter).get();
//			Document document = getDoc();
			if (document != null) {
				Element table = document.select("TABLE").get(0);
				setElemsByType(table.select("TR"));
				LOG.info("JSP elements received in {}", type);
			}
		} catch (NullPointerException | IOException e) {
			LOG.error("Error in getTrElements()", e);
		}
	}
	private Elements getElemsByType() {
		switch (type) {
		case CALC_AND_WIND_NOTE:
			return noteElements;
		case STO:
			return stoElements;
		case MECH_CALC_RESULTS:
			return resultsElements;
		default:
			return null;
		}
	}
	private void setElemsByType(Elements elements) {
		switch (type) {
		case CALC_AND_WIND_NOTE:
			noteElements = elements;
			break;
		case STO:
			stoElements = elements;
			break;
		case MECH_CALC_RESULTS:
			resultsElements = elements;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	private Document getDoc() throws IOException {
		File file = new File("D:\\Program Data\\JSP\\" + Config.getProperty(getKeyOfProperty()));
		return Jsoup.parse(file, "utf-8");
	}
	
	private Connection getConnection(String number) {
		String url = Config.getProperty(getKeyOfProperty()) + number;
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
	private String getKeyOfProperty() {
		String key = null;
		switch (type) {
		case CALC_AND_WIND_NOTE:
			key = Config.DB_NOTE_URL;
			break;
		case STO:
			key = Config.DB_STO_URL;
			break;
		case MECH_CALC_RESULTS:
			key = Config.DB_MECH_CALC_RESULTS_URL;
			break;
		default:
			throw new IllegalArgumentException("Wrong document type:" + type);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("URL of JSP received in {}", type.name());
		}
		return key;
	}
}
