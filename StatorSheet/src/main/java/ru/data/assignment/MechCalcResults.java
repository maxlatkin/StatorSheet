package ru.data.assignment;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MechCalcResults extends DocumentOfWnc {
	
	private static final Logger LOG = LoggerFactory.getLogger(MechCalcResults.class);
	
	public MechCalcResults() {
		type = DocumentTypes.MECH_CALC_RESULTS;
	}

	@Override
	protected void setDataFromColsToDataStore(Elements cols) {
		LOG.info("Data assigned in MechCalcResults");
	}
}
