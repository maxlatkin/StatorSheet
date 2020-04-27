package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class Screw02DimAssignment implements ScrewDimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw02DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				String screwSolid = "SCREW_02_SOLID_2";
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
				//Nearest Point same as in Screw 1
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.
								getScrew0102NearestPoints().get(DataStore.getScrewDiam()));
				//Second Nearest Point in Screw 2
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew02NearestPoints().get(DataStore.getScrewDiam()));
				//Far Top Point 1
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.
								getScrew01FarTopPoints().get(DataStore.getScrewDiam()));
				//Far Top Point 2
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.
								getScrew01FarTopPoints().get(DataStore.getScrewDiam()));
				//Fat Bottom Point 1
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(6)).SetDimValue(DataStore.
								getScrew01FarBottomPoints().get(DataStore.getScrewDiam()));
				//Fat Bottom Point 2
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(7)).SetDimValue(DataStore.
								getScrew01FarBottomPoints().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(8)).SetDimValue(DataStore.
								getScrew02ExtRads().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(9)).SetDimValue(DataStore.getScrewShift());
				String screwHole = "SCREW_02_HOLE_2";
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
				//Second Nearest Point in Screw 2
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.
								getScrew02NearestPoints().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew02ExtRads().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.getScrewShift());
			} else if (DataStore.getScrewQty() == 4) {
				String screwSolid = "SCREW_02_SOLID_4";
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
				//Nearest Point same as in Screw 1
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.
								getScrew0102NearestPoints().get(DataStore.getScrewDiam()));
				//Second Nearest Point in Screw 2
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew02NearestPoints().get(DataStore.getScrewDiam()));
				//Far Top Point 1
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.
								getScrew01FarTopPoints().get(DataStore.getScrewDiam()));
				//Far Top Point 2
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.
								getScrew01FarTopPoints().get(DataStore.getScrewDiam()));
				//Fat Bottom Point 1
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(6)).SetDimValue(DataStore.
								getScrew01FarBottomPoints().get(DataStore.getScrewDiam()));
				//Fat Bottom Point 2
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(7)).SetDimValue(DataStore.
								getScrew01FarBottomPoints().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(8)).SetDimValue(DataStore.
								getScrew02ExtRads().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(9)).SetDimValue(DataStore.getScrewShift());
				//Axis of symmetry for screw
				((Dimension)currSolid.GetFeatureByName(screwSolid).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(22)).SetDimValue(360.0 / DataStore.getSegmQty() / 4);
				String screwHole = "SCREW_02_HOLE_4";
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
				//Second Nearest Point in Screw 2
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.
								getScrew02NearestPoints().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew02ExtRads().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(6)).SetDimValue(DataStore.getScrewShift());
			}
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_02", e);
		}
	}
}
