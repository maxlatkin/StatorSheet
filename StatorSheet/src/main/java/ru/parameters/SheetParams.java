package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.ruselprom.parameters.Parameters;

public class SheetParams implements Params {
	
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
	public void create(Model currModel) {
		try {
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_INT_DIAM", DataStore.getIntDiam(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_EXT_DIAM", DataStore.getExtDiam(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SLOT_QTY", DataStore.getSlotQty(), currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SHEET_THCK", DataStore.getSheetThck(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SEGM_QTY", DataStore.getSegmQty(), currModel);
			LOG.info("Sheet parameters created");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating sheet parameters", e);
		}
	}
}
