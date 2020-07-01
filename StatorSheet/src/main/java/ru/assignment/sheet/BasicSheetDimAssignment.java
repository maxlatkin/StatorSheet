package ru.assignment.sheet;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class BasicSheetDimAssignment extends SheetDimAssignment {
	
	private static final Logger LOG = LoggerFactory.getLogger(BasicSheetDimAssignment.class);

	public BasicSheetDimAssignment(Solid currSolid) {
		super(currSolid);
	}
	
	@Override
	public void assign() {
		try {
			setDimValue(ModelFeat.SHEET, 0, DataStore.getExtDiam());
			setDimValue(ModelFeat.SHEET, 1, DataStore.getIntDiam());
			setArrayOfDimValue(ModelFeat.SLOT, getSlotIndexAndValue());
			setSlotShiftDimValue(ModelFeat.SLOT, 8);
			setDimValue(ModelFeat.MARK, 0, DataStore.getMarkRound());
			setDimValue(ModelFeat.MARK, 1, DataStore.getMarkRadius());
			LOG.info("Dimensions for the BasicSheet assigned");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Failed to assing dimensions!", e);
		}
	}
	
	private Map<Integer, Double> getSlotIndexAndValue() {
		Map<Integer, Double> slotIndexAndValue = new HashMap<>();
		slotIndexAndValue.put(0, DataStore.getSlotHghtToWdg());
		slotIndexAndValue.put(1, DataStore.getWedgeThck());
		slotIndexAndValue.put(2, DataStore.getWedgeGap());
		slotIndexAndValue.put(3, DataStore.getWedgeAngleTop());
		slotIndexAndValue.put(4, DataStore.getWedgeAngleBottom());
		slotIndexAndValue.put(5, DataStore.getSlotWdth());
		slotIndexAndValue.put(6, DataStore.getRoundWedgeOfSlot());
		return slotIndexAndValue;
	}
}
