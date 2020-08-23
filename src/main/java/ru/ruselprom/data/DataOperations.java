package ru.ruselprom.data;

import ru.ruselprom.data.calculation.ScrewShift;
import ru.ruselprom.data.calculation.SlotHghtToWdg;
import ru.ruselprom.data.calculation.SlotWedgeWdth;
import ru.ruselprom.data.check.ExtAndIntDiams;
import ru.ruselprom.data.check.ScrewQty;
import ru.ruselprom.data.check.SlotAndSegmQtyWithScrewType;
import ru.ruselprom.data.check.SlotStepAndQty;
import ru.ruselprom.general.SheetType;
import ru.ruselprom.wnc.documents.DocFactory;
import ru.ruselprom.wnc.documents.DocTypes;
import ru.ruselprom.wnc.documents.Documents;

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

	public static void calculateCommonVars() {
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
	
	public static void calculateDifferentVars(SheetType sheetType) {
		if (sheetType == SheetType.VENT) {
			DataStore.setIntDiam(DataStore.getIntDiam() + 4);
			DataStore.setSheetThck(1);
			double roundedSlotWedgeWdth = Math.ceil(DataStore.getSlotWedgeWdth() * 10.0) / 10.0;
			DataStore.setSlotWdth(roundedSlotWedgeWdth + 1.5);
		}
	}
}