package Labb5.model.event;

import Labb5.model.*;
import Labb5.simulator.*;

public class PaymentEvent extends Event{

	private StoreState storeState;

	public PaymentEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}

	/*
	 *TODO Hur lång tid tar det att betala?
	 *TODO minska antal människor i butiken
	 *TODO om det är någon i FIFO kön ta den personen.
	 * */
	public void eventTriggered() {
		storeState.setEventName("Shopping: ");
		storeState.setCurrentID(Integer.toString(super.getQueue().getID()));
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasRegFreeTime(freeRegTime);
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasInLineTime(peopleInLineTime);
		storeState.update();
		//----------------------------------------------- Before notifying

		//Removes costumer from store.
		storeState.decreasPeopleInStore();
		//Changes the time of the current time.
		storeState.setTime(getTimeStamp());

		//Checks if the queue is empty.
		if(!storeState.isLineEmpty()){

			//Creates a new payment event if there is people in queue.
			PaymentEvent EventPay = new PaymentEvent(storeState,getQueue(),getTimeStamp() +
					storeState.getCustomerPayTime(storeState.getNextInLine()));

			//Adds the event to EventQueue.
			getQueue().addEvent(EventPay);

			//Removes the one who's first in the queue.
			storeState.removeInLine();

		}
		
	}

}
