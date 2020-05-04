package ru.parameters.calculated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.Params;
import ru.ruselprom.parameters.Parameters;

public class CalculatedParams implements Params {
	
	private static CalculatedParams instance;
	private static final Logger LOG = LoggerFactory.getLogger(CalculatedParams.class);
	
	private CalculatedParams() {}
	
	public static CalculatedParams getInstance() {
        if (instance == null) {
            instance = new CalculatedParams();
        }
        return instance;
    }
	
	@Override
	public void create(Model currModel) {
		try {
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_HGHT_TOTAL", getSlotHghtTotal(), 
					LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_WDG_WDTH", getSlotWdgWdth(), 
					LengthUnitType.LENGTHUNIT_MM, currModel);
//			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_WDG_FLT_W", getSlotWdgWdth(), 
//					LengthUnitType.LENGTHUNIT_MM, currModel);
			LOG.info("Calculated parameters created");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating calculated parameters", e);
		}
	}
	
	private double getSlotHghtTotal() {
		return DataStore.getSlotHghtToWdg() + DataStore.getWedgeThck() + DataStore.getWedgeGap();
	}
	private double getSlotWdgWdth() {
		double triangleHeight = DataStore.getWedgeThck()*Math.sin(Math.toRadians(DataStore.getWedgeAngleTop()))*
				Math.cos(Math.toRadians(DataStore.getWedgeAngleTop()));
		return DataStore.getSlotWdth() + 2*triangleHeight;
	}
}
