package ru.ruselprom.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.uifc.uifcCheckButton.uifcCheckButton;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcLabel.uifcLabel;
import com.ptc.uifc.uifcOptionMenu.uifcOptionMenu;
import com.ptc.uifc.uifcPushButton.uifcPushButton;

import ru.ruselprom.wnc.documents.DocNumbers;
import ru.ruselprom.wnc.documents.DocTypes;


public class UiDialog extends DefaultUICommandActionListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(UiDialog.class);
	public static final String DIALOG = "StatorSheet";
	public static final String OK_BUTTON = "CommitOK";
	public static final String CANCEL_BUTTON = "CommitCancel";
	public static final String SCREWS_OM = "OM_Screws";
	public static final String SCREWS_T = "T_Screws";
	public static final String SCREW_4_CB = "CB_S34_Screw_4";
	public static final String SCREW_4_T = "T_S34_Images";
	private static UiDialog instance;

	private UiDialog(){}
    
    public static UiDialog getInstance() {
        if (instance == null) {
            instance = new UiDialog();
        }
        return instance;
    }
	
	@Override
	public void OnCommand() throws jxthrowable {
		showDialog();
	}
	public void showDialog() {
		try {
			uifcComponent.CreateDialog(DIALOG, DIALOG);
			uifcPushButton.PushButtonFind(DIALOG, CANCEL_BUTTON).AddActionListener(UiCancelListener.getInstance());		
			uifcPushButton.PushButtonFind(DIALOG, OK_BUTTON).AddActionListener(UiOkListener.getInstance());
			uifcOptionMenu.OptionMenuFind(DIALOG, SCREWS_OM).AddActionListener(new UiScrewsOMListener());
			uifcCheckButton.CheckButtonFind(DIALOG, SCREW_4_CB).AddActionListener(new UiFourthScrewCBListener());
			
			
			uifcPushButton.PushButtonFind(DIALOG, "PB_Note").AddActionListener(new UiNotePbListener());
			uifcPushButton.PushButtonFind(DIALOG, "PB_STO").AddActionListener(new UiStoPbListener());
			uifcPushButton.PushButtonFind(DIALOG, "PB_Results").AddActionListener(new UiResultsPbListener());
			
			uifcLabel.LabelFind(DIALOG, "L_S1_Image").SetImage("Screw\\s1.png");
			uifcLabel.LabelFind(DIALOG, "L_S2_Image").SetImage("Screw\\s2.png");
			uifcLabel.LabelFind(DIALOG, "L_S34_Image_1").SetImage("Screw\\s3_4_1.png");
			uifcLabel.LabelFind(DIALOG, "L_S34_Image_2").SetImage("Screw\\s3_4_2.png");
			uifcLabel.LabelFind(DIALOG, "L_S5_Image").SetImage("Screw\\s5.png");
			uifcLabel.LabelFind(DIALOG, "L_S6_Image").SetImage("Screw\\s6.png");
			uifcLabel.LabelFind(DIALOG, "L_S7_Image").SetImage("Screw\\s7.png");
			uifcLabel.LabelFind(DIALOG, "L_S37_Image").SetImage("Screw\\s37.png");
			
			uifcComponent.ActivateDialog(DIALOG);	
			uifcComponent.DestroyDialog(DIALOG);
		} catch (Exception e) {
			LOG.error("Error in showDialog()", e);
		}
	}

	public void setNumbersToOM(String numberFilter,String omName, DocTypes type) throws jxthrowable {
		stringseq numbers = stringseq.create();
		DocNumbers docNumbers = new DocNumbers(type);
		for (String number: docNumbers.getSetOfDocNumbers(numberFilter)) {
			numbers.append(number);				
		}
		uifcOptionMenu.OptionMenuFind(DIALOG, omName).SetStringValueArray(numbers);
	}
}
