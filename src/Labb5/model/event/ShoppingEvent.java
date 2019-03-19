package Labb5.model.event;

import Labb5.model.*;
import Labb5.simulator.*;

/**
 * Shopping Event holds a costumer, and performance a shopping action for that specific costumer.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */

class ShoppingEvent extends Event{

	private StoreState storeState;
	private int ID;
	/**
	 * 
	 * @param storeState Specific state.
	 * @param queue the queue that holds events.
	 * @param time the time when the event occurs.
	 */
	public ShoppingEvent(StoreState storeState, EventQueue queue, double time, int ID) {
		super(queue,time);
		this.storeState = storeState;
		this.ID = ID;
	}
/**
 * Checks if there is a free register. If so, make a new PaymentEvent. Else, put customer in the FIFO queue.
 */
	public void eventTriggered() {
		// Update time, current ID, time registers have been free and time there have been people in line.
		storeState.setEventName("Shopping: ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.setCurrentID(Integer.toString(ID));
		
	//	System.out.println("the ID "+ ID+" with TimeStamp "+ super.getTimeStamp());
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseRegFreeTime(freeRegTime);
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseInLineTime(peopleInLineTime);
		storeState.update();
		//--------------------------------------------------------------------
		// update the general time.
		storeState.setTime(getTimeStamp());
		// Sets timeStamp for paytime.
		double timeStamp = storeState.getTime() + storeState.getCustomerPayTime(ID);
		// Checks if there is someone in line. True = add customer to the line, False = go to the register.
		if(storeState.FreeRegs()) {
			storeState.increaseRegsInUse();
			//System.out.println("In shopping "+ID);
			super.getQueue().addEvent(new PaymentEvent(storeState,super.getQueue(),timeStamp, ID),ID);
		}else {
			storeState.addInLine(ID);
		}	
	}

}
