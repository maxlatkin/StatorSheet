package ru.parameters.calculated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ParamsSetting;
import ru.ruselprom.parameters.Parameters;

public class CalculatedParams implements ParamsSetting {
	
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
	public void setValue(Model currModel) {
		try {
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLOT_HGHT_TOTAL", getSlotHghtTotal(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLOT_WDG_WDTH", getSlotWdgWdth(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLOT_HGHT_ADD", getSlotHghtAdd(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLT_PER_SEGM", getSltPerSegm(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_ANGL_PER_SEGM", Math.toRadians(getAnglPerSegm()), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SLOT_ANGL", Math.toRadians(getSlotAngl()), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_H_ANGL_PER_SEGM", Math.toRadians(getHAnglPerSegm()), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_CHORD_INT", getChordInt(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_CHORD_SLOT", getChordSlot(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_RAD_DELTA", getRadDelta(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_CHORD_EXT", getChordExt(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_06_HGHT", getScrew06Hght(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_CHORD_SCREW_06", getChordScrew06(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SHEET_H_DIM_00", getSheetHDim00(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_CHORD_EXT_CUT", getChordExtCut(), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_01_ANGL1", Math.toRadians(getScrew01Angl1()), currModel);
			Parameters.setDoubleParamValue("AA_STATOR_CORE_SCREW_01_ANGL2", Math.toRadians(getScrew01Angl2()), currModel);
			LOG.info("Calculated parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting calculated parameters", e);
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
	private double getSlotHghtAdd() {
		return DataStore.getWedgeThck() + DataStore.getWedgeGap();
	}
	private double getSltPerSegm() {
		return (double)DataStore.getSlotQty() / DataStore.getSegmQty();
	}
	private double getAnglPerSegm() {
		return 360.0 / DataStore.getSegmQty();
	}
	private double getSlotAngl() {
		return 360.0 / DataStore.getSlotQty();
	}
	private double getHAnglPerSegm() {
		return getAnglPerSegm() / 2;
	}
	private double getChordInt() {
		return DataStore.getIntDiam() * Math.sin(Math.toRadians(getHAnglPerSegm()));
	}
	private double getChordSlot() {
		double intRadAndSlotHght = DataStore.getIntDiam()/2 + getSlotHghtTotal();
		double twoSin = 2*Math.sin(Math.toRadians(getAnglPerSegm()*intRadAndSlotHght/(DataStore.getIntDiam() + 2*
				getSlotHghtTotal())));
		return twoSin * intRadAndSlotHght - 2*DataStore.getSegmPruning() *
				Math.cos(Math.toRadians(getHAnglPerSegm()));
	}
	private double getRadDelta() {
		return DataStore.getExtDiam()/2 - DataStore.getIntDiam()/2;
	}
	private double getChordExt() {
		return DataStore.getExtDiam()*Math.sin(Math.toRadians(getHAnglPerSegm()));
	}
	private double getScrew06Hght() {
		double extRad = DataStore.getExtDiam()/2;
		return extRad - Math.sqrt(Math.pow(extRad, 2) - Math.pow(DataStore.getScrew06Wdth()/2, 2));
	}
	private double getChordScrew06() {
		double shiftSlice = 2 * getScrew06Hght() * Math.cos(Math.toRadians(getHAnglPerSegm()));
		double shiftPrun = 2 * DataStore.getSegmPruning() * Math.cos(Math.toRadians(getHAnglPerSegm()));
		return getChordExt() - shiftSlice - shiftPrun;
	}
	private double getSheetHDim00() {
		double intRad = DataStore.getIntDiam() / 2;
		double hSlotWdth = DataStore.getSlotWdth() / 2;
		double secondPart = intRad * (1 - Math.cos(Math.asin((getChordInt() - 
				2 * (hSlotWdth * Math.cos(Math.toRadians(getHAnglPerSegm())) + 
						(intRad - Math.sqrt(Math.pow(intRad, 2) - Math.pow(hSlotWdth, 2)))
						* Math.sin(Math.toRadians(getHAnglPerSegm())))) / DataStore.getIntDiam())));
		return getRadDelta() + secondPart;
	}
	private double getChordExtCut() {
		double extRad = DataStore.getExtDiam() / 2;
		double secondPart = 2 * (DataStore.getSegmPruning() * Math.cos(Math.toRadians(getHAnglPerSegm())) +
				(extRad - Math.sqrt(Math.pow(extRad, 2) - Math.pow(DataStore.getSegmPruning(), 2))) *
				Math.sin(Math.toRadians(getHAnglPerSegm())));
		return getChordExt() - secondPart;
	}
	private double getScrew01Angl1() {
		if (DataStore.getScrewShift() > 360.0 / DataStore.getSegmQty() / 4) {
			return DataStore.getScrewShift() - getScrew01Angl2();
		} else if (DataStore.getScrewShift() < 360.0 / DataStore.getSegmQty() / 4) {
			return DataStore.getScrewShift();
		} else {
			return 0;
		}
	}
	private double getScrew01Angl2() {
		if (DataStore.getScrewShift() > 360.0 / DataStore.getSegmQty() / 4 && DataStore.getScrewQty() == 4) {
			return (360.0 / DataStore.getSegmQty() - (360.0 / DataStore.getSegmQty() - DataStore.getScrewShift()*2)*2)/2;
		} else if (DataStore.getScrewShift() < 360.0 / DataStore.getSegmQty() / 4 && DataStore.getScrewQty() == 4) {
			return 360.0 / DataStore.getSegmQty() / 2 - DataStore.getScrewShift() * 2;
		} else {
			return 0;
		}
	}
}
