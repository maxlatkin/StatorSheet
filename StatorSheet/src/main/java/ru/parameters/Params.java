package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcModel.Model;

import ru.parameters.creation.ModelParams;
import ru.parameters.setting.CalculatedParams;
import ru.parameters.setting.SheetParams;
import ru.parameters.setting.SlotParams;
import ru.parameters.setting.screw.ScrewParamsFactory;

public final class Params {
	
	private static final Logger LOG = LoggerFactory.getLogger(Params.class);
	
	private Params() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void createAllParams(Model currModel) {
		ModelParams.create(currModel);
		LOG.info("All parameters created");
	}
	
	public static void setAllParams(Model currModel) {
		SheetParams.getInstance().setValue(currModel);
		SlotParams.getInstance().setValue(currModel);
		ScrewParamsFactory.getParams().setValue(currModel);
		CalculatedParams.getInstance().setValue(currModel);
		LOG.info("All parameters set");
	}
}
