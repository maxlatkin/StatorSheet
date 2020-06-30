package ru.data;

import ru.data.calculation.ScrewShift;
import ru.data.calculation.SlotHghtToWdg;
import ru.data.check.ExtAndIntDiams;
import ru.data.check.ScrewQty;
import ru.data.check.SlotAndSegmQtyWithScrewType;
import ru.data.check.SlotStepAndQty;

public class DataOperations {

	private DataOperations() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void checkVars() {
		new ExtAndIntDiams().check();
		new SlotStepAndQty().check();
		new SlotAndSegmQtyWithScrewType().check();
		new ScrewQty().check();
	}

	public static void calculateVars() {
		DataStore.setSlotHghtToWdg(SlotHghtToWdg.getInstance().calculate());
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