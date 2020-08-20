package ru.ruselprom.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcPushButton.DefaultPushButtonListener;
import com.ptc.uifc.uifcPushButton.PushButton;


public class UiCancelListener extends DefaultPushButtonListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(UiCancelListener.class);
	
	private boolean isCancelPressed;
	private static UiCancelListener instance;

	private UiCancelListener(){}
    
    public static UiCancelListener getInstance() {
        if (instance == null) {
            instance = new UiCancelListener();
        }
        return instance;
    }
	
	
	public boolean isCancelPressed() {
		return isCancelPressed;
	}

	public void setCancelPressed(boolean isClosePressed) {
		this.isCancelPressed = isClosePressed;
	}

	@Override
	public void OnActivate(PushButton handle) throws jxthrowable {
		isCancelPressed = true;
		LOG.info("Cancel button is pressed");
		uifcComponent.ExitDialog(handle.GetDialog(),0);
	}
}
