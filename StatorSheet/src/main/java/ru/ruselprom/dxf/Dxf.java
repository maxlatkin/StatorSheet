package ru.ruselprom.dxf;

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
import com.ptc.pfc.pfcServer.Server;
import com.ptc.pfc.pfcSession.Session;

import ru.ruselprom.wnc.Models;

public class Dxf {
	
	private Session session;
	private Model model;
	private static final Logger LOG = LoggerFactory.getLogger(Dxf.class);

	public Dxf(Session session, Model model) {
		this.session = session;
		this.model = model;
	}

	public void createWithNameOfModel() {
		try {
			Drawing drawOfSegm = (Drawing)Models.getInstance().getDxfTempFromSession();
	        DXFExportInstructions exportInstructions = pfcModel.DXFExportInstructions_Create();
	        String dxfName = session.GetCurrentDirectory() + model.GetFullName();
	        drawOfSegm.Export(dxfName, exportInstructions);
	        String dxfLog = dxfName + "_dxf__out.log.1";
	        deleteFile(dxfLog);
	        LOG.info("dxf is created");
		} catch (jxthrowable e) {
			LOG.error("Error creating dxf", e);
		}
	}

	public void addToModelInWS() {
		try {
			Server server = session.GetActiveServer();
			session.SetConfigOption("allow_import_file_extension", "dxf");
			String dxfForCopy = session.GetCurrentDirectory() + model.GetFullName() + ".dxf";
			String targetWorkspace = "wtws://" + server.GetAlias() + "/" + server.GetActiveWorkspace() + "/";
			session.CopyFileToWS(dxfForCopy, targetWorkspace, model.GetFileName());
			deleteFile(dxfForCopy);
			LOG.info("dxf is added to model in WS");
		} catch (jxthrowable e) {
			LOG.error("Error adding model to model in WS", e);
		}
	}
	
	private void deleteFile(String fileName) {
		try {
			Path path = Paths.get(fileName);
		    Files.delete(path);
		} catch (NoSuchFileException | DirectoryNotEmptyException e) {
			LOG.error("No Such File or Directory is not empty", e);
		} catch (IOException e) {
			LOG.error("Error deleting the file", e);
		}
	}
}
