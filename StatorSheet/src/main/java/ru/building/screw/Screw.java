package ru.building.screw;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcModelItem.ModelItems;
import com.ptc.pfc.pfcSolid.Solid;

import ru.building.Buildable;
import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddRef;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.fet.patterns.TwoRotatPattern;

public abstract class Screw implements Buildable {
	protected Solid currSolid;
	
	public Screw(Solid currSolid) {
		this.currSolid = currSolid;
	}

	protected void buildScrew0102(Solid currSolid, ModelFeat solid, ModelFeat extSolid, ModelFeat arSolid,
			ModelFeat hole, ModelFeat extHole, ModelFeat arHole) throws jxthrowable {
		ExtrusionAddRef screwSolid = new ExtrusionAddRef();
		ModelItems items = currSolid.GetFeatureByName(ModelFeat.EXT_SHEET.name()).ListSubItems(ModelItemType.ITEM_SURFACE);
		screwSolid.build(extSolid.name(), solid.name(), items.get(0), items.get(1), currSolid);
		TwoRotatPattern screwAr = new TwoRotatPattern(ModelFeat.Y.name(), ModelFeat.Z.name());
		screwAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
				arSolid.name(), extSolid.name(), currSolid);
		ExtrusionCut screwHole = new ExtrusionCut();
		screwHole.build(extHole.name(), hole.name(), currSolid);
		screwAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
				arHole.name(), extHole.name(), currSolid);
	}
	
	protected void buildScrew03(Solid currSolid, ModelFeat hole, ModelFeat extHole, ModelFeat arHole) throws jxthrowable {
		ExtrusionCut screwHole = new ExtrusionCut();
		screwHole.build(extHole.name(), hole.name(), currSolid);
		TwoRotatPattern screwHoleAr = new TwoRotatPattern(ModelFeat.Y.name(), ModelFeat.Z.name());
		screwHoleAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(), 
				arHole.name(), extHole.name(), currSolid);
	}
	
	protected void buildScrew050607(Solid currSolid, ModelFeat hole, ModelFeat extHole, ModelFeat arHole) throws jxthrowable {
		ExtrusionCut screwHole = new ExtrusionCut();
		screwHole.build(extHole.name(), hole.name(), currSolid);
		RotatPattern360 screwAr = new RotatPattern360(ModelFeat.Z.name());
		int screwQty;
		if (DataStore.getTypeOfScrew() / 10 == 0) {
			screwQty = DataStore.getTotalScrewQty();
		} else {
			screwQty = DataStore.getTotalSecondScrewQty();
		}
		screwAr.patternBuild(screwQty, 1, arHole.name(), extHole.name(), currSolid);
	}
}
