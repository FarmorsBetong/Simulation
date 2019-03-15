package Labb5.simulator;

public class Simulator {

    //instance variables
    private EventQueue queue;
    private State state;

    /**
     * Simulator constructor
     * @param queue the EventQueue reference.
     * @param state the general state.
     */
    public Simulator(EventQueue queue, State state){
        this.state = state;
        this.queue = queue;
    }

    /**
     * run starts and keeps the simulation going.
     */
    public void run(){


        //loops until the breaks is false
        while(!state.getEmergencyStop()){

            //Calls getNextEvent that returns the event who's next in line and triggers it's effect.
            queue.getNextEvent().eventTriggered();
        }
    }
}