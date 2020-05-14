package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.fet.patterns.TwoRotatPattern;

public class Screw01 implements Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw01.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				ExtrusionAddSym screwSolid = new ExtrusionAddSym();
				screwSolid.build(DataStore.getSheetThck(), ModelFeat.EXT_SCREW_01_SOLID_2.name(),
						ModelFeat.SCREW_01_SOLID_2.name(), currSolid);
				TwoRotatPattern screwAr = new TwoRotatPattern(ModelFeat.Y.name(), ModelFeat.Z.name());
				screwAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
						ModelFeat.AR_SCREW_01_SOLID_2.name(), ModelFeat.EXT_SCREW_01_SOLID_2.name(), currSolid);
				ExtrusionCut screwHole = new ExtrusionCut();
				screwHole.build(ModelFeat.EXT_SCREW_01_HOLE_2.name(), ModelFeat.SCREW_01_HOLE_2.name(), currSolid);
				screwAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(), 
						ModelFeat.AR_SCREW_01_HOLE_2.name(), ModelFeat.EXT_SCREW_01_HOLE_2.name(), currSolid);
				
				ModelFeat.deleteAllScrewExceptOf(currSolid, ModelFeat.SCREW_01_SOLID_2, ModelFeat.SCREW_01_HOLE_2);
				LOG.info("Screw_01_Qty=2 is built");
			} else if (DataStore.getScrewQty() == 4) {
				ExtrusionAddSym screwSolid = new ExtrusionAddSym();
				screwSolid.build(DataStore.getSheetThck(), "EXT_SCREW_01_SOLID_4", "SCREW_01_SOLID_4", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_01_SOLID_4", "EXT_SCREW_01_SOLID_4", currSolid);
				ExtrusionCut screwHole = new ExtrusionCut();
				screwHole.build("EXT_SCREW_01_HOLE_4", "SCREW_01_HOLE_4", currSolid);
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_01_HOLE_4", "EXT_SCREW_01_HOLE_4", currSolid);
				
				ModelFeat.deleteAllScrewExceptOf(currSolid, ModelFeat.SCREW_01_SOLID_4, ModelFeat.SCREW_01_HOLE_4);
				LOG.info("Screw_01_Qty=4 is built");
			}
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_01", e);
		}
	}
}
