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
	private String modelName;
	private String partOfModelNumber;
	private Model part;
	private Model drw;
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
			ModelDescriptor modelDescriptor = pfcModel.ModelDescriptor_Create(ModelType.MDL_DRAWING, DataStore.getTempDrw(), null);
			session.RetrieveModel(modelDescriptor);
			part = session.GetModelFromFileName(DataStore.getTempPart());
			part.Rename(getModelName(), false);
			drw = session.GetModelFromFileName(DataStore.getTempDrw());
			drw.Rename(getModelName(), false);
			Parameters.setStringParamValue("ОБОЗНАЧЕНИЕ", getModelNumder(), part);
			Parameters.setStringParamValue("ОБОЗНАЧЕНИЕ", getModelNumder(), drw);
			LOG.info("Models is retrieved, renamed and renumbered");
		} catch (jxthrowable e) {
			LOG.error("Error retrieving models", e);
		}
	}
	
	public Model getPartFromSession() {
		LOG.info("Part is got");
		return part;
	}
	
	public Model getDrwFromSession() {
		LOG.info("Drw is got");
		return drw;
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
