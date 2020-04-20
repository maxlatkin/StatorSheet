package ru.dimensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.ruselprom.fet.info.Info;

public class DimAssignment {
	public static final Logger LOG = LoggerFactory.getLogger(DimAssignment.class);
	
	private DimAssignment() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static void assignDims() throws jxthrowable {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			Solid currSolid = (Solid)session.GetModel(DataStore.MODEL_NAME, ModelType.MDL_PART);
			
			String sheetSectionName = "SHEET";
			((Dimension)currSolid.GetFeatureByName(sheetSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.EXT_DIAM);
			((Dimension)currSolid.GetFeatureByName(sheetSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.INT_DIAM);
			LOG.info("Assigned dimensions for the {}", sheetSectionName);
			String transformSectionName = "TRANSFORM_CORE_TO_SHEET";
			((Dimension)currSolid.GetFeatureByName(transformSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.EXT_DIAM + 70);
			((Dimension)currSolid.GetFeatureByName(transformSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.SEGM_PRUNING);
			((Dimension)currSolid.GetFeatureByName(transformSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(360.0 / DataStore.SEGM_QTY);
			LOG.info("Assigned dimensions for the {}", transformSectionName);
			String slotWithoutRoundSectionName = "SLOT_WITHOUT_ROUND";
			String slotWithRoundSectionName = "SLOT_WITH_ROUND";
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.SLOT_HGHT_TO_WDG);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(0)).SetDimValue(DataStore.SLOT_HGHT_TO_WDG);
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.WEDGE_THCK);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(1)).SetDimValue(DataStore.WEDGE_THCK);
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.WEDGE_GAP);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(2)).SetDimValue(DataStore.WEDGE_GAP);
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(5)).SetDimValue(DataStore.SLOT_WDTH);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(9)).SetDimValue(DataStore.SLOT_WDTH);
			((Dimension)currSolid.GetFeatureByName(slotWithoutRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(8)).SetDimValue(DataStore.SLOT_ANGLE);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(7)).SetDimValue(DataStore.SLOT_ANGLE);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(8)).SetDimValue(DataStore.SLOT_ROUND_INT);
			((Dimension)currSolid.GetFeatureByName(slotWithRoundSectionName).
					ListSubItems(ModelItemType.ITEM_DIMENSION).get(10)).SetDimValue(DataStore.SLOT_ROUND_EXT);
			LOG.info("Assigned dimensions for the {} and {}", slotWithoutRoundSectionName, slotWithRoundSectionName);
			
			
			Info.getDimensionsInfoIn("SLOT_WITHOUT_ROUND", currSolid);
			Info.getDimensionsInfoIn("SLOT_WITH_ROUND", currSolid);
		} catch (jxthrowable e) {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			session.UIShowMessageDialog("2", null);
			LOG.error("Failed to assing dimensions!", e);
		}
	}
}
