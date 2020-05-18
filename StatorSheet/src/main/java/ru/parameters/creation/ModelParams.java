package ru.parameters.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.parameters.ModelParamNames;
import ru.ruselprom.parameters.Parameters;

public final class ModelParams {
	
	private static final Logger LOG = LoggerFactory.getLogger(ModelParams.class);
	
	private ModelParams() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void create(Model currModel) {
		try {
			for (ModelParamNames paramName: ModelParamNames.values()) {
				switch (paramName.getParamType()) {
				case DOUBLE_LENGTH_MM:
					Parameters.createDoubleLengthParam(paramName.name(), 0, LengthUnitType.LENGTHUNIT_MM, currModel);
					break;
				case DOUBLE_LENGTH_M:
					Parameters.createDoubleLengthParam(paramName.name(), 0, LengthUnitType.LENGTHUNIT_M, currModel);
					break;
				case DOUBLE_IN_DEG:
					Parameters.createDoubleParamInDeg(paramName.name(), 0, currModel);
					break;
				case DOUBLE:
					Parameters.createDoubleParam(paramName.name(), 0, currModel);
					break;
				case INT:
					Parameters.createIntParam(paramName.name(), 0, currModel);
					break;
				case BOOL:
					Parameters.createBoolParam(paramName.name(), false, currModel);
					break;
				case STRING:
					Parameters.createStringParam(paramName.name(), "NONE", currModel);
					break;
				default:
					IllegalArgumentException e = new IllegalArgumentException("Unknown type of param!");
					LOG.error("ModelParams - incorrect param type entered", e);
					throw e;
				}
			}
			LOG.info("Model parameters created");
		} catch (jxthrowable e) {
			LOG.error("Error in creating all parameters", e);
		}
	}
}
