package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.ruselprom.parameters.Parameters;

public class SlotParams implements Params {
	
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
	public void create(Model currModel) {
		try {
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_WDTH", DataStore.getSlotWdth(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_HGHT_TO_WDG", DataStore.getSlotHghtToWdg(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_WEDGE_THCK", DataStore.getWedgeThck(), LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_WEDGE_GAP", DataStore.getWedgeGap(), LengthUnitType.LENGTHUNIT_MM, currModel);
			LOG.info("Slot parameters created");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating slot parameters", e);
		}
	}
}
