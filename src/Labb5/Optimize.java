package Labb5;

import Labb5.model.StoreState;
import Labb5.model.event.ClosingEvent;
import Labb5.model.event.StartEvent;
import Labb5.model.event.StopEvent;
import Labb5.simulator.*;

import java.util.Random;

public class Optimize implements K {


    public static void main(String[] args) {

        method2(SEED);


    }

    private static StoreState method1(int cashier, long seed){



        //variables
        final double[] PICK_TIME = {LOW_COLLECTION_TIME,HIGH_COLLECTION_TIME};

        final double[] PAY_TIME = {LOW_PAYMENT_TIME,HIGH_PAYMENT_TIME};

        //Creates a specific state reference.
        StoreState specificState = new StoreState(cashier,M,L,PICK_TIME,PAY_TIME,seed);

        //Creates a queue where Events are stored, and a state and sends it to the simulator.
        EventQueue queue = new EventQueue();
        //Creates a general state.
        State state = new State();
        //Creates simulator with the state and queue;
        Simulator sim = new Simulator(queue,state);



        StartEvent startEvent = new StartEvent(specificState,queue,0);
        ClosingEvent closeEvent = new ClosingEvent(specificState,queue,END_TIME);
        StopEvent stopEvent = new StopEvent(specificState,queue,STOP_TIME);

        queue.addEvent(startEvent);
        queue.addEvent(closeEvent);
        queue.addEvent(stopEvent);

        sim.run();
        return specificState;
    }


    private static int method2(long seed){
        StoreState state;
        int missed = 199999;
        int bestRegisterAmount = 0;

        //Loops through
        for(int cashiers = M; cashiers >= 1; cashiers--){


            state = method1(cashiers,seed);

            //If the amount of missed people increase, then break.
            if(state.getMissed() > missed){
               break;
            }
                missed = state.getMissed();
                bestRegisterAmount = cashiers;

            System.out.println("Kassor:" + bestRegisterAmount + " missade :" + missed + " statemissed: " + state.getMissed());

        }

        printStart();
        System.out.println(" (" + missed + "): " + bestRegisterAmount);
        return bestRegisterAmount;
    }



    private static void method3(int seed){

        //Variables
        Random random = new Random(seed);
        int counter = 0;
        int bestRegisterAmount = 9001;

        while(true) {

            int temp = method2(random.nextInt());

            if (temp >= bestRegisterAmount) {
                counter++;
            } else {
                counter = 0;
            }

            if (counter == 100) {
                break;
            }
        }



    }

    private static void printStart(){
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Max som ryms, M..........:" + M);
        System.out.println("Ankomshastighet, lambda..:" + L);
        System.out.println("Plocktider, [P_min..Pmax]: [" + LOW_COLLECTION_TIME + " .. " + HIGH_COLLECTION_TIME + "]" );
        System.out.println("Betaltider, [K_min..Kmax]: [" + LOW_PAYMENT_TIME + " .. " + HIGH_PAYMENT_TIME + "]");
        System.out.println("Frö, f...................:" + SEED + "\n");

        System.out.println("Stängning sker tiden " + END_TIME + " och stophändelsen sker tiden " + STOP_TIME + "\n");
        System.out.print("Minsta antal kassor som ger minimalt antal missade:");
    }
}
