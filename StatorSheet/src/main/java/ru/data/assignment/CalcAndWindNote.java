package ru.data.assignment;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.data.DataStore;

public class CalcAndWindNote extends DocumentOfWnc {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalcAndWindNote.class);
	
	public CalcAndWindNote() {
		type = DocumentTypes.CALC_AND_WIND_NOTE;
	}

	@Override
	protected void setDataFromColsToDataStore(Elements cols) {
		DataStore.setExtDiam(Double.parseDouble(cols.get(2).text()));
		DataStore.setIntDiam(Double.parseDouble(cols.get(3).text()));
		DataStore.setTotalSlotHght(Double.parseDouble(cols.get(4).text()));
		DataStore.setSlotWdth(Double.parseDouble(cols.get(5).text()));
		DataStore.setSlotQty(Integer.parseInt(cols.get(6).text()));
		DataStore.setSlotStep(Integer.parseInt(cols.get(7).text()));
		DataStore.setSegmRolling(cols.get(8).text());
		DataStore.setSegmQty(Integer.parseInt(cols.get(9).text()));
		DataStore.setPoleQty(Integer.parseInt(cols.get(10).text()));
		LOG.info("Data assigned in CalcAndWindNote");
	}
}
