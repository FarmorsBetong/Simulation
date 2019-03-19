package Labb5.model.event;

import Labb5.model.StoreState;
import Labb5.simulator.*;
import Labb5.view.SimView;


/**
 * Stop Event is used to pull the break of the simulation.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class StopEvent extends Event{

	SimView view;

	private StoreState storeState;
/**
 * 
 * @param storeState specific state.
 * @param queue the queue that holds events.
 * @param time the time when the event occurs.
 * @param view an object of the view.
 */
	public StopEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}

	/**
	 *
	 * @param storeState specific state.
	 * @param queue the queue that holds events.
	 * @param time the time when the event occurs.
	 */

	public StopEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}

	/**
	 *
	 * Triggers the "RaiseEmergencyStop" method in storeState which ends the simulations.
	 */
	public void eventTriggered() {
		storeState.setEventName("Stop");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.update();

		//prints the result before shutting down.
		storeState.raiseEmergencyStop();
	}
}
