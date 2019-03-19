package Labb5.model.event;

import Labb5.model.Customer;
import Labb5.model.StoreState;
import Labb5.simulator.Event;
import Labb5.simulator.EventQueue;


/**
 *
 * Arrival event creates a costumer and enters the store if possible.
 *
 *@author roblof-8, johlax-8, wesjon-5, jakmor-8.
 */

public class ArrivalEvent extends Event{
	private StoreState storeState;
	/**
	 * 
	 * @param storeState Specific state.
	 * @param queue The queue that holds events.
	 * @param time the time when the event occurs.
	 *
	 */
	public ArrivalEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;
	}
/**
 * Creates a new arrival. Checks if the store is open and if there is room for the new customer.
 */
	public void eventTriggered(){
		//System.out.println("Arrival Time: " + super.getTimeStamp() );
		// Set all pre update variables.
		storeState.setEventName("Arrival ");
		storeState.setCurrentTime(super.getTimeStamp());
		Customer customer = new Customer(storeState.getCustomerIDSize(), storeState, storeState.getPeopleInStore() < storeState.getMaxPeople());
		storeState.getCustomerID().add(customer);
		storeState.setCurrentID(Integer.toString(customer.getID()));
		if(!super.getQueue().isNextLast()) {
			double freeRegTime = super.getTimeStamp() - storeState.getTime();
			storeState.increaseRegFreeTime(freeRegTime);
		}
		
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increaseInLineTime(peopleInLineTime);
		
		storeState.update();
		//-------------------------------------------------------------------
		// change the rest of the variables.
		if(storeState.getIsOpen()) {
			
			storeState.setTime(getTimeStamp());
			
			// Is there still room for a new customer in the store?
			if (storeState.getPeopleInStore() < storeState.getMaxPeople()) {
				storeState.increasePeopleInStore();
				// Make a timeStamp for a new shoppingEvent.
				double timeStamp = storeState.getTime() + customer.getPickTime();
				// Make a new ShoppingEvent
			//	System.out.println("send from arrival "+customer.getID()+ " with timeStamp " + timeStamp);
				super.getQueue().addEvent(new ShoppingEvent(storeState, super.getQueue(), timeStamp, customer.getID()), customer.getID());
			}
			else {
				storeState.increaseMissed();
			}
			// Make a new ArrivalEvent and add it to the EventQueue.
			double arrTime = storeState.getTime() + storeState.getNextExponetialTime();
			super.getQueue().addEvent(new ArrivalEvent(storeState, super.getQueue(), arrTime));
		}
	}
}
