package ru.wnc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcExceptions.XToolkitGeneralError;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcUI.MessageDialogOptions;
import com.ptc.pfc.pfcUI.MessageDialogType;
import com.ptc.pfc.pfcUI.pfcUI;

import ru.data.DataStore;
import ru.exceptions.RetrieveModelException;
import ru.general.AppProperties;
import ru.ruselprom.parameters.Parameters;

public class Models {
	
	private static Models instance;
	private String modelName;
	private String partOfModelNumber;
	private Model part;
	private Model drw;
	private Model dxfTemp;
	private static final Logger LOG = LoggerFactory.getLogger(Models.class);
	private Models(){}
	
	public static Models getInstance() {
        if (instance == null) {
            instance = new Models();
        }
        return instance;
    }
	
	public void retrieveToSessionWithRenameAndRenumber() {
		try {
			modelName = null;
			partOfModelNumber = null;
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			drw = session.RetrieveModel(pfcModel.ModelDescriptor_Create(ModelType.MDL_DRAWING, AppProperties.DRW_TEMP, null));
			drw.Rename(getModelName(), false);
			dxfTemp = session.RetrieveModel(pfcModel.ModelDescriptor_Create(ModelType.MDL_DRAWING, AppProperties.DXF_TEMP, null));
			dxfTemp.Rename(getDate("HHmmss") + "_dxf_temp",  false);
			part = session.GetModel(AppProperties.PART_TEMP, ModelType.MDL_PART);
			part.Rename(getModelName(), false);
			Parameters.setStringParamValue("ОБОЗНАЧЕНИЕ", getModelNumder(), part);
			Parameters.setStringParamValue("ОБОЗНАЧЕНИЕ", getModelNumder(), drw);
			LOG.info("Models is retrieved, renamed and renumbered");
		} catch (XToolkitGeneralError e) {
			handleGeneralError();
		} catch (jxthrowable e) {
			LOG.error("Error retrieving models", e);
		}
	}
	
	public Model getDxfTempFromSession() {
		LOG.info("А new dxfTemp is received from session");
		return dxfTemp;
	}
	public Model getPartFromSession() {
		LOG.info("А new part is received from session");
		return part;
	}
	
	public Model getDrwFromSession() {
		LOG.info("А new drw is received from session");
		return drw;
	}
	
	private void handleGeneralError() {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			MessageDialogOptions dialogOptions = pfcUI.MessageDialogOptions_Create();
			dialogOptions.SetDialogLabel("Ошибка при получении шаблонов моделей");
			dialogOptions.SetMessageDialogType(MessageDialogType.MESSAGE_ERROR);
			session.UIShowMessageDialog("Ошибка: модели не были найдены в локальном кэше, рабочей области и сервере!"
					+ "\nВозможно, Вы не подключены к серверу.", dialogOptions);
			throw new RetrieveModelException("wnc is offline");
		} catch (jxthrowable e) {
			LOG.error(e.toString());
		}
	}
	
	private String getModelNumder() {
		if (partOfModelNumber == null) {
			partOfModelNumber = getDate("mmssSS");
		}
		return String.format("XXXX.%s.%s", DataStore.getSegmQty() == 1 ? "757221" : "757227", partOfModelNumber);
	}
	private String getModelName() {
		if (modelName == null) {
			modelName = getDate("yyyy_MM_dd_HH_mm_ss_SS");
		}
		return modelName;
	}
	private String getDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}
}
