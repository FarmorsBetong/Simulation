package Labb5.model.event;

import Labb5.ExponentialRandomStream;
import Labb5.model.Customer;
import Labb5.model.StoreState;
import Labb5.simulator.Event;
import Labb5.simulator.EventQueue;

public class ArrivalEvent extends Event{

	private StoreState storeState;
	/**
	 * 
	 * @param storeState
	 * @param queue
	 * @param time
	 */
	public ArrivalEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}
/**
 * Creates a new arrival. Checks if the store is open and if there is room for the new customer.
 */
	public void eventTriggered(){
		if(storeState.getIsOpen()) {
			// Set all pre update variables.
			Customer customer = new Customer(storeState.getCustomerIDSize(), storeState.getP(), storeState.getK(),  storeState.getSeed());
			storeState.getCustomerID().add(customer);
			double freeRegTime = super.getTimeStamp() - storeState.getTime();
			storeState.increasRegFreeTime(freeRegTime);
			double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
			storeState.increasInLineTime(peopleInLineTime);
			storeState.setCurrentID(Integer.toString(customer.getID()));
			
			storeState.update();
			//-------------------------------------------------------------------
			// change the rest of the variables.
			storeState.setTime(getTimeStamp());
			storeState.increaseTotalAmountOfCustomers();
			
			// Is there still room for a new customer in the store?
			if (storeState.getPeopleInStore() < storeState.getMaxPeople()) {
				storeState.increasPeopleInStore();
				// Make a timeStamp for a new shoppingEvent.
				double timeStamp = storeState.getTime() + customer.getPickTime();
				// Make a new ShoppingEvent
				super.getQueue().addEvent(new ShoppingEvent(storeState, super.getQueue(), timeStamp), customer.getID());
			}
			else {
				storeState.increaseMissed();
			}
			// Make a new ArrivalEvent and add it to the EventQueue.
			ExponentialRandomStream Time = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed()); 
			double arrTime = storeState.getTime() + Time.next();
			super.getQueue().addEvent(new ArrivalEvent(storeState,super.getQueue(),arrTime));

		}
		else {
			storeState.increaseTotalAmountOfCustomers();
		}
	}
}
