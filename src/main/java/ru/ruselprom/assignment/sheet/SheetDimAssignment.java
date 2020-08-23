package ru.ruselprom.assignment.sheet;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.assignment.DimAssignment;
import ru.ruselprom.data.DataStore;
import ru.ruselprom.general.ModelFeat;

public abstract class SheetDimAssignment extends DimAssignment {
	
	private static final Logger LOG = LoggerFactory.getLogger(SheetDimAssignment.class);

	public SheetDimAssignment(Solid currSolid) {
		super(currSolid);
	}
	
	protected void setSlotShiftDimValue(ModelFeat modelFeat, int index) {
		try {
			if (DataStore.getSegmQty() != 1) {
				setDimValue(modelFeat, index, getHalfSegmAngle());
				setArrayOfDimValue(ModelFeat.TRANSFORM_CORE_TO_SHEET, getTransformCoreToSheetIndexAndValue());
			} else {
				setDimValue(modelFeat, index, 0);
			}
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error setting slotShift", e);
		}
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
