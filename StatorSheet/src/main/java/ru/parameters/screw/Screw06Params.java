package ru.parameters.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw06Params implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw06Params.class);
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue("AA_STATOR_CORE_SCREW_06_EXIST", true, currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_06_WDTH", DataStore.getScrew06Wdth(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_06_SHIFT", Math.toRadians(DataStore.getScrewShift()), currModel);
			Parameters.setIntParamValue("AA_STATOR_CORE_SCREW_06_QTY", DataStore.getScrewQty(), currModel);
			LOG.info("Screw06 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw06 parameters", e);
		}
	}
}
