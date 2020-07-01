package ru.assignment.sheet;

import java.util.HashMap;
import java.util.Map;

import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.DimAssignment;
import ru.data.DataStore;

public abstract class SheetDimAssignment extends DimAssignment {

	public SheetDimAssignment(Solid currSolid) {
		super(currSolid);
	}
	
	protected Map<Integer, Double> getTransformCoreToSheetIndexAndValue() {
		Map<Integer, Double> transformCoreToSheetIndexAndValue = new HashMap<>();
		transformCoreToSheetIndexAndValue.put(0, DataStore.getExtDiam() + 100);
		transformCoreToSheetIndexAndValue.put(1, DataStore.getSegmPruning());
		transformCoreToSheetIndexAndValue.put(2, 360.0 / DataStore.getSegmQty());
		return transformCoreToSheetIndexAndValue;
	}
	protected double getHalfSegmAngle() {
		return 360.0 / DataStore.getSegmQty() / 2;
	}
}
