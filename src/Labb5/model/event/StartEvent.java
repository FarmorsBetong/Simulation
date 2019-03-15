package Labb5.model.event;

import Labb5.ExponentialRandomStream;

import Labb5.model.StoreState;
import Labb5.simulator.*;
public class StartEvent extends Event{

	/*
	 * Lägg ur nödbromsen
	 * öppna butiken
	 * skapa en ankomsthändelse.
	 * */
	/*private CashRegisterState item;
	StartEvent(CashRegisterState item){
		this.item = item;
	}*/
	private StoreState storeState;
	private SimView view;

/**
 * 
 * @param storeState
 * @param queue
 * @param time
 * @param view
 */
	public StartEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}
/**
 * Deactivate the break, open the store, create the first ArrivalEvent. 
 */
	public void eventTriggered() {
		// Pre update changes.
		view.printStartup();
		storeState.setEventName("Start");
		storeState.update();
		//After update changes.
		storeState.deactivateEmergencyStop();
		storeState.openStore();
		// The timeStamp for when the first arrival happens.
		ExponentialRandomStream Time = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed());
		double arrTime =storeState.getTime() + Time.next();
		super.getQueue().addEvent(new ArrivalEvent(storeState, super.getQueue(), arrTime));
	}

}