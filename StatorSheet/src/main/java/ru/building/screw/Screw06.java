package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.operations.FetOperations;
import ru.ruselprom.fet.patterns.RotatPattern360;

public class Screw06 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw06.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			ExtrusionCut screwHole = new ExtrusionCut();
			screwHole.build("EXT_SCREW_06_HOLE", "SCREW_06_HOLE", currSolid);
			RotatPattern360 screwAr = new RotatPattern360("Z");
			screwAr.patternBuild(DataStore.getSegmQty() * DataStore.getScrewQty(), 1, 
					"AR_SCREW_06_HOLE", "EXT_SCREW_06_HOLE", currSolid);
			FetOperations.deleteFeature(currSolid, "SCREW_01_SOLID_2", "SCREW_01_HOLE_2",
					"SCREW_01_SOLID_4", "SCREW_01_HOLE_4",
					"SCREW_02_SOLID_2", "SCREW_02_HOLE_2",
					"SCREW_02_SOLID_4", "SCREW_02_HOLE_4",
					"SCREW_03_HOLE_2", "SCREW_03_HOLE_4",
					"SCREW_04_HOLE", "SCREW_05_HOLE", "SCREW_07_HOLE");
			LOG.info("Screw_06_Qty={} is built", DataStore.getScrewQty());
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_06", e);
		}
	}
}
