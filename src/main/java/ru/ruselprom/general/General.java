package ru.ruselprom.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.assignment.screw.ScrewDimAssignmentBuilder;
import ru.ruselprom.assignment.sheet.SheetDimAssignmentFactory;
import ru.ruselprom.building.TransformAndMark;
import ru.ruselprom.building.screw.ScrewBuilder;
import ru.ruselprom.building.sheet.SheetFactory;
import ru.ruselprom.data.DataStore;
import ru.ruselprom.drawing.DrawingDimensions;
import ru.ruselprom.dxf.Dxf;
import ru.ruselprom.exceptions.RetrieveModelException;
import ru.ruselprom.parameters.Params;
import ru.ruselprom.wnc.Models;

public class General {
	
	private static final Logger LOG = LoggerFactory.getLogger(General.class);
	
	private General() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void execute(SheetType sheetType) {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			LOG.info("Session received in {}", General.class);
						
			Models.getInstance().retrieveToSessionWithRenameAndRenumber();
			Solid currSolid = (Solid) Models.getInstance().getPartFromSession();
			Model currDrw =  Models.getInstance().getDrwFromSession();
			
			SheetDimAssignmentFactory.getSheetDimAssignment(currSolid, sheetType).assign();
			SheetFactory.getSheet(currSolid, sheetType).build();
			
			ScrewDimAssignmentBuilder.assign(currSolid);
			ScrewBuilder.build(currSolid);
			
			if (DataStore.getSegmQty() != 1) {
				new TransformAndMark(currSolid).build();
				ProProgram.getInstance().addConditions(currSolid);
			}
			
			Params.setAllParams(currSolid, sheetType);
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
			currSolid.Display();
		} catch (RetrieveModelException | NullPointerException | jxthrowable e) {
			LOG.error("Error in the General class!", e);
		}
	}
}
