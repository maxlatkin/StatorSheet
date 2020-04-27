package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.fet.info.Info;

public class Screw03And04DimAssignment implements ScrewDimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			Info.getDimensionsInfoIn("SCREW_03_HOLE_2", currSolid);
			Info.getDimensionsInfoIn("SCREW_03_HOLE_4", currSolid);
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_03And04", e);
		}
	}
}
