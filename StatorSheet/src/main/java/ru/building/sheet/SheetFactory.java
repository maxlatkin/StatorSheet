package ru.building.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.general.SheetType;

public class SheetFactory {

	private static final Logger LOG = LoggerFactory.getLogger(SheetFactory.class);
	
	private SheetFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static Sheet getSheet(SheetType sheetType) {
		Sheet sheet = null;
		switch (sheetType) {
		case BASIC:
			sheet = new BasicSheet();
			break;
		case VENT:
			sheet = new VentSheet();
			break;
		default:
			IllegalArgumentException e = new IllegalArgumentException("Unknown type of sheet!");
			LOG.error("SheetFactory - incorrect sheet type entered", e);
			throw e;
		}
		LOG.info("Sheet got from SheetFactory");
		return sheet;
	}
}
