package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.operations.FetOperations;
import ru.ruselprom.fet.patterns.RotatPattern360;

public class Screw03And04 implements Screw {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrew03Qty() == 2) {
				ExtrusionCut screw03Hole = new ExtrusionCut();
				screw03Hole.build("EXT_SCREW_03_HOLE_2", "SCREW_03_HOLE_2", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_03_HOLE_2", "EXT_SCREW_03_HOLE_2", currSolid);
				if (DataStore.isScrew04Exist()) {
					ExtrusionCut screw04Hole = new ExtrusionCut();
					screw04Hole.build("EXT_SCREW_04_HOLE", "SCREW_04_HOLE", currSolid);
					screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_03_HOLE_4", "EXT_SCREW_04_HOLE", currSolid);
				} else {
					FetOperations.deleteComponent(currSolid, "SCREW_04_HOLE");
				}
				FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_2", "SCREW_01_HOLE_2",
						"SCREW_01_SOLID_4", "SCREW_01_HOLE_4",
						"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
						"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
						"SCREW_03_HOLE_4", "SCREW_05_HOLE",
						"SCREW_06_HOLE", "SCREW_07_HOLE");
				LOG.info("Screw_03And04_Qty=2 is built");
			} else if (DataStore.getScrew03Qty() == 4) {
				ExtrusionCut screw03Hole = new ExtrusionCut();
				screw03Hole.build("EXT_SCREW_03_HOLE_4", "SCREW_03_HOLE_4", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_03_HOLE_4", "EXT_SCREW_03_HOLE_4", currSolid);
				if (DataStore.isScrew04Exist()) {
					ExtrusionCut screw04Hole = new ExtrusionCut();
					screw04Hole.build("EXT_SCREW_04_HOLE", "SCREW_04_HOLE", currSolid);
					screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_04_HOLE", "EXT_SCREW_04_HOLE", currSolid);
				} else {
					FetOperations.deleteComponent(currSolid, "SCREW_04_HOLE");
				}
				FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_2", "SCREW_01_HOLE_2",
						"SCREW_01_SOLID_4", "SCREW_01_HOLE_4",
						"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
						"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
						"SCREW_03_HOLE_2", "SCREW_05_HOLE",
						"SCREW_06_HOLE", "SCREW_07_HOLE");
				LOG.info("Screw_03And04_Qty=4 is built");
			}
		} catch (jxthrowable e) {
			LOG.error("Error creating the Screw_03And04", e);
		}
	}

}
