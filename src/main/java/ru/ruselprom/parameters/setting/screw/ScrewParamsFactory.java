package ru.ruselprom.parameters.setting.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ruselprom.parameters.setting.ParamsSetting;

public class ScrewParamsFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewParamsFactory.class);
	
	private ScrewParamsFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static ParamsSetting getParams(int typeOfScrew, double screwShift) {
		ParamsSetting params = null;
		switch (typeOfScrew) {
		case 1:
			params = new Screw01Params(screwShift);
			break;
		case 2:
			params = new Screw02Params(screwShift);
			break;
		case 3:
			params = new Screw03and04Params(screwShift);
			break;
		case 5:
			params = new Screw05Params(screwShift);
			break;
		case 6:
			params = new Screw06Params(screwShift);
			break;
		case 7:
			params = new Screw07Params(screwShift);
			break;
		default:
			IllegalArgumentException e = new IllegalArgumentException("Unknown type of screw!");
			LOG.error("ScrewParamsFactory - incorrect screw type entered", e);
			throw e;
		}
		LOG.info("ScrewParams got from ScrewParamsFactory");
		return params;
	}
}