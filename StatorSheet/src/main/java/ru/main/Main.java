package ru.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.SheetDimAssignment;
import ru.assignment.screw.ScrewDimAssignmentFactory;
import ru.building.Sheet;
import ru.building.screw.ScrewFactory;
import ru.data.DataStore;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.info.Info;
import ru.ruselprom.templates.TemplateModel;

public class Main {
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private Main() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void execute() {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			LOG.info("Session received in {}", Main.class);
			TemplateModel templateModel = new TemplateModel(DataStore.getTempFile(), DataStore.getModelsPath());
			Solid currSolid = (Solid) templateModel.retrieve();
			LOG.info("Model is retrieved");
			SheetDimAssignment.assign(currSolid);
			Sheet.build(currSolid);
			
			ScrewDimAssignmentFactory.getScrewDimAssignment().assign(currSolid);
			ScrewFactory.getScrew().build(currSolid);
			
			ExtrusionCut transformCoreToSheet = new ExtrusionCut();
			transformCoreToSheet.build("EXT_TRANSFORM", "TRANSFORM_CORE_TO_SHEET", currSolid);
			LOG.info("transformCoreToSheet is built");
			ExtrusionCut mark = new ExtrusionCut();
			mark.build("EXT_MARK", "MARK", currSolid);
			Info.getDimensionsInfoIn("MARK", currSolid);
			LOG.info("mark is built");
			session.CreateModelWindow(currSolid).Activate();
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in the main class!", e);
		}
	}
}
