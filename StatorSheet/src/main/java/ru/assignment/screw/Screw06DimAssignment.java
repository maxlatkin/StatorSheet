package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class Screw06DimAssignment implements ScrewDimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw06DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			String sheetSectionName = "SHEET";
			((Dimension)currSolid.GetFeatureByName(sheetSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getExtDiam());
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_06", e);
		}
	}
}
