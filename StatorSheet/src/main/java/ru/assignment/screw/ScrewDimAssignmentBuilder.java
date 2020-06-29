package ru.assignment.screw;

import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import java.lang.*;
import ru.data.calculation.ScrewShift;
import ru.parameters.setting.CalculatedParams;
import ru.parameters.setting.screw.ScrewParamsFactory;

public class ScrewDimAssignmentBuilder {
	private ScrewDimAssignmentBuilder() {
	    throw new IllegalStateException("Utility class");
	}
	public static void buildScrew(Solid currSolid) {
		int typeOfScrew = DataStore.getTypeOfScrew();
		if (typeOfScrew / 10 == 0) {
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid, typeOfScrew).assign();
		} else {
			int firstTypeOfScrew = typeOfScrew / 10;
			ScrewParamsFactory.getParams(firstTypeOfScrew).setValue(currSolid);
			ScrewShift.getInstance(firstTypeOfScrew).calculate();
			CalculatedParams.getInstance().setValue(currSolid);
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid, firstTypeOfScrew).assign();
			
			int secondTypeOfScrew = typeOfScrew % 10;
			ScrewParamsFactory.getParams(secondTypeOfScrew).setValue(currSolid);
			ScrewShift.getInstance(secondTypeOfScrew).calculate();
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid, secondTypeOfScrew).assign();
		}
		
	}
}
