package ru.assignment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
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
			
			if (DataStore.isSlotWithRound()) {
				setArrayOfDimValue(ModelFeat.SLOT_WITH_ROUND, getSlotWithRoundIndexAndValue());
				if (DataStore.getSegmQty() != 1) {
					setDimValue(ModelFeat.SLOT_WITH_ROUND, 10, getHalfSegmAngle());
				} else {
					setDimValue(ModelFeat.SLOT_WITH_ROUND, 10, 0);
				}
			} else {
				setArrayOfDimValue(ModelFeat.SLOT_WITHOUT_ROUND, getSlotWithoutRoundIndexAndValue());
				if (DataStore.getSegmQty() != 1) {
					setDimValue(ModelFeat.SLOT_WITHOUT_ROUND, 8, getHalfSegmAngle());
				} else {
					setDimValue(ModelFeat.SLOT_WITHOUT_ROUND, 8, 0);
				}
			}

			if (DataStore.getSegmQty() != 1) {
				setArrayOfDimValue(ModelFeat.TRANSFORM_CORE_TO_SHEET, getTransformCoreToSheetIndexAndValue());
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

	private Map<Integer, Double> getSlotWithRoundIndexAndValue() {
		Map<Integer, Double> slotWithRoundIndexAndValue = new HashMap<>();
		setCommonSlotIndexAndValue(slotWithRoundIndexAndValue);
		slotWithRoundIndexAndValue.put(7, DataStore.getSlotRoundBottom());
		slotWithRoundIndexAndValue.put(8, DataStore.getSlotWdth());
		slotWithRoundIndexAndValue.put(9, DataStore.getSlotRoundTop());
		return slotWithRoundIndexAndValue;
	}
	
	private Map<Integer, Double> getSlotWithoutRoundIndexAndValue() {
		Map<Integer, Double> slotWithoutRoundIndexAndValue = new HashMap<>();
		setCommonSlotIndexAndValue(slotWithoutRoundIndexAndValue);
		slotWithoutRoundIndexAndValue.put(5, DataStore.getSlotWdth());
		return slotWithoutRoundIndexAndValue;
	}
	
	private Map<Integer, Double> setCommonSlotIndexAndValue(Map<Integer, Double> commonSlotIndexAndValue) {
		commonSlotIndexAndValue.put(0, DataStore.getSlotHghtToWdg());
		commonSlotIndexAndValue.put(1, DataStore.getWedgeThck());
		commonSlotIndexAndValue.put(2, DataStore.getWedgeGap());
		commonSlotIndexAndValue.put(3, DataStore.getWedgeAngleTop());
		commonSlotIndexAndValue.put(4, DataStore.getWedgeAngleBottom());
		return commonSlotIndexAndValue;
	}

	private double getHalfSegmAngle() {
		return 360.0 / DataStore.getSegmQty() / 2;
	}
}
