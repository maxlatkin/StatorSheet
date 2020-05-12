package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;

public class Screw05DimAssignment implements ScrewDimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw05DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			String screwHole = "SCREW_05_HOLE";
			//Screw Wdth
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrew05Wdth());
			//Screw Hght
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getScrew05Hght());
			//Rad
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getExtDiam()/2);
			//Screw Shift
			((Dimension)currSolid.GetFeatureByName(screwHole).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(90 - DataStore.getScrewShift());
			//Mark Shift
			((Dimension)currSolid.GetFeatureByName("MARK").
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getMarkShift() + 
							DataStore.getScrew05Hght());
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_05", e);
		}
	}
}
