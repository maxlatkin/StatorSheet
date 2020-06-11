package ru.dxf;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDrawing.Drawing;
import com.ptc.pfc.pfcModel.DXFExportInstructions;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.pfcModel;

import ru.general.AppProperties;
import ru.wnc.Models;

public class Dxf {
	
	private static final Logger LOG = LoggerFactory.getLogger(Dxf.class);
	
	public void createWithNameOf(Model currModel) {
		try {
			Drawing drawOfSegm = (Drawing)Models.getInstance().getDxfTempFromSession();
	        DXFExportInstructions exportInstructions = pfcModel.DXFExportInstructions_Create();
	        String dxfName = AppProperties.DXF_PATH + File.separator + currModel.GetFullName();
	        drawOfSegm.Export(dxfName, exportInstructions);
	        deleteLogOfDxf(dxfName);
	        drawOfSegm.Erase();
		} catch (jxthrowable e) {
			LOG.error("Error creating dxf", e);
		}
	}

	private void deleteLogOfDxf(String dxfName) {
		Path path = Paths.get(dxfName + "_dxf__out.log.1");
		try {
		    Files.delete(path);
		} catch (NoSuchFileException | DirectoryNotEmptyException e) {
			LOG.error("No Such File or Directory is not empty", e);
		} catch (IOException e) {
			LOG.error("Error deleting the log of dxf", e);
		}
	}
}
