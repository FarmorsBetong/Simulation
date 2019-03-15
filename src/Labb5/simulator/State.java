package Labb5.simulator;

import java.util.Observable;

public class State extends Observable {

	private boolean emergencyStop = false;
	private double time;

	public State(){
		this.time = 0;
	}

	/**
	 * Stops the simulation by changing the flag "emergencyStop" to true
	 */
	public void raiseEmergencyStop() {
		emergencyStop = true;
	}
	
	public void deactivateEmergencyStop() {
		emergencyStop = false;
	}

	public boolean getEmergencyStop(){
		return emergencyStop;
	}

	/**
	 *
	 * @return Returns the time
	 */
	public double getTime() {
		return time;
	}
	
	public void setTime(double newTime) {
		this.time = newTime;
	}

}
