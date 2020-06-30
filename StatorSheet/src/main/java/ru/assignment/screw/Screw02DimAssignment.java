package ru.assignment.screw;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.DimAssignment;
import ru.data.DataStore;
import ru.general.ModelFeat;

public class Screw02DimAssignment extends DimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw02DimAssignment.class);
	private double screwShift;

	public Screw02DimAssignment(Solid currSolid, double screwShift) {
		super(currSolid);
		this.screwShift = screwShift;
	}

	@Override
	public void assign() {
		try {
			if (DataStore.getScrewQty() == 2) {
				setArrayOfDimValue(ModelFeat.SCREW_02_SOLID_2, getScrewSolid02IndexAndValue());
				setArrayOfDimValue(ModelFeat.SCREW_02_HOLE_2, getScrewHole02IndexAndValue());
			} else if (DataStore.getScrewQty() == 4) {
				setArrayOfDimValue(ModelFeat.SCREW_02_SOLID_4, getScrewSolid04IndexAndValue());
				setArrayOfDimValue(ModelFeat.SCREW_02_HOLE_4, getScrewHole04IndexAndValue());
			}
			setDimValue(ModelFeat.MARK, 2, DataStore.getMarkShift());
			LOG.info("Dimensions for the Screw02 assigned");
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_02", e);
		}
	}

	private Map<Integer, Double> getScrewHole04IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		setCommonScrewHoleIndexAndValue(screwHoleIndexAndValue);
		screwHoleIndexAndValue.put(6, screwShift);
		screwHoleIndexAndValue.put(7, screwShift - 
				(2 * screwShift - (360.0 / DataStore.getSegmQty()) / 2));
		return screwHoleIndexAndValue;
	}

	private Map<Integer, Double> getScrewHole02IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		setCommonScrewHoleIndexAndValue(screwHoleIndexAndValue);
		screwHoleIndexAndValue.put(4, screwShift);
		return screwHoleIndexAndValue;
	}

	private void setCommonScrewHoleIndexAndValue(Map<Integer, Double> screwHoleIndexAndValue) {
		screwHoleIndexAndValue.put(0, DataStore.getStudHoleDiam());
		screwHoleIndexAndValue.put(1, DataStore.getExtDiam()/2);
		screwHoleIndexAndValue.put(2, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()));
		screwHoleIndexAndValue.put(3, DataStore.getScrew02ExtRads().get(DataStore.getStudHoleDiam()));
	}
	
	private Map<Integer, Double> getScrewSolid04IndexAndValue() {
		Map<Integer, Double> screwSolidIndexAndValue = new HashMap<>();
		setCommonScrewSolidIndexAndValue(screwSolidIndexAndValue);
		screwSolidIndexAndValue.put(9, screwShift);
		screwSolidIndexAndValue.put(22, screwShift - 
				(2 * screwShift - (360.0 / DataStore.getSegmQty()) / 2));
		return screwSolidIndexAndValue;
	}

	private Map<Integer, Double> getScrewSolid02IndexAndValue() {
		Map<Integer, Double> screwSolidIndexAndValue = new HashMap<>();
		setCommonScrewSolidIndexAndValue(screwSolidIndexAndValue);
		screwSolidIndexAndValue.put(9, screwShift);
		return screwSolidIndexAndValue;
	}

	private void setCommonScrewSolidIndexAndValue(Map<Integer, Double> screwSolidIndexAndValue) {
		screwSolidIndexAndValue.put(0, DataStore.getStudHoleDiam());
		screwSolidIndexAndValue.put(1, DataStore.getExtDiam()/2);
		screwSolidIndexAndValue.put(2, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(3, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(4, DataStore.getScrew0102FarTopPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(5, DataStore.getScrew0102FarTopPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(6, DataStore.getScrew0102FarBottomPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(7, DataStore.getScrew0102FarBottomPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(8, DataStore.getScrew02ExtRads().get(DataStore.getStudHoleDiam()));
	}
}
