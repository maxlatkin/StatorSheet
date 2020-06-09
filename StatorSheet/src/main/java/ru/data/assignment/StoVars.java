package ru.data.assignment;

import static java.lang.Double.parseDouble;

import java.util.Arrays;
import java.util.Map;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.data.DataStore;
import ru.exceptions.InputCheckException;
import ru.wnc.documents.DocVars;
import ru.wnc.documents.DocumentTypes;

public class StoVars extends DocVars {
	
	private double[] studHoleDiams;
	private Elements columns;
	private double difBetwDiamOfStudAndDiamOfHole;
	
	private static final Logger LOG = LoggerFactory.getLogger(StoVars.class);
	
	public StoVars() {
		type = DocumentTypes.STO;
	}

	@Override
	protected void setDataFromColsToDataStore(Elements cols) {
		columns = cols;
		difBetwDiamOfStudAndDiamOfHole = parseDouble(cols.get(23).text());
		setStudHoleDiams();
		DataStore.setSheetThck(parseDouble(cols.get(2).text()));
		DataStore.setSegmPruning(parseDouble(cols.get(3).text()));
		DataStore.setWedgeThck(parseDouble(cols.get(4).text()));
		DataStore.setWedgeGap(parseDouble(cols.get(5).text()));
		DataStore.setWedgeAngleTop(parseDouble(cols.get(6).text()));
		DataStore.setWedgeAngleBottom(parseDouble(cols.get(7).text()));
		DataStore.setRoundAtBottomOfSlot(parseDouble(cols.get(8).text()));
		DataStore.setRoundWedgeOfSlot(parseDouble(cols.get(9).text()));
		DataStore.setRoundAtTopOfSlot(parseDouble(cols.get(10).text()));
		DataStore.setMarkRound(parseDouble(cols.get(11).text()));
		DataStore.setMarkRadius(parseDouble(cols.get(12).text()));
		DataStore.setMarkShift(parseDouble(cols.get(13).text()));
		setToMapByIndex(14, DataStore.getScrew010203NearestPoints());
		setToMapByIndex(15, DataStore.getScrew020304NearestPoints());
		setRadsToMapByIndex(16, DataStore.getScrew01ExtRads());
		setRadsToMapByIndex(17, DataStore.getScrew01MidRads());
		setRadsToMapByIndex(18, DataStore.getScrew02ExtRads());
		setIntRadsToMapByIndex(19, DataStore.getScrew0304IntRads());
		setToMapByIndex(20, DataStore.getScrew0102FarTopPoints());
		setToMapByIndex(21, DataStore.getScrew0102FarBottomPoints());
		DataStore.setDifBetwDiamOfStudAndDiamOfHole(parseDouble(cols.get(23).text()));
		DataStore.setScrew05Wdth(parseDouble(cols.get(25).text()));
		DataStore.setScrew05Hght(parseDouble(cols.get(26).text()));
		DataStore.setScrew07Wdth(parseDouble(cols.get(27).text()));
		DataStore.setScrew07Hght(parseDouble(cols.get(28).text()));
		DataStore.setScrew07Gap(parseDouble(cols.get(29).text()));
		LOG.info("Data assigned in Sto");
	}
	private void setIntRadsToMapByIndex(int index, Map<Double, Double> map) {
		double[] array = getArray(index);
		for (int i = 0; i < array.length; i++) {
			map.put(studHoleDiams[i], DataStore.getExtDiam()/2 - array[i]);
		}
	}
	private void setRadsToMapByIndex(int index, Map<Double, Double> map) {
		double[] array = getArray(index);
		for (int i = 0; i < array.length; i++) {
			map.put(studHoleDiams[i], array[i] + DataStore.getExtDiam()/2);
		}
	}
	
	private void setToMapByIndex(int index, Map<Double, Double> map) {
		double[] array = getArray(index);
		for (int i = 0; i < array.length; i++) {
			map.put(studHoleDiams[i], array[i]);
		}
	}

	private double[] getArray(int index) {
		double[] list = parseAndSortStringArray(columns.get(index).text().split(";"));
		if (studHoleDiams.length != list.length) {
			throw new InputCheckException("studDiams.length != list.length");
		}
		return list;
	}
	private void setStudHoleDiams() {
		double[] studDiams = parseAndSortStringArray(columns.get(22).text().split(";"));
		for (int i = 0; i < studDiams.length; i++) {
			studDiams[i] = studDiams[i] + difBetwDiamOfStudAndDiamOfHole;
		}
		studHoleDiams = studDiams;
	}
	private double[] parseAndSortStringArray(String[] strings) {
		double[] doubleAr = new double[strings.length];
		for (int i = 0; i < strings.length; i++) {
			doubleAr[i] = parseDouble(strings[i]);
		}
		Arrays.sort(doubleAr);
		return doubleAr;
	}
}
