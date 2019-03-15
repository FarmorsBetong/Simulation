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


	public StartEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}

	public void eventTriggered() {
		view.printStartup();
		storeState.setEventName("Start");
		storeState.update();
		storeState.deactivateEmergencyStop();
		storeState.openStore();
		ExponentialRandomStream Time = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed());
		double arrTime =storeState.getTime() + Time.next();
		super.getQueue().addEvent(new ArrivalEvent(storeState, super.getQueue(), arrTime));
	}

}