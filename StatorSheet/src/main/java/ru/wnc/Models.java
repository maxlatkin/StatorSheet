package ru.wnc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

import ru.data.DataStore;
import ru.ruselprom.parameters.Parameters;

public class Models {
	
	private static Models instance;
	private Session session;
	private String modelName;
	private String partOfModelNumber;
	private static final Logger LOG = LoggerFactory.getLogger(Models.class);
	private Models(){}
	
	public static Models getInstance() {
        if (instance == null) {
            instance = new Models();
        }
        return instance;
    }
	
	public void retrieve() {
		try {
			modelName = null;
			partOfModelNumber = null;
			session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			ModelDescriptor modelDescriptor = pfcModel.ModelDescriptor_Create(ModelType.MDL_DRAWING, DataStore.getTempDrw(), null);
			session.RetrieveModel(modelDescriptor);
			String partName = getModelName() + ".prt";
			session.GetModelFromFileName(DataStore.getTempPart()).Rename(partName, false);
			String drwName = getModelName() + ".drw";
			session.GetModelFromFileName(DataStore.getTempDrw()).Rename(drwName, false);
			LOG.info("Models is retrieved");
		} catch (jxthrowable e) {
			LOG.error("Error retrieving models", e);
		}
	}
	
	public Model getPart() {
		try {
			Model part = session.GetModel(getModelName(), ModelType.MDL_PART);
			LOG.info("Part is got");
			return part;
		} catch (jxthrowable e) {
			LOG.error("Error getting part", e);
			return null;
		}
	}
	
	public Model getDrw() {
		try {
			Model drw = session.GetModel(getModelName(), ModelType.MDL_DRAWING);
			LOG.info("Drw is got");
			return drw;
		} catch (jxthrowable e) {
			LOG.error("Error getting drw", e);
			return null;
		}
	}
	
	public void saveWithNewNumber(Model currModel) {
		try {
			Parameters.setStringParamValue("ОБОЗНАЧЕНИЕ", getModelNumder(), currModel);
			currModel.Save();
			if (LOG.isInfoEnabled()) {
				LOG.info("{} is saved", currModel.GetFileName());
			}
		} catch (jxthrowable e) {
			LOG.error("Error in checkInModel", e);
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
