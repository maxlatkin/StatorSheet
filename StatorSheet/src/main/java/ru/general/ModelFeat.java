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

import ru.ruselprom.fet.operations.FetOperations;

public enum ModelFeat {
	X,
	Y,
	Z,
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
	MARK;
	
	private static final Logger LOG = LoggerFactory.getLogger(ModelFeat.class);

	public static void deleteAllScrewExceptOf(Solid currSolid ,ModelFeat...exceptedFeatNames) {
		try {
			List<ModelFeat> deletedFeatNames = Stream.of(
					SCREW_01_SOLID_2, SCREW_01_HOLE_2,	SCREW_01_SOLID_4, SCREW_01_HOLE_4,
					SCREW_02_SOLID_2, SCREW_02_HOLE_2, SCREW_02_SOLID_4, SCREW_02_HOLE_4,
					SCREW_03_HOLE_2, SCREW_03_HOLE_4, SCREW_04_HOLE, SCREW_05_HOLE,
					SCREW_06_HOLE, SCREW_07_HOLE).collect(Collectors.toList());
			
			for (ModelFeat exceptedFeatName : exceptedFeatNames) {
				deletedFeatNames.remove(deletedFeatNames.indexOf(exceptedFeatName));
			}
			
			ArrayList<String> listNames = new ArrayList<>();
			for (ModelFeat deletedFeatName : deletedFeatNames) {
				listNames.add(deletedFeatName.name());
			}
			
			FetOperations.deleteFeature(currSolid, listNames.toArray(new String[deletedFeatNames.size()]));
			String notDelFeatNames = Arrays.toString(exceptedFeatNames);
			LOG.info("All screw features removed except {}", notDelFeatNames);
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in deleteAllSectionExcept(...)", e);
		}
	}
}
