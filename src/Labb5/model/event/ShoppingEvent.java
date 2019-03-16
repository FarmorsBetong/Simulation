package Labb5.model.event;

import Labb5.model.*;
import Labb5.simulator.*;

class ShoppingEvent extends Event{

	private StoreState storeState;
	/**
	 * 
	 * @param storeState
	 * @param queue
	 * @param time
	 */
	public ShoppingEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue,time);
		this.storeState = storeState;
	}
/**
 * Checks if there is a free register. If so, make a new PaymentEvent. Else, put customer in the FIFO queue.
 */
	public void eventTriggered() {
		// Update time, current ID, time registers have been free and time there have been people in line.
		storeState.setEventName("Shopping: ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.setCurrentID(Integer.toString(super.getQueue().getID()));
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasRegFreeTime(freeRegTime);
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasInLineTime(peopleInLineTime);
		storeState.update();
		//--------------------------------------------------------------------
		// update the general time.
		storeState.setTime(getTimeStamp());
		// Sets timeStamp for paytime.
		double timeStamp = storeState.getTime() + storeState.getCustomerPayTime(super.getQueue().getID());
		// Checks if there is someone in line. True = add customer to the line, False = go to the register.
		if(storeState.FreeRegs()) {
			super.getQueue().addEvent(new PaymentEvent(storeState,super.getQueue(),timeStamp),super.getQueue().getID());
		}else {
			storeState.addInLine(super.getQueue().getID());
		}
		
		
		
		
	
	}

}
