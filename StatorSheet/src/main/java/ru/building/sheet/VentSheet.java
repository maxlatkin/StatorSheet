package ru.building.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.patterns.RotatPattern360;

public class VentSheet extends Sheet {
	
	private static final Logger LOG = LoggerFactory.getLogger(VentSheet.class);

	@Override
	public void build(Solid currSolid) {
		try {
			buildSheetBody(currSolid);
			buildSlot(currSolid, ModelFeat.EXT_VENT_SLOT, ModelFeat.VENT_SLOT);
			
			RotatPattern360 slotPattern = new RotatPattern360(ModelFeat.Z.name());
			
			if (DataStore.isSlotWithRound()) {
				//TODO - build round
			} else {
				slotPattern.patternBuild(DataStore.getSlotQty(), 1, ModelFeat.AR_VENT_SLOT.name(), ModelFeat.EXT_VENT_SLOT.name(), currSolid);
			}
			LOG.info("VentSheet is built");
		} catch (jxthrowable e) {
			LOG.error("Error in creating VentSheet!", e);
		}
	}
}
