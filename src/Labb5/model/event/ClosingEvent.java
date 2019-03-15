package Labb5.model.event;

import Labb5.model.StoreState;
import Labb5.simulator.*;
public class ClosingEvent extends Event{

	private StoreState storeState;
	public ClosingEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}

	public void eventTriggered() {
		storeState.setEventName("Closing ");
		storeState.setCurrentID("---");
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasRegFreeTime(freeRegTime);
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasInLineTime(peopleInLineTime);
		
		storeState.update();
		
		storeState.setTime(getTimeStamp());
		storeState.closeStore();
		
	}
}
