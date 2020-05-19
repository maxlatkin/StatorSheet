package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.DimAssignment;
import ru.data.DataStore;

public class ScrewDimAssignmentFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewDimAssignmentFactory.class);
	
	private ScrewDimAssignmentFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static DimAssignment getScrewDimAssignment(Solid currSolid) {
		DimAssignment screwDimAssignment = null;
		switch (DataStore.getTypeOfScrew()) {
		case 1:
			screwDimAssignment = new Screw01DimAssignment(currSolid);
			break;
		case 2:
			screwDimAssignment = new Screw02DimAssignment(currSolid);
			break;
		case 3:
			screwDimAssignment = new Screw03And04DimAssignment(currSolid);
			break;
		case 5:
			screwDimAssignment = new Screw05DimAssignment(currSolid);
			break;
		case 6:
			screwDimAssignment = new Screw06DimAssignment(currSolid);
			break;
		case 7:
			screwDimAssignment = new Screw07DimAssignment(currSolid);
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
