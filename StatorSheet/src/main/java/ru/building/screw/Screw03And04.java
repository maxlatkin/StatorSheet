package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.operations.FetOperations;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.fet.patterns.TwoRotatPattern;

public class Screw03And04 implements Screw {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				ExtrusionCut screw03Hole = new ExtrusionCut();
				screw03Hole.build("EXT_SCREW_03_HOLE_2", "SCREW_03_HOLE_2", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_03_HOLE_2", "EXT_SCREW_03_HOLE_2", currSolid);
				FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_2", "SCREW_01_HOLE_2",
						"SCREW_01_SOLID_4", "SCREW_01_HOLE_4",
						"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
						"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
						"SCREW_03_HOLE_4", "SCREW_05_HOLE",
						"SCREW_06_HOLE", "SCREW_07_HOLE");
			} else if (DataStore.getScrewQty() == 4) {
				ExtrusionCut screw03Hole = new ExtrusionCut();
				screw03Hole.build("EXT_SCREW_03_HOLE_4", "SCREW_03_HOLE_4", currSolid);
				RotatPattern360 screwAr = new RotatPattern360("Z");
				screwAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_03_HOLE_4", "EXT_SCREW_03_HOLE_4", currSolid);
				FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_2", "SCREW_01_HOLE_2",
						"SCREW_01_SOLID_4", "SCREW_01_HOLE_4",
						"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
						"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
						"SCREW_03_HOLE_2", "SCREW_05_HOLE",
						"SCREW_06_HOLE", "SCREW_07_HOLE");
			}
			if (DataStore.isScrew04Exist()) {
				ExtrusionCut screw04Hole = new ExtrusionCut();
				screw04Hole.build("EXT_SCREW_04_HOLE", "SCREW_04_HOLE", currSolid);
				if (DataStore.getScrewQty() == 2) {
					RotatPattern360 screw04HoleAr = new RotatPattern360("Z");
					screw04HoleAr.patternBuild(DataStore.getSegmQty(), 1, "AR_SCREW_04_HOLE", "EXT_SCREW_04_HOLE", currSolid);
				} else if (DataStore.getScrewQty() == 4) {
					TwoRotatPattern screw04HoleTwoAr = new TwoRotatPattern("Z","Z");
					screw04HoleTwoAr.patternBuild(DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
							2, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)),
							"AR_SCREW_04_HOLE", "EXT_SCREW_04_HOLE", currSolid);
				}
			} else {
				FetOperations.deleteFeature(currSolid, "SCREW_04_HOLE");
			}
			LOG.info("Screw_03And04_Qty is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error building the Screw_03And04", e);
		}
	}

}
