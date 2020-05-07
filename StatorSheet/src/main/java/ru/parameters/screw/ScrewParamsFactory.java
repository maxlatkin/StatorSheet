package ru.parameters.screw;

import ru.data.DataStore;
import ru.parameters.ParamsSetting;

public class ScrewParamsFactory {
	
	private ScrewParamsFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static ParamsSetting getParams() {
		ParamsSetting params = null;
		if (DataStore.getTypeOfScrew() == 1) {
			params = new Screw01Params();
		} else if (DataStore.getTypeOfScrew() == 2) {
			params = new Screw02Params();
		} else if (DataStore.getTypeOfScrew() == 3) {
			params = new Screw03and04Params();
		} else if (DataStore.getTypeOfScrew() == 5) {
			params = new Screw05Params();
		} else if (DataStore.getTypeOfScrew() == 6) {
			params = new Screw06Params();
		} else if (DataStore.getTypeOfScrew() == 7) {
			params = new Screw07Params();
		}
		return params;
	}
}
