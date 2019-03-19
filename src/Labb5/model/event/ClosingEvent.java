package Labb5.model.event;

import Labb5.model.StoreState;
import Labb5.simulator.*;

/**
 * Closing Event is used to close the store.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class ClosingEvent extends Event{


	private StoreState storeState;
	/**
	 * 
	 * @param storeState Specific state.
	 * @param queue the queue that holds events.
	 * @param time the time when the event occurs.
	 */
	public ClosingEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}
/**
 * Closing down the store.
 */
	public void eventTriggered() {
		// Set all pre update variables.
		storeState.setEventName("Closing ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.setCurrentID("---");		
		
		// The time registers have been free since last timeStamp.
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseRegFreeTime(freeRegTime);
		// The time people have been in line since last timeStamp.
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseInLineTime(peopleInLineTime);
		storeState.update();
		//-------------------------------------------------
		// change the rest of the variables
		storeState.setTime(getTimeStamp());
		storeState.closeStore();
		
	}
}
