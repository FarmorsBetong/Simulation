package Labb5.model.event;

import Labb5.model.*;
import Labb5.simulator.*;

/**
 * Payment Event holds a costumer ID and performance the payment for that specific costumer.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */

public class PaymentEvent extends Event{

	private StoreState storeState;
	private int ID;

	/**
	 *
	 * @param storeState Specific state.
	 * @param queue the queue that holds events.
	 * @param time the time when the event occurs.
	 * @param ID the costumer ID.
	 */

	public PaymentEvent(StoreState storeState, EventQueue queue, double time, int ID) {
		super(queue, time);
		this.storeState = storeState;
		this.ID = ID;
	}

	/**
	 * Changes the information about the costumer and event effects and updates the specific state.
	 * Removes the latest guy in the register then adds the next costumer to the register. If there is people in
	 * the queue then it takes the first one in line.
	 *
	 */

	public void eventTriggered() {
		storeState.setEventName("Payment: ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.setCurrentID(Integer.toString(ID));
		
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseRegFreeTime(freeRegTime);
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseInLineTime(peopleInLineTime);
		storeState.update();
		//----------------------------------------------- Before notifying

		//Removes costumer from store/ the last person in the register leaves.
		storeState.decreasePeopleInStore();
		//Changes the time of the current time.
		storeState.setTime(getTimeStamp());

		//Checks if the queue is empty.
		if(!storeState.isLineEmpty()){

			//Creates a new payment event if there is people in queue.
			int nextInLine = storeState.getNextInLine();
			PaymentEvent EventPay = new PaymentEvent(storeState,super.getQueue(),super.getTimeStamp() +	storeState.getCustomerPayTime(nextInLine), nextInLine);

			//Adds the event to EventQueue.
			super.getQueue().addEvent(EventPay, storeState.getNextInLine());

			//Removes the one who's first in the queue.
			storeState.removeInLine();

		}else {
			storeState.decreaseRegsInUse();
		}
		
	}

}
