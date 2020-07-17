package ru.ruselprom.general;

public enum SheetType {
	BASIC,
	VENT;
	
	private static SheetType currSheetType;

	public static SheetType getCurrSheetType() {
		return currSheetType;
	}

	public static void setCurrSheetType(SheetType currSheetType) {
		SheetType.currSheetType = currSheetType;
	}
}