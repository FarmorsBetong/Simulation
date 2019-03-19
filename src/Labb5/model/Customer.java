package Labb5.model;
/**
 * The class that defines a costumer in the store.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class Customer {

	private int ID; 
	private double pickTime;
	private double payTime;

	/**
	 *
	 * @param ID costum ID
	 * @param storeState specific state
	 * @param check checker
	 */
	public Customer(int ID, StoreState storeState, boolean check) {
		//Makes references to the uniformed class to be able to generate streams.
		
		
		this.ID = ID;
		if(check) {
			this.pickTime = storeState.getNextPickTime();
			this.payTime = storeState.getNextPayTime();
		}
	}
/**
 * 
 * @return The ID
 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * @return The pickTime.
	 */
	public double getPickTime() {
		return pickTime;
	}
	
	/**
	 * 
	 * @return The payTime.
	 */
	public double getPayTime() {
		return payTime;
	}
}
