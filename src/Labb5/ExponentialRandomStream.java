package Labb5;

import java.util.Random;

/**
 * Calculates the arrival time
 *
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8.
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  /**
	   * 
	   * @param lambda the lambda god
	   * @param seed the seed.
	   */
	public ExponentialRandomStream(double lambda, long seed) {
	  	// Sets random to use this seed.
		rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  /**
	   * 
	   * @param lambda lambda god
	   */
	public ExponentialRandomStream(double lambda) {
		// Sets random run without seed.
		rand = new Random();
	    this.lambda = lambda;
	}
	  /**
	   * 
	   * @return A random double that is divided with lambda.
	   */
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}

