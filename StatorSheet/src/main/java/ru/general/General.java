package ru.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;

import ru.assignment.SheetDimAssignment;
import ru.assignment.screw.ScrewDimAssignmentFactory;
import ru.building.Sheet;
import ru.building.TransformAndMark;
import ru.building.screw.ScrewFactory;
import ru.data.DataOperations;
import ru.data.DataStore;
import ru.data.DocumentsList;
import ru.drawing.DrawingDimensions;
import ru.exceptions.InputCheckException;
import ru.parameters.Params;
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
			
			DocumentsList.assignVarsToDataStoreByDoc("Creo_1");
			
			DataOperations.checkVars();
			DataOperations.calculateVars();
			
			Models.getInstance().retrieveToSessionWithRename();
			Solid currSolid = (Solid) Models.getInstance().getPartFromSession();
			Model currDrw =  Models.getInstance().getDrwFromSession();
			
			new SheetDimAssignment(currSolid).assign();
			Sheet.getInstance().build(currSolid);
			
			ScrewDimAssignmentFactory.getScrewDimAssignment(currSolid).assign();
			ScrewFactory.getScrew().build(currSolid);
			
			if (DataStore.getSegmQty() != 1) {
				TransformAndMark.getInstance().build(currSolid);
			}
			
			Params.setAllParams(currSolid);
			session.CreateModelWindow(currSolid).Activate();
			
			ProProgram.getInstance().addConditions(currSolid);
			
			new DrawingDimensions().setTo(currDrw);
			
			Models.getInstance().saveWithNewNumber(currSolid);
			Models.getInstance().saveWithNewNumber(currDrw);
		} catch (InputCheckException | NullPointerException | jxthrowable e) {
			LOG.error("Error in the General class!", e);
		}
	}
}
