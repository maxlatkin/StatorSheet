package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.fet.patterns.TwoRotatPattern;

public class Screw03And04 extends Screw {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				buildScrew03(currSolid, ModelFeat.SCREW_03_HOLE_2, ModelFeat.EXT_SCREW_03_HOLE_2, ModelFeat.AR_SCREW_03_HOLE_2);
			} else if (DataStore.getScrewQty() == 4) {
				buildScrew03(currSolid, ModelFeat.SCREW_03_HOLE_4, ModelFeat.EXT_SCREW_03_HOLE_4, ModelFeat.AR_SCREW_03_HOLE_4);
			}
			if (DataStore.isScrew04Exist()) {
				ExtrusionCut screw04Hole = new ExtrusionCut();
				screw04Hole.build(ModelFeat.EXT_SCREW_04_HOLE.name(), ModelFeat.SCREW_04_HOLE.name(), currSolid);
				if (DataStore.getScrewQty() == 2) {
					RotatPattern360 screw04HoleAr = new RotatPattern360(ModelFeat.Z.name());
					screw04HoleAr.patternBuild(DataStore.getSegmQty(), 1,
							ModelFeat.AR_SCREW_04_HOLE.name(), ModelFeat.EXT_SCREW_04_HOLE.name(), currSolid);
				} else if (DataStore.getScrewQty() == 4) {
					TwoRotatPattern screw04HoleTwoAr = new TwoRotatPattern(ModelFeat.Z.name(), ModelFeat.Z.name());
					screw04HoleTwoAr.patternBuild(DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
							2, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)),
							ModelFeat.AR_SCREW_04_HOLE.name(), ModelFeat.EXT_SCREW_04_HOLE.name(), currSolid);
				}
			}
			LOG.info("Screw03And04 is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error building the Screw_03And04", e);
		}
	}

}
