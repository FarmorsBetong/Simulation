package Labb5.model.event;

import Labb5.model.StoreState;
import Labb5.simulator.*;

public class StopEvent extends Event{

	SimView view;

	private StoreState storeState;
	public StopEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}

	/*Lägg i nödbromsen*/
	public void eventTriggered() {
		storeState.setEventName("Stop");
		storeState.update();
		storeState.setTime(getTimeStamp());

		//prints the result before shutting down.
		view.printResult();
		storeState.raiseEmergencyStop();
	}
}
