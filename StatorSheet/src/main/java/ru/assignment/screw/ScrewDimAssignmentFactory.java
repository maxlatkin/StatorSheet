package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.externaldata.DataStore;

public class ScrewDimAssignmentFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewDimAssignmentFactory.class);
	
	private ScrewDimAssignmentFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static ScrewDimAssignment getScrewDimAssignment() {
		ScrewDimAssignment screwDimAssignment = null;
		switch (DataStore.getTypeOfScrew()) {
		case 1:
			screwDimAssignment = new Screw01DimAssignment();
			break;
		case 2:
			screwDimAssignment = new Screw02DimAssignment();
			break;
		case 3:
			screwDimAssignment = new Screw03And04DimAssignment();
			break;
		case 5:
			screwDimAssignment = new Screw05DimAssignment();
			break;
		case 6:
			screwDimAssignment = new Screw06DimAssignment();
			break;
		case 7:
			screwDimAssignment = new Screw07DimAssignment();
			break;
		default:
			LOG.error("ScrewDimAssignmentFactory - incorrect screw type entered");
			break;
		}
		LOG.info("ScrewDimAssignment got from ScrewDimAssignmentFactory");
		return screwDimAssignment;
	}
}
