package ru.building.screw;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.general.ModelFeat;
import ru.ruselprom.fet.extrusions.add.ExtrusionAddSym;
import ru.ruselprom.fet.extrusions.cut.ExtrusionCut;
import ru.ruselprom.fet.patterns.RotatPattern360;
import ru.ruselprom.fet.patterns.TwoRotatPattern;

public abstract class Screw {
	public abstract void build(Solid currSolid);
	
	protected void buildScrew0102(Solid currSolid, ModelFeat solid, ModelFeat extSolid, ModelFeat arSolid,
			ModelFeat hole, ModelFeat extHole, ModelFeat arHole) throws jxthrowable {
		ExtrusionAddSym screwSolid = new ExtrusionAddSym();
		screwSolid.build(DataStore.getSheetThck(), extSolid.name(),
				solid.name(), currSolid);
		TwoRotatPattern screwAr = new TwoRotatPattern(ModelFeat.Y.name(), ModelFeat.Z.name());
		screwAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
				arSolid.name(), extSolid.name(), currSolid);
		ExtrusionCut screwHole = new ExtrusionCut();
		screwHole.build(extHole.name(), hole.name(), currSolid);
		screwAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(),
				arHole.name(), extHole.name(), currSolid);
		ModelFeat.deleteAllScrewExceptOf(currSolid, solid, hole);
	}
	
	protected void buildScrew03(Solid currSolid, ModelFeat hole, ModelFeat extHole, ModelFeat arHole) throws jxthrowable {
		ExtrusionCut screwHole = new ExtrusionCut();
		screwHole.build(extHole.name(), hole.name(), currSolid);
		TwoRotatPattern screwHoleAr = new TwoRotatPattern(ModelFeat.Y.name(), ModelFeat.Z.name());
		screwHoleAr.patternBuild(2, 180, DataStore.getSegmQty(), 360.0 / DataStore.getSegmQty(), 
				arHole.name(), extHole.name(), currSolid);
		ModelFeat.deleteAllScrewExceptOf(currSolid, hole, ModelFeat.SCREW_04_HOLE);
	}
	
	protected void buildScrew050607(Solid currSolid, ModelFeat hole, ModelFeat extHole, ModelFeat arHole) throws jxthrowable {
		ExtrusionCut screwHole = new ExtrusionCut();
		screwHole.build(extHole.name(), hole.name(), currSolid);
		RotatPattern360 screwAr = new RotatPattern360(ModelFeat.Z.name());
		screwAr.patternBuild(DataStore.getSegmQty() * DataStore.getScrewQty(), 1, 
				arHole.name(), extHole.name(), currSolid);
		ModelFeat.deleteAllScrewExceptOf(currSolid, hole);
	}
}
