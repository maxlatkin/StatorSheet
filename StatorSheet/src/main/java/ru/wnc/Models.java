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

public class Models {
	
	private static Models instance;
	private Session session;
	private String modelName;
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
			session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			ModelDescriptor modelDescriptor = pfcModel.ModelDescriptor_Create(ModelType.MDL_DRAWING, DataStore.getTempDrw(), null);
			session.RetrieveModel(modelDescriptor);
			String partName = getName() + ".prt";
			session.GetModelFromFileName(DataStore.getTempPart()).Rename(partName, false);
			String drwName = getName() + ".drw";
			session.GetModelFromFileName(DataStore.getTempDrw()).Rename(drwName, false);
			LOG.info("Models is retrieved");
		} catch (jxthrowable e) {
			LOG.error("Error retrieving models", e);
		}
	}
	
	public Model getPart() {
		try {
			Model part = session.GetModel(getName(), ModelType.MDL_PART);
			LOG.info("Part is got");
			return part;
		} catch (jxthrowable e) {
			LOG.error("Error getting part", e);
			return null;
		}
	}
	
	public Model getDrw() {
		try {
			Model drw = session.GetModel(getName(), ModelType.MDL_DRAWING);
			LOG.info("Drw is got");
			return drw;
		} catch (jxthrowable e) {
			LOG.error("Error getting drw", e);
			return null;
		}
	}
	
	private String getName() {
		if (modelName == null) {
			modelName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SS").format(new Date());
		}
		return modelName;
	}
}
