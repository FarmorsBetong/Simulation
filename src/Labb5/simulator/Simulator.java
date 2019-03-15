package Labb5.simulator;

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
            queue.getNextEvent().eventTriggered();

            // sets the time of the simulations
            //state.setTime();


        }
    }
}