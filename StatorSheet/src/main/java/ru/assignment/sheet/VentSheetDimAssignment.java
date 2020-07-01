package ru.assignment.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class VentSheetDimAssignment extends SheetDimAssignment {
	
	private static final Logger LOG = LoggerFactory.getLogger(VentSheetDimAssignment.class);

	public VentSheetDimAssignment(Solid currSolid) {
		super(currSolid);
	}

	@Override
	public void assign() {
		try {
			setDimValue(ModelFeat.SHEET, 0, DataStore.getExtDiam());
			setDimValue(ModelFeat.SHEET, 1, DataStore.getIntDiam() + 4);
			setDimValue(ModelFeat.VENT_SLOT, 0, DataStore.getSlotWedgeWdth() + 1.5);
			setDimValue(ModelFeat.VENT_SLOT, 1, DataStore.getTotalSlotHght());
			setSlotShiftDimValue(ModelFeat.VENT_SLOT, 2);
			setDimValue(ModelFeat.MARK, 0, DataStore.getMarkRound());
			setDimValue(ModelFeat.MARK, 1, DataStore.getMarkRadius());
			LOG.info("Dimensions for the VentSheet assigned");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Failed to assing dimensions!", e);
		}
	}
}
