package ru.parameters.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;

import ru.ruselprom.parameters.Parameters;

public final class CreationOfParams {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreationOfParams.class);
	
	private CreationOfParams() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void create(Model currModel) {
		try {
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_INT_DIAM", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_EXT_DIAM", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SLOT_QTY", 0, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SHEET_THCK", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SEGM_QTY", 0, currModel);
			
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_WDTH", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_HGHT_TO_WDG", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_WEDGE_THCK", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_WEDGE_GAP", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_01_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_01_DIAM", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_01_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_01_QTY", 0, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_02_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_02_DIAM", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_02_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_02_QTY", 0, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_03_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_03_DIAM", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_03_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_03_QTY", 0, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_04_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_04_DIAM", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_04_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_04_QTY", 0, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_05_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_05_WDTH", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_05_HGHT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_05_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_05_QTY", 0, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_06_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_06_WDTH", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_06_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_06_QTY", 0, currModel);
			
			Parameters.createBoolParam("AA_STATOR_CORE_SCREW_07_EXIST", false, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_07_WDTH", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_07_HGHT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_07_SHIFT", 0, currModel);
			Parameters.createIntParam("AA_STATOR_CORE_SCREW_07_QTY", 0, currModel);
			
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_HGHT_TOTAL", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_WDG_WDTH", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SLOT_HGHT_ADD", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParam("AA_STATOR_CORE_SLT_PER_SEGM", 0, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_ANGL_PER_SEGM", 0, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SLOT_ANGL", 0, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_H_ANGL_PER_SEGM", 0, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_CHORD_INT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_CHORD_SLOT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_RAD_DELTA", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_CHORD_EXT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SCREW_06_HGHT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_CHORD_SCREW_06", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_SHEET_H_DIM_00", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleLengthParam("AA_STATOR_CORE_CHORD_EXT_CUT", 0, LengthUnitType.LENGTHUNIT_MM, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_01_ANGL1", 0, currModel);
			Parameters.createDoubleParamInDeg("AA_STATOR_CORE_SCREW_01_ANGL2", 0, currModel);
			LOG.info("All parameters created");
		} catch (jxthrowable e) {
			LOG.error("Error in creating all parameters", e);
		}
	}
}
