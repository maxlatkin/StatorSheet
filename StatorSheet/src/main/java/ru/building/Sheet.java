package ru.building;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.operations.FetOperations;
import ru.ruselprom.fet.patterns.RotatPattern360;

public final class Sheet {
	
	public static final Logger LOG = LoggerFactory.getLogger(Sheet.class);
	
	private Sheet() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void create(Solid currSolid) {
		try {
			ExtrusionAddSym sheetThck = new ExtrusionAddSym();
			sheetThck.build(DataStore.getSheetThck(), "EXT_SHEET", "SHEET", currSolid);
			ExtrusionCut slot = new ExtrusionCut();
			if (DataStore.isSlotWithRound()) {
				slot.build("EXT_SLOT", "SLOT_WITH_ROUND", currSolid);
				FetOperations.deleteFeature(currSolid, "SLOT_WITHOUT_ROUND");
			} else {
				slot.build("EXT_SLOT", "SLOT_WITHOUT_ROUND", currSolid);
				FetOperations.deleteFeature(currSolid, "SLOT_WITH_ROUND");
			}
			RotatPattern360 slotAr = new RotatPattern360("Z");
			slotAr.patternBuild(DataStore.getSlotQty(), 1, "AR_SLOT", "EXT_SLOT", currSolid);
			LOG.info("Sheet is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating sheet!", e);
		}
	}
}
