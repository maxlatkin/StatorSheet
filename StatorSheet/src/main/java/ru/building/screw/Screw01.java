package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.general.ModelFeat;

public class Screw01 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw01.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				buildScrew0102(currSolid,
						ModelFeat.SCREW_01_SOLID_2, ModelFeat.EXT_SCREW_01_SOLID_2,	ModelFeat.AR_SCREW_01_SOLID_2,
						ModelFeat.SCREW_01_HOLE_2, ModelFeat.EXT_SCREW_01_HOLE_2, ModelFeat.AR_SCREW_01_HOLE_2);
				LOG.info("Screw_01_Qty=2 is built");
			} else if (DataStore.getScrewQty() == 4) {
				buildScrew0102(currSolid,
						ModelFeat.SCREW_01_SOLID_4, ModelFeat.EXT_SCREW_01_SOLID_4,	ModelFeat.AR_SCREW_01_SOLID_4,
						ModelFeat.SCREW_01_HOLE_4, ModelFeat.EXT_SCREW_01_HOLE_4, ModelFeat.AR_SCREW_01_HOLE_4);
				LOG.info("Screw_01_Qty=4 is built");
			}
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_01", e);
		}
	}
}
