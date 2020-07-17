package ru.ruselprom.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.assignment.DimAssignment;

public class ScrewDimAssignmentFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewDimAssignmentFactory.class);
	
	private ScrewDimAssignmentFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static DimAssignment getScrewDimAssignment(Solid currSolid, int typeOfScrew, double screwShift) {
		DimAssignment screwDimAssignment = null;
		switch (typeOfScrew) {
		case 1:
			screwDimAssignment = new Screw01DimAssignment(currSolid, screwShift);
			break;
		case 2:
			screwDimAssignment = new Screw02DimAssignment(currSolid, screwShift);
			break;
		case 3:
			screwDimAssignment = new Screw03And04DimAssignment(currSolid, screwShift);
			break;
		case 5:
			screwDimAssignment = new Screw05DimAssignment(currSolid, screwShift);
			break;
		case 6:
			screwDimAssignment = new Screw06DimAssignment(currSolid, screwShift);
			break;
		case 7:
			screwDimAssignment = new Screw07DimAssignment(currSolid, screwShift);
			break;
		default:
			IllegalArgumentException e = new IllegalArgumentException("Unknown type of screw!");
			LOG.error("ScrewDimAssignmentFactory - incorrect screw type entered", e);
			throw e;
		}
		LOG.info("ScrewDimAssignment got from ScrewDimAssignmentFactory");
		return screwDimAssignment;
	}
}
