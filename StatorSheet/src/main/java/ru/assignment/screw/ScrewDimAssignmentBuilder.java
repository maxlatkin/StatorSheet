package ru.assignment.screw;

import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class ScrewDimAssignmentBuilder {
	private ScrewDimAssignmentBuilder() {
	    throw new IllegalStateException("Utility class");
	}
	public static void assign(Solid currSolid) {
		int typeOfScrew = DataStore.getTypeOfScrew();
		if (typeOfScrew / 10 == 0) {
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid, typeOfScrew, DataStore.getScrewShift()).assign();
		} else {
			int firstTypeOfScrew = typeOfScrew / 10;
			int secondTypeOfScrew = typeOfScrew % 10;
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid,firstTypeOfScrew, 
					DataStore.getScrewShift()).assign();
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid, secondTypeOfScrew, 
					DataStore.getSecondScrewShift()).assign();
		}
	}
}
