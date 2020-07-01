package ru.data;

import ru.data.calculation.ScrewShift;
import ru.data.calculation.SlotHghtToWdg;
import ru.data.calculation.SlotWedgeWdth;
import ru.data.check.ExtAndIntDiams;
import ru.data.check.ScrewQty;
import ru.data.check.SlotAndSegmQtyWithScrewType;
import ru.data.check.SlotStepAndQty;
import ru.wnc.documents.DocFactory;
import ru.wnc.documents.DocTypes;
import ru.wnc.documents.Documents;

public class DataOperations {

	private DataOperations() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void assignVarsToDataStore() {
		DocFactory docFactory = new DocFactory();
		docFactory.getDocument(DocTypes.CALC_AND_WIND_NOTE).assignToDataStore(Documents.getNoteName());
		docFactory.getDocument(DocTypes.STO).assignToDataStore(Documents.getStoName());
		docFactory.getDocument(DocTypes.MECH_CALC_RESULTS).assignToDataStore(Documents.getResultsName());
	}
	
	public static void checkVars() {
		new ExtAndIntDiams().check();
		new SlotStepAndQty().check();
		new SlotAndSegmQtyWithScrewType().check();
		new ScrewQty().check();
	}

	public static void calculateVars() {
		DataStore.setSlotHghtToWdg(new SlotHghtToWdg().calculate());
		DataStore.setSlotWedgeWdth(new SlotWedgeWdth().calculate());
		int typeOfScrew = DataStore.getTypeOfScrew();
		if (typeOfScrew / 10 == 0) {
			DataStore.setScrewShift(ScrewShift.getInstance(typeOfScrew).calculate());
		} else {
			int firstTypeOfScrew = typeOfScrew / 10;
			int secondTypeOfScrew = typeOfScrew % 10;
			DataStore.setScrewShift(ScrewShift.getInstance(firstTypeOfScrew).calculate());
			DataStore.setSecondScrewShift(ScrewShift.getInstance(secondTypeOfScrew).calculate());
		}
	}
}