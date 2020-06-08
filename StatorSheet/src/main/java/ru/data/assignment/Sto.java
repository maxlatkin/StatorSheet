package ru.data.assignment;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.Double.parseDouble;
import ru.data.DataStore;

public class Sto extends DocumentOfWnc {
	
	private static final Logger LOG = LoggerFactory.getLogger(Sto.class);
	
	public Sto() {
		type = DocumentTypes.STO;
	}

	@Override
	protected void setDataFromColsToDataStore(Elements cols) {
		DataStore.setSheetThck(parseDouble(cols.get(2).text()));
		DataStore.setSegmPruning(parseDouble(cols.get(3).text()));
		DataStore.setWedgeThck(parseDouble(cols.get(4).text()));
//		DataStore.setWedgeGap(wedgeGap);
		LOG.info("Data assigned in Sto");
	}
}
