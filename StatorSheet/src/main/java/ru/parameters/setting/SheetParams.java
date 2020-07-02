package ru.parameters.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.general.SheetType;
import ru.parameters.ModelParamNames;
import ru.ruselprom.parameters.Parameters;

public class SheetParams implements ParamsSetting {
	
	private static final Logger LOG = LoggerFactory.getLogger(SheetParams.class);
	private SheetType sheetType;
	
	public SheetParams(SheetType sheetType) {
		this.sheetType = sheetType;
	}

	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_INT_DIAM.name(), DataStore.getIntDiam(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_EXT_DIAM.name(), DataStore.getExtDiam(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_QTY.name(), DataStore.getSlotQty(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SHEET_THCK.name(), DataStore.getSheetThck(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SEGM_QTY.name(), DataStore.getSegmQty(), currModel);
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SEGM_MARK_EXIST.name(), DataStore.getSegmQty() != 1, currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SEGM_MARK_SHIFT.name(), DataStore.getMarkShift(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_WDG_WDTH.name(), DataStore.getSlotWedgeWdth(), currModel);
			Parameters.setStringParamValue(ModelParamNames.AA_STATOR_CORE_SHEET_TYPE.name(), sheetType.name(), currModel);
			LOG.info("Sheet parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting sheet parameters", e);
		}
	}
}
