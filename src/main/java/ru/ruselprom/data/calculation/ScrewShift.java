package ru.ruselprom.data.calculation;

import static ru.ruselprom.data.DataStore.getScrewQty;
import static ru.ruselprom.data.DataStore.getSegmQty;
import static ru.ruselprom.data.DataStore.isScrew04Exist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ruselprom.exceptions.InvalidInputException;

public class ScrewShift implements Calculable {

	private static final Logger LOG = LoggerFactory.getLogger(ScrewShift.class);
	private static ScrewShift instance;
	private int typeOfScrew;
	
    private ScrewShift(){}
    public static ScrewShift getInstance(int typeOfScrew) {
        if (instance == null) {
            instance = new ScrewShift();
        }
        instance.typeOfScrew = typeOfScrew;
        return instance;
    }
	
	@Override
	public double calculate() {
		double screwShift = getScrewShiftByType(typeOfScrew);
		LOG.info("ScrewShift was calculated: {}", screwShift);
		return screwShift;
	}
	
	private double getScrewShiftByType(int typeOfScrew) {
		switch (typeOfScrew) {
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
		throw new IllegalArgumentException("Unknown type of screw:" + typeOfScrew);
	}
 
	private double getShiftForScrew0102() {
		int screwQty = getScrewQty();
		if (screwQty == 2) {
            return 360.0 / getSegmQty() / 4;
        } else if (screwQty == 4) {
            return  360.0 / (getSegmQty() * screwQty) * 1.5;
        }
        throw new InvalidInputException("Wrong number of screw:" + screwQty);
	}
	
	private double getShiftForScrew03() {
		int screwQty = getScrewQty();
		if (screwQty == 2) {
            if (isScrew04Exist()) return 360.0 / getSegmQty() / 3;
            else return 360.0 / getSegmQty() / 4;
        } else if (screwQty == 4) {
            if (isScrew04Exist()) return 360.0 / (getSegmQty() * (screwQty + 2)) * 2.5;
            else return 360.0 / (getSegmQty() * screwQty) * 1.5;
        }
        throw new InvalidInputException("Wrong number of screw:" + screwQty);
	}
	
	private double getShiftForScrew050607() {
		if (getSegmQty() == 1) return 0;
		else return 360.0 / getSegmQty() / 2;
	}
}
