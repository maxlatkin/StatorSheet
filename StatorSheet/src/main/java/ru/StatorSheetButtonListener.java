package ru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

import ru.data.DataOperations;
import ru.exceptions.InputCheckException;
import ru.general.General;
import ru.general.SheetType;
import ru.ui.UiCancelListener;
import ru.ui.UiDialog;

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
			DataOperations.calculateVars();
			General.execute(SheetType.BASIC);
			LOG.info("The StatorSheet application completed.\n");
		} catch (InputCheckException e) {
			LOG.error("Input error", e);
		} catch (Exception e) {
			LOG.error("Error in button listener", e);
		}
	}
}
