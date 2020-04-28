package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class Screw03And04DimAssignment implements ScrewDimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04DimAssignment.class);
	
	@Override
	public void assign(Solid currSolid) {
		try {
			if (DataStore.getScrewQty() == 2) {
				String screwHole = "SCREW_03_HOLE_2";
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.
								getScrew03IntRads().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getScrewDiam());
				//Nearest Point same as in Screw 1
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew010203NearestPoints().get(DataStore.getScrewDiam()));
				//Second Nearest Point in Screw 3
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.
								getScrew0203NearestPoints().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.getScrewShift());
			} else if (DataStore.getScrewQty() == 4) {
				String screwHole = "SCREW_03_HOLE_4";
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.
								getScrew03IntRads().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getExtDiam()/2);
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getScrewDiam());
				//Nearest Point same as in Screw 1
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew010203NearestPoints().get(DataStore.getScrewDiam()));
				//Second Nearest Point in Screw 3
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.
								getScrew0203NearestPoints().get(DataStore.getScrewDiam()));
				//Screw Shift
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.getScrewShift());
				//Axis of symmetry for screw
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(10)).SetDimValue(360.0 / DataStore.getSegmQty() / 4);
			}
			if (DataStore.isScrew04Exist()) {
				String screwHole = "SCREW_04_HOLE";
				//Screw Diam
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getScrewDiam());
				//Int Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.
								getScrew03IntRads().get(DataStore.getScrewDiam()));
				//Ext Rad
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getExtDiam()/2);
				//Point 
				((Dimension)currSolid.GetFeatureByName(screwHole).
						ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.
								getScrew0203NearestPoints().get(DataStore.getScrewDiam()));
			}
			((Dimension)currSolid.GetFeatureByName("MARK").
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getMarkShift());
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_03And04", e);
		}
	}
}
