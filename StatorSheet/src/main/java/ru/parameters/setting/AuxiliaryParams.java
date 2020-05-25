package ru.parameters.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcGeometry.Edges;
import com.ptc.pfc.pfcGeometry.Surface;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.wfc.wfcGeometry.WSurface;

import ru.data.DataStore;
import ru.general.ModelFeat;
import ru.parameters.ModelParamNames;
import ru.ruselprom.parameters.Parameters;

public class AuxiliaryParams implements ParamsSetting {
	private static AuxiliaryParams instance;
	private static final Logger LOG = LoggerFactory.getLogger(AuxiliaryParams.class);
	
	private AuxiliaryParams() {}
	
	public static AuxiliaryParams getInstance() {
        if (instance == null) {
            instance = new AuxiliaryParams();
        }
        return instance;
    }

	@Override
	public void setValue(Model currModel) {
		try {
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_05_CORNER.name(), true, currModel);
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_06_CORNER.name(), true, currModel);
			Parameters.setBoolParamValue(ModelParamNames.AA_STATOR_CORE_SCREW_07_CORNER.name(), true, currModel);
			Parameters.setStringParamValue(ModelParamNames.AA_STATOR_CORE_SEGM_ROLLING.name(), DataStore.getSegmRolling(), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SLOT_WDG_FLT_W.name(), getSlotWdgFltW(currModel), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SHEET_AREA.name(), getSheetArea(currModel), currModel);
			Parameters.setDoubleParamValue(ModelParamNames.AA_STATOR_CORE_SHEET_PERIMETER.name(), getSheetPerimeter(currModel), currModel);
			LOG.info("Auxiliary parameters set");
		} catch (NullPointerException | jxthrowable e) {
			LOG.error("Error in setting auxiliary parameters", e);
		} 
	}
	private double getSlotWdgFltW(Model currModel) throws jxthrowable {
		double distBetwCenters = ((Dimension)((Solid)currModel).GetFeatureByName(ModelFeat.SLOT_WITHOUT_ROUND.name()).
				ListSubItems(ModelItemType.ITEM_DIMENSION).get(7)).GetDimValue();
		return distBetwCenters + DataStore.getSlotRoundBottom();
	}
	private double getSheetArea(Model currModel) throws jxthrowable {
		return ((Surface)((Solid)currModel).GetFeatureByName(ModelFeat.EXT_SHEET.name()).
				ListSubItems(ModelItemType.ITEM_SURFACE).get(0)).EvalArea() / 1e+6;
	}
	private double getSheetPerimeter(Model currModel) throws jxthrowable {
		double value = 0;
		Edges edges = ((WSurface)((Solid)currModel).GetFeatureByName(ModelFeat.EXT_SHEET.name()).
				ListSubItems(ModelItemType.ITEM_SURFACE).get(0)).ListContours().get(0).ListElements();
		for (int i = 0; i < edges.getarraysize(); i++) {
			value += edges.get(i).EvalLength();
		}
		return value;
	}
}
