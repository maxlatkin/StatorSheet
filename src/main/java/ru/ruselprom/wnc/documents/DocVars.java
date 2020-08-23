package ru.ruselprom.wnc.documents;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DocVars {
	private static final Logger LOG = LoggerFactory.getLogger(DocVars.class);
	protected DocTypes type;
	
	public void assignToDataStore(String number) {
		try {
			Elements rows = DocConnection.getInstance(type).getTrElems();
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
}
