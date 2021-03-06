package Labb5.view;

import java.util.Observable;
import java.util.Observer;

import Labb5.model.StoreState;
import Labb5.simulator.*;

/**
 *
 * The specific view that observers the state and prints the changes.
 * @author roblof-8, johlax-8, wesjon-5, jakmor-8
 */

public class SimView extends View implements Observer{
	private StoreState currentState;
	private String Pstr;
	private String Kstr;

	public SimView(StoreState state) {
		super(state);
		this.currentState = state;
		Pstr = "[" + String.valueOf(currentState.getP()[0]) + ".." + String.valueOf(currentState.getP()[1] + "]");
		Kstr = "[" + String.valueOf(currentState.getK()[0]) + ".." + String.valueOf(currentState.getK()[1] + "]");
	}

	private void printStartup() {

		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Antal kassor, N..........:" + currentState.getAmOfRegs());
		System.out.println("Max som ryms, M..........:" + currentState.getMaxPeople());
		System.out.println("Ankomshastighet, lambda..:" + currentState.getLambda());
		System.out.println("Plocktider, [P_min..Pmax]: " + Pstr);
		System.out.println("Betaltider, [K_min..Kmax]: " + Kstr);
		System.out.println("Frö, f...................:" + currentState.getSeed());

		System.out.println("FÖRLOPP");
		System.out.println("=======");
		System.out.println(
				"  Tid    Händelse    Kund    ?    led    ledT    I    $    :-(    köat    köT    köar    [Kassakö..]");

	}

	private void printResult() {
		System.out.println("RESULTAT");
		System.out.println("========");
		System.out.println("1) av " + currentState.getCustomerID().size() + " kunder handlade "
				+ String.valueOf(currentState.getCustomerID().size() - currentState.getMissed()) + " medan "
				+ currentState.getMissed() + " missades.");
		System.out.println("2) Totalt tid " + currentState.getAmOfRegs() + " kassor har varit" + " lediga: "
				+ String.format("%.2f",currentState.getFreeTimeRegs()) + " te.");
		double snittTid = currentState.getFreeTimeRegs() / currentState.getAmOfRegs();
		double percentTid = (snittTid / currentState.getTime())*100;
		System.out.println("Genomsnittlig ledig kassatid: " + String.format("%.2f",snittTid) + " te." + "(dvs " +  String.format("%.2f",percentTid)
				+ "% av tiden från öppning till sista kunden betalat).");

		int totAmQueuedPeeps = currentState.getPeopleInLineTotal();
		double snittKoTid = currentState.getInLineTime() / totAmQueuedPeeps;
		System.out.println("3) Total tid " + totAmQueuedPeeps + " kunder" + " tvingats köa: "
				+ String.format("%.2f",currentState.getInLineTime()) + " te.");
		System.out.print("Genomsnittlig kötid: " + String.format("%.2f",snittKoTid) + " te.");
	}

	/**
	 *
	 * Prints out the changes depending on what event that event just changed the state.
	 *
	 * @param arg0 observable object
	 * @param f observer object
	 */

	@Override
	public void update(Observable arg0, Object f) {

		if(currentState.getEventName().equals("Start")){
			printStartup();
		}
		else if(currentState.getEventName().equals("Stop")){
			printResult();
		}
		else {
			String time = String.valueOf(String.format("%.2f", currentState.getCurrentTime())) + "    ";

			String event;

			if (currentState.getEventName() == "Arrival ") {
				event = "Arrival     ";
			} else if (currentState.getEventName() == "Shopping: ") {
				event = "Shopping    ";
			} else if (currentState.getEventName() == "Payment: ") {
				event = "Payment     ";
			} else if (currentState.getEventName() == "Closing ") {
				event = "Closing   ";
			} else {
				event = currentState.getEventName();
			}

			String custNum = String.valueOf(currentState.getCurrentID() + "  ");
			String open = (currentState.getIsOpen()) ? "Ö  " : "S  ";
			String amOfFreeRegs = String.valueOf(currentState.getAmOfRegs() - currentState.getRegsInUse()) + "  ";
			String sumTimeFreeRegs = String.valueOf(String.format("%.2f", currentState.getFreeTimeRegs())) + " ";
			String amOfCusts = String.valueOf(currentState.getPeopleInStore()) + " ";
			String doneCusts = String.valueOf(currentState.getCustomersDone()) + "  ";
			String missedCusts = String.valueOf(currentState.getMissed()) + "   ";
			String totAmOfQueuedPeeps = String.valueOf(currentState.getPeopleInLineTotal()) + "   ";
			String timeQueued = String.valueOf(String.format("%.2f", currentState.getInLineTime())) + "  ";
			String inLine = String.valueOf(currentState.getQueueSize()) + "    ";
			String wholeQueue = currentState.getQueue() + " ";

			String infoRow;
			if (event == "Start" || event == "Stop") {
				infoRow = time + event;
			} else {

				infoRow = time + event + custNum + "    " + open + "   " + amOfFreeRegs + "   " + sumTimeFreeRegs + "   " + amOfCusts + "   " + doneCusts
						+ "   " + missedCusts + "   " + totAmOfQueuedPeeps + "   " + timeQueued + "   " + inLine + "   " + wholeQueue;
			}

			System.out.println("  " + infoRow);
		}
	}

}
