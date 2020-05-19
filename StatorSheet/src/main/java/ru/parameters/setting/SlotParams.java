package ru.parameters.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ModelParamNames;
import ru.ruselprom.parameters.Parameters;

public class SlotParams implements ParamsSetting {
	
	private static SlotParams instance;
	private static final Logger LOG = LoggerFactory.getLogger(SlotParams.class);
	
	private SlotParams() {}
	
	public static SlotParams getInstance() {
        if (instance == null) {
            instance = new SlotParams();
        }
        return instance;
    }
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_WDTH.name(), DataStore.getSlotWdth(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_HGHT_TO_WDG.name(), DataStore.getSlotHghtToWdg(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_WEDGE_THCK.name(), DataStore.getWedgeThck(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_WEDGE_GAP.name(), DataStore.getWedgeGap(), currModel);
			LOG.info("Slot parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting slot parameters", e);
		}
	}
}
