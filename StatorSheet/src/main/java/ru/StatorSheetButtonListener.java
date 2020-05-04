package ru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

import ru.general.General;

public class StatorSheetButtonListener extends DefaultUICommandActionListener {
    private static final Logger LOG = LoggerFactory.getLogger(StatorSheetButtonListener.class);
    
	@Override
	public void OnCommand() throws jxthrowable {
		LOG.info("The button has been pressed!");
		General.execute();
		LOG.info("The StatorSheet application completed.\n");
	}
}
