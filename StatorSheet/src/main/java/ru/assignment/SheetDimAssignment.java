package ru.assignment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class SheetDimAssignment extends DimAssignment {
	
	private static final Logger LOG = LoggerFactory.getLogger(SheetDimAssignment.class);

	public SheetDimAssignment(Solid currSolid) {
		super(currSolid);
	}
	
	@Override
	public void assign() {
		try {
			setDimValue(ModelFeat.SHEET, 0, DataStore.getExtDiam());
			setDimValue(ModelFeat.SHEET, 1, DataStore.getIntDiam());
			
			setArrayOfDimValue(ModelFeat.SLOT, getSlotWithoutRoundIndexAndValue());
			if (DataStore.getSegmQty() != 1) {
				setDimValue(ModelFeat.SLOT, 8, getHalfSegmAngle());
				setArrayOfDimValue(ModelFeat.TRANSFORM_CORE_TO_SHEET, getTransformCoreToSheetIndexAndValue());
			} else {
				setDimValue(ModelFeat.SLOT, 8, 0);
			}
			
			setDimValue(ModelFeat.MARK, 0, DataStore.getMarkRound());
			setDimValue(ModelFeat.MARK, 1, DataStore.getMarkRadius());
			LOG.info("Dimensions for the Sheet assigned");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Failed to assing dimensions!", e);
		}
	}
	
	private Map<Integer, Double> getTransformCoreToSheetIndexAndValue() {
		Map<Integer, Double> transformCoreToSheetIndexAndValue = new HashMap<>();
		transformCoreToSheetIndexAndValue.put(0, DataStore.getExtDiam() + 100);
		transformCoreToSheetIndexAndValue.put(1, DataStore.getSegmPruning());
		transformCoreToSheetIndexAndValue.put(2, 360.0 / DataStore.getSegmQty());
		return transformCoreToSheetIndexAndValue;
	}

	private Map<Integer, Double> getSlotWithoutRoundIndexAndValue() {
		Map<Integer, Double> slotWithoutRoundIndexAndValue = new HashMap<>();
		slotWithoutRoundIndexAndValue.put(0, DataStore.getSlotHghtToWdg());
		slotWithoutRoundIndexAndValue.put(1, DataStore.getWedgeThck());
		slotWithoutRoundIndexAndValue.put(2, DataStore.getWedgeGap());
		slotWithoutRoundIndexAndValue.put(3, DataStore.getWedgeAngleTop());
		slotWithoutRoundIndexAndValue.put(4, DataStore.getWedgeAngleBottom());
		slotWithoutRoundIndexAndValue.put(5, DataStore.getSlotWdth());
		slotWithoutRoundIndexAndValue.put(6, DataStore.getRoundWedgeOfSlot());
		return slotWithoutRoundIndexAndValue;
	}
	
	private double getHalfSegmAngle() {
		return 360.0 / DataStore.getSegmQty() / 2;
	}
}
