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

public class ScrewQty implements Checkable {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScrewQty.class);
	private double screwQty;
	private double segmQty;
	
	@Override
	public void check() {
		try {
			screwQty = DataStore.getScrewQty();
			segmQty = DataStore.getSegmQty();
			int typeOfScrew = DataStore.getTypeOfScrew();
			boolean isScrew04Exist = DataStore.isScrew04Exist();
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			MessageDialogOptions dialogOptions = pfcUI.MessageDialogOptions_Create();
			dialogOptions.SetDialogLabel("Ошибка при получении данных");
			dialogOptions.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
			if (!isRemainderZero()) {
				session.UIShowMessageDialog("Ошибка: на сегмент приходится нецелое число крепежей"
						+ "\nОбратитесь к расчётчику.", dialogOptions);
				throw new InputCheckException("Remainder of slotQty/segmQty is not zero");
			}
			if ((screwQty != 2 || screwQty != 4) && (typeOfScrew == 1 || typeOfScrew == 2 || (typeOfScrew == 3 && !isScrew04Exist))) {
				session.UIShowMessageDialog("Ошибка: неправильное число крепежей на сегмент для данного типа!"
						+ "\nВыберите другой тип крепежа или обратитесь к расчётчику.", dialogOptions);
				throw new InputCheckException("Wrong screwQty for this screw type");
			}			
		} catch (jxthrowable e) {
			LOG.error("Error checking ScrewQty", e);
		}
	}
	private boolean isRemainderZero() {
		return screwQty % segmQty == 0;
	}
}