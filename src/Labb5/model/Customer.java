package Labb5.model;


import Labb5.UniformedRandomStream;
/**
 * 
 * @author wesjon-5
 *
 */
public class Customer {

	private int ID; 
	private double pickTime;
	private double payTime;
/**
 * 
 * @param ID
 * @param P
 * @param K
 * @param seed
 */
	public Customer(int ID, double P[], double K[], long seed) {
		//Makes references to the uniformed class to be able to generate streams.
		UniformedRandomStream pickSteam = new UniformedRandomStream(P[0], P[1], seed);
		UniformedRandomStream payStream = new UniformedRandomStream(K[0], K[1], seed);
		
		this.ID = ID;
		this.pickTime = pickSteam.next();
		this.payTime = payStream.next();
		
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
