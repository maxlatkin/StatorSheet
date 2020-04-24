package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.ruselprom.fet.info.Info;

public class Screw01DimAssignment implements ScrewDimAssignment {
	
	private static final Logger LOG = LoggerFactory.getLogger(Screw01DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				String screwSolid = "SCREW_01_SOLID_2";
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(7)).SetDimValue(DataStore.getExtDiam()/2);
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.
								getScrew01ExtRads().get(DataStore.getScrewDiam()));
				//Mid Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.
								getScrew01MidRads().get(DataStore.getScrewDiam()));
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Nearest Point
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.
								getScrew01NearestPoints().get(DataStore.getScrewDiam()));
				//Far Top Point
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.
								getScrew01FarTopPoints().get(DataStore.getScrewDiam()));
				//Fat Bottom Point
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew01FarBottomPoints().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(6)).SetDimValue(DataStore.getScrewShift());
				String screwHole = "SCREW_01_HOLE_2";
				//Mid Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.
								getScrew01MidRads().get(DataStore.getScrewDiam()));
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getScrewShift());
			} else if (DataStore.getScrewQty() == 4) {
				String screwSolid = "SCREW_01_SOLID_4";
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(14)).SetDimValue(DataStore.getExtDiam()/2);
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.
								getScrew01ExtRads().get(DataStore.getScrewDiam()));
				//Mid Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.
								getScrew01MidRads().get(DataStore.getScrewDiam()));
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Nearest Point
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.
								getScrew01NearestPoints().get(DataStore.getScrewDiam()));
				//Far Top Point
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.
								getScrew01FarTopPoints().get(DataStore.getScrewDiam()));
				//Fat Bottom Point
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew01FarBottomPoints().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(6)).SetDimValue(DataStore.getScrewShift());
				String screwHole = "SCREW_01_HOLE_4";
				//Mid Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.
								getScrew01MidRads().get(DataStore.getScrewDiam()));
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.getScrewShift());
			}
			Info.getDimensionsInfoIn("SCREW_01_SOLID_4", currSolid);
			Info.getDimensionsInfoIn("SCREW_01_HOLE_4", currSolid);
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_01", e);
		}
	}
}
