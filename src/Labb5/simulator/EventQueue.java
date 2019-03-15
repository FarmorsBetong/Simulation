package Labb5.simulator;

public class EventQueue {

	private class Node {
		private Event event;
		private int ID;
		private Node next;

		Node(Event event, int ID) {
			this.event = event;
			this.ID = ID;

		}

		Node(Event event) {
			this.event = event;
		}

	}

	private Node header;
	private Node last;

	/**
	 * Creates a header node with an invalid index
	 */
	public EventQueue() {
		header = new Node(null, -1);
		this.last = header;
	}

	/**
	 * L�gger till ett event i k�n, sorteras automatiskt
	 * 
	 * @param event The event.
	 * @param ID    Which costumer the event is for.
	 */
	public void addEvent(Event event, int ID) {

		
		Node after = header.next;
		Node prev = null;
		
		if (after == null) {
			last.next = new Node(event);
			last = last.next;
		}
		
		while (event.getTimeStamp() > after.event.getTimeStamp()) {
		
			prev = after;
			after = after.next;
		}

		prev.next = new Node(event, ID);
		prev.next.next = after;

	}

	public void addEvent(Event event) {
//		System.out.println("HEJ");
		Node after = header.next;
		Node prev = null;

		if (after == null) {
			last.next = new Node(event);
			last = last.next;
		}

		while (event.getTimeStamp() > after.event.getTimeStamp()) {

			prev = after;
			after = after.next;
		}

		prev.next = new Node(event);
		prev.next.next = after;

	}

	/**
	 * 
	 * @return Kund-ID f�r n�sta event
	 */
	public int getID() {
		try {
			return header.next.ID;
		} catch (NullPointerException e) {
			return 0;
		}
	}

	public Node getEvent(int index) {
		Node node = header;
		Node pointer = header.next;
		for (int i = index; i > 0; i--) {
			if (node == null) {
				throw new IndexOutOfBoundsException();
			}
			node = pointer;
			pointer = pointer.next;
		}
		return node;
	}

	/**
	 * Returnerar och tar bort nästa event i k�n
	 * 
	 * @return N�sta event i k�n
	 */
	public Event getNextEvent() {
		Event next = header.next.event;
		removeNext();
		return next;
	}

	private void removeNext() {
		try {
			header.next = header.next.next;
		} catch (NullPointerException e) {
			return;
		}
	}

}
