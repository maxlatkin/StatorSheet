package ru.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.data.DataStore;
import ru.ruselprom.fet.operations.FetOperations;

public enum ModelFeat {
	X,
	Y,
	Z,
	SHEET,
	EXT_SHEET,
	SLOT,
	EXT_SLOT,
	AR_SLOT,
	VENT_SLOT,
	EXT_VENT_SLOT,
	AR_VENT_SLOT,
	ROUND,
	AR_ROUND,
	TRANSFORM_CORE_TO_SHEET,
	EXT_TRANSFORM,
	SCREW_01_SOLID_2,
	SCREW_01_HOLE_2,
	SCREW_01_SOLID_4,
	SCREW_01_HOLE_4,
	SCREW_02_SOLID_2,
	SCREW_02_HOLE_2,
	SCREW_02_SOLID_4,
	SCREW_02_HOLE_4,
	SCREW_03_HOLE_2,
	SCREW_03_HOLE_4,
	SCREW_04_HOLE,
	SCREW_05_HOLE,
	SCREW_06_HOLE,
	SCREW_07_HOLE,
	EXT_SCREW_01_SOLID_2,
	AR_SCREW_01_SOLID_2,
	EXT_SCREW_01_HOLE_2,
	AR_SCREW_01_HOLE_2,
	EXT_SCREW_01_SOLID_4,
	AR_SCREW_01_SOLID_4,
	EXT_SCREW_01_HOLE_4,
	AR_SCREW_01_HOLE_4,
	EXT_SCREW_02_SOLID_2,
	AR_SCREW_02_SOLID_2,
	EXT_SCREW_02_HOLE_2,
	AR_SCREW_02_HOLE_2,
	EXT_SCREW_02_SOLID_4,
	AR_SCREW_02_SOLID_4,
	EXT_SCREW_02_HOLE_4,
	AR_SCREW_02_HOLE_4,
	EXT_SCREW_03_HOLE_2,
	AR_SCREW_03_HOLE_2,
	EXT_SCREW_03_HOLE_4,
	AR_SCREW_03_HOLE_4,
	EXT_SCREW_04_HOLE,
	AR_SCREW_04_HOLE,
	EXT_SCREW_05_HOLE,
	AR_SCREW_05_HOLE,
	EXT_SCREW_06_HOLE,
	AR_SCREW_06_HOLE,
	EXT_SCREW_07_HOLE,
	AR_SCREW_07_HOLE,
	MARK,
	EXT_MARK;
	
	private static final Logger LOG = LoggerFactory.getLogger(ModelFeat.class);

	public static void deleteScrewExceptTypes(Solid currSolid, int...typesOfScrew) {
		List<String> feats = new ArrayList<>();
		int screwQty = DataStore.getScrewQty();
		for (int typeOfScrew : typesOfScrew) {
			if (typeOfScrew == 1 && screwQty == 2) {
				feats.add(SCREW_01_SOLID_2.name());
				feats.add(SCREW_01_HOLE_2.name());
			} else if (typeOfScrew == 1 && screwQty == 4) {
				feats.add(SCREW_01_SOLID_4.name());
				feats.add(SCREW_01_HOLE_4.name());
			} else if (typeOfScrew == 2 && screwQty == 2) {
				feats.add(SCREW_02_SOLID_2.name());
				feats.add(SCREW_02_HOLE_2.name());
			} else if (typeOfScrew == 2 && screwQty == 4) {
				feats.add(SCREW_02_SOLID_4.name());
				feats.add(SCREW_02_HOLE_4.name());
			} else if (typeOfScrew == 3 && screwQty == 2) {
				feats.add(SCREW_03_HOLE_2.name());
			} else if (typeOfScrew == 3 && screwQty == 4) {
				feats.add(SCREW_03_HOLE_4.name());
			} else if (DataStore.isScrew04Exist()) {
				feats.add(SCREW_04_HOLE.name());
			} else if (typeOfScrew == 5) {
				feats.add(SCREW_05_HOLE.name());
			} else if (typeOfScrew == 6) {
				feats.add(SCREW_06_HOLE.name());
			} else if (typeOfScrew == 7) {
				feats.add(SCREW_07_HOLE.name());
			}
		}
		deleteAllScrewExceptOf(currSolid, feats.toArray(new String[feats.size()]));
	}
	
	private static void deleteAllScrewExceptOf(Solid currSolid, String...exceptedFeatNames) {
		try {
			List<String> deletedFeatNames = Stream.of(
		            SCREW_01_SOLID_2.name(), SCREW_01_HOLE_2.name(),
		            SCREW_01_SOLID_4.name(), SCREW_01_HOLE_4.name(),
		            SCREW_02_SOLID_2.name(), SCREW_02_HOLE_2.name(),
		            SCREW_02_SOLID_4.name(), SCREW_02_HOLE_4.name(),
		            SCREW_03_HOLE_2.name(), SCREW_03_HOLE_4.name(),
		            SCREW_04_HOLE.name(), SCREW_05_HOLE.name(),
		            SCREW_06_HOLE.name(), SCREW_07_HOLE.name()).
		            collect(Collectors.toList());
			String notDelFeatNames = Arrays.toString(exceptedFeatNames);
			LOG.info("All screw features removed except {}", notDelFeatNames);
			LOG.info("All screw features removed except {}", deletedFeatNames);
			for (String exceptedFeatName : exceptedFeatNames) {
				deletedFeatNames.remove(deletedFeatNames.indexOf(exceptedFeatName));
			}
			FetOperations.deleteFeature(currSolid, deletedFeatNames.toArray(new String[deletedFeatNames.size()]));
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in deleteAllSectionExcept(...)", e);
		}
	}
}
