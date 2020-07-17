package ru.ruselprom.data.assignment;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ruselprom.data.DataStore;
import ru.ruselprom.wnc.documents.DocTypes;
import ru.ruselprom.wnc.documents.DocVars;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CalcAndWindNoteVars extends DocVars {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalcAndWindNoteVars.class);
	
	public CalcAndWindNoteVars() {
		type = DocTypes.CALC_AND_WIND_NOTE;
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
		LOG.info("Data assigned to DataStore from CalcAndWindNote");
	}
}
