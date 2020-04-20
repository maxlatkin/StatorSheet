package ru.data;

public class DataStore {
	
	public static final String TEMP_FILE = "stator_sheet.prt";
	public static final String MODELS_PATH = "D:\\Project\\pro\\models\\StatorSheet";
	public static final String MODEL_NAME = "sheet";
	public static final double SHEET_THCK = 0.5;
	public static final int SLOT_QTY = 72;
	public static final boolean SLOT_WITH_ROUND = true;
	public static final double EXT_DIAM = 700;
	public static final double INT_DIAM = 500;
	public static final double SEGM_PRUNING = 0.2;
	public static final int SEGM_QTY = 4;
	public static final double SLOT_HGHT_TO_WDG = 44;
	public static final double WEDGE_THCK = 5;
	public static final double WEDGE_GAP = 1;
	public static final double SLOT_WDTH = 12;
	public static final double SLOT_ROUND_INT = 1;
	public static final double SLOT_ROUND_EXT = 0.4;
	public static final double SLOT_ANGLE = 45;
	
	
	private DataStore() {
		throw new IllegalStateException("Utility class");
	}
}
