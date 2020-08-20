package ru.ruselprom;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class StatorSheetButton {
	private static final String MSG_FILE = "stator_sheet_button.txt";
	private static final Logger LOG = LoggerFactory.getLogger(StatorSheetButton.class);
	
	private StatorSheetButton() {
		throw new IllegalStateException("Utility class");
	}


    public static void start() {
    	try {
			setLogbackFile();
			LOG.info("The StatorSheet application has begun its work.");
			
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);			 
			UICommand uiCommand = session.UICreateCommand("StatorSheet", new StatorSheetButtonListener());		
			uiCommand.SetIcon("statorsheet_16x16.png");
			uiCommand.Designate(MSG_FILE, "StatorSheet.label", "Stator sheet creation.", null);
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in start() method", e);
		}
    }

    public static void stop() {
    	LOG.info("The StatorSheet application stopped.");
    }
    
    private static void setLogbackFile() {
    	try {
			LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			context.reset();
			JoranConfigurator configurator = new JoranConfigurator();
			InputStream stream = StatorSheetButton.class.getResourceAsStream("/logback.xml");
			configurator.setContext(context);
			configurator.doConfigure(stream);
			stream.close();
		} catch (JoranException | IOException e) {
			e.printStackTrace();
		}
    }
}
