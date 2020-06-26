package ru.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.data.DataStore;

public final class ScrewFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewFactory.class);
	
	private ScrewFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Screw getScrew() {
		Screw screw = null;
		switch (DataStore.getTypeOfScrew()) {
		case 1:
			screw = new Screw01();
			break;
		case 2:
			screw = new Screw02();
			break;
		case 3:
			screw = new Screw03And04();
			break;
		case 5:
			screw = new Screw05();
			break;
		case 6:
			screw = new Screw06();
			break;
		case 7:
			screw = new Screw07();
			break;
		case 37:
			screw = new Screw03And07();
			break;
		default:
			IllegalArgumentException e = new IllegalArgumentException("Unknown type of screw!");
			LOG.error("ScrewFactory - incorrect screw type entered", e);
			throw e;
		}
		LOG.info("Screw got from ScrewFactory");
		return screw;
	}
}
