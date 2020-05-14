package ru.assignment.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.DimAssignment;
import ru.externaldata.DataStore;

public class Screw03And04DimAssignment extends DimAssignment {

	private static final Logger LOG = LoggerFactory.getLogger(Screw03And04DimAssignment.class);
	private Solid solid;
	
	public Screw03And04DimAssignment(Solid currSolid) {
		super(currSolid);
	}
	
	@Override
	public void assign() {
		try {
			solid = currSolid;
			
			double[] screw03HoleValue = {
					DataStore.getScrew03IntRads().get(DataStore.getScrewDiam()),
					DataStore.getExtDiam()/2,
					DataStore.getScrewDiam(),
					DataStore.getScrew010203NearestPoints().get(DataStore.getScrewDiam()),
					DataStore.getScrew0203NearestPoints().get(DataStore.getScrewDiam())};
			
			if (DataStore.getScrewQty() == 2) {
				String screwHole = "SCREW_03_HOLE_2";
				setArrayOfDimValue(screwHole, screw03HoleValue);
				if (DataStore.isScrew04Exist()) {
					setDimValue(screwHole, 5, 360.0 / DataStore.getSegmQty() / 3);
				} else {
					setDimValue(screwHole, 5, 360.0 / DataStore.getSegmQty() / 4);
				}
			} else if (DataStore.getScrewQty() == 4) {
				String screwHole = "SCREW_03_HOLE_4";
				setArrayOfDimValue(screwHole, screw03HoleValue);
				//Axis of symmetry for screw // 1.5 шага, когда 1/4 сегмента; 2.5 шага, когда 1/3 сегм.
				if (DataStore.isScrew04Exist()) {
					setDimValue(screwHole, 5, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)) * 2.5);
					setDimValue(screwHole, 10, 360.0 / DataStore.getSegmQty() / 3);
				} else {
					setDimValue(screwHole, 5, 360.0 / (DataStore.getSegmQty() * DataStore.getScrewQty()) * 1.5);
					setDimValue(screwHole, 10, 360.0 / DataStore.getSegmQty() / 4);
				}
			}
			
			
			double[] screw04HoleValue = {
					DataStore.getScrewDiam(),
					DataStore.getScrew03IntRads().get(DataStore.getScrewDiam()),
					DataStore.getExtDiam()/2,
					DataStore.getScrew0203NearestPoints().get(DataStore.getScrewDiam()),
					};
			
			if (DataStore.isScrew04Exist()) {
				String screwHole = "SCREW_04_HOLE";
				setArrayOfDimValue(screwHole, screw04HoleValue);
				if(DataStore.isScrew04Exist() && DataStore.getScrewQty() == 4) {
					setDimValue(screwHole, 4, 360.0 / (DataStore.getSegmQty() * (DataStore.getScrewQty() + 2)) * 0.5);
				}
			}
			
			
			setDimValue("MARK", 2, DataStore.getMarkShift());
			
			LOG.info("Dimensions for the Screw03and04 assigned");
		} catch (jxthrowable e) {
			LOG.error("Error assigning dimensions to the Screw_03And04", e);
		}
	}

	private void setArrayOfDimValue(String featName, double[] arrayOfDimValue) throws jxthrowable {
		for (int i = 0; i < arrayOfDimValue.length; i++) {
			setDimValue(featName, i, arrayOfDimValue[i]);
		}
	}
	
	private void setDimValue(String sectionName, int index, double value) throws jxthrowable {
		((Dimension)solid.GetFeatureByName(sectionName).
				ListSubItems(ModelItemType.ITEM_DIMENSION).get(index)).SetDimValue(value);
	}
}
