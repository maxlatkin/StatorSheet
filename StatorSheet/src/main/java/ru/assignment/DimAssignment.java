package ru.assignment;

import java.util.Map;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.externaldata.DataStore;
import ru.general.ModelFeat;

public abstract class DimAssignment {
	
	protected Solid currSolid;
	
	public DimAssignment(Solid currSolid) {
		this.currSolid = currSolid;
	}

	public abstract void assign();
	
	protected double getQuarterSegmAngle() {
		return 360.0 / DataStore.getSegmQty() / 4;
	}
	
	protected double getOneAndHalfStepScrew() {
		return 360.0 / (DataStore.getSegmQty() * DataStore.getScrewQty()) * 1.5;
	}
	
	protected double getHalfStepScrew() {
		return 360.0 / (DataStore.getSegmQty() * DataStore.getScrewQty()) * 0.5;
	}
	
	protected void setArrayOfDimValue(ModelFeat sectionName, Map<Integer, Double> indexAndValue) throws jxthrowable {
		for (Map.Entry<Integer, Double> pair: indexAndValue.entrySet()) {
			setDimValue(sectionName, pair.getKey(), pair.getValue());
		}
	}
	
	protected void setDimValue(ModelFeat sectionName, int index, double value) throws jxthrowable {
		((Dimension)currSolid.GetFeatureByName(sectionName.name()).
				ListSubItems(ModelItemType.ITEM_DIMENSION).get(index)).SetDimValue(value);
	}
}
