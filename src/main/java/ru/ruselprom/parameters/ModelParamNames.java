package ru.ruselprom.parameters;

public enum ModelParamNames {
	AA_STATOR_CORE_INT_DIAM(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_EXT_DIAM(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SLOT_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SHEET_THCK(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SEGM_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SLOT_WDTH(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SLOT_HGHT_TO_WDG(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_WEDGE_THCK(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_WEDGE_GAP(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_01_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_01_DIAM(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_01_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_01_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SCREW_02_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_02_DIAM(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_02_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_02_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SCREW_03_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_03_DIAM(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_03_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_03_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SCREW_04_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_04_DIAM(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_04_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_04_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SCREW_05_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_05_WDTH(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_05_HGHT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_05_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_05_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SCREW_06_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_06_WDTH(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_06_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_06_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SCREW_07_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_07_WDTH(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_07_HGHT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_07_SHIFT(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_07_QTY(ModelParamTypes.INT),
	AA_STATOR_CORE_SLOT_HGHT_TOTAL(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SLOT_WDG_WDTH(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SLOT_HGHT_ADD(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SLT_PER_SEGM(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_ANGL_PER_SEGM(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SLOT_ANGL(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_H_ANGL_PER_SEGM(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_CHORD_INT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_CHORD_SLOT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_RAD_DELTA(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_CHORD_EXT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_06_HGHT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_CHORD_SCREW_06(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SHEET_H_DIM_00(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_CHORD_EXT_CUT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_01_ANGL1(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_01_ANGL2(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_02_ANGL1(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_02_ANGL2(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_03_ANGL1(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SCREW_03_ANGL2(ModelParamTypes.DOUBLE_IN_DEG),
	AA_STATOR_CORE_SEGM_MARK_EXIST(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SEGM_MARK_SHIFT(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_05_CORNER(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_06_CORNER(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SCREW_07_CORNER(ModelParamTypes.BOOL),
	AA_STATOR_CORE_CHORD_SCREW_05(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_05_H_STEP(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_SCREW_05_STEP(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_SCREW_06_STEP(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_SCREW_06_H_STEP(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_CHORD_SCREW_07(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_07_STEP(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_SCREW_07_H_STEP(ModelParamTypes.DOUBLE),
	AA_SETUP_MODE(ModelParamTypes.BOOL),
	AA_STATOR_CORE_SEGM_ROLLING(ModelParamTypes.STRING),
	AA_STATOR_CORE_SCREW_07_NECK(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_07_H_NECK(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SCREW_07_H_WDTH(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SLOT_WDG_FLT_W(ModelParamTypes.DOUBLE_LENGTH_MM),
	AA_STATOR_CORE_SHEET_AREA(ModelParamTypes.DOUBLE),
	AA_STATOR_CORE_SHEET_PERIMETER(ModelParamTypes.DOUBLE_LENGTH_M),
	AA_STATOR_CORE_NOTE(ModelParamTypes.STRING),
	AA_STATOR_CORE_STO(ModelParamTypes.STRING),
	AA_STATOR_CORE_MECH_RESULTS(ModelParamTypes.STRING),
	AA_STATOR_CORE_SHEET_TYPE(ModelParamTypes.STRING);
	
	private ModelParamTypes paramType;

	private ModelParamNames(ModelParamTypes paramType) {
		this.paramType = paramType;
	}
	
	public ModelParamTypes getParamType() {
		return paramType;
	}
}