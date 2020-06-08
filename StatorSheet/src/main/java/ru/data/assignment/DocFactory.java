package ru.data.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocFactory.class);
	
	public DocumentOfWnc getDocument(DocumentTypes type) {
		DocumentOfWnc document = null;
		switch (type) {
		case CALC_AND_WIND_NOTE:
			document = new CalcAndWindNote();
			break;
		case STO:
			document = new Sto();
			break;
		case MECH_CALC_RESULTS:
			document = new MechCalcResults();
			break;
		default:
			throw new IllegalArgumentException("Wrong document type:" + type);
		}
		LOG.info("Document is got");
		return document;
	}
}
