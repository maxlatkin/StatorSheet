package ru.data.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcUI.MessageDialogOptions;
import com.ptc.pfc.pfcUI.MessageDialogType;
import com.ptc.pfc.pfcUI.pfcUI;

import ru.data.DataStore;
import ru.exceptions.InputCheckException;

public class ExtAndIntDiams implements Checkable {

	private static final Logger LOG = LoggerFactory.getLogger(ExtAndIntDiams.class);
	
	@Override
	public void check() {
		try {
			if (DataStore.getIntDiam() >= DataStore.getExtDiam()) {
				Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
				MessageDialogOptions dialogOptions = pfcUI.MessageDialogOptions_Create();
				dialogOptions.SetDialogLabel("Ошибка при получении данных");
				dialogOptions.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
				session.UIShowMessageDialog("Ошибка: внутренний диаметр сегмента больше или равен внешнему!"
						+ "\nОбратитесь к расчётчику.", dialogOptions);
				throw new InputCheckException("intDiam > extDiam");
			}
		} catch (NullPointerException | jxthrowable e) {
			LOG.error(e.toString());
		}
	}
}