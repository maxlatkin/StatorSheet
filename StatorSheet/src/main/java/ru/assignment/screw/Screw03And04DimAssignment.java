package ru.assignment.screw;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.DimAssignment;
import ru.externaldata.DataStore;
import ru.general.ModelFeat;

public class Screw03And04DimAssignment extends DimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04DimAssignment.class);
	
	public Screw03And04DimAssignment(Solid currSolid) {
		super(currSolid);
	}
	
	@Override
	public void assign() {
		try {
			if (DataStore.getScrewQty() == 2) {
				setArrayOfDimValue(ModelFeat.SCREW_03_HOLE_2, getCommonScrew03IndexAndValue());
				if (DataStore.isScrew04Exist()) {
					setDimValue(ModelFeat.SCREW_03_HOLE_2, 5, 360.0 / DataStore.getSegmQty() / 3);
				} else {
					setDimValue(ModelFeat.SCREW_03_HOLE_2, 5, getQuarterSegmAngle());
				}
			} else if (DataStore.getScrewQty() == 4) {
				setArrayOfDimValue(ModelFeat.SCREW_03_HOLE_4, getCommonScrew03IndexAndValue());
				if (DataStore.isScrew04Exist()) {
					setDimValue(ModelFeat.SCREW_03_HOLE_4, 5, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)) * 2.5);
					setDimValue(ModelFeat.SCREW_03_HOLE_4, 10, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)) * 1.5);
				} else {
					setDimValue(ModelFeat.SCREW_03_HOLE_4, 5, getOneAndHalfStepScrew());
					setDimValue(ModelFeat.SCREW_03_HOLE_4, 10, getHalfStepScrew());
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
		screwHoleIndexAndValue.put(0, DataStore.getScrewDiam());
		screwHoleIndexAndValue.put(1, DataStore.getScrew03IntRads().get(DataStore.getScrewDiam()));
		screwHoleIndexAndValue.put(2, DataStore.getExtDiam()/2);
		screwHoleIndexAndValue.put(3, DataStore.getScrew0203NearestPoints().get(DataStore.getScrewDiam()));
		return screwHoleIndexAndValue;
	}

	private Map<Integer, Double> getCommonScrew03IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		screwHoleIndexAndValue.put(0, DataStore.getScrew03IntRads().get(DataStore.getScrewDiam()));
		screwHoleIndexAndValue.put(1, DataStore.getExtDiam()/2);
		screwHoleIndexAndValue.put(2, DataStore.getScrewDiam());
		screwHoleIndexAndValue.put(3, DataStore.getScrew010203NearestPoints().get(DataStore.getScrewDiam()));
		screwHoleIndexAndValue.put(4, DataStore.getScrew0203NearestPoints().get(DataStore.getScrewDiam()));
		return screwHoleIndexAndValue;
	}
}
