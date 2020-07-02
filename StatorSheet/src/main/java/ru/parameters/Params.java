package ru.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.pfc.pfcModel.Model;

import ru.data.DataStore;
import ru.general.SheetType;
import ru.parameters.setting.AuxiliaryParams;
import ru.parameters.setting.CalculatedParams;
import ru.parameters.setting.SheetParams;
import ru.parameters.setting.SlotParams;
import ru.parameters.setting.screw.ScrewParamsFactory;

public final class Params {
	
	private static final Logger LOG = LoggerFactory.getLogger(Params.class);
	
	private Params() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void setAllParams(Model currModel, SheetType sheetType) {
		new SheetParams(sheetType).setValue(currModel);
		SlotParams.getInstance().setValue(currModel);
		AuxiliaryParams.getInstance().setValue(currModel);
		CalculatedParams.getInstance().setValue(currModel);
		int typeOfScrew = DataStore.getTypeOfScrew();
		if (typeOfScrew / 10 == 0) {
			ScrewParamsFactory.getParams(typeOfScrew, DataStore.getScrewShift()).setValue(currModel);
		} else {
			int firstTypeOfScrew = typeOfScrew / 10;
			int secondTypeOfScrew = typeOfScrew % 10;
			ScrewParamsFactory.getParams(firstTypeOfScrew, DataStore.getScrewShift()).setValue(currModel);
			ScrewParamsFactory.getParams(secondTypeOfScrew, DataStore.getSecondScrewShift()).setValue(currModel);
		}
		LOG.info("All parameters set");
	}
}
