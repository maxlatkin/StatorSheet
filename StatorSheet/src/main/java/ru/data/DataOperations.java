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
		ScrewShift.getInstance().calculate();
		SlotHghtToWdg.getInstance().calculate();
	}
}