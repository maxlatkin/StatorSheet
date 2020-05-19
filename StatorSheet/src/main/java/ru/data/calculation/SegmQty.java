package ru.data.calculation;

import ru.data.DataStore;

public class SegmQty implements Calculated {
	
	private static SegmQty instance;
    private SegmQty(){}
    public static SegmQty getInstance() {
        if (instance == null) {
            instance = new SegmQty();
        }
        return instance;
    }
    
	@Override
	public void calculate() {
		int slotQty = DataStore.getSlotQty();
		int slotStep = DataStore.getSlotStep();
		boolean cuttingTurned = DataStore.isCuttingTurned();
		int numbOfPolePairs = Math.floorDiv(slotQty, slotStep);
		int segmQty = 3;
		int numbOfFulfilledConditions = 0;
		while (numbOfFulfilledConditions < 4) {
			numbOfFulfilledConditions = 0;
			segmQty++;
			if (slotQty % segmQty == 0) numbOfFulfilledConditions++;
			if (numbOfPolePairs % segmQty != 0) numbOfFulfilledConditions++;
			if (segmQty % numbOfPolePairs != 0) numbOfFulfilledConditions++;
			if (cuttingTurned || DataStore.getExtDiam() * Math.sin(Math.toRadians(180.0 / segmQty)) 
					<= DataStore.getElectSteelRollWidth()) numbOfFulfilledConditions++;
		}
		DataStore.setSegmQty(segmQty);
	}
}
