package ru.data;

import java.util.HashMap;
import java.util.Map;

public final class DataStore {
	
	private static double sheetThck;
	private static int slotQty;
	private static boolean slotWithRound;
	private static double extDiam;
	private static double intDiam;
	private static double segmPruning;
	private static int segmQty;
	private static double slotHghtToWdg;
	private static double wedgeThck;
	private static double wedgeGap;
	private static double wedgeAngleTop;
	private static double wedgeAngleBottom;
	private static double slotWdth;
	private static double roundAtBottomOfSlot;
	private static double roundWedgeOfSlot;
	private static double roundAtTopOfSlot;
	private static double markRound;
	private static double markRadius;
	private static double markShift;
	private static int screwQty;
	private static boolean screw04Exist;
	private static int typeOfScrew;
	private static Map<Double, Double> screw010203NearestPoints = new HashMap<>();
	private static Map<Double, Double> screw020304NearestPoints = new HashMap<>();
	private static Map<Double, Double> screw01ExtRads = new HashMap<>();
	private static Map<Double, Double> screw01MidRads = new HashMap<>();
	private static Map<Double, Double> screw02ExtRads = new HashMap<>();
	private static Map<Double, Double> screw0304IntRads = new HashMap<>();
	private static Map<Double, Double> screw0102FarTopPoints = new HashMap<>();
	private static Map<Double, Double> screw0102FarBottomPoints = new HashMap<>();
	private static double studDiam;
	private static double studHoleDiam;
	private static double screwShift;
	private static double secondScrewShift;
	private static double screw05Wdth;
	private static double screw05Hght;
	private static double screw06Wdth;
	private static double screw07Wdth;
	private static double screw07NeckWdth;
	private static double screw07Hght;
	private static double screw07Gap;
	private static String segmRolling;
	private static int slotStep;
	private static double totalSlotHght;
	private static int totalScrewQty;
	private static int totalSecondScrewQty;
	private static double screw04Shift;
	private static double difBetwDiamOfStudAndDiamOfHole;
	private static double slotWedgeWdth;
	
	public static double getSlotWedgeWdth() {
		return slotWedgeWdth;
	}

	public static double getSecondScrewShift() {
		return secondScrewShift;
	}

	public static double getScrew07NeckWdth() {
		screw07NeckWdth = 19;
		return screw07NeckWdth;
	}

	public static int getTotalSecondScrewQty() {
		totalSecondScrewQty = 8;
		return totalSecondScrewQty;
	}

	public static double getStudDiam() {
		return studDiam;
	}

	public static double getDifBetwDiamOfStudAndDiamOfHole() {
		return difBetwDiamOfStudAndDiamOfHole;
	}

	public static double getScrew04Shift() {
		screw04Shift = getScrewShift() - 120.0 / getSegmQty();
		return screw04Shift;
	}

	public static int getTotalScrewQty() {
		return totalScrewQty;
	}

	public static double getTotalSlotHght() {
		return totalSlotHght;
	}

	public static int getSlotStep() {
		return slotStep;
	}

	public static String getSegmRolling() {
		//"VERTICAL"; //"HORIZONTAL"; "NONE"
		return segmRolling;
	}

	public static double getScrew07Gap() {
		return screw07Gap;
	}

	public static double getScrew07Wdth() {
		return screw07Wdth;
	}

	public static double getScrew07Hght() {
		return screw07Hght;
	}

	public static double getScrew06Wdth() {
		screw06Wdth = getScrew05Wdth() + 20;
		return screw06Wdth;
	}

	public static double getScrew05Wdth() {
		return screw05Wdth;
	}

	public static double getScrew05Hght() {
		return screw05Hght;
	}

	public static Map<Double, Double> getScrew0304IntRads() {
		return screw0304IntRads;
	}

	public static Map<Double, Double> getScrew02ExtRads() {
		return screw02ExtRads;
	}

	public static Map<Double, Double> getScrew020304NearestPoints() {
		return screw020304NearestPoints;
	}

	public static double getStudHoleDiam() {
		studHoleDiam = getStudDiam() + getDifBetwDiamOfStudAndDiamOfHole();
		return studHoleDiam;
	}

	public static double getScrewShift() {
		return screwShift;
	}

	public static Map<Double, Double> getScrew0102FarBottomPoints() {
		return screw0102FarBottomPoints;
	}

	public static Map<Double, Double> getScrew0102FarTopPoints() {
		return screw0102FarTopPoints;
	}

	public static Map<Double, Double> getScrew01MidRads() {
		return screw01MidRads;
	}

	public static Map<Double, Double> getScrew01ExtRads() {
		return screw01ExtRads;
	}

	public static Map<Double, Double> getScrew010203NearestPoints() {
		return screw010203NearestPoints;
	}

	public static int getTypeOfScrew() {
		return typeOfScrew;
	}

	public static boolean isScrew04Exist() {
		return screw04Exist;
	}

