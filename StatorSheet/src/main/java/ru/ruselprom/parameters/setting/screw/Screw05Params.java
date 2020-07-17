package ru.ruselprom.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.ruselprom.data.DataStore;
import ru.ruselprom.parameters.ModelParamNames;
import ru.ruselprom.parameters.Parameters;
import ru.ruselprom.parameters.setting.ParamsSetting;

public class Screw05Params implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw05Params.class);
	private double screwShift;
	
	public Screw05Params(double screwShift) {
		this.screwShift = screwShift;
	}

	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_EXIST.name(), true, currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_WDTH.name(), DataStore.getScrew05Wdth(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_HGHT.name(), DataStore.getScrew05Hght(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_SHIFT.name(), Math.toRadians(screwShift), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_QTY.name(), DataStore.getTotalScrewQty(), currModel);
			LOG.info("Screw05 parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting Screw05 parameters", e);
		}
	}
}
