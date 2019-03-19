package Labb5.model.event;


import Labb5.model.StoreState;
import Labb5.simulator.*;
import Labb5.view.SimView;

/**
 * Start Event is used to create a chain of events.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */
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
 * @param storeState Specific state.
 * @param queue the queue that holds events.
 * @param time the time when the event occurs.
 * @param view an object of the view.
 */
	public StartEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}

	public StartEvent(StoreState storeState, EventQueue queue, double time){
		super(queue,time);
		this.storeState = storeState;

	}
/**
 * Deactivate the break, open the store, create the first ArrivalEvent. 
 */
	public void eventTriggered() {
		// Pre update changes.
		storeState.setEventName("Start");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.update();
		//After update changes.
		storeState.deactivateEmergencyStop();
		storeState.openStore();
		// The timeStamp for when the first arrival happens.
		
		double arrTime = storeState.getTime() + storeState.getNextExponetialTime();
		super.getQueue().addEvent(new ArrivalEvent(storeState, super.getQueue(), arrTime));
	}

}