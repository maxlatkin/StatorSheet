package ru.data.calculation;

import ru.data.DataStore;

public class ScrewShift implements Calculated {

	private static ScrewShift instance;
    private ScrewShift(){}
    public static ScrewShift getInstance() {
        if (instance == null) {
            instance = new ScrewShift();
        }
        return instance;
    }
	
	@Override
	public void calculate() {
		if (DataStore.getTypeOfScrew() == 1 || DataStore.getTypeOfScrew() == 2) {
			if (DataStore.getScrewQty() == 2) {
				DataStore.setScrewShift(360.0 / DataStore.getSegmQty() / 4);
			} else if (DataStore.getScrewQty() == 4) {
				DataStore.setScrewShift(360.0 / (DataStore.getSegmQty() * DataStore.getScrewQty()) * 1.5);
			}
		} else if (DataStore.getTypeOfScrew() == 3) {
			if (DataStore.getScrewQty() == 2 && DataStore.isScrew04Exist()) {
				DataStore.setScrewShift(360.0 / DataStore.getSegmQty() / 3);
			} else if (DataStore.getScrewQty() == 2 && !DataStore.isScrew04Exist()) {
				DataStore.setScrewShift(360.0 / DataStore.getSegmQty() / 4);
			} else if (DataStore.getScrewQty() == 4 && DataStore.isScrew04Exist()) {
				DataStore.setScrewShift(360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)) * 2.5);
			} else if (DataStore.getScrewQty() == 4 && !DataStore.isScrew04Exist()) {
				DataStore.setScrewShift(360.0 / (DataStore.getSegmQty() * DataStore.getScrewQty()) * 1.5);
			}
		} else if (DataStore.getTypeOfScrew() == 5 || DataStore.getTypeOfScrew() == 6 || DataStore.getTypeOfScrew() == 7) {
			if (DataStore.getSegmQty() == 1) {
				DataStore.setScrewShift(0);
			} else {
				DataStore.setScrewShift(360.0 / DataStore.getSegmQty() / 2);
			}
		} else {
			throw new IllegalArgumentException("Unknown type of Screw");
		}
	}
 
}
