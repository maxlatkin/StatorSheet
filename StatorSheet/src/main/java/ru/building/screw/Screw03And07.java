package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class Screw03And07 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw03And07.class);

	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				buildScrew03(currSolid, ModelFeat.SCREW_03_HOLE_2, ModelFeat.EXT_SCREW_03_HOLE_2, ModelFeat.AR_SCREW_03_HOLE_2);
			} else if (DataStore.getScrewQty() == 4) {
				buildScrew03(currSolid, ModelFeat.SCREW_03_HOLE_4, ModelFeat.EXT_SCREW_03_HOLE_4, ModelFeat.AR_SCREW_03_HOLE_4);
			}
			buildScrew050607(currSolid, ModelFeat.SCREW_07_HOLE, ModelFeat.EXT_SCREW_07_HOLE, ModelFeat.AR_SCREW_07_HOLE);
			LOG.info("Screw03And07 is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error building the Screw_03And07", e);
		}
	}
}
