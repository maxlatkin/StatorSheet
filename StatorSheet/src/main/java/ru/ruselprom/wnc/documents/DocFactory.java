package ru.ruselprom.wnc.documents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ruselprom.data.assignment.CalcAndWindNoteVars;
import ru.ruselprom.data.assignment.MechCalcResultsVars;
import ru.ruselprom.data.assignment.StoVars;

public class DocFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocFactory.class);
	
	public DocVars getDocument(DocTypes type) {
		DocVars document = null;
		switch (type) {
		case CALC_AND_WIND_NOTE:
			document = new CalcAndWindNoteVars();
			break;
		case STO:
			document = new StoVars();
			break;
		case MECH_CALC_RESULTS:
			document = new MechCalcResultsVars();
			break;
		default:
			throw new IllegalArgumentException("Wrong document type:" + type);
		}
		LOG.info("{} document is got", type);
		return document;
	}
}
