package Labb5;

import Labb5.model.event.ClosingEvent;
import Labb5.model.event.StartEvent;
import Labb5.model.event.StopEvent;
import Labb5.simulator.*;
import Labb5.model.StoreState;


/**
 * @authors roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class RunSim {

    public static void main(String[] args) {


        //Creates constants the defines the specific state.
        final int CASHIER = 2;
        final int MAX_PEOPLE = 5;
        final double lambda = 1.0;
        final double[] PICK_TIME = {0.5,1.0};
        final double[] PAY_TIME = {2.0,3.0};
        final long SEED = 1234;
        final double END_TIME = 10.0;
        final double STOP_TIME = 999.00;

        /*
        final int CASHIER = 2;
        final int MAX_PEOPLE = 5;
        final double lambda = 1.0;
        final double[] PICK_TIME = {0.5,1.0};
        final double[] PAY_TIME = {2.0,3.0};
        final long SEED = 1234;
        final double END_TIME = 10.0;
        final double STOP_TIME = 999.00;
        */



        //Creates a specific state reference.
        StoreState specificState = new StoreState(CASHIER,MAX_PEOPLE,
                lambda,PICK_TIME,PAY_TIME,SEED);

        //Creates a queue where Events are stored, and a state and sends it to the simulator.
        EventQueue queue = new EventQueue();
        //Creates a general state.
        State state = new State();
        //Creates simulator with the state and queue;
        Simulator sim = new Simulator(queue,state);
        //Creates a view.
        SimView view = new SimView(specificState);


        StartEvent startEvent = new StartEvent(specificState,queue,0, view);
        ClosingEvent closeEvent = new ClosingEvent(specificState,queue,END_TIME);
        StopEvent stopEvent = new StopEvent(specificState,queue,STOP_TIME, view);

        queue.addEvent(startEvent);
        queue.addEvent(closeEvent);
        queue.addEvent(stopEvent);

        sim.run();
    }

}