package ru.assignment.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcSolid.Solid;

import ru.general.SheetType;

public class SheetDimAssignmentFactory {
	private static final Logger LOG = LoggerFactory.getLogger(SheetDimAssignmentFactory.class);
	
	private SheetDimAssignmentFactory() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static SheetDimAssignment getSheetDimAssignment(Solid currSolid, SheetType sheetType) {
		SheetDimAssignment sheetDimAssignment = null;
		switch (sheetType) {
		case BASIC:
			sheetDimAssignment = new BasicSheetDimAssignment(currSolid);
			break;
		case VENT:
			sheetDimAssignment = new VentSheetDimAssignment(currSolid);
			break;
		default:
			IllegalArgumentException e = new IllegalArgumentException("Unknown type of sheet!");
			LOG.error("SheetDimAssignmentFactory - incorrect sheet type entered", e);
			throw e;
		}
		LOG.info("SheetDimAssignment got from SheetDimAssignmentFactory");
		return sheetDimAssignment;
	}
}
