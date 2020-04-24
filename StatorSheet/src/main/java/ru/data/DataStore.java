package ru.data;

public final class DataStore {
	
	private static String tempFile = "stator_sheet.prt";
	private static String modelsPath = "D:\\Project\\pro\\models\\StatorSheet";
	private static String modelName = "sheet";
	private static double sheetThck = 0.5;
	private static int slotQty = 72;
	private static boolean slotWithRound = true;
	private static double extDiam = 700;
	private static double intDiam = 500;
	private static double segmPruning = 0.2;
	private static int segmQty = 4;
	private static double slotHghtToWdg = 44;
	private static double wedgeThck = 5;
	private static double wedgeGap = 1;
	private static double wedgeAngleTop = 30;
	private static double wedgeAngleBottom = 60;
	private static double slotWdth = 12;
	private static double slotRoundBottom = 1;
	private static double slotRoundTop = 0.4;
	private static double slotAngle = 45;
	private static double markRound = 0.4;
	private static double markRadius = 1.5;
	private static double markShift = 15;
	private static int screw01Qty;
	private static int screw02Qty;
	private static int screw03Qty;
	private static int screw05Qty;
	private static int screw06Qty;
	private static int screw07Qty;
	private static boolean screw04Exist;
	private static int typeOfScrew;
	
	public static int getTypeOfScrew() {
		typeOfScrew = 3;
		return typeOfScrew;
	}

	public static boolean isScrew04Exist() {
		screw04Exist = true;
		return screw04Exist;
	}

	public static int getScrew01Qty() {
		screw01Qty = 4;
		return screw01Qty;
	}

	public static int getScrew02Qty() {
		screw02Qty = 2;
		return screw02Qty;
	}

	public static int getScrew03Qty() {
		screw03Qty = 4;
		return screw03Qty;
	}

	public static int getScrew05Qty() {
		screw05Qty = 2;
		return screw05Qty;
	}

	public static int getScrew06Qty() {
		screw06Qty = 2;
		return screw06Qty;
	}

	public static int getScrew07Qty() {
		screw07Qty = 2;
		return screw07Qty;
	}

	public static String getTempFile() {
		return tempFile;
	}

	public static String getModelsPath() {
		return modelsPath;
	}

	public static String getModelName() {
		return modelName;
	}

	public static double getSheetThck() {
		return sheetThck;
	}

	public static int getSlotQty() {
		return slotQty;
	}

	public static boolean isSlotWithRound() {
		return slotWithRound;
	}

	public static double getExtDiam() {
		return extDiam;
	}

	public static double getIntDiam() {
		return intDiam;
	}

	public static double getSegmPruning() {
		return segmPruning;
	}

	public static int getSegmQty() {
		return segmQty;
	}

	public static double getSlotHghtToWdg() {
		return slotHghtToWdg;
	}

	public static double getWedgeThck() {
		return wedgeThck;
	}

	public static double getWedgeGap() {
		return wedgeGap;
	}

	public static double getWedgeAngleTop() {
		return wedgeAngleTop;
	}

	public static double getWedgeAngleBottom() {
		return wedgeAngleBottom;
	}

	public static double getSlotWdth() {
		return slotWdth;
	}

	public static double getSlotRoundBottom() {
		return slotRoundBottom;
	}

	public static double getSlotRoundTop() {
		return slotRoundTop;
	}

	public static double getSlotAngle() {
		return slotAngle;
	}

	public static double getMarkRound() {
		return markRound;
	}

	public static double getMarkRadius() {
		return markRadius;
	}

	public static double getMarkShift() {
		return markShift;
	}

	private DataStore() {
		throw new IllegalStateException("Utility class");
	}
}
