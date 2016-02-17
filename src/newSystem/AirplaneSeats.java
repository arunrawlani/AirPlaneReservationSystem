package newSystem;

import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;

/**
 * 
 */

/**
 * @author Arun Rawlani
 * <p>
 * Contains the Seat class and is responsible for the collection of Seat objects in the reservation
 * system. Also deals with special cases when reservation is to be done for a particular Seat Object.
 * </p>
 */
public class AirplaneSeats {

	private Seat[][] seats; 
	private JButton[][] buttons;
	private ReentrantLock aLock;
	private boolean isFull;
	private int nbAvailableSeats = 200;

	/**
	 * @param rows		Initializes the number of rows
	 * @param columns	Initializes the number of columns
	 * @param buttons	This is the collection of buttons that will be manipulated
	 * 					  <br>when the user makes changes to the Seats in AirplaneSeats
	 */
	public AirplaneSeats(int rows, int columns, JButton[][] buttons){
		this.seats = new Seat[rows][columns];
		this.buttons = buttons;
		this.aLock = new ReentrantLock();
		this.isFull = false;
		for(int i = 0; i<rows; i++){
			for(int k = 0; k<columns; k++){
				seats[i][k] = new Seat(i+1,k+1);
			}
		}
	}
	
	/**
	 * This method checks whether or not the AirplaneSeats has more free seats.
	 * Only one thread may access this at any given moment.
	 * @return true if full, false otherwise
	 * 
	 */
	public boolean isFull(){
		aLock.lock();
		boolean b = this.isFull;
		aLock.unlock();
		return b;
	}

	/**
	 * <p>
	 * This method attempts to book a seat in row and column specified by the parameters below. 
	 * Upon booking, a button will display the name of the agent that booked the particular seat
	 * it represents.
	 * @param i						The row of the seat to be booked
	 * @param j						The column of the seat to be booked
	 * @param actor					The thread id of the person/thread that booked the particular seat
	 * @throws SeatBookedException	Thrown when an agent attempts booking on an already-booked seat
	 */
	public void book(int i, int j, String actor) throws AlreadyBooked {
		aLock.lock();
		Seat seat = seats[i][j];
		if(!seat.isBooked()){
			seat.book(true, actor);
			JButton button = this.buttons[i][j];
			button.setText(actor);
			button.setEnabled(false);
			this.nbAvailableSeats--;
			if(this.nbAvailableSeats == 0){
				this.isFull = true;
			}
			aLock.unlock();
		}
		else{
			aLock.unlock();
			throw new AlreadyBooked("Seat"+"("+(i+1)+","+(j+1)+") "
											+ "is already booked by "+seat.getPassengerName()+"!");
		}
	}

	private class Seat{
		private int[] coords = new int[2];
		private boolean booked;
		private String passName;

		public Seat(int row, int column){
			this.coords[0] = row;
			this.coords[1] = column;
			this.booked = false;
		}

		public boolean isBooked(){
			return this.booked;
		}

		public void book(boolean b, String passName){
			this.booked = b;
			this.passName = passName;
		}
		
		public String getPassengerName(){
			return this.passName;
		}
	}
}
