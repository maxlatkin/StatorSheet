package ru.building.screw;

import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.general.ModelFeat;

public class ScrewBuilder {
	private ScrewBuilder() {
	    throw new IllegalStateException("Utility class");
	}
	public static void build(Solid currSolid) {
		int typeOfScrew = DataStore.getTypeOfScrew();
		if (typeOfScrew / 10 == 0) {
			ScrewFactory.getScrew(currSolid, typeOfScrew).build();
			ModelFeat.deleteScrewExceptTypes(currSolid, typeOfScrew);
		} else {
			int firstTypeOfScrew = typeOfScrew / 10;
			int secondTypeOfScrew = typeOfScrew % 10;
			ScrewFactory.getScrew(currSolid, firstTypeOfScrew).build();
			ScrewFactory.getScrew(currSolid, secondTypeOfScrew).build();
			ModelFeat.deleteScrewExceptTypes(currSolid, firstTypeOfScrew, secondTypeOfScrew);
		}
	}
}
