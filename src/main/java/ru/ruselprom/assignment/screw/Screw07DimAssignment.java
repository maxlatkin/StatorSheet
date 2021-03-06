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

public class Screw07DimAssignment extends DimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw07DimAssignment.class);
	private double screwShift;
	
	public Screw07DimAssignment(Solid currSolid, double screwShift) {
		super(currSolid);
		this.screwShift = screwShift;
	}

	@Override
	public void assign() {
		try {
			Map<Integer, Double> screwSolidIndexAndValue = new HashMap<>();
			screwSolidIndexAndValue.put(0, DataStore.getScrew07Wdth());
			screwSolidIndexAndValue.put(1, DataStore.getScrew07Gap() + DataStore.getScrew07Hght());
			screwSolidIndexAndValue.put(3, DataStore.getScrew07Hght());
			screwSolidIndexAndValue.put(4, DataStore.getScrew07NeckWdth());
			screwSolidIndexAndValue.put(5, DataStore.getExtDiam()/2);
			screwSolidIndexAndValue.put(6, screwShift);
			setArrayOfDimValue(ModelFeat.SCREW_07_HOLE, screwSolidIndexAndValue);
			setDimValue(ModelFeat.MARK, 2,
					DataStore.getMarkShift() + DataStore.getScrew07Hght() +	DataStore.getScrew07Gap());
			LOG.info("Dimensions for the Screw07 assigned");
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_07", e);
		}
	}
}
