package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class Screw02 extends Screw {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw02.class);
	
	@Override
	public void build(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				buildScrew0102(currSolid,
						ModelFeat.SCREW_02_SOLID_2, ModelFeat.EXT_SCREW_02_SOLID_2,	ModelFeat.AR_SCREW_02_SOLID_2,
						ModelFeat.SCREW_02_HOLE_2, ModelFeat.EXT_SCREW_02_HOLE_2, ModelFeat.AR_SCREW_02_HOLE_2);
				ModelFeat.deleteAllScrewExceptOf(currSolid, ModelFeat.SCREW_02_SOLID_2, ModelFeat.SCREW_02_HOLE_2);
				LOG.info("Screw_02_Qty=2 is built");
			} else if (DataStore.getScrewQty() == 4) {
				buildScrew0102(currSolid,
						ModelFeat.SCREW_02_SOLID_4, ModelFeat.EXT_SCREW_02_SOLID_4,	ModelFeat.AR_SCREW_02_SOLID_4,
						ModelFeat.SCREW_02_HOLE_4, ModelFeat.EXT_SCREW_02_HOLE_4, ModelFeat.AR_SCREW_02_HOLE_4);
				ModelFeat.deleteAllScrewExceptOf(currSolid, ModelFeat.SCREW_02_SOLID_4, ModelFeat.SCREW_02_HOLE_4);
				LOG.info("Screw_02_Qty=4 is built");
			}
		} catch (jxthrowable e) {
			LOG.error("Error building the Screw_02", e);
		}
	}
}
