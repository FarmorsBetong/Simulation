package Labb5.model.event;

import Labb5.ExponentialRandomStream;
import Labb5.model.Customer;
import Labb5.model.StoreState;
import Labb5.simulator.Event;
import Labb5.simulator.EventQueue;

public class ArrivalEvent extends Event{

	
	/* Är snabbköpet öppet ? 
	 * Är det fullt? 
	 * skapa en ny kund.
	 * skapa plockhändelse
	 * * Hur lång tid tar det att ploka varor?
	 * om öppet skape en ny ankomsthändelse
	 * 
	 * */

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
 * 
 */
	public void eventTriggered(){
		if(storeState.getIsOpen()) {
			// Set all pre update variables.
			storeState.setEventName("Arrival");
			Customer customer = new Customer(storeState.getCustomerIDSize(), storeState.getP(), storeState.getK(),  storeState.getSeed());
			storeState.getCustomerID().add(customer);
			double freeRegTime = super.getTimeStamp() - storeState.getTime();
			storeState.increasRegFreeTime(freeRegTime);
			double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
			storeState.increasInLineTime(peopleInLineTime);
			storeState.setCurrentID(Integer.toString(customer.getID()));
			
			storeState.update();
			//-------------------------------------------------------------------
			storeState.setTime(getTimeStamp());
			storeState.increaseTotalAmountOfCustomers();
			
			if (storeState.getPeopleInStore() < storeState.getMaxPeople()) {
				storeState.increasPeopleInStore();
				double timeStamp = storeState.getTime() + customer.getPickTime();
				super.getQueue().addEvent(new ShoppingEvent(storeState, super.getQueue(), timeStamp), customer.getID());
			}
			else {
				storeState.increaseMissed();
			}

			ExponentialRandomStream Time = new ExponentialRandomStream(storeState.getLambda(), storeState.getSeed()); 
			double arrTime = storeState.getTime() + Time.next();
			super.getQueue().addEvent(new ArrivalEvent(storeState,super.getQueue(),arrTime));
			//UniformedRandomStream payStream = new UniformedRandomStream(K[0], K[1], seed);
			//SortedSequence.add(new ArrivalEvent(item), arrTime);
		}
		else {
			storeState.increaseTotalAmountOfCustomers();
		}
	}

}
