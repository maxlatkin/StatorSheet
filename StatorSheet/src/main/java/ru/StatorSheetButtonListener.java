package ru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

import ru.main.Main;

public class StatorSheetButtonListener extends DefaultUICommandActionListener {
    public static final Logger LOG = LoggerFactory.getLogger(StatorSheetButtonListener.class);
    
	@Override
	public void OnCommand() throws jxthrowable {
		LOG.info("The button has been pressed!");
		Main.execute();
		LOG.info("The StatorSheet application completed.\n");
	}
}
