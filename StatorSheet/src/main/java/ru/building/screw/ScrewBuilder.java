package ru.building.screw;

import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class ScrewBuilder {
	private ScrewBuilder() {
	    throw new IllegalStateException("Utility class");
	}
	public static void buildScrew(Solid currSolid) {
		int typeOfScrew = DataStore.getTypeOfScrew();
		if (typeOfScrew / 10 == 0) {
			ScrewFactory.getScrew(typeOfScrew).build(currSolid);
		} else {
			int firstTypeOfScrew = typeOfScrew / 10;
			ScrewFactory.getScrew(firstTypeOfScrew).build(currSolid);
			int secondTypeOfScrew = typeOfScrew % 10;
			ScrewFactory.getScrew(secondTypeOfScrew).build(currSolid);
		}
	}
}
