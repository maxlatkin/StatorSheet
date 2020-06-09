package ru.data.assignment;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.data.DataStore;
import ru.wnc.documents.DocVars;
import ru.wnc.documents.DocumentTypes;

public class MechCalcResultsVars extends DocVars {
	
	private static final Logger LOG = LoggerFactory.getLogger(MechCalcResultsVars.class);
	
	public MechCalcResultsVars() {
		type = DocumentTypes.MECH_CALC_RESULTS;
	}

	@Override
	protected void setDataFromColsToDataStore(Elements cols) {
		DataStore.setTotalScrewQty(parseInt(cols.get(2).text()));
		DataStore.setStudDiam(parseDouble(cols.get(3).text()));
		LOG.info("Data assigned in MechCalcResults");
	}
}
