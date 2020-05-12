package ru.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.externaldata.DataStore;
import ru.parameters.ModelParamNames;
import ru.parameters.setting.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw02Params implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw02Params.class);
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_02_EXIST.name(), true, currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_02_DIAM.name(), DataStore.getScrewDiam(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_02_SHIFT.name(), Math.toRadians(DataStore.getScrewShift()), currModel);
			Parameters.setIntParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_02_QTY.name(), DataStore.getScrewQty(), currModel);
			LOG.info("Screw02 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw02 parameters", e);
		}
	}

}
