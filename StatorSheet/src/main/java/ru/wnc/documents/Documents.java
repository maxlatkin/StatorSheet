package ru.wnc.documents;

public class Documents {
	private static String noteName = null;
	private static String stoName = null;
	private static String resultsName = null;
	private Documents() {
	    throw new IllegalStateException("Utility class");
	  }
	public static String getNoteName() {
		return noteName;
	}
	public static String getStoName() {
		return stoName;
	}
	public static String getResultsName() {
		return resultsName;
	}
	public static void setNoteName(String noteName) {
		Documents.noteName = noteName;
	}
	public static void setStoName(String stoName) {
		Documents.stoName = stoName;
	}
	public static void setResultsName(String resultsName) {
		Documents.resultsName = resultsName;
	}
}
