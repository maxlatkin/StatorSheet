package ru.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ModelParamNames;
import ru.parameters.setting.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class Screw01Params implements ParamsSetting {

	private static final Logger LOG = LoggerFactory.getLogger(Screw01Params.class);
	private double screwShift;
	
	public Screw01Params(double screwShift) {
		this.screwShift = screwShift;
	}

	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_01_EXIST.name(), true, currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_01_DIAM.name(), DataStore.getStudHoleDiam(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_01_SHIFT.name(), Math.toRadians(screwShift), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_01_QTY.name(), DataStore.getTotalScrewQty(), currModel);
			LOG.info("Screw01 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw01 parameters", e);
		}
	}
}
