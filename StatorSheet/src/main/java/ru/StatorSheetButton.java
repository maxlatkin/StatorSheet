package ru;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

public class StatorSheetButton {
	private static final String MSG_FILE = "stator_sheet_button.txt";
	public static final Logger LOG = Logger.getLogger(StatorSheetButtonListener.class.getName());
	
	private StatorSheetButton() {
    throw new IllegalStateException("Utility class");
  }


    public static void start() throws jxthrowable{ 
		try {
			LogManager.getLogManager().readConfiguration(StatorSheetButtonListener.class.getResourceAsStream("/logging.properties"));
		} catch (IOException e) {
		    System.err.println("Could not setup logger configuration: " + e.toString());
		}
		LOG.info("The StatorSheet application has begun its work.");
    	
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);			 
    	UICommand uiCommand = session.UICreateCommand("StatorSheet", new StatorSheetButtonListener());		
    	uiCommand.SetIcon("statorsheet_16x16.png");
		uiCommand.Designate(MSG_FILE, "StatorSheet.label", "Stator sheet creation.", null);
    }

    public static void stop() throws jxthrowable{
    	LOG.log(Level.INFO, "The StatorSheet application has completed its work.");
    }
}
