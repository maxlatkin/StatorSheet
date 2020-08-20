package ru.ruselprom.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.general.ModelFeat;

public class Screw06 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw06.class);
	
	public Screw06(Solid currSolid) {
		super(currSolid);
	}

	@Override
	public void build() {
		try {
			buildScrew050607(currSolid, ModelFeat.SCREW_06_HOLE, ModelFeat.EXT_SCREW_06_HOLE, ModelFeat.AR_SCREW_06_HOLE);
			LOG.info("Screw_06 is built");
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_06", e);
		}
	}
}
