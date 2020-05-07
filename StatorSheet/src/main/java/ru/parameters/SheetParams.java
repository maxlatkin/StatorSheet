package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.ruselprom.parameters.Parameters;

public class SheetParams implements ParamsSetting {
	
	private static SheetParams instance;
	private static final Logger LOG = LoggerFactory.getLogger(SheetParams.class);
	
	private SheetParams() {}
	
	public static SheetParams getInstance() {
        if (instance == null) {
            instance = new SheetParams();
        }
        return instance;
    }
	
	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setDoubleParamValue("AA_STATOR_CORE_INT_DIAM", DataStore.getIntDiam(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_EXT_DIAM", DataStore.getExtDiam(), currModel);
			Parameters.setIntParamValue("AA_STATOR_CORE_SLOT_QTY", DataStore.getSlotQty(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SHEET_THCK", DataStore.getSheetThck(), currModel);
			Parameters.setIntParamValue("AA_STATOR_CORE_SEGM_QTY", DataStore.getSegmQty(), currModel);
			LOG.info("Sheet parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting sheet parameters", e);
		}
	}
}
