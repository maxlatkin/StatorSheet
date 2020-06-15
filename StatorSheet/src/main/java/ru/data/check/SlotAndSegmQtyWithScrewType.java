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

public class SlotAndSegmQtyWithScrewType implements Checkable {

	private static final Logger LOG = LoggerFactory.getLogger(SlotAndSegmQtyWithScrewType.class);
	private MessageDialogOptions dialogOptions;
	private double slotQty;
	private double segmQty;
	
	@Override
	public void check() {
		try {
			slotQty = DataStore.getSlotQty();
			segmQty = DataStore.getSegmQty();
			int typeOfScrew = DataStore.getTypeOfScrew();
			boolean isScrew04Exist = DataStore.isScrew04Exist();
			int screwQty = DataStore.getScrewQty();
			dialogOptions = pfcUI.MessageDialogOptions_Create();
			dialogOptions.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
			dialogOptions.SetDialogLabel("Ошибка при получении данных");
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			if (!isRemainderZero()) {
				session.UIShowMessageDialog("Ошибка: на сегмент приходится нецелое число пазов"
						+ "\nОбратитесь к расчётчику.", dialogOptions);
				throw new InputCheckException("Remainder of slotQty/segmQty is not zero");
			}
			if (isRatioOfSlotQtyAndSegmQtyEven()) {
				if (typeOfScrew == 3 && isScrew04Exist) {
					session.UIShowMessageDialog("Ошибка: крепеж с открытыми пазами под шпильки укладывают с перекрытием 1/3."
							+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/2."
							+ "\nОбратитесь к расчётчику.", dialogOptions);
					throw new InputCheckException("screw03and04 with overlap 1/2");
				} else if ((typeOfScrew == 5 || typeOfScrew == 6 || typeOfScrew == 7) && screwQty % 2 != 0) {
					session.UIShowMessageDialog("Ошибка: данный тип крепежа при нечетном числе на сегмент "
							+ "укладывают с перекрытием 1/3."
							+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/2."
							+ "\nОбратитесь к расчётчику.", dialogOptions);
					throw new InputCheckException("screw050607 with odd screwQty with overlap 1/2");
				}
			}
			if (!isRatioOfSlotQtyAndSegmQtyEven()) {
				if (typeOfScrew == 1 || typeOfScrew == 2 || (typeOfScrew == 3 && !isScrew04Exist)) {
					session.UIShowMessageDialog("Ошибка: данный тип крепежа укладывают с перекрытием 1/2."
							+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/3."
							+ "\nОбратитесь к расчётчику.", dialogOptions);
					throw new InputCheckException("screw010203 with overlap 1/3");
				} else if ((typeOfScrew == 5 || typeOfScrew == 6 || typeOfScrew == 7) && screwQty % 2 == 0) {
					session.UIShowMessageDialog("Ошибка: данный тип крепежа при четном числе на сегмент "
							+ "укладывают с перекрытием 1/2."
							+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/3."
							+ "\nОбратитесь к расчётчику.", dialogOptions);
					throw new InputCheckException("screw050607 with even screwQty with overlap 1/3");
				}
			}
		} catch (jxthrowable e) {
			LOG.error("Error checking SlotAndSegmQtyWithScrewType", e);
		}
	}
	
	private boolean isRemainderZero() {
		return slotQty % segmQty == 0;
	}
	private boolean isRatioOfSlotQtyAndSegmQtyEven() {
		return (slotQty / segmQty) % 2 == 0;
	}
}
