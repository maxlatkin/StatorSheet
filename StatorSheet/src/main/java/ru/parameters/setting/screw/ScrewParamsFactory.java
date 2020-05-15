package ru.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.externaldata.DataStore;
import ru.parameters.setting.ParamsSetting;

public class ScrewParamsFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewParamsFactory.class);
	
	private ScrewParamsFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static ParamsSetting getParams() {
		ParamsSetting params = null;
		switch (DataStore.getTypeOfScrew()) {
		case 1:
			params = new Screw01Params();
			break;
		case 2:
			params = new Screw02Params();
			break;
		case 3:
			params = new Screw03and04Params();
			break;
		case 5:
			params = new Screw05Params();
			break;
		case 6:
			params = new Screw06Params();
			break;
		case 7:
			params = new Screw07Params();
			break;
		default:
			LOG.error("ScrewParamsFactory - incorrect screw type entered");
			break;
		}
		LOG.info("ScrewParams got from ScrewParamsFactory");
		return params;
	}
}