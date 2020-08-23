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

public class Screw03And04DimAssignment extends DimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04DimAssignment.class);
	private double screwShift;
	
	public Screw03And04DimAssignment(Solid currSolid, double screwShift) {
		super(currSolid);
		this.screwShift = screwShift;
	}

	@Override
	public void assign() {
		try {
			if (DataStore.getScrewQty() == 2) {
				setArrayOfDimValue(ModelFeat.SCREW_03_HOLE_2, getCommonScrew03IndexAndValue());
				setDimValue(ModelFeat.SCREW_03_HOLE_2, 5, screwShift);
			} else if (DataStore.getScrewQty() == 4) {
				setArrayOfDimValue(ModelFeat.SCREW_03_HOLE_4, getCommonScrew03IndexAndValue());
				setDimValue(ModelFeat.SCREW_03_HOLE_4, 5, screwShift);
				if (DataStore.isScrew04Exist()) {
					setDimValue(ModelFeat.SCREW_03_HOLE_4, 10, screwShift - 
							(2 * screwShift - (360.0 / DataStore.getSegmQty()) * 2 / 3));
				} else {
					setDimValue(ModelFeat.SCREW_03_HOLE_4, 10, screwShift - 
							(2 * screwShift - (360.0 / DataStore.getSegmQty()) / 2));
				}
			}
			
			if (DataStore.isScrew04Exist()) {
				setArrayOfDimValue(ModelFeat.SCREW_04_HOLE, getScrew04IndexAndValue());
				if(DataStore.isScrew04Exist() && DataStore.getScrewQty() == 4) {
					setDimValue(ModelFeat.SCREW_04_HOLE, 4, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)) * 0.5);
				}
			}
			setDimValue(ModelFeat.MARK, 2, DataStore.getMarkShift());
			LOG.info("Dimensions for the Screw03and04 assigned");
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_03And04", e);
		}
	}

	private Map<Integer, Double> getScrew04IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		screwHoleIndexAndValue.put(0, DataStore.getStudHoleDiam());
		screwHoleIndexAndValue.put(1, DataStore.getScrew0304IntRads().get(DataStore.getStudHoleDiam()));
		screwHoleIndexAndValue.put(2, DataStore.getExtDiam()/2);
		screwHoleIndexAndValue.put(3, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()));
		return screwHoleIndexAndValue;
	}

	private Map<Integer, Double> getCommonScrew03IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		screwHoleIndexAndValue.put(0, DataStore.getScrew0304IntRads().get(DataStore.getStudHoleDiam()));
		screwHoleIndexAndValue.put(1, DataStore.getExtDiam()/2);
		screwHoleIndexAndValue.put(2, DataStore.getStudHoleDiam());
		screwHoleIndexAndValue.put(3, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()));
		screwHoleIndexAndValue.put(4, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()));
		return screwHoleIndexAndValue;
	}
}
