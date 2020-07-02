package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.general.ModelFeat;

public class Screw05 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw05.class);
	
	public Screw05(Solid currSolid) {
		super(currSolid);
	}
	
	@Override
	public void build() {
		try {
			buildScrew050607(currSolid, ModelFeat.SCREW_05_HOLE, ModelFeat.EXT_SCREW_05_HOLE, ModelFeat.AR_SCREW_05_HOLE);
			LOG.info("Screw_05 is built");
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_05", e);
		}
	}
}
