package ru.ruselprom.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.ruselprom.data.DataStore;
import ru.ruselprom.parameters.ModelParamNames;
import ru.ruselprom.lib.parameters.Parameters;
import ru.ruselprom.parameters.setting.ParamsSetting;

public class Screw06Params implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw06Params.class);
	private double screwShift;
	
	public Screw06Params(double screwShift) {
		this.screwShift = screwShift;
	}

	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_EXIST.name(), true, currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_WDTH.name(), DataStore.getScrew06Wdth(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_SHIFT.name(), Math.toRadians(screwShift), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_QTY.name(), DataStore.getTotalScrewQty(), currModel);
			LOG.info("Screw06 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw06 parameters", e);
		}
	}
}
