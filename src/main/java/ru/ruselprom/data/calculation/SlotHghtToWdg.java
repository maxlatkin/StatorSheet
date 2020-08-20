package ru.ruselprom.data.calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.ruselprom.data.DataStore;

public class SlotHghtToWdg implements Calculable {
	
	private static final Logger LOG = LoggerFactory.getLogger(SlotHghtToWdg.class);

	@Override
	public double calculate() {
		double slotHghtToWdg = DataStore.getTotalSlotHght() - DataStore.getWedgeThck() - DataStore.getWedgeGap();
		LOG.info("SlotHghtToWdg was calculated: {}", slotHghtToWdg);
		return slotHghtToWdg;
	}
}
