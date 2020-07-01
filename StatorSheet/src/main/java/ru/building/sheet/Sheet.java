package ru.building.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.building.Buildable;
import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;

public abstract class Sheet implements Buildable {
	
	private static final Logger LOG = LoggerFactory.getLogger(Sheet.class);
	
	
	protected void buildSlot(Solid currSolid, ModelFeat extSlot, ModelFeat slot) {
		try {
			ExtrusionCut slotCut = new ExtrusionCut();
			slotCut.build(extSlot.name(),slot.name(), currSolid);
		} catch (jxthrowable e) {
			LOG.error("Error slot building");
		}
	}
	
	protected void buildSheetBody(Solid currSolid) {
		try {
			ExtrusionAddSym sheet = new ExtrusionAddSym();
			sheet.build(DataStore.getSheetThck(), ModelFeat.EXT_SHEET.name(), ModelFeat.SHEET.name(), currSolid);
		} catch (jxthrowable e) {
			LOG.error("Error sheet body building");
		}
	}
}
