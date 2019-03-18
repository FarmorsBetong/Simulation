package Labb5.model.event;

import Labb5.model.StoreState;
import Labb5.simulator.*;


/**
 * @authors roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class StopEvent extends Event{

	SimView view;

	private StoreState storeState;
/**
 * 
 * @param storeState
 * @param queue
 * @param time
 * @param view
 */
	public StopEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}

	public StopEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}

	/**
	 *
	 * Lägg i nödbromsen
	 */
	public void eventTriggered() {
		storeState.setEventName("Stop");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.update();

		//prints the result before shutting down.
		storeState.raiseEmergencyStop();
	}
}
