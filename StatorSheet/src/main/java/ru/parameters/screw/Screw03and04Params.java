package ru.parameters.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.Params;
import ru.ruselprom.parameters.Parameters;

public class Screw03and04Params implements Params {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03and04Params.class);
	
	@Override
	public void create(Model currModel) {
		try {
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_03_EXIST", true, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_03_DIAM", DataStore.getScrewDiam(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_03_SHIFT", DataStore.getScrewShift(), currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_03_QTY", DataStore.getScrewQty(), currModel);
			LOG.info("Screw03 parameters created");
			if (DataStore.isScrew04Exist()) {
				Parameters.createBoolParam("AA_STATOR_CORE_SCREW_04_EXIST", true, currModel);
				Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_04_DIAM", DataStore.getScrewDiam(), LengthUnitType.LENGTHUNIT_MM, currModel);
				Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_04_SHIFT", DataStore.getScrewShift(), currModel);
				Parameters.createIntParam("AA_STATOR_CORE_SCREW_04_QTY", DataStore.getScrewQty(), currModel);
				LOG.info("Screw04 parameters created");
			}
		} catch (jxthrowable e) {
			LOG.error("Error in creating Screw03and04 parameters", e);
		}
	}

}
