package ru.assignment.screw;

import ru.data.DataStore;

public class ScrewDimAssignmentFactory {
	
	private ScrewDimAssignmentFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static ScrewDimAssignment getScrewDimAssignment() {
		ScrewDimAssignment screwDimAssignment = null;
		if (DataStore.getTypeOfScrew() == 1) {
			screwDimAssignment = new Screw01DimAssignment();
		} else if (DataStore.getTypeOfScrew() == 2) {
			screwDimAssignment = new Screw02DimAssignment();
		} else if (DataStore.getTypeOfScrew() == 3) {
			screwDimAssignment = new Screw03And04DimAssignment();
		} else if (DataStore.getTypeOfScrew() == 5) {
			screwDimAssignment = new Screw05DimAssignment();
		} else if (DataStore.getTypeOfScrew() == 6) {
			screwDimAssignment = new Screw06DimAssignment();
		} else if (DataStore.getTypeOfScrew() == 7) {
			screwDimAssignment = new Screw07DimAssignment();
		}
		return screwDimAssignment;
	}
}
