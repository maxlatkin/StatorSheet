package ru.parameters.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.parameters.ModelParamNames;
import ru.ruselprom.fet.info.Info;
import ru.ruselprom.parameters.Parameters;

public class AuxiliaryParams implements ParamsSetting {
	private static AuxiliaryParams instance;
	private static final Logger LOG = LoggerFactory.getLogger(AuxiliaryParams.class);
	
	private AuxiliaryParams() {}
	
	public static AuxiliaryParams getInstance() {
        if (instance == null) {
            instance = new AuxiliaryParams();
        }
        return instance;
    }

	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_CORNER.name(), true, currModel);
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_CORNER.name(), true, currModel);
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_07_CORNER.name(), true, currModel);
			Parameters.setStringParamValue(ModelParamNames.AA_STATOR_CORE_SEGM_ROLLING.name(), DataStore.getSegmRolling(), currModel);
			
			Info.getDimensionsInfoIn("SLOT_WITH_ROUND", (Solid)currModel);
			LOG.info("Auxiliary parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting auxiliary parameters", e);
		}
		
	}
}
