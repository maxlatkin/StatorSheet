package ru.data.calculation;

import static ru.data.DataStore.getScrewQty;
import static ru.data.DataStore.getSegmQty;
import static ru.data.DataStore.isScrew04Exist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.data.DataStore;

public class ScrewShift implements Calculable {

	private static final Logger LOG = LoggerFactory.getLogger(ScrewShift.class);
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
		try {
			double screwShift = getScrewShiftByType();
			DataStore.setScrewShift(screwShift);
			LOG.info("ScrewShift was calculated: {}", screwShift);
		} catch (IllegalArgumentException e) {
			LOG.error("Error calculating screwShift", e);
		}
	}
	
	private double getScrewShiftByType() {
		switch (DataStore.getTypeOfScrew()) {
		case 1:
			return getShiftForScrew0102();
		case 2:
			return getShiftForScrew0102();
		case 3:
			return getShiftForScrew03();
		case 5:
			return getShiftForScrew050607();
		case 6:
			return getShiftForScrew050607();
		case 7:
			return getShiftForScrew050607();
		default:
			break;	
		}
		throw new IllegalArgumentException("Unknown type of Screw");
	}
 
	private double getShiftForScrew0102() {
		if (getScrewQty() == 2) {
            return 360.0 / getSegmQty() / 4;
        } else if (getScrewQty() == 4) {
            return  360.0 / (getSegmQty() * getScrewQty()) * 1.5;
        }
        throw new IllegalArgumentException("Wrong number of screw");
	}
	
	private double getShiftForScrew03() {
		if (getScrewQty() == 2) {
            if (isScrew04Exist()) return 360.0 / getSegmQty() / 3;
            else return 360.0 / getSegmQty() / 4;
        } else if (getScrewQty() == 4) {
            if (isScrew04Exist()) return 360.0 / (getSegmQty() * (getScrewQty() + 2)) * 2.5;
            else return 360.0 / (getSegmQty() * getScrewQty()) * 1.5;
        }
        throw new IllegalArgumentException("Wrong number of screw");
	}
	
	private double getShiftForScrew050607() {
		if (getSegmQty() == 1) return 0;
		else return 360.0 / getSegmQty() / 2;
	}
}
