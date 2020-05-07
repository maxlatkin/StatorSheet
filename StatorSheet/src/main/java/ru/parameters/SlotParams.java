package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
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
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLOT_WDTH", DataStore.getSlotWdth(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLOT_HGHT_TO_WDG", DataStore.getSlotHghtToWdg(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_WEDGE_THCK", DataStore.getWedgeThck(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_WEDGE_GAP", DataStore.getWedgeGap(), currModel);
			LOG.info("Slot parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting slot parameters", e);
		}
	}
}
