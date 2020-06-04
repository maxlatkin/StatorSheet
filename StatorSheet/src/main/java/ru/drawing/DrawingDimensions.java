package ru.drawing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.DimTolerance;
import com.ptc.pfc.pfcDimension.pfcDimension;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.wfc.wfcDimension.WDimension;

import ru.data.DataStore;

public class DrawingDimensions {
	
	private static final Logger LOG = LoggerFactory.getLogger(DrawingDimensions.class);
	private Model drw;
	
	public void setTo(Model currDrw) {
		try {
			drw = currDrw;
			setScrew01Dims();
			setScrew02Dims();
			setScrew03And04Dims();
			setScrew07Dims();
			setSlotDims();
			setMarkDim();
			LOG.info("Dimensions are set");
		} catch (jxthrowable e) {
			LOG.error("Setting dimensions error", e);
		}
	}
	private void setScrew01Dims() throws jxthrowable {
		setValueToDimById(110, DataStore.getScrew01ExtRads().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.1));
		setValueToDimById(113, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.1));
		setValueToDimById(116, DataStore.getScrew0102FarTopPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(119, DataStore.getScrew0102FarBottomPoints().get(DataStore.getStudHoleDiam()), null);
		setValueToDimById(122, DataStore.getScrew01MidRads().get(DataStore.getStudHoleDiam()), null);
		getDimById(122).SetAsBasic(true);
	}
	private void setScrew02Dims() throws jxthrowable {
		setValueToDimById(146, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()),pfcDimension.DimTolSymmetric_Create(0.1));
		setValueToDimById(149, DataStore.getScrew0102FarTopPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(152, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(155, DataStore.getScrew0102FarTopPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(159, DataStore.getScrew0102FarBottomPoints().get(DataStore.getStudHoleDiam()), null);
		setValueToDimById(162, DataStore.getScrew0102FarBottomPoints().get(DataStore.getStudHoleDiam()), null);
		setValueToDimById(163, DataStore.getExtDiam() / 2, pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(164, DataStore.getScrew02ExtRads().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.1));
	}
	private void setScrew03And04Dims() throws jxthrowable {
		setValueToDimById(278, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(281, DataStore.getScrew010203NearestPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.1));
		setValueToDimById(282, DataStore.getScrew0304IntRads().get(DataStore.getStudHoleDiam()), null);
		getDimById(282).SetAsBasic(true);
		setValueToDimById(310, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(313, DataStore.getScrew020304NearestPoints().get(DataStore.getStudHoleDiam()), pfcDimension.DimTolSymmetric_Create(0.5));
		setValueToDimById(315, DataStore.getScrew0304IntRads().get(DataStore.getStudHoleDiam()), null);
		getDimById(315).SetAsBasic(true);
	}
	private void setScrew07Dims() throws jxthrowable {
		setValueToDimById(245, 60, null);
		setValueToDimById(249, DataStore.getScrew07Hght() + DataStore.getScrew07Gap(), pfcDimension.DimTolSymmetric_Create(0.1));
	}
	private void setSlotDims() throws jxthrowable {
		setValueToDimById(0, DataStore.getRoundWedgeOfSlot(), null);
		setValueToDimById(1, DataStore.getRoundWedgeOfSlot(), null);
		setValueToDimById(4, DataStore.getRoundAtTopOfSlot(), null);
		setValueToDimById(5, DataStore.getRoundAtTopOfSlot(), null);
		setValueToDimById(8, DataStore.getWedgeAngleTop(), null);
		setValueToDimById(11, DataStore.getWedgeAngleBottom(),pfcDimension.DimTolSymmetric_Create(1.0/6));
		setValueToDimById(12, DataStore.getWedgeThck(), null);
		setValueToDimById(14, DataStore.getRoundAtBottomOfSlot(), null);
	}
	private void setMarkDim() throws jxthrowable {
		setValueToDimById(41, DataStore.getSegmPruning(), pfcDimension.DimTolPlusMinus_Create(0.1, 0.0));
		setValueToDimById(42, DataStore.getMarkRadius(), null);
		setValueToDimById(43, DataStore.getMarkRound(), null);
	}
	private void setValueToDimById(int id, double value, DimTolerance tolerance) throws jxthrowable {
		getDimById(id).SetOverrideValue(value);
		if (tolerance != null) {
			getDimById(id).SetTolerance(tolerance);
		}
	}
	private WDimension getDimById(int id) throws jxthrowable {
		return (WDimension)drw.GetItemById(ModelItemType.ITEM_DIMENSION, id);
	}
}
