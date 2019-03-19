
package Labb5;

import java.util.Random;

/**
 * Calculates pick and payment time.
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */
public class UniformedRandomStream {

    private Random rand;
    private double lower, width;
/**
 * 
 * @param lower lower pick time
 * @param upper higest pick time
 * @param seed the seed
 */
    public UniformedRandomStream(double lower, double upper, long seed) {
    	// Sets random to use this seed.
    	rand = new Random(seed);
        this.lower = lower;
        this.width = upper-lower;
    }
/**
 * 
 * @param lower lowest picktime
 * @param upper highest picktime
 */
    public UniformedRandomStream(double lower, double upper) {
    	// Sets random run without seed.
        rand = new Random();
        this.lower = lower;
        this.width = upper-lower;
    }
/**
 * 
 * @return A random double between lower and upper.
 */
    public double next() {
    	double test = lower+rand.nextDouble()*width;
    	//System.out.println("Test = " + test);
        return test;//lower+rand.nextDouble()*width;
    }
}
