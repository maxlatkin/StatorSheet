package ru.parameters.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw02Params implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw02Params.class);
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue("AA_STATOR_CORE_SCREW_02_EXIST", true, currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_02_DIAM", DataStore.getScrewDiam(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_02_SHIFT", Math.toRadians(DataStore.getScrewShift()), currModel);
			Parameters.setIntParamValue("AA_STATOR_CORE_SCREW_02_QTY", DataStore.getScrewQty(), currModel);
			LOG.info("Screw02 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw02 parameters", e);
		}
	}

}
