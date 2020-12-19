package ru.ruselprom.building.sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.data.DataStore;
import ru.ruselprom.lib.fet.operations.FetOperations;
import ru.ruselprom.lib.fet.round.RadiusAndEdgeIndices;
import ru.ruselprom.lib.fet.round.Round;
import ru.ruselprom.general.ModelFeat;

public class VentSheet extends Sheet {
	
	public VentSheet(Solid currSolid) {
		super(currSolid);
	}

	private static final Logger LOG = LoggerFactory.getLogger(VentSheet.class);

	@Override
	public void build() {
		try {
			buildSheetBody();
			buildSlot(ModelFeat.EXT_VENT_SLOT, ModelFeat.VENT_SLOT);
			buildSlotPatternWithRoundOrNot(DataStore.isSlotWithRound(), ModelFeat.AR_VENT_SLOT, ModelFeat.EXT_VENT_SLOT);
			FetOperations.deleteFeature(currSolid, ModelFeat.SLOT.name());
			LOG.info("VentSheet is built");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in creating VentSheet!", e);
		}
	}

	@Override
	protected void buildRound() {
		try {
			int[] indices = {18, 19, 24, 25};
			Round round = new Round();
			RadiusAndEdgeIndices radiusAndEdgeIndices = new RadiusAndEdgeIndices(DataStore.getRoundAtBottomOfSlot(), indices);
			round.build(ModelFeat.ROUND.name(), ModelFeat.EXT_VENT_SLOT.name(), currSolid, radiusAndEdgeIndices);
		} catch (jxthrowable e) {
			LOG.error("Error building round", e);
		}
	}
}
