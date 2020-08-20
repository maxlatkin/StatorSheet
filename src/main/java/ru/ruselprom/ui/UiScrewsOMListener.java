package ru.ruselprom.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.cipjava.stringseq;
import com.ptc.uifc.uifcOptionMenu.OptionMenu;
import com.ptc.uifc.uifcOptionMenu.OptionMenuListener_u;
import com.ptc.uifc.uifcOptionMenu.uifcOptionMenu;
import com.ptc.uifc.uifcTab.uifcTab;

public class UiScrewsOMListener extends OptionMenuListener_u {
	
	private static final Logger LOG = LoggerFactory.getLogger(UiScrewsOMListener.class);

	@Override
	public void OnChange(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnDragEnter(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnDragExit(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnDragMove(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnDrop(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnFocusIn(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnFocusOut(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnHelp(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnItemActivate(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnItemHover(OptionMenu arg0) throws jxthrowable {}

	@Override
	public void OnItemSelect(OptionMenu arg0) throws jxthrowable {
		try {
			stringseq selectedItem  = uifcOptionMenu.OptionMenuFind(UiDialog.DIALOG, UiDialog.SCREWS_OM).GetSelectedItemNameArray();
			String value = selectedItem.get(0);
			stringseq screw = stringseq.create();
			switch (value) {
			case "1":
				screw.append("LO_Screw_1");
				break;
			case "2":
				screw.append("LO_Screw_2");
				break;
			case "3":
				screw.append("LO_Screw_3and4");
				break;
			case "5":
				screw.append("LO_Screw_5");
				break;
			case "6":
				screw.append("LO_Screw_6");
				break;
			case "7":
				screw.append("LO_Screw_7");
				break;
			case "37":
				screw.append("LO_Screw_37");
				break;
			default:
				break;	
			}
			uifcTab.TabFind(UiDialog.DIALOG, UiDialog.SCREWS_T).SetSelectedItemNameArray(screw);
		} catch (Exception e) {
			LOG.error("Error in UiScrewsOMListener", e);
		}
	}

}
