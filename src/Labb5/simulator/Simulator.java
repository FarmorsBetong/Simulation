package Labb5.simulator;

/**
 * @authors roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class Simulator {
    private EventQueue queue;
    private State state;

    public Simulator(EventQueue queue, State state){
        this.state = state;
        this.queue = queue;
    }


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

            // sets the time of the simulations
            //state.setTime();


        }
    }
}