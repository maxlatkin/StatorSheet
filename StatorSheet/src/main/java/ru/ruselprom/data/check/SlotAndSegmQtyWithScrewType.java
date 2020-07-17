package ru.ruselprom.data.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcUI.MessageDialogOptions;
import com.ptc.pfc.pfcUI.MessageDialogType;
import com.ptc.pfc.pfcUI.pfcUI;

import ru.ruselprom.data.DataStore;
import ru.ruselprom.exceptions.InputCheckException;

public class SlotAndSegmQtyWithScrewType implements Checkable {

	private static final Logger LOG = LoggerFactory.getLogger(SlotAndSegmQtyWithScrewType.class);
	private MessageDialogOptions dialogOptions;
	private Session session;
	private double slotQty;
	private double segmQty;
	
	@Override
	public void check() {
		try {
			session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			dialogOptions = pfcUI.MessageDialogOptions_Create();
			dialogOptions.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
			dialogOptions.SetDialogLabel("Ошибка при получении данных");
			slotQty = DataStore.getSlotQty();
			segmQty = DataStore.getSegmQty();
			int typeOfScrew = DataStore.getTypeOfScrew();
			boolean isScrew04Exist = DataStore.isScrew04Exist();
			int screwQty = DataStore.getScrewQty();
			intNumOfGroovesPerSegmCheck();
			typeOfScrewForWholeSheetCheck(typeOfScrew);
			typeOfScrewForOverlapCheck(typeOfScrew, isScrew04Exist, screwQty);
		} catch (jxthrowable e) {
			LOG.error("Error checking SlotAndSegmQtyWithScrewType", e);
		}
	}

	private void intNumOfGroovesPerSegmCheck() throws jxthrowable {
		if (!isRemainderZero()) {
			session.UIShowMessageDialog("Ошибка: на сегмент приходится нецелое число пазов"
					+ "\nОбратитесь к расчётчику.", dialogOptions);
			throw new InputCheckException("Remainder of slotQty/segmQty is not zero");
		}
	}
	
	private boolean isRemainderZero() {
		return slotQty % segmQty == 0;
	}
	
	private void typeOfScrewForWholeSheetCheck(int typeOfScrew) throws jxthrowable {
		if (segmQty == 1 && typeOfScrew != 5 && typeOfScrew != 6) {
			session.UIShowMessageDialog("Ошибка: данный тип крепежа не существует для цельного листа."
					+ "\nВыберите другой тип крепежа или обратитесь к расчётчику.", dialogOptions);
			throw new InputCheckException("This type of screw does not exist for a whole sheet");
		}
	}	
	
	private void typeOfScrewForOverlapCheck(int typeOfScrew, boolean isScrew04Exist, int screwQty) throws jxthrowable {
		if (isRatioOfSlotQtyAndSegmQtyEven()) {
			overlap1_2Check(typeOfScrew, isScrew04Exist, screwQty);
		} else {
			overlap1_3Check(typeOfScrew, isScrew04Exist, screwQty);
		}
	}
	
	private boolean isRatioOfSlotQtyAndSegmQtyEven() {
		return (slotQty / segmQty) % 2 == 0;
	}
	
	private void overlap1_2Check(int typeOfScrew, boolean isScrew04Exist, int screwQty)
			throws jxthrowable {
		if (typeOfScrew == 3 && isScrew04Exist) {
			session.UIShowMessageDialog("Ошибка: крепеж с открытыми пазами под шпильки укладывают с перекрытием 1/3."
					+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/2."
					+ "\nВыберите другой тип крепежа или обратитесь к расчётчику.", dialogOptions);
			throw new InputCheckException("screw03and04 with overlap 1/2");
		} else if ((typeOfScrew == 5 || typeOfScrew == 6 || typeOfScrew == 7) && screwQty % 2 != 0) {
			session.UIShowMessageDialog("Ошибка: данный тип крепежа при нечетном числе на сегмент "
					+ "укладывают с перекрытием 1/3."
					+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/2."
					+ "\nВыберите другой тип крепежа или обратитесь к расчётчику.", dialogOptions);
			throw new InputCheckException("screw050607 with odd screwQty with overlap 1/2");
		}
	}
	
	private void overlap1_3Check(int typeOfScrew, boolean isScrew04Exist, int screwQty)
			throws jxthrowable {
		if (typeOfScrew == 1 || typeOfScrew == 2 || (typeOfScrew == 3 && !isScrew04Exist)) {
			session.UIShowMessageDialog("Ошибка: данный тип крепежа укладывают с перекрытием 1/2."
					+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/3."
					+ "\nВыберите другой тип крепежа или обратитесь к расчётчику.", dialogOptions);
			throw new InputCheckException("screw010203 with overlap 1/3");
		} else if ((typeOfScrew == 5 || typeOfScrew == 6 || typeOfScrew == 7) && screwQty % 2 == 0) {
			session.UIShowMessageDialog("Ошибка: данный тип крепежа при четном числе на сегмент "
					+ "укладывают с перекрытием 1/2."
					+ "\nПри текущем числе пазов и сегментов необходимо укладывать с перекрытием 1/3."
					+ "\nВыберите другой тип крепежа или обратитесь к расчётчику.", dialogOptions);
			throw new InputCheckException("screw050607 with even screwQty with overlap 1/3");
		}
	}
}