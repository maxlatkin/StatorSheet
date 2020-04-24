package ru.building.screw;

import ru.data.DataStore;

public class ScrewFactory {
	
	private ScrewFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Screw getScrew() {
		Screw screw = null;
		if (DataStore.getTypeOfScrew() == 1) {
			screw = new Screw01();
		} else if (DataStore.getTypeOfScrew() == 2) {
			screw = new Screw02();
		} else if (DataStore.getTypeOfScrew() == 3) {
			screw = new Screw03And04();
		} else if (DataStore.getTypeOfScrew() == 5) {
			screw = new Screw05();
		} else if (DataStore.getTypeOfScrew() == 6) {
			screw = new Screw06();
		} else if (DataStore.getTypeOfScrew() == 7) {
			screw = new Screw07();
		}
		return screw;
	}
}
