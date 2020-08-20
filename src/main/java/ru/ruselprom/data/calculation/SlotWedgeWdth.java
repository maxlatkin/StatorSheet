package ru.ruselprom.data.calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ruselprom.data.DataStore;

public class SlotWedgeWdth implements Calculable {

	private static final Logger LOG = LoggerFactory.getLogger(SlotWedgeWdth.class);
	
	@Override
	public double calculate() {
		double triangleHeight = DataStore.getWedgeThck() * 
				Math.sin(Math.toRadians(DataStore.getWedgeAngleTop()))*
				Math.cos(Math.toRadians(DataStore.getWedgeAngleTop()));
		double slotWedgeWdth = DataStore.getSlotWdth() + 2 * triangleHeight;
		LOG.info("SlotWedgeWdth was calculated: {}", slotWedgeWdth);
		return slotWedgeWdth;
	}
}
