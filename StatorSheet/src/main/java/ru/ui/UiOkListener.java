package ru.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.uifc.uifcCheckButton.uifcCheckButton;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcCore.CheckState;
import com.ptc.uifc.uifcOptionMenu.uifcOptionMenu;
import com.ptc.uifc.uifcPushButton.DefaultPushButtonListener;
import com.ptc.uifc.uifcPushButton.PushButton;

import ru.data.DataStore;
import ru.general.General;

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
			stringseq noteName = uifcOptionMenu.OptionMenuFind(UiDialog.DIALOG, "OM_Note").GetSelectedItemNameArray();
			stringseq stoName = uifcOptionMenu.OptionMenuFind(UiDialog.DIALOG, "OM_STO").GetSelectedItemNameArray();
			stringseq resultsName = uifcOptionMenu.OptionMenuFind(UiDialog.DIALOG, "OM_Results").GetSelectedItemNameArray();
			General.setNoteName(noteName.get(0));
			General.setStoName(stoName.get(0));
			General.setResultsName(resultsName.get(0));
			CheckState roundState = uifcCheckButton.CheckButtonFind(UiDialog.DIALOG, "CB_Round").GetCheckedState();
			DataStore.setSlotWithRound(roundState.getValue() == 1);
			stringseq selectedItem  = uifcOptionMenu.OptionMenuFind(UiDialog.DIALOG, UiDialog.SCREWS_OM).GetSelectedItemNameArray();
			DataStore.setTypeOfScrew(Integer.parseInt(selectedItem.get(0)));
			CheckState screw04State = uifcCheckButton.CheckButtonFind(UiDialog.DIALOG, "CB_S34_Screw_4").GetCheckedState();
			DataStore.setScrew04Exist(screw04State.getValue() == 1);
			uifcComponent.ExitDialog(handle.GetDialog(),0);
		} catch (Exception e) {
			LOG.error("Error in UiOkListener", e);
		}
	}
}
