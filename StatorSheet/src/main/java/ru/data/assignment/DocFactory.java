package ru.data.assignment;

public class DocFactory {
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
		return document;
	}
}
