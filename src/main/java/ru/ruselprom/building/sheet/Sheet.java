package ru.ruselprom.building.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.base.Direction;
import ru.ruselprom.building.Buildable;
import ru.ruselprom.data.DataStore;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RefPattern;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.general.ModelFeat;

public abstract class Sheet implements Buildable {
	
	private static final Logger LOG = LoggerFactory.getLogger(Sheet.class);
	protected Solid currSolid;
	
	public Sheet(Solid currSolid) {
		this.currSolid = currSolid;
	}
	
	protected void buildSheetBody() {
		try {
			ExtrusionAddSym sheet = new ExtrusionAddSym();
			sheet.build(DataStore.getSheetThck(), ModelFeat.EXT_SHEET.name(), ModelFeat.SHEET.name(), currSolid);
		} catch (jxthrowable e) {
			LOG.error("Error building sheet body");
		}
	}

	protected void buildSlot(ModelFeat extSlot, ModelFeat slot) {
		try {
			ExtrusionCut slotCut = new ExtrusionCut();
			slotCut.build(extSlot.name(), slot.name(), currSolid);
		} catch (jxthrowable e) {
			LOG.error("Error building slot");
		}
	}
	
	protected void buildSlotPatternWithRoundOrNot(boolean withRound, ModelFeat arSlot, ModelFeat extSlot) {
		try {
			RotatPattern360 slotPattern = new RotatPattern360(ModelFeat.Z.name());
			if (withRound) {
				buildRound();
				slotPattern.patternBuild(DataStore.getSlotQty(), Direction.CLOCKWISE, arSlot.name(), extSlot.name(), currSolid);
				RefPattern roundPattern = new RefPattern();
				roundPattern.patternBuild(ModelFeat.AR_ROUND.name(), ModelFeat.ROUND.name(), currSolid);
			} else {
				slotPattern.patternBuild(DataStore.getSlotQty(), Direction.CLOCKWISE, arSlot.name(), extSlot.name(), currSolid);
			}
		} catch (jxthrowable e) {
			LOG.error("Error building slotPattern");
		}
	}
	
	protected abstract void buildRound();
}
