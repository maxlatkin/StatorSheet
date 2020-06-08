package ru.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcPushButton.DefaultPushButtonListener;
import com.ptc.uifc.uifcPushButton.PushButton;

public class UiOkListener extends DefaultPushButtonListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(UiOkListener.class);
	private static UiOkListener instance;

	private UiOkListener(){}
    
    public static UiOkListener getInstance() {
        if (instance == null) {
            instance = new UiOkListener();
        }
        return instance;
    }
	@Override
	public void OnActivate(PushButton handle) throws jxthrowable {
		try {
			LOG.info("Ok button is pressed");
			uifcComponent.ExitDialog(handle.GetDialog(),0);
		} catch (Exception e) {
			LOG.error("Error in UiOkListener", e);
		}
	}
}
