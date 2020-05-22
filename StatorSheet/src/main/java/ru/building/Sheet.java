package ru.building;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.TestPattern;
import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.fet.round.RadiusAndEdgeIndices;
import ru.ruselprom.fet.round.Round;

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
			slot.build(ModelFeat.EXT_SLOT.name(), ModelFeat.SLOT_WITHOUT_ROUND.name(), currSolid);
			RotatPattern360 slotAr = new RotatPattern360(ModelFeat.Z.name());
			slotAr.patternBuild(DataStore.getSlotQty(), 1, ModelFeat.AR_SLOT.name(), ModelFeat.EXT_SLOT.name(), currSolid);
			if (DataStore.isSlotWithRound()) {
				int[] edges1 = {48,49,54,61};
				int[] edges2 = {55,56,57,58,59,60};
				Round round = new Round();
				RadiusAndEdgeIndices firstSetOfRounds = new RadiusAndEdgeIndices(DataStore.getSlotRoundTop()/2, edges1);
				RadiusAndEdgeIndices secondSetOfRounds = new RadiusAndEdgeIndices(DataStore.getSlotRoundBottom()/2, edges2);
				RadiusAndEdgeIndices[] setsOfRounds = {firstSetOfRounds, secondSetOfRounds};
				round.build("TEST", "EXT_SLOT", currSolid, setsOfRounds);
				
				TestPattern testPattern = new TestPattern("Z");
				testPattern.patternBuild(DataStore.getSlotQty(), 1, "BY", "TEST", currSolid);
			}
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating sheet!", e);
		}
	}
}
