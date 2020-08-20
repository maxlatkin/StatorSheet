package ru.ruselprom.ui;

import com.ptc.cipjava.jxthrowable;
import com.ptc.uifc.uifcInputPanel.uifcInputPanel;
import com.ptc.uifc.uifcPushButton.PushButton;
import com.ptc.uifc.uifcPushButton.PushButtonListener_u;

import ru.ruselprom.wnc.documents.DocTypes;

public class UiNotePbListener extends PushButtonListener_u {

	@Override
	public void OnActivate(PushButton arg0) throws jxthrowable {
		String noteNumberFilter = uifcInputPanel.InputPanelFind(UiDialog.DIALOG, "IP_Note").GetStringValue();
		UiDialog.getInstance().setNumbersToOM(noteNumberFilter,"OM_Note", DocTypes.CALC_AND_WIND_NOTE);
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
