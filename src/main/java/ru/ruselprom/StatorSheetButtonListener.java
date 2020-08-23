package ru.ruselprom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

import ru.ruselprom.data.DataOperations;
import ru.ruselprom.data.DataStore;
import ru.ruselprom.exceptions.InputCheckException;
import ru.ruselprom.general.General;
import ru.ruselprom.general.SheetType;
import ru.ruselprom.ui.UiCancelListener;
import ru.ruselprom.ui.UiDialog;

public class StatorSheetButtonListener extends DefaultUICommandActionListener {
    private static final Logger LOG = LoggerFactory.getLogger(StatorSheetButtonListener.class);
    
	@Override
	public void OnCommand() throws jxthrowable {
		try {
			LOG.info("The button has been pressed!");
			UiDialog.getInstance().showDialog();
			if (UiCancelListener.getInstance().isCancelPressed()) {
				UiCancelListener.getInstance().setCancelPressed(false);
				return;
			}
			
			
			DataOperations.assignVarsToDataStore();
			DataOperations.checkVars();
			DataOperations.calculateCommonVars();
			
			SheetType.setCurrSheetType(SheetType.BASIC);
			DataOperations.calculateDifferentVars(SheetType.getCurrSheetType());
			General.execute(SheetType.getCurrSheetType());
			if (DataStore.getVentDucts()) {
				SheetType.setCurrSheetType(SheetType.VENT);
				DataOperations.calculateDifferentVars(SheetType.getCurrSheetType());
				General.execute(SheetType.getCurrSheetType());
			}
			LOG.info("The StatorSheet application completed.\n");
		} catch (InputCheckException e) {
			LOG.error("Input error", e);
		} catch (Exception e) {
			LOG.error("Error in button listener", e);
		}
	}
}
