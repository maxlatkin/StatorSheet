package ru.building;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.operations.FetOperations;
import ru.ruselprom.fet.patterns.RotatPattern360;

public final class Sheet {
	
	private static final Logger LOG = LoggerFactory.getLogger(Sheet.class);
	
	private Sheet() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void build(Solid currSolid) {
		try {
			ExtrusionAddSym sheetThck = new ExtrusionAddSym();
			sheetThck.build(DataStore.getSheetThck(), ModelFeat.EXT_SHEET.name(), ModelFeat.SHEET.name(), currSolid);
			ExtrusionCut slot = new ExtrusionCut();
			if (DataStore.isSlotWithRound()) {
				slot.build(ModelFeat.EXT_SLOT.name(), ModelFeat.SLOT_WITH_ROUND.name(), currSolid);
				FetOperations.deleteFeature(currSolid, ModelFeat.SLOT_WITHOUT_ROUND.name());
			} else {
				slot.build(ModelFeat.EXT_SLOT.name(), ModelFeat.SLOT_WITHOUT_ROUND.name(), currSolid);
				FetOperations.deleteFeature(currSolid, ModelFeat.SLOT_WITH_ROUND.name());
			}
			RotatPattern360 slotAr = new RotatPattern360(ModelFeat.Z.name());
			slotAr.patternBuild(DataStore.getSlotQty(), 1, ModelFeat.AR_SLOT.name(), ModelFeat.EXT_SLOT.name(), currSolid);
			LOG.info("Sheet is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating sheet!", e);
		}
	}
}
