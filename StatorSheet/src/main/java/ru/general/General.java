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
import ru.drawing.DrawingDimensions;
import ru.dxf.Dxf;
import ru.exceptions.InputCheckException;
import ru.parameters.Params;
import ru.wnc.Models;
import ru.wnc.documents.DocFactory;
import ru.wnc.documents.DocumentTypes;

public class General {
	
	private static final Logger LOG = LoggerFactory.getLogger(General.class);
	private static String noteName = null; 
	private static String stoName = null; 
	private static String resultsName = null; 
	
	public static void setNoteName(String noteName) {
		General.noteName = noteName;
	}

	public static void setStoName(String stoName) {
		General.stoName = stoName;
	}

	public static void setResultsName(String resultsName) {
		General.resultsName = resultsName;
	}

	private General() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void execute() {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			LOG.info("Session received in {}", General.class);
			
			DocFactory docFactory = new DocFactory();
			docFactory.getDocument(DocumentTypes.CALC_AND_WIND_NOTE).assignToDataStore(noteName);
			docFactory.getDocument(DocumentTypes.STO).assignToDataStore(stoName);
			docFactory.getDocument(DocumentTypes.MECH_CALC_RESULTS).assignToDataStore(resultsName);
			
			DataOperations.checkVars();
			DataOperations.calculateVars();
			
			Models.getInstance().retrieveToSessionWithRenameAndRenumber();
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
			
//			currSolid.Save();
//			currDrw.Save();
			
//			new Dxf().create(currSolid);
		} catch (InputCheckException | NullPointerException | jxthrowable e) {
			LOG.error("Error in the General class!", e);
		}
	}
}
