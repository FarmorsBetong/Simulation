package Labb5;

import Labb5.model.StoreState;
import Labb5.model.event.ClosingEvent;
import Labb5.model.event.StartEvent;
import Labb5.model.event.StopEvent;
import Labb5.simulator.*;

/**
 * Optimize is contains 3 methods that together finds the best and optimized register amount.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */

import java.util.Random;

public class Optimize implements K {

    /**
     *
     * @param args argument for main method.
     *  The main method sets up and uses the private methods that is defined in this class
     *  which is, "simulation", "bestRegAmount" and "OptimalRegAmount". These methods calculates the
     *  optimized amount of registers you should have under different circumstances, like with different seed
     *  and lambda value.
     *
     */

    public static void main(String[] args) {

        bestRegAmount(SEED);


    }

    private static StoreState simulation(int cashier, long seed){



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


    private static int bestRegAmount(long seed){
        StoreState state;
        int missed = 199999;
        int bestRegisterAmount = 0;

        //Loops through
        for(int cashiers = M; cashiers >= 1; cashiers--){


            state = simulation(cashiers,seed);

            //If the amount of missed people increase, then break.
            if(state.getMissed() > missed){
               break;
            }
                missed = state.getMissed();
                bestRegisterAmount = cashiers;
        }

        printStart();
        System.out.println(" (" + missed + "): " + bestRegisterAmount);
        return bestRegisterAmount;
    }



    private static void optimalRegAmount(int seed){

        //Variables
        Random random = new Random(seed);
        int counter = 0;
        int bestRegisterAmount = 1;
        int varv = 1;

        while(true) {

            int temp = bestRegAmount(random.nextInt());

            if(temp > bestRegisterAmount) {
                counter = 0;
                bestRegisterAmount = temp;
            }
            else {
                counter++;
            }

            if (counter == 100) {
                break;
            }
            System.out.println("BestKassaNu :" + bestRegisterAmount + " Temp KAssa:" + temp);
            varv++;
            System.out.println("Den mest optimala kassaantalet: " + bestRegisterAmount);
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
