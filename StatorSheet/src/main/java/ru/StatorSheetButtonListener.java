package ru;

import java.util.logging.Logger;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

public class StatorSheetButtonListener extends DefaultUICommandActionListener {
	private static final String MODELS_PATH = "D:\\Project\\pro\\models\\CuttingPatterns";
    private static final String SEGMENT_TEMP_NAME = "ev_20200219_stator_sheet.prt";
    private static final String SEGMENT_NAME = "scheme.prt";
    
    
	@Override
	public void OnCommand() throws jxthrowable {
		StatorSheetButton.LOG.info("The button has been pressed!\nThe button has been pressed!\nThe button has been pressed!");
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		session.UIShowMessageDialog("33", null);
	}
	
	
}
