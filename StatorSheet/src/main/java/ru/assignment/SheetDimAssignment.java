package ru.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;

public class SheetDimAssignment {
	public static final Logger LOG = LoggerFactory.getLogger(SheetDimAssignment.class);
	
	private SheetDimAssignment() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static void assign(Solid currSolid) {
		try {
			String sheetSectionName = "SHEET";
			((Dimension)currSolid.GetFeatureByName(sheetSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getExtDiam());
			((Dimension)currSolid.GetFeatureByName(sheetSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getIntDiam());
			LOG.info("Assigned dimensions for the {}", sheetSectionName);
			String transformSectionName = "TRANSFORM_CORE_TO_SHEET";
			((Dimension)currSolid.GetFeatureByName(transformSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getExtDiam() + 70);
			((Dimension)currSolid.GetFeatureByName(transformSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getSegmPruning());
			((Dimension)currSolid.GetFeatureByName(transformSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(360.0 / DataStore.getSegmQty());
			LOG.info("Assigned dimensions for the {}", transformSectionName);
			String slotWithoutRoundSectionName = "SLOT_WITHOUT_ROUND";
			String slotWithRoundSectionName = "SLOT_WITH_ROUND";
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getSlotHghtToWdg());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getSlotHghtToWdg());
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getWedgeThck());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getWedgeThck());
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getWedgeGap());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.getWedgeGap());
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.getWedgeAngleTop());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(3)).SetDimValue(DataStore.getWedgeAngleTop());
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.getWedgeAngleBottom());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(4)).SetDimValue(DataStore.getWedgeAngleBottom());
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.getSlotWdth());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(9)).SetDimValue(DataStore.getSlotWdth());
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(8)).SetDimValue(DataStore.getSlotAngle());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(7)).SetDimValue(DataStore.getSlotAngle());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(8)).SetDimValue(DataStore.getSlotRoundBottom());
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(10)).SetDimValue(DataStore.getSlotRoundTop());
			LOG.info("Assigned dimensions for the {} and {}", slotWithoutRoundSectionName, slotWithRoundSectionName);
			String markSectionName = "MARK";
			((Dimension)currSolid.GetFeatureByName(markSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.getMarkRound());
			((Dimension)currSolid.GetFeatureByName(markSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.getMarkRadius());
			LOG.info("Assigned dimensions for the {}", markSectionName);
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Failed to assing dimensions!", e);
		}
	}
}
