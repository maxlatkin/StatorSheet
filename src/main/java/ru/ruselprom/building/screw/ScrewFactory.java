package ru.ruselprom.building.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcSolid.Solid;

public final class ScrewFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewFactory.class);
	
	private ScrewFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Screw getScrew(Solid currSolid, int typeOfScrew) {
		Screw screw = null;
		switch (typeOfScrew) {
		case 1:
			screw = new Screw01(currSolid);
			break;
		case 2:
			screw = new Screw02(currSolid);
			break;
		case 3:
			screw = new Screw03And04(currSolid);
			break;
		case 5:
			screw = new Screw05(currSolid);
			break;
		case 6:
			screw = new Screw06(currSolid);
			break;
		case 7:
			screw = new Screw07(currSolid);
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
