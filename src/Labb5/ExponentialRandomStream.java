package Labb5;

import java.util.Random;

/**
 * @authors roblof-8, johlax-8, wesjon-5, jakmor-8.
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  /**
	   * 
	   * @param lambda
	   * @param seed
	   */
	public ExponentialRandomStream(double lambda, long seed) {
	  	// Sets random to use this seed.
		rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  /**
	   * 
	   * @param lambda
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

