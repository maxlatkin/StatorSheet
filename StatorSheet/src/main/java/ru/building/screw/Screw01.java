package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.operations.FetOperations;
import ru.ruselprom.fet.patterns.RotatPattern360;

public class Screw01 implements Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw01.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				ExtrusionAddSym screwSolid = new ExtrusionAddSym();
				screwSolid.build(DataStore.getSheetThck(), "EXT_SCREW_01_SOLID_2", "SCREW_01_SOLID_2", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_01_SOLID_2", "EXT_SCREW_01_SOLID_2", currSolid);
				ExtrusionCut screwHole = new ExtrusionCut();
				screwHole.build("EXT_SCREW_01_HOLE_2", "SCREW_01_HOLE_2", currSolid);
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_01_HOLE_2", "EXT_SCREW_01_HOLE_2", currSolid);
				FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_4", "SCREW_01_HOLE_4",
						"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
						"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
						"SCREW_03_HOLE_2", "SCREW_03_HOLE_4",
						"SCREW_04_HOLE", "SCREW_05_HOLE",
						"SCREW_06_HOLE", "SCREW_07_HOLE");
				LOG.info("Screw_01_Qty=2 is built");
			} else if (DataStore.getScrewQty() == 4) {
				ExtrusionAddSym screwSolid = new ExtrusionAddSym();
				screwSolid.build(DataStore.getSheetThck(), "EXT_SCREW_01_SOLID_4", "SCREW_01_SOLID_4", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_01_SOLID_4", "EXT_SCREW_01_SOLID_4", currSolid);
				ExtrusionCut screwHole = new ExtrusionCut();
				screwHole.build("EXT_SCREW_01_HOLE_4", "SCREW_01_HOLE_4", currSolid);
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_01_HOLE_4", "EXT_SCREW_01_HOLE_4", currSolid);
				FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_2", "SCREW_01_HOLE_2",
						"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
						"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
						"SCREW_03_HOLE_2", "SCREW_03_HOLE_4",
						"SCREW_04_HOLE", "SCREW_05_HOLE",
						"SCREW_06_HOLE", "SCREW_07_HOLE");
				LOG.info("Screw_01_Qty=4 is built");
			}
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_01", e);
		}
	}
}
