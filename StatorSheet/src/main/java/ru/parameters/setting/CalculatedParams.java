package ru.parameters.setting;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import static ru.ruselprom.parameters.Parameters.setDoubleParamValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.parameters.ModelParamNames;

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
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_WDG_WDTH.name(), getSlotWdgWdth(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_HGHT_ADD.name(), getSlotHghtAdd(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLT_PER_SEGM.name(), getSltPerSegm(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_ANGL_PER_SEGM.name(), toRadians(getAnglPerSegm()), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_ANGL.name(), toRadians(getSlotAngl()), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_H_ANGL_PER_SEGM.name(), toRadians(getHAnglPerSegm()), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_INT.name(), getChordInt(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_SLOT.name(), getChordSlot(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_RAD_DELTA.name(), getRadDelta(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_EXT.name(), getChordExt(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_HGHT.name(), getScrew06Hght(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_SCREW_06.name(), getChordScrew06(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SHEET_H_DIM_00.name(), getSheetHDim00(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_EXT_CUT.name(), getChordExtCut(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_01_ANGL1.name(), toRadians(getScrew010203Angl1()), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_01_ANGL2.name(), toRadians(getScrew010203Angl2()), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_02_ANGL1.name(), toRadians(getScrew010203Angl1()), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_02_ANGL2.name(), toRadians(getScrew010203Angl2()), currModel);
			if (DataStore.isScrew04Exist()) {
				setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_ANGL1.name(), toRadians(getScrew03Angl1()), currModel);
				setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_ANGL2.name(), toRadians(getScrew03Angl2()), currModel);
			} else {
				setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_ANGL1.name(), toRadians(getScrew010203Angl1()), currModel);
				setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_03_ANGL2.name(), toRadians(getScrew010203Angl2()), currModel);
			}
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_SCREW_05.name(), getChordScrew05(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_STEP.name(), getScrew05067Step(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_H_STEP.name(), getScrew05067HStep(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_STEP.name(), getScrew05067Step(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_H_STEP.name(), getScrew05067HStep(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_07_STEP.name(), getScrew05067Step(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_07_H_STEP.name(), getScrew05067HStep(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_CHORD_SCREW_07.name(), getChordScrew07(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_07_H_NECK.name(), getScrew07HNeck(), currModel);
			setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_07_H_WDTH.name(), getScrew07HWdth(), currModel);
			
			LOG.info("Calculated parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting calculated parameters", e);
		}
	}

	private double getScrew03Angl2() {
		return DataStore.getScrewShift() - DataStore.getScrew04Shift() - getScrew03Angl1();
	}

	private double getScrew03Angl1() {
		return 2 * (360.0 / DataStore.getSegmQty() / 2 - DataStore.getScrewShift());
	}
	
	private double getSlotWdgWdth() {
		double triangleHeight = DataStore.getWedgeThck()*sin(toRadians(DataStore.getWedgeAngleTop()))*
				cos(toRadians(DataStore.getWedgeAngleTop()));
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
		return DataStore.getIntDiam() * sin(toRadians(getHAnglPerSegm()));
	}
	private double getChordSlot() {
		double intRadAndSlotHght = DataStore.getIntDiam()/2 + DataStore.getTotalSlotHght();
		double twoSin = 2*sin(toRadians(getAnglPerSegm()*intRadAndSlotHght/(DataStore.getIntDiam() + 2*
				DataStore.getTotalSlotHght())));
		return twoSin * intRadAndSlotHght - 2*DataStore.getSegmPruning() *
				cos(toRadians(getHAnglPerSegm()));
	}
	private double getRadDelta() {
		return DataStore.getExtDiam()/2 - DataStore.getIntDiam()/2;
	}
	private double getChordExt() {
		return DataStore.getExtDiam()*sin(toRadians(getHAnglPerSegm()));
	}
	private double getScrew06Hght() {
		double extRad = DataStore.getExtDiam()/2;
		return extRad - sqrt(pow(extRad, 2) - pow(DataStore.getScrew06Wdth()/2, 2));
	}
	private double getChordScrew06() {
		double shiftFlat = 2 * getScrew06Hght() * cos(toRadians(getHAnglPerSegm()));
		double shiftPrun = 2 * DataStore.getSegmPruning() * cos(toRadians(getHAnglPerSegm()));
		return getChordExt() - shiftFlat - shiftPrun;
	}
	private double getSheetHDim00() {
		double intRad = DataStore.getIntDiam() / 2;
		double hSlotWdth = DataStore.getSlotWdth() / 2;
		double secondPart = intRad * (1 - cos(Math.asin((getChordInt() - 
				2 * (hSlotWdth * cos(toRadians(getHAnglPerSegm())) + 
						(intRad - sqrt(pow(intRad, 2) - pow(hSlotWdth, 2)))
						* sin(toRadians(getHAnglPerSegm())))) / DataStore.getIntDiam())));
		return getRadDelta() + secondPart;
	}
	private double getChordExtCut() {
		double extRad = DataStore.getExtDiam() / 2;
		double secondPart = 2 * (DataStore.getSegmPruning() * cos(toRadians(getHAnglPerSegm())) +
				(extRad - sqrt(pow(extRad, 2) - pow(DataStore.getSegmPruning(), 2))) *
				sin(toRadians(getHAnglPerSegm())));
		return getChordExt() - secondPart;
	}
	private double getScrew010203Angl1() {
		if (DataStore.getScrewShift() > 360.0 / DataStore.getSegmQty() / 4) {
			return DataStore.getScrewShift() - getScrew010203Angl2();
		} else if (DataStore.getScrewShift() < 360.0 / DataStore.getSegmQty() / 4) {
			return DataStore.getScrewShift();
		} else {
			return 0;
		}
	}
	private double getScrew010203Angl2() {
		if (DataStore.getScrewShift() > 360.0 / DataStore.getSegmQty() / 4 && DataStore.getScrewQty() == 4) {
			return 2 * DataStore.getScrewShift() - (360.0 / DataStore.getSegmQty()) / 2;
		} else if (DataStore.getScrewShift() < 360.0 / DataStore.getSegmQty() / 4 && DataStore.getScrewQty() == 4) {
			return 360.0 / DataStore.getSegmQty() / 2 - DataStore.getScrewShift() * 2;
		} else {
			return 0;
		}
	}
	private double getChordScrew05() {
		double shiftEdge = 2 * DataStore.getScrew05Hght() * cos(toRadians(getHAnglPerSegm()));
		double shiftPrun = 2 * DataStore.getSegmPruning() * cos(toRadians(getHAnglPerSegm()));
		return getChordExt() - shiftEdge - shiftPrun;
	}
	private double getScrew05067Step() {
		int screwQty;
		if (DataStore.getTypeOfScrew() / 10 == 0) {
			screwQty = DataStore.getTotalScrewQty();
		} else {
			screwQty = DataStore.getTotalSecondScrewQty();
		}
		return 360.0 / screwQty;
	}
	private double getScrew05067HStep() {
		return getScrew05067Step() / 2;
	}
	private double getChordScrew07() {
		double shiftDovetail = 2 * (DataStore.getScrew07Hght() + DataStore.getScrew07Gap()) * cos(toRadians(getHAnglPerSegm()));
		double shiftPrun = 2 * DataStore.getSegmPruning() * cos(toRadians(getHAnglPerSegm()));
		return getChordExt() - shiftDovetail - shiftPrun;
	}
	private double getScrew07HNeck() {
		return DataStore.getScrew07NeckWdth() / 2;
	}
	private double getScrew07HWdth() {
		return DataStore.getScrew07Wdth() / 2;
	} 
}
