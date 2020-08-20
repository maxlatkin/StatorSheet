package ru.ruselprom.building;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.general.ModelFeat;

public class TransformAndMark implements Buildable {
	
	private static final Logger LOG = LoggerFactory.getLogger(TransformAndMark.class);
	private Solid currSolid;

	public TransformAndMark(Solid currSolid) {
		this.currSolid = currSolid;
	}

	@Override
	public void build() {
		try {
			ExtrusionCut transformCoreToSheet = new ExtrusionCut();
			transformCoreToSheet.build(ModelFeat.EXT_TRANSFORM.name(), ModelFeat.TRANSFORM_CORE_TO_SHEET.name(), currSolid);
			LOG.info("transformCoreToSheet is built");
			ExtrusionCut mark = new ExtrusionCut();
			mark.build(ModelFeat.EXT_MARK.name(), ModelFeat.MARK.name(), currSolid);
			LOG.info("mark is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in building TransformAndMark", e);
		}
	}
}
