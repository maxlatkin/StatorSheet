package ru.parameters.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.Params;
import ru.ruselprom.parameters.Parameters;

public class Screw06Params implements Params {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw06Params.class);
	
	@Override
	public void create(Model currModel) {
		try {
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_06_EXIST", true, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_06_WDTH", DataStore.getScrew06Wdth(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_06_SHIFT", DataStore.getScrewShift(), currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_06_QTY", DataStore.getScrewQty(), currModel);
			LOG.info("Screw06 parameters created");
		} catch (jxthrowable e) {
			LOG.error("Error in creating Screw06 parameters", e);
		}
	}
}
