package ru.parameters.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw05Params implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw05Params.class);
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue("AA_STATOR_CORE_SCREW_05_EXIST", true, currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_05_WDTH", DataStore.getScrew05Wdth(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_05_HGHT", DataStore.getScrew05Hght(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_05_SHIFT", Math.toRadians(DataStore.getScrewShift()), currModel);
			Parameters.setIntParamValue("AA_STATOR_CORE_SCREW_05_QTY", DataStore.getScrewQty(), currModel);
			LOG.info("Screw05 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw05 parameters", e);
		}
	}
}
