package Labb5;

public class Optimize {


    public static void main(String[] args) {
        RunSim simulation = new RunSim();

        //Take preferred constants
        String[] arguments1 = {"1","2","3.","2.2", "2.5", "2.6", "2.8","1234"};


        // runs the simulations with the preferred constants
        simulation.main(arguments1);

    }
}
