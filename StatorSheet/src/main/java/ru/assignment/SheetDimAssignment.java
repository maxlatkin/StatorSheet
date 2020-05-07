package ru.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class SheetDimAssignment {
	private static final Logger LOG = LoggerFactory.getLogger(SheetDimAssignment.class);
	private static Solid currSolid;
	
	private SheetDimAssignment() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static void assign(Solid solid) {
		try {
			currSolid = solid;
			String sheetSectionName = "SHEET";
			setDimValue(sheetSectionName, 0, DataStore.getExtDiam());
			setDimValue(sheetSectionName, 1, DataStore.getIntDiam());
			LOG.info("Assigned dimensions for the {}", sheetSectionName);
			
			if (DataStore.getSegmQty() != 1) {
				String transformSectionName = "TRANSFORM_CORE_TO_SHEET";
				setDimValue(transformSectionName, 0, DataStore.getExtDiam() + 100);
				setDimValue(transformSectionName, 1, DataStore.getSegmPruning());
				setDimValue(transformSectionName, 2, 360.0 / DataStore.getSegmQty());
				LOG.info("Assigned dimensions for the {}", transformSectionName);
			}
			
			double[] slotDimValue = {DataStore.getSlotHghtToWdg(), DataStore.getWedgeThck(), DataStore.getWedgeGap(),
					DataStore.getWedgeAngleTop(), DataStore.getWedgeAngleBottom(), DataStore.getSlotWdth()};
			if (DataStore.isSlotWithRound()) {
				String slotWithRoundSectionName = "SLOT_WITH_ROUND";
				int[] slotWithRoundDimIndex = {0, 1, 2, 3, 4, 9};
				for (int i = 0; i < slotWithRoundDimIndex.length; i++) {
					setDimValue(slotWithRoundSectionName, slotWithRoundDimIndex[i], slotDimValue[i]);
				}
				if (DataStore.getSegmQty() != 1) {
					setDimValue(slotWithRoundSectionName, 7, 360.0 / DataStore.getSegmQty() / 2);
				} else {
					setDimValue(slotWithRoundSectionName, 7, 0);
				}
				setDimValue(slotWithRoundSectionName, 8, DataStore.getSlotRoundBottom());
				setDimValue(slotWithRoundSectionName, 10, DataStore.getSlotRoundTop());
				LOG.info("Assigned dimensions for the {}", slotWithRoundSectionName);
			} else {
				String slotWithoutRoundSectionName = "SLOT_WITHOUT_ROUND";
				int[] slotWithoutRoundDimIndex = {0, 1, 2, 3, 4, 5};
				for (int i = 0; i < slotWithoutRoundDimIndex.length; i++) {
					setDimValue(slotWithoutRoundSectionName, slotWithoutRoundDimIndex[i], slotDimValue[i]);
				}
				if (DataStore.getSegmQty() != 1) {
					setDimValue(slotWithoutRoundSectionName, 8, 360.0 / DataStore.getSegmQty() / 2);
				} else {
					setDimValue(slotWithoutRoundSectionName, 8, 0);
				}
				LOG.info("Assigned dimensions for the {}", slotWithoutRoundSectionName);
			}
			
			String markSectionName = "MARK";
			setDimValue(markSectionName, 0, DataStore.getMarkRound());
			setDimValue(markSectionName, 1, DataStore.getMarkRadius());
			LOG.info("Assigned dimensions for the {}", markSectionName);
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Failed to assing dimensions!", e);
		}
	}
	
	private static void setDimValue(String sectionName, int index, double value) throws jxthrowable {
		((Dimension)currSolid.GetFeatureByName(sectionName).
				ListSubItems(ModelItemType.ITEM_DIMENSION).get(index)).SetDimValue(value);
	}
}
