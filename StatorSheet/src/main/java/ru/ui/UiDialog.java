package ru.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.uifc.uifcCheckButton.uifcCheckButton;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcInputPanel.uifcInputPanel;
import com.ptc.uifc.uifcLabel.uifcLabel;
import com.ptc.uifc.uifcOptionMenu.uifcOptionMenu;
import com.ptc.uifc.uifcPushButton.PushButton;
import com.ptc.uifc.uifcPushButton.PushButtonListener_u;
import com.ptc.uifc.uifcPushButton.uifcPushButton;

import ru.wnc.documents.DocNumbers;
import ru.wnc.documents.DocumentTypes;


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
			
			
//			setNumbersToOM("","OM_Note", DocumentTypes.CALC_AND_WIND_NOTE);
			setNumbersToOM("","OM_STO", DocumentTypes.STO);
			setNumbersToOM("","OM_Results", DocumentTypes.MECH_CALC_RESULTS);
			filterNoteNumber();
			
			uifcLabel.LabelFind(DIALOG, "L_S1_Image").SetImage("Screw\\s1.png");
			uifcLabel.LabelFind(DIALOG, "L_S2_Image").SetImage("Screw\\s2.png");
			uifcLabel.LabelFind(DIALOG, "L_S34_Image_1").SetImage("Screw\\s3_4_1.png");
			uifcLabel.LabelFind(DIALOG, "L_S34_Image_2").SetImage("Screw\\s3_4_2.png");
			uifcLabel.LabelFind(DIALOG, "L_S5_Image").SetImage("Screw\\s5.png");
			uifcLabel.LabelFind(DIALOG, "L_S6_Image").SetImage("Screw\\s6.png");
			uifcLabel.LabelFind(DIALOG, "L_S7_Image").SetImage("Screw\\s7.png");
			
			uifcComponent.ActivateDialog(DIALOG);	
			uifcComponent.DestroyDialog(DIALOG);
		} catch (Exception e) {
			LOG.error("Error in showDialog()", e);
		}
	}

	private void filterNoteNumber() throws jxthrowable {
		uifcPushButton.PushButtonFind(DIALOG, "PB_Note").AddActionListener(new PushButtonListener_u() {
			
			@Override
			public void OnMouseExit(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnMouseEnter(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnHelp(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnFocusOut(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnFocusIn(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnEndDrag(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnDropNowhere(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnDrop(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnDragNowhere(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnDragMove(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnDragExit(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnDragEnter(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnBeginDrag(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void OnActivate(PushButton arg0) throws jxthrowable {
				// TODO Auto-generated method stub
				String noteNumberFilter = uifcInputPanel.InputPanelFind(DIALOG, "IP_Note").GetStringValue();
				setNumbersToOM(noteNumberFilter,"OM_Note", DocumentTypes.CALC_AND_WIND_NOTE);
			}
		});
	}

	private void setNumbersToOM(String numberFilter,String omName, DocumentTypes type) throws jxthrowable {
		stringseq numbers = stringseq.create();
		DocNumbers docNumbers = new DocNumbers(type);
		for (String number: docNumbers.getSetOfDocNumbers(numberFilter)) {
			numbers.append(number);				
		}
		uifcOptionMenu.OptionMenuFind(DIALOG, omName).SetStringValueArray(numbers);
	}
}
