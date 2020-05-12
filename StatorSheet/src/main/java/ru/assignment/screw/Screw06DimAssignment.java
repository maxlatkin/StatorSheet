package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;

public class Screw06DimAssignment implements ScrewDimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw06DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			String screwHole = "SCREW_06_HOLE";
			//Screw Wdth
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrew06Wdth());
			//Rad
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
			//Screw Shift
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getScrewShift());
			//Mark Shift
			((Dimension)currSolid.GetFeatureByName("MARK").
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getMarkShift() + 
							DataStore.getExtDiam()/2 - Math.sqrt(Math.pow(DataStore.getExtDiam()/2, 2) - Math.
									pow(DataStore.getScrew06Wdth()/2, 2)));
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_06", e);
		}
	}
}
