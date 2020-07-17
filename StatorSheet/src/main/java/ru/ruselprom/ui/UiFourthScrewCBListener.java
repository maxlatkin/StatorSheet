package ru.ruselprom.ui;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.uifc.uifcCheckButton.CheckButton;
import com.ptc.uifc.uifcCheckButton.CheckButtonListener_u;
import com.ptc.uifc.uifcCheckButton.uifcCheckButton;
import com.ptc.uifc.uifcCore.CheckState;
import com.ptc.uifc.uifcTab.uifcTab;

public class UiFourthScrewCBListener extends CheckButtonListener_u {

	@Override
	public void OnActivate(CheckButton arg0) throws jxthrowable {
		CheckState checkState = uifcCheckButton.CheckButtonFind(UiDialog.DIALOG, UiDialog.SCREW_4_CB).GetCheckedState();
		stringseq image = stringseq.create();
		if (checkState.getValue() == 0) {
			image.append("LO_S34_Image_1");
		} else if (checkState.getValue() == 1) {
			image.append("LO_S34_Image_2");
		}
		uifcTab.TabFind(UiDialog.DIALOG, UiDialog.SCREW_4_T).SetSelectedItemNameArray(image);
	}

	@Override
	public void OnBeginDrag(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnDragEnter(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnDragExit(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnDragMove(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnDragNowhere(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnDrop(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnDropNowhere(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnEndDrag(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnFocusIn(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnFocusOut(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnHelp(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnMouseEnter(CheckButton arg0) throws jxthrowable {}

	@Override
	public void OnMouseExit(CheckButton arg0) throws jxthrowable {}

}
