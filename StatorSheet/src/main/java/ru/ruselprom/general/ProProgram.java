package ru.ruselprom.general;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.ProgramExportInstructions;
import com.ptc.pfc.pfcModel.ProgramImportInstructions;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.base.Regeneration;
import ru.ruselprom.data.DataStore;

public class ProProgram {
	
	private String fileName;
	private String startOfCondition = "IF AA_STATOR_CORE_SHEET_MODE == \"PRT\"";
	private String endOfCondition = "ENDIF";
	private String startOfBlock = "ADD FEATURE";
	private String endOfBlock = "END ADD";
	private String endOfRoundCode = "REF GENERAL PATTERN";
	private static ProProgram instance;
	private static final Logger LOG = LoggerFactory.getLogger(ProProgram.class);
	
    private ProProgram(){}
    
    public static ProProgram getInstance() {
        if (instance == null) {
            instance = new ProProgram();
        }
        return instance;
    }
	
	public void addConditions(Solid currSolid) {
		try {
			setFileName();
			exportToFile(currSolid);
			overwriteFile();
			importFile(currSolid);
			Regeneration.regenerateSolid(currSolid);
			Files.delete(Paths.get(fileName));
			LOG.info("Conditions added to the ProProgram file");
		} catch (NullPointerException | IOException | jxthrowable e) {
			LOG.error("Error adding conditions", e);
		}
	}
	
	private void setFileName() {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			fileName = session.GetCurrentDirectory() + "stator_sheet.pls";
		} catch (jxthrowable e) {
			LOG.error("Error setting file name", e);
		}
	}

	private void importFile(Solid currSolid) throws jxthrowable {
		ProgramImportInstructions importInstructions = pfcModel.ProgramImportInstructions_Create();
		currSolid.Import(fileName, importInstructions);
	}
	private void exportToFile(Solid currSolid) throws jxthrowable {
		ProgramExportInstructions exportInstructions = pfcModel.ProgramExportInstructions_Create();
		currSolid.Export(fileName, exportInstructions);
	}
	private void overwriteFile() {
		File file = new File(fileName);
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8))) {
			String line;
			int lineNumber = 0;
			int transNumber = 0;
			int markNumber = 0;
			int roundStartNumber = 0;
			ArrayList<String> text = new ArrayList<>(4000);
			while((line = reader.readLine()) != null ) {
				text.add(line);
				if (line.contains(ModelFeat.EXT_TRANSFORM.name())) {
					transNumber = lineNumber;
				}
				if (line.contains(ModelFeat.EXT_MARK.name())) {
					markNumber = lineNumber;
				}
				if (line.contains(ModelFeat.AR_ROUND.name())) {
					roundStartNumber = lineNumber;
				}
				lineNumber++;
			}
			addCodeBlockToCondition(transNumber, text);
			addCodeBlockToCondition(markNumber, text);
			if (DataStore.isSlotWithRound()) {
				addRoundCodeToCondition(roundStartNumber, text);
			}
			writeToFile(file, text);
		} catch (IOException | IndexOutOfBoundsException e) {
			LOG.error("File overwrite error", e);
		}
	}

	private void addRoundCodeToCondition(int roundStartNumber, ArrayList<String> text) {
		while(!text.get(roundStartNumber).contains(startOfBlock)) {
			roundStartNumber--;
		}
		text.add(roundStartNumber, startOfCondition);
		int roundEndNumber = 0;
		for (int i = text.size() - 1; i >= 0; i--) {
			if (text.get(i).contains(endOfRoundCode)) {
				roundEndNumber = i;
				break;
			}
		}
		while(!text.get(roundEndNumber).contains(endOfBlock)) {
			roundEndNumber++;
		}
		text.add(roundEndNumber + 1, endOfCondition);
	}
	private void addCodeBlockToCondition(int transNumber, ArrayList<String> text) {
		while(!text.get(transNumber).contains(startOfBlock)) {
			transNumber--;
		}
		text.add(transNumber, startOfCondition);
		while(!text.get(transNumber).contains(endOfBlock)) {
			transNumber++;
		}
		text.add(transNumber + 1, endOfCondition);
	}
	private void writeToFile(File file, ArrayList<String> text) {
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file),StandardCharsets.UTF_8))) {
			for (int i = 0; i < text.size(); i++) {
				bw.write(text.get(i));
				bw.newLine();
			}
		} catch (IOException e) {
			LOG.error("Error writing file", e);
		}
	}
}
