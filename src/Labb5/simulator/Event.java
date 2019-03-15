package Labb5.simulator;


public abstract class Event {

    private EventQueue queue;
    private double timeStamp;

    public Event(EventQueue queue, double timeStamp){
        this.queue = queue;
        this.timeStamp = timeStamp;
    }

    public EventQueue getQueue(){
        return queue;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    public abstract void eventTriggered();

}
