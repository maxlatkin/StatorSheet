package ru.data;

import java.util.HashMap;
import java.util.Map;

public final class DataStore {
	
	private static String tempFile = "stator_sheet.prt";
	private static String modelsPath = "D:\\Project\\pro\\models\\StatorSheet";
	private static String modelName = "sheet";
	private static double sheetThck = 0.5;
	private static int slotQty = 72;
	private static boolean slotWithRound = true;
	private static double extDiam;
	private static double intDiam;
	private static double segmPruning = 0.2;
	private static int segmQty;
	private static double slotHghtToWdg = 44;
	private static double wedgeThck = 5;
	private static double wedgeGap = 1;
	private static double wedgeAngleTop = 30;
	private static double wedgeAngleBottom = 60;
	private static double slotWdth = 12;
	private static double slotRoundBottom = 1;
	private static double slotRoundTop = 0.4;
	private static double markRound = 0.4;
	private static double markRadius = 1.5;
	private static double markShift = 15;
	private static int screwQty;
	private static boolean screw04Exist;
	private static int typeOfScrew;
	private static Map<Double, Integer> screw010203NearestPoints = new HashMap<>();
	private static Map<Double, Integer> screw0203NearestPoints = new HashMap<>();
	private static Map<Double, Double> screw01ExtRads = new HashMap<>();
	private static Map<Double, Double> screw01MidRads = new HashMap<>();
	private static Map<Double, Double> screw02ExtRads = new HashMap<>();
	private static Map<Double, Double> screw03IntRads = new HashMap<>();
	private static Map<Double, Integer> screw01FarTopPoints = new HashMap<>();
	private static Map<Double, Integer> screw01FarBottomPoints = new HashMap<>();
	private static double screwDiam;
	private static double screwShift;
	private static double screw05Wdth;
	private static double screw05Hght;
	private static double screw06Wdth;
	private static double screw07Wdth;
	private static double screw07Hght;
	private static double screw07Gap;
	
	public static double getScrew07Gap() {
		screw07Gap = 1;
		return screw07Gap;
	}

	public static double getScrew07Wdth() {
		screw07Wdth = 27;
		return screw07Wdth;
	}

	public static double getScrew07Hght() {
		screw07Hght = 9;
		return screw07Hght;
	}

	public static double getScrew06Wdth() {
		screw06Wdth = 80;
		return screw06Wdth;
	}

	public static double getScrew05Wdth() {
		screw05Wdth = 20;
		return screw05Wdth;
	}

	public static double getScrew05Hght() {
		screw05Hght = 5;
		return screw05Hght;
	}

	public static Map<Double, Double> getScrew03IntRads() {
		screw03IntRads.put(20.3, getExtDiam()/2 - 8);
		screw03IntRads.put(24.3, getExtDiam()/2 - 10);
		screw03IntRads.put(30.3, getExtDiam()/2 - 12);
		screw03IntRads.put(36.3, getExtDiam()/2 - 14);
		return screw03IntRads;
	}

	public static Map<Double, Double> getScrew02ExtRads() {
		screw02ExtRads.put(20.3, getExtDiam()/2 + 8);
		screw02ExtRads.put(24.3, getExtDiam()/2 + 10);
		screw02ExtRads.put(30.3, getExtDiam()/2 + 12);
		screw02ExtRads.put(36.3, getExtDiam()/2 + 14);
		return screw02ExtRads;
	}

	public static Map<Double, Integer> getScrew0203NearestPoints() {
		screw0203NearestPoints.put(20.3, 20);
		screw0203NearestPoints.put(24.3, 22);
		screw0203NearestPoints.put(30.3, 29);
		screw0203NearestPoints.put(36.3, 35);
		return screw0203NearestPoints;
	}

	public static double getScrewDiam() {
		screwDiam = 20.3;
		return screwDiam;
	}

	public static double getScrewShift() {
		screwShift = 30;
		return screwShift;
	}

	public static Map<Double, Integer> getScrew01FarBottomPoints() {
		screw01FarBottomPoints.put(20.3, 35);
		screw01FarBottomPoints.put(24.3, 40);
		screw01FarBottomPoints.put(30.3, 50);
		screw01FarBottomPoints.put(36.3, 60);
		return screw01FarBottomPoints;
	}

	public static Map<Double, Integer> getScrew01FarTopPoints() {
		screw01FarTopPoints.put(20.3, 30);
		screw01FarTopPoints.put(24.3, 35);
		screw01FarTopPoints.put(30.3, 45);
		screw01FarTopPoints.put(36.3, 55);
		return screw01FarTopPoints;
	}

	public static Map<Double, Double> getScrew01MidRads() {
		screw01MidRads.put(20.3, getExtDiam()/2 + 7);
		screw01MidRads.put(24.3, getExtDiam()/2 + 8);
		screw01MidRads.put(30.3, getExtDiam()/2 + 10);
		screw01MidRads.put(36.3, getExtDiam()/2 + 12);
		return screw01MidRads;
	}

	public static Map<Double, Double> getScrew01ExtRads() {
		screw01ExtRads.put(20.3, getExtDiam()/2 + 15);
		screw01ExtRads.put(24.3, getExtDiam()/2 + 18);
		screw01ExtRads.put(30.3, getExtDiam()/2 + 22);
		screw01ExtRads.put(36.3, getExtDiam()/2 + 26);
		return screw01ExtRads;
	}

	public static Map<Double, Integer> getScrew010203NearestPoints() {
		screw010203NearestPoints.put(20.3, 8);
		screw010203NearestPoints.put(24.3, 10);
		screw010203NearestPoints.put(30.3, 12);
		screw010203NearestPoints.put(36.3, 14);
		return screw010203NearestPoints;
	}

	public static int getTypeOfScrew() {
		typeOfScrew = 1;
		return typeOfScrew;
	}

	public static boolean isScrew04Exist() {
		screw04Exist = true;
		return screw04Exist;
	}

	public static int getScrewQty() {
		screwQty = 4;
		return screwQty;
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
		extDiam = 560;
		return extDiam;
	}

	public static double getIntDiam() {
		intDiam = 400;
		return intDiam;
	}

	public static double getSegmPruning() {
		return segmPruning;
	}

	public static int getSegmQty() {
		segmQty = 4;
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