	public static int getScrewQty() {
		screwQty = getTotalScrewQty() / getSegmQty();
		return screwQty;
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

	public static double getRoundAtBottomOfSlot() {
		return roundAtBottomOfSlot;
	}

	public static double getRoundWedgeOfSlot() {
		return roundWedgeOfSlot;
	}

	public static double getRoundAtTopOfSlot() {
		return roundAtTopOfSlot;
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

	public static void setScrewShift(double screwShift) {
		DataStore.screwShift = screwShift;
	}

	public static void setSegmQty(int segmQty) {
		DataStore.segmQty = segmQty;
	}

	public static void setExtDiam(double extDiam) {
		DataStore.extDiam = extDiam;
	}

	public static void setSlotQty(int slotQty) {
		DataStore.slotQty = slotQty;
	}

	public static void setIntDiam(double intDiam) {
		DataStore.intDiam = intDiam;
	}
	
	public static void setTotalSlotHght(double totalSlotHght) {
		DataStore.totalSlotHght = totalSlotHght;
	}

	public static void setSlotWdth(double slotWdth) {
		DataStore.slotWdth = slotWdth;
	}

	public static void setSlotStep(int slotStep) {
		DataStore.slotStep = slotStep;
	}

	public static void setSegmRolling(String segmRolling) {
		DataStore.segmRolling = segmRolling;
	}

	public static void setSlotHghtToWdg(double slotHghtToWdg) {
		DataStore.slotHghtToWdg = slotHghtToWdg;
	}

	public static void setSheetThck(double sheetThck) {
		DataStore.sheetThck = sheetThck;
	}

	public static void setSegmPruning(double segmPruning) {
		DataStore.segmPruning = segmPruning;
	}

	public static void setWedgeThck(double wedgeThck) {
		DataStore.wedgeThck = wedgeThck;
	}

	public static void setWedgeGap(double wedgeGap) {
		DataStore.wedgeGap = wedgeGap;
	}

	public static void setWedgeAngleTop(double wedgeAngleTop) {
		DataStore.wedgeAngleTop = wedgeAngleTop;
	}

	public static void setWedgeAngleBottom(double wedgeAngleBottom) {
		DataStore.wedgeAngleBottom = wedgeAngleBottom;
	}

	public static void setRoundAtBottomOfSlot(double roundAtBottomOfSlot) {
		DataStore.roundAtBottomOfSlot = roundAtBottomOfSlot;
	}

	public static void setRoundWedgeOfSlot(double roundWedgeOfSlot) {
		DataStore.roundWedgeOfSlot = roundWedgeOfSlot;
	}

	public static void setRoundAtTopOfSlot(double roundAtTopOfSlot) {
		DataStore.roundAtTopOfSlot = roundAtTopOfSlot;
	}

	public static void setMarkRound(double markRound) {
		DataStore.markRound = markRound;
	}

	public static void setMarkRadius(double markRadius) {
		DataStore.markRadius = markRadius;
	}

	public static void setMarkShift(double markShift) {
		DataStore.markShift = markShift;
	}

	public static void setDifBetwDiamOfStudAndDiamOfHole(double difBetwDiamOfStudAndDiamOfHole) {
		DataStore.difBetwDiamOfStudAndDiamOfHole = difBetwDiamOfStudAndDiamOfHole;
	}

	public static void setTotalScrewQty(int totalScrewQty) {
		DataStore.totalScrewQty = totalScrewQty;
	}

	public static void setStudDiam(double studDiam) {
		DataStore.studDiam = studDiam;
	}

	public static void setScrew05Wdth(double screw05Wdth) {
		DataStore.screw05Wdth = screw05Wdth;
	}

	public static void setScrew05Hght(double screw05Hght) {
		DataStore.screw05Hght = screw05Hght;
	}

	public static void setScrew07Wdth(double screw07Wdth) {
		DataStore.screw07Wdth = screw07Wdth;
	}

	public static void setScrew07Hght(double screw07Hght) {
		DataStore.screw07Hght = screw07Hght;
	}

	public static void setScrew07Gap(double screw07Gap) {
		DataStore.screw07Gap = screw07Gap;
	}

	public static void setSlotWithRound(boolean slotWithRound) {
		DataStore.slotWithRound = slotWithRound;
	}

	public static void setTypeOfScrew(int typeOfScrew) {
		DataStore.typeOfScrew = typeOfScrew;
	}
	
	public static void setScrew04Exist(boolean screw04Exist) {
		DataStore.screw04Exist = screw04Exist;
	}

	public static void setSecondScrewShift(double secondScrewShift) {
		DataStore.secondScrewShift = secondScrewShift;
	}

	public static void setSlotWedgeWdth(double slotWedgeWdth) {
		DataStore.slotWedgeWdth = slotWedgeWdth;
	}

	private DataStore() {
		throw new IllegalStateException("Utility class");
	}
}
