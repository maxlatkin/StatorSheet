package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.general.ModelFeat;

public class Screw07 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw07.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			buildScrew050607(currSolid, ModelFeat.SCREW_07_HOLE, ModelFeat.EXT_SCREW_07_HOLE, ModelFeat.AR_SCREW_07_HOLE);
			LOG.info("Screw_07 is built");
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_07", e);
		}
	}
}
