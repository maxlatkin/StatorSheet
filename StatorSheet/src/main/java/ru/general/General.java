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
import ru.assignment.screw.ScrewDimAssignmentBuilder;
import ru.building.Sheet;
import ru.building.TransformAndMark;
import ru.building.screw.ScrewBuilder;
import ru.data.DataStore;
import ru.drawing.DrawingDimensions;
import ru.dxf.Dxf;
import ru.exceptions.InputCheckException;
import ru.exceptions.RetrieveModelException;
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
			
			Models.getInstance().retrieveToSessionWithRenameAndRenumber();
			Solid currSolid = (Solid) Models.getInstance().getPartFromSession();
			Model currDrw =  Models.getInstance().getDrwFromSession();
			
			new SheetDimAssignment(currSolid).assign();
			Sheet.getInstance().build(currSolid);
			
			ScrewDimAssignmentBuilder.assign(currSolid);
			ScrewBuilder.build(currSolid);
			
			if (DataStore.getSegmQty() != 1) {
				TransformAndMark.getInstance().build(currSolid);
				ProProgram.getInstance().addConditions(currSolid);
			}
			
			Params.setAllParams(currSolid);
			new DrawingDimensions().setTo(currDrw);
			
			currSolid.Save();
			currDrw.Save();
			
			if (DataStore.isSlotWithRound()) {
				Dxf dxf = new Dxf(session, currSolid);
				dxf.createWithNameOfModel();
				dxf.addToModelInWS();
			}
			Models.getInstance().getDxfTempFromSession().Erase();
			session.CreateModelWindow(currSolid).Activate();
		} catch (InputCheckException | RetrieveModelException | NullPointerException | jxthrowable e) {
			LOG.error("Error in the General class!", e);
		}
	}
}
