package Labb5.simulator;

import java.util.Observable;
import java.util.Observer;

/**
 *General view.
 *
 * @author roblof-8, jakmor-8, johlax-8, wesjon-5.
 *
 */

public abstract class View implements Observer {

    public View(State s){
        s.addObserver(this);
    }

    @Override
    public abstract void update(Observable o, Object arg);


}
