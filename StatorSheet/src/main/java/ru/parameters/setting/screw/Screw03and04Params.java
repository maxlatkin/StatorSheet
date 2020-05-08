package ru.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ModelParamNames;
import ru.parameters.setting.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw03and04Params implements ParamsSetting {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03and04Params.class);
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_EXIST.name(), true, currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_DIAM.name(), DataStore.getScrewDiam(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_SHIFT.name(), Math.toRadians(DataStore.getScrewShift()), currModel);
			Parameters.setIntParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_QTY.name(), DataStore.getScrewQty(), currModel);
			LOG.info("Screw03 parameters set");
			if (DataStore.isScrew04Exist()) {
				Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_04_EXIST.name(), true, currModel);
				Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_04_DIAM.name(), DataStore.getScrewDiam(), currModel);
				Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_04_SHIFT.name(), Math.toRadians(DataStore.getScrewShift()), currModel);
				Parameters.setIntParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_04_QTY.name(), DataStore.getScrewQty(), currModel);
				LOG.info("Screw04 parameters set");
			}
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw03and04 parameters", e);
		}
	}

}
