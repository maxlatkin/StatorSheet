package ru.ruselprom.assignment;

import java.util.Map;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.general.ModelFeat;

public abstract class DimAssignment {
	
	protected Solid currSolid;
	
	public DimAssignment(Solid currSolid) {
		this.currSolid = currSolid;
	}

	public abstract void assign();
	
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
