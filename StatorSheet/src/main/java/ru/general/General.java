package ru.general;

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
import ru.data.DocumentsList;
import ru.data.calculation.ScrewShift;
import ru.data.calculation.SlotHghtToWdg;
import ru.data.check.ExtAndIntDiams;
import ru.data.check.SlotStepAndQty;
import ru.drawing.DrawingDimensions;
import ru.exceptions.InputCheckException;
import ru.parameters.Params;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.wnc.Models;

public class General {
	
	private static final Logger LOG = LoggerFactory.getLogger(General.class);
	
	private General() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void execute() {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			LOG.info("Session received in {}", General.class);
			
			DocumentsList.assignDataToDataStoreByDoc("Creo_1");
			new ExtAndIntDiams().check();
			new SlotStepAndQty().check();
			ScrewShift.getInstance().calculate();
			SlotHghtToWdg.getInstance().calculate();
			
			Models.getInstance().retrieve();
			Solid currSolid = (Solid) Models.getInstance().getPart();
			LOG.info("Model is retrieved");
			new SheetDimAssignment(currSolid).assign();
			Sheet.build(currSolid);
			
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid).assign();
			ScrewFactory.getScrew().build(currSolid);
			
			if (DataStore.getSegmQty() != 1) {
				ExtrusionCut transformCoreToSheet = new ExtrusionCut();
				transformCoreToSheet.build(ModelFeat.EXT_TRANSFORM.name(), ModelFeat.TRANSFORM_CORE_TO_SHEET.name(), currSolid);
				LOG.info("transformCoreToSheet is built");
				ExtrusionCut mark = new ExtrusionCut();
				mark.build(ModelFeat.EXT_MARK.name(), ModelFeat.MARK.name(), currSolid);
				LOG.info("mark is built");
			}
			
			Params.setAllParams(currSolid);
			session.CreateModelWindow(currSolid).Activate();
			
			ProProgram.getInstance().addConditions(currSolid);
			
			new DrawingDimensions().set(Models.getInstance().getDrw());
		} catch (InputCheckException | NullPointerException | jxthrowable e) {
			LOG.error("Error in the General class!", e);
		}
	}
}
