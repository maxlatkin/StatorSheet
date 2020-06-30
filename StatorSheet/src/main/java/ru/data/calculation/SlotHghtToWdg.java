package ru.data.calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.data.DataStore;

public class SlotHghtToWdg implements Calculable {
	
	private static final Logger LOG = LoggerFactory.getLogger(SlotHghtToWdg.class);
	private static SlotHghtToWdg instance;
	
	private SlotHghtToWdg(){}
    
    public static SlotHghtToWdg getInstance() {
        if (instance == null) {
            instance = new SlotHghtToWdg();
        }
        return instance;
    }

	@Override
	public double calculate() {
		double slotHghtToWdg = DataStore.getTotalSlotHght() - DataStore.getWedgeThck() - DataStore.getWedgeGap();
		LOG.info("SlotHghtToWdg was calculated: {}", slotHghtToWdg);
		return slotHghtToWdg;
	}
}
