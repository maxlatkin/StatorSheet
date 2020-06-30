package ru.ui;

import com.ptc.cipjava.jxthrowable;
import com.ptc.uifc.uifcInputPanel.uifcInputPanel;
import com.ptc.uifc.uifcPushButton.PushButton;
import com.ptc.uifc.uifcPushButton.PushButtonListener_u;

import ru.wnc.documents.DocTypes;

public class UiResultsPbListener extends PushButtonListener_u {

	@Override
	public void OnActivate(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		String resultsNumberFilter = uifcInputPanel.InputPanelFind(UiDialog.DIALOG, "IP_Results").GetStringValue();
		UiDialog.getInstance().setNumbersToOM(resultsNumberFilter,"OM_Results", DocTypes.MECH_CALC_RESULTS);
	}

	@Override
	public void OnBeginDrag(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnDragEnter(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnDragExit(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnDragMove(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnDragNowhere(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnDrop(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnDropNowhere(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnEndDrag(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnFocusIn(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnFocusOut(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnHelp(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnMouseEnter(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnMouseExit(PushButton arg0) throws jxthrowable {
		// TODO Auto-generated method stub
		
	}

}
