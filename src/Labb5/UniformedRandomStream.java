
package Labb5;

import java.util.Random;


public class UniformedRandomStream {

    private Random rand;
    private double lower, width;

    public UniformedRandomStream(double lower, double upper, long seed) {
        rand = new Random(seed);
        this.lower = lower;
        this.width = upper-lower;
    }

    public UniformedRandomStream(double lower, double upper) {
        rand = new Random();
        this.lower = lower;
        this.width = upper-lower;
    }

    public double next() {
        return lower+rand.nextDouble()*width;
    }
}
