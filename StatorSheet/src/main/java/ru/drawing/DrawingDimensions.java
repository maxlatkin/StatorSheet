package ru.drawing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.wfc.wfcDimension.WDimension;

import ru.data.DataStore;
import ru.ruselprom.templates.TemplateModel;

public class DrawingDimensions {
	
	private static final Logger LOG = LoggerFactory.getLogger(DrawingDimensions.class);
	private Model drw;
	
	public void set() {
		try {
			TemplateModel templateDrw = new TemplateModel(DataStore.getTempDrw(), DataStore.getModelsPath());
			drw = templateDrw.retrieve();
			getDimById(0).SetOverrideValue(DataStore.getRoundWedgeOfSlot());
			getDimById(1).SetOverrideValue(DataStore.getRoundWedgeOfSlot());
			getDimById(4).SetOverrideValue(DataStore.getRoundAtTopOfSlot());
			getDimById(5).SetOverrideValue(DataStore.getRoundAtTopOfSlot());
			getDimById(14).SetOverrideValue(DataStore.getRoundAtBottomOfSlot());
			LOG.info("Dimensions are set");
		} catch (jxthrowable e) {
			LOG.error("Setting dimensions error", e);
		}
	}
	private WDimension getDimById(int id) throws jxthrowable {
		return (WDimension)drw.GetItemById(ModelItemType.ITEM_DIMENSION, id);
	}
}
