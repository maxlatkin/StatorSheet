package ru.building;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RefPattern;
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
			ExtrusionAddSym sheet = new ExtrusionAddSym();
			sheet.build(DataStore.getSheetThck(), ModelFeat.EXT_SHEET.name(), ModelFeat.SHEET.name(), currSolid);
			
			ExtrusionCut slot = new ExtrusionCut();
			slot.build(ModelFeat.EXT_SLOT.name(), ModelFeat.SLOT_WITHOUT_ROUND.name(), currSolid);
			
			RotatPattern360 slotPattern = new RotatPattern360(ModelFeat.Z.name());
			
			if (DataStore.isSlotWithRound()) {
				int[] firstEdgeIndices = {48,49,54,61};
				int[] secondEdgeIndices = {55,56,57,58,59,60};
				Round round = new Round();
				RadiusAndEdgeIndices firstSetOfRounds = new RadiusAndEdgeIndices(DataStore.getSlotRoundTop()/2, firstEdgeIndices);
				RadiusAndEdgeIndices secondSetOfRounds = new RadiusAndEdgeIndices(DataStore.getSlotRoundBottom()/2, secondEdgeIndices);
				RadiusAndEdgeIndices[] setsOfRounds = {firstSetOfRounds, secondSetOfRounds};
				round.build(ModelFeat.ROUND.name(), ModelFeat.EXT_SLOT.name(), currSolid, setsOfRounds);
				
				slotPattern.patternBuild(DataStore.getSlotQty(), 1, ModelFeat.AR_SLOT.name(), ModelFeat.EXT_SLOT.name(), currSolid);
				RefPattern roundPattern = new RefPattern();
				roundPattern.patternBuild(ModelFeat.AR_ROUND.name(), ModelFeat.ROUND.name(), currSolid);
			} else {
				slotPattern.patternBuild(DataStore.getSlotQty(), 1, ModelFeat.AR_SLOT.name(), ModelFeat.EXT_SLOT.name(), currSolid);
			}
			LOG.info("Sheet was built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating sheet!", e);
		}
	}
}
