package Labb5.model;

import java.util.ArrayList;

import Labb5.simulator.State;

/**
 *
 * @author wesjon-5, 
 *
 */
public class StoreState extends State{
	// TODO Fix the casher variables.
	private int maxPeople;
	private double lambda;
	private double[] P;
	private double[] K;
	private long seed;
	private FIFO inLine = new FIFO();
	private int peopleInStore = 0;
	private int totalAmountOfCustomers = 0;
	private int missedCustomers = 0;
	private String eventName = "";
	private ArrayList<Customer> CustomerID = new ArrayList<Customer>();
	private boolean isOpen = false;
	private Registers cashier;
	private String currentID = "";
	private int totAmOfRegs;

	/**
	 * @param cashiers How many cashiers is there in the store.
	 * @param maxPeople Max amount of people in the store.
	 * @param lambda Time divider.
	 * @param P Between what time variables dose it take for a customer to pick products.
	 * @param K Between what time variables dose it take for a customer to pay.
	 * @param seed The seed for how all the randomizes operate on.
	 */

	public StoreState(int cashiers, int maxPeople, double lambda, double[] P, double[] K, long seed) {
		this.totAmOfRegs = cashiers;
		this.cashier = new Registers(cashiers);
		this.maxPeople = maxPeople;
		this.lambda = lambda;
		this.P = P;
		this.K = K;
		this.seed = seed;

	}

	//All the getClasses
	/**
	 * @return The max numbers of people allowed in the store.
	 */
	public int getMaxPeople() {
		return maxPeople;
	}

	/**
	 * Sets the name of the current event.
	 * @param name
	 */
	public void setEventName(String name){
	    this.eventName = name;
    }

	/**
	 * 
	 * @return The total amount of customers.
	 */
	public int getTotalAmountOfCustomers(){
	    return getTotalOfCustormers();
    }

	/**
	 * Increases the amount of total customers. 
	 */
    public void increaseTotalAmountOfCustomers(){
	    this.totalAmountOfCustomers +=1;
    }

    /**
     * 
     * @return The size of customers in line.
     */
	public int getQueueSize() {
		return inLine.size();
	}
	
	/**
	 * 
	 * @return The 
	 */
	public int getRegsInUse() {
		return cashier.getUsed();
	}

	public int getAmOfRegs() {
		return totAmOfRegs;
	}

	public String getQueue() {
		return inLine.toString();
	}

	public double getLambda() {
		return lambda;
	}

	public double[] getP(){
		return P;
	}

	public double[] getK(){
		return K;
	}
	/**
	 * @return The programs seed.
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * @return The next person in line to pay.
	 */
	public int getNextInLine() {
		return inLine.first();
	}

	/**
	 * Removes the first person in the line.
	 */
	public void removeInLine() {
		inLine.removeFirst();
	}

	/**
	 * @return True if the line is empty else false.
	 */
	public boolean isLineEmpty() {
		return inLine.isEmpty();
	}

	/**
	 * @return The amount of people in the store right now.
	 */
	public int getPeopleInStore() {
		return peopleInStore;
	}
	
	public void increasPeopleInStore() {
		peopleInStore++;
	}
	
	public void decreasPeopleInStore() {
		peopleInStore--;
	}

	/**
	 * @return The total amount of customers that have arrived to the store.
	 */
	public int getTotalOfCustormers() {
		return totalAmountOfCustomers;
	}

	/**
	 * @return The amount of missed customers.
	 */
	public int getMissed() {
		return missedCustomers;
	}
	public void increaseMissed(){
	    this.missedCustomers ++;
    }

	/**
	 * @return The name of the current event.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 *
	 * @param i
	 * @return The last customer.
	 */
	public int getCustomerID(int i){
		return CustomerID.get(i).getID();
	}

	/**
	 * @param i
	 * @return The time it takes for this customer to pick products.
	 */
	public double getCustomerBuyTime(int i) {
		return CustomerID.get(i).getPickTime();
	}

	/**
	 *
	 * @param i
	 * @return The time it takes for this customer to pay for the products.
	 */
	public double getCustomerPayTime(int i) {
		return CustomerID.get(i).getPayTime();
	}
	/**
	 * @return Is the store open.
	 */
	public boolean getIsOpen() {
		return isOpen;
	}
	
	public int getCustomerIDSize(){
		return CustomerID.size();
	}
	
	public ArrayList<Customer> getCustomerID(){
		return CustomerID;
	}

	public void openStore() {
		isOpen = true;
	}
	
	public void closeStore() {
		isOpen = false;
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}

	public void increasRegFreeTime(double time) {
		cashier.increseFreeCashierTime(time);
	}

	public void setCurrentID(String ID) {
		this.currentID = ID;
	}
	
	public String getCurrentID() {
		return currentID;
	}

	public void increasInLineTime(double time) {
		inLine.increasInLineTime(time);
	}
	
	public double getInLineTime() {
		return inLine.getInLineTime();
	}
	
	public boolean FreeRegs() {
		return cashier.getFreeRegs();
	}
	
	public void addInLine(int ID) {
		inLine.add(ID);
	}
	
	public double getFreeTimeRegs(){
		return cashier.getFreeTime();
	}
	
	public int getPeopleInLineTotal() {
		return inLine.getPeopleInLineTotal();
	}

}
