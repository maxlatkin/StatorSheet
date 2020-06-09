package ru.data.assignment;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import ru.data.DataStore;
import ru.wnc.documents.DocumentOfWnc;
import ru.wnc.documents.DocumentTypes;

public class CalcAndWindNote extends DocumentOfWnc {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalcAndWindNote.class);
	
	public CalcAndWindNote() {
		type = DocumentTypes.CALC_AND_WIND_NOTE;
	}

	@Override
	protected void setDataFromColsToDataStore(Elements cols) {
		DataStore.setExtDiam(parseDouble(cols.get(2).text()));
		DataStore.setIntDiam(parseDouble(cols.get(3).text()));
		DataStore.setTotalSlotHght(parseDouble(cols.get(4).text()));
		DataStore.setSlotWdth(parseDouble(cols.get(5).text()));
		DataStore.setSlotQty(parseInt(cols.get(6).text()));
		DataStore.setSlotStep(parseInt(cols.get(7).text()));
		DataStore.setSegmRolling(cols.get(8).text());
		DataStore.setSegmQty(parseInt(cols.get(9).text()));
		DataStore.setPoleQty(parseInt(cols.get(10).text()));
		LOG.info("Data assigned in CalcAndWindNote");
	}
}
