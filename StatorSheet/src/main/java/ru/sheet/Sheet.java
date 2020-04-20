package ru.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcWindow.Window;

import ru.data.DataStore;
import ru.dimensions.DimAssignment;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.templates.TemplateModel;

public class Sheet {
	
	public static final Logger LOG = LoggerFactory.getLogger(Sheet.class);
	
	private Sheet() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void create() {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			LOG.info("Session received in {}", Sheet.class);
			TemplateModel templateModel = new TemplateModel(DataStore.TEMP_FILE, DataStore.MODELS_PATH, DataStore.MODEL_NAME);
			templateModel.createModel();
			LOG.info("Model is created");
			Solid currSolid = (Solid)session.GetModel(DataStore.MODEL_NAME, ModelType.MDL_PART);
			DimAssignment.assignDims();
			ExtrusionAddSym sheetThck = new ExtrusionAddSym();
			sheetThck.build(DataStore.SHEET_THCK, "EXT_SHEET", "SHEET", currSolid);
			LOG.info("sheetThck is built");
			ExtrusionCut slot = new ExtrusionCut();
			if (DataStore.SLOT_WITH_ROUND) {
				slot.build("EXT_SLOT", "SLOT_WITH_ROUND", currSolid);
			} else {
				slot.build("EXT_SLOT", "SLOT_WITHOUT_ROUND", currSolid);
			}
			LOG.info("slot is built");
			RotatPattern360 slotAr = new RotatPattern360("Z");
			slotAr.patternBuild(DataStore.SLOT_QTY, 1, "AR_SLOT", "EXT_SLOT", currSolid);
			LOG.info("slotAr is built");
			ExtrusionCut transformCoreToSheet = new ExtrusionCut();
			transformCoreToSheet.build("EXT_TRANSFORM", "TRANSFORM_CORE_TO_SHEET", currSolid);
			LOG.info("transformCoreToSheet is built");
			ExtrusionCut mark = new ExtrusionCut();
			mark.build("EXT_MARK", "MARK", currSolid);
			LOG.info("mark is built");
			
			session.CreateModelWindow(currSolid).Activate();
		} catch (jxthrowable e) {
			LOG.error("Failed to create sheet!", e);
		}
	}
}
