package ru.dxf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDrawing.Drawing;
import com.ptc.pfc.pfcModel.DXFExportInstructions;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

import ru.ruselprom.base.Macro;
import ru.ruselprom.base.OrientViews;
import ru.ruselprom.drawing.DrawingCreator;
import ru.ruselprom.drawing.DrawingFiller;

public class Dxf {
	
	private static final Logger LOG = LoggerFactory.getLogger(Dxf.class);
	
	public void create(Model currModel) {
		try {
			DrawingCreator drawingCreator = new DrawingCreator(currModel);
			Drawing drawOfSegm = drawingCreator.createDrawing("kek.drw", "models\\StatorSheet\\drw_temp");
			DrawingFiller drawOfSegmFiller = new DrawingFiller(drawOfSegm);
	        drawOfSegmFiller.createView(1, OrientViews.СПЕРЕДИ, currModel);
	        Macro.execute(getMacroCode());
	        DXFExportInstructions exportInstructions = pfcModel.DXFExportInstructions_Create();
	        String dxfName = "models\\StatorSheet\\" + currModel.GetFullName();
	        drawOfSegm.Export(dxfName, exportInstructions);
	        Path path = Paths.get(dxfName + "_dxf__out.log.1");
	        try {
	            Files.delete(path);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	private String getMacroCode() throws jxthrowable {
		if (isCreoInRussian()) { 
			return "~ Command `ProCmdMdlTreeSearch` ;" +
                    "~ Input `selspecdlg0` `SelOptionRadio` `Заметка`;" +
                    "~ Activate `selspecdlg0` `SelectButton`;" +
                    "~ Command `ProCmdEditDelete`;";
		} else {
			return "~ Command `ProCmdMdlTreeSearch` ;" +
                    "~ Input `selspecdlg0` `SelOptionRadio` `Note`;" +
                    "~ Activate `selspecdlg0` `SelectButton`;" +
                    "~ Command `ProCmdEditDelete`;";
		}
	}
	private boolean isCreoInRussian() throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		String creoLang = session.GetEnvironmentVariable("PRO_LANG");
		return creoLang == null || creoLang.equals("RUSSIAN");
	}
}
