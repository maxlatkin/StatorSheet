package ru.ruselprom.assignment.screw;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.assignment.DimAssignment;
import ru.ruselprom.data.DataStore;
import ru.ruselprom.general.ModelFeat;

public class Screw05DimAssignment extends DimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw05DimAssignment.class);
	private double screwShift;
	
	public Screw05DimAssignment(Solid currSolid, double screwShift) {
		super(currSolid);
		this.screwShift = screwShift;
	}

	@Override
	public void assign() {
		try {
			Map<Integer, Double> screwSolidIndexAndValue = new HashMap<>();
			screwSolidIndexAndValue.put(0, DataStore.getScrew05Wdth());
			screwSolidIndexAndValue.put(1, DataStore.getScrew05Hght());
			screwSolidIndexAndValue.put(2, DataStore.getExtDiam()/2);
			screwSolidIndexAndValue.put(3, screwShift);
			setArrayOfDimValue(ModelFeat.SCREW_05_HOLE, screwSolidIndexAndValue);
			setDimValue(ModelFeat.MARK, 2, DataStore.getMarkShift() + DataStore.getScrew05Hght());
			LOG.info("Dimensions for the Screw05 assigned");
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_05", e);
		}
	}
}
