package Labb5.simulator;

/**
 *
 * Simulator takes in a general queue and a state and then runs the simulation.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class Simulator {
    private EventQueue queue;
    private State state;

    public Simulator(EventQueue queue, State state){
        this.state = state;
        this.queue = queue;
    }


    /**
     * Run method just starts a loop that goes until the simulations emergancyStop is true.
     * It gets next event from the event queue and trigger the events effect.
     */
    public void run(){

        //loops until the breaks is false
        while(!state.getEmergencyStop()){

            //Calls getNextEvent that returns the event who's next in line and triggers it's effect.
            try {
        	queue.getNextEvent().eventTriggered();
            }
            catch(NullPointerException e) {
            	break;
            }
        }
    }
}