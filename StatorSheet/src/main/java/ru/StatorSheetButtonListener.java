package ru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

import ru.sheet.Sheet;

public class StatorSheetButtonListener extends DefaultUICommandActionListener {
    public static final Logger LOG = LoggerFactory.getLogger(StatorSheetButtonListener.class);
    
	@Override
	public void OnCommand() throws jxthrowable {
		LOG.info("The button has been pressed!");
		Sheet.create();
		LOG.info("The StatorSheet application completed.\n");
	}
}
