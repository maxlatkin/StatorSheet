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

public class Screw01DimAssignment extends DimAssignment {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw01DimAssignment.class);

	public Screw01DimAssignment(Solid currSolid) {
		super(currSolid);
	}

	@Override
	public void assign() {
		try {
			if (DataStore.getScrewQty() == 2) {
				setArrayOfDimValue(ModelFeat.SCREW_01_SOLID_2, getScrewSolid02IndexAndValue());
				setArrayOfDimValue(ModelFeat.SCREW_01_HOLE_2, getScrewHole02IndexAndValue());
			} else if (DataStore.getScrewQty() == 4) {
				setArrayOfDimValue(ModelFeat.SCREW_01_SOLID_4, getScrewSolid04IndexAndValue());
				setArrayOfDimValue(ModelFeat.SCREW_01_HOLE_4, getScrewHole04IndexAndValue());
			}
			setDimValue(ModelFeat.MARK, 2, DataStore.getMarkShift());
			LOG.info("Dimensions for the Screw01 assigned");
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_01", e);
		}
	}

	private Map<Integer, Double> getScrewHole04IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		setCommonScrewHoleIndexAndValue(screwHoleIndexAndValue);
		screwHoleIndexAndValue.put(3, DataStore.getScrewShift());
		screwHoleIndexAndValue.put(5, DataStore.getScrewShift() - 
				(2 * DataStore.getScrewShift() - (360.0 / DataStore.getSegmQty()) / 2));
		return screwHoleIndexAndValue;
	}

	private Map<Integer, Double> getScrewHole02IndexAndValue() {
		Map<Integer, Double> screwHoleIndexAndValue = new HashMap<>();
		setCommonScrewHoleIndexAndValue(screwHoleIndexAndValue);
		screwHoleIndexAndValue.put(2, DataStore.getScrewShift());
		return screwHoleIndexAndValue;
	}

	private void setCommonScrewHoleIndexAndValue(Map<Integer, Double> screwHoleIndexAndValue) {
		screwHoleIndexAndValue.put(0, DataStore.getStudHoleDiam());
		screwHoleIndexAndValue.put(1, DataStore.getScrew01MidRads().get(DataStore.getStudHoleDiam()));
	}

	private Map<Integer, Double> getScrewSolid04IndexAndValue() {
		Map<Integer, Double> screwSolidIndexAndValue = new HashMap<>();
		screwSolidIndexAndValue.put(0, DataStore.getStudHoleDiam());                                             
		screwSolidIndexAndValue.put(1, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()));
		screwSolidIndexAndValue.put(2, DataStore.getScrew01FarTopPoints().get(DataStore.getStudHoleDiam()));     
		screwSolidIndexAndValue.put(3, DataStore.getScrew01FarBottomPoints().get(DataStore.getStudHoleDiam()));  
		screwSolidIndexAndValue.put(4, DataStore.getScrew01ExtRads().get(DataStore.getStudHoleDiam()));          
		screwSolidIndexAndValue.put(5, DataStore.getScrew01MidRads().get(DataStore.getStudHoleDiam()));          
		screwSolidIndexAndValue.put(6, DataStore.getScrewShift());     
		screwSolidIndexAndValue.put(13, DataStore.getExtDiam()/2);                                            
		screwSolidIndexAndValue.put(14, DataStore.getScrewShift() - 
				(2 * DataStore.getScrewShift() - (360.0 / DataStore.getSegmQty()) / 2));
		return screwSolidIndexAndValue;
	}


	private Map<Integer, Double> getScrewSolid02IndexAndValue() {
		Map<Integer, Double> screwSolidIndexAndValue = new HashMap<>();
		screwSolidIndexAndValue.put(0, DataStore.getScrewShift());                                   
		screwSolidIndexAndValue.put(1, DataStore.getScrew01MidRads().get(DataStore.getStudHoleDiam()));          
		screwSolidIndexAndValue.put(2, DataStore.getExtDiam()/2);                                             
		screwSolidIndexAndValue.put(3, DataStore.getStudHoleDiam());                                             
		screwSolidIndexAndValue.put(4, DataStore.getScrew01ExtRads().get(DataStore.getStudHoleDiam()));          
		screwSolidIndexAndValue.put(5, DataStore.getScrew01FarTopPoints().get(DataStore.getStudHoleDiam()));     
		screwSolidIndexAndValue.put(6, DataStore.getScrew01FarBottomPoints().get(DataStore.getStudHoleDiam()));  
		screwSolidIndexAndValue.put(7, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()));
		return screwSolidIndexAndValue;
	}
}
