package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class Screw06 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw06.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			buildScrew050607(currSolid, ModelFeat.SCREW_06_HOLE, ModelFeat.EXT_SCREW_06_HOLE, ModelFeat.AR_SCREW_06_HOLE);
			ModelFeat.deleteAllScrewExceptOf(currSolid, ModelFeat.SCREW_06_HOLE);
			LOG.info("Screw_06_Qty={} is built", DataStore.getScrewQty());
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_06", e);
		}
	}
}
