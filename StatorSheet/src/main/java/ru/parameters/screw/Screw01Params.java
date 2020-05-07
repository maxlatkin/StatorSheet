package ru.parameters.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw01Params implements ParamsSetting {

	private static final Logger LOG = LoggerFactory.getLogger(Screw01Params.class);
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue("AA_STATOR_CORE_SCREW_01_EXIST", true, currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_01_DIAM", DataStore.getScrewDiam(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_01_SHIFT", Math.toRadians(DataStore.getScrewShift()), currModel);
			Parameters.setIntParamValue("AA_STATOR_CORE_SCREW_01_QTY", DataStore.getScrewQty(), currModel);
			LOG.info("Screw01 parameters set");
		} catch (jxthrowable e) {
			LOG.error("Error in setting Screw01 parameters", e);
		}
	}
}
