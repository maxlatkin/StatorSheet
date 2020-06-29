package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcModel.Model;

import ru.parameters.setting.AuxiliaryParams;
import ru.parameters.setting.SheetParams;
import ru.parameters.setting.SlotParams;

public final class Params {
	
	private static final Logger LOG = LoggerFactory.getLogger(Params.class);
	
	private Params() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void setAllParams(Model currModel) {
		SheetParams.getInstance().setValue(currModel);
		SlotParams.getInstance().setValue(currModel);
		AuxiliaryParams.getInstance().setValue(currModel);
		LOG.info("All parameters set");
	}
}
