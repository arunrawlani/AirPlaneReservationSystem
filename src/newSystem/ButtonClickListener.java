package newSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */

/**
 * @author Arun Rawlani
 * <p>
 * The clicks by the user thread on the UI buttons are handled by this class. It responds to the actions
 * by manipulating the button contents and reserving the seat that corresponds to the button.</p>
 *
 */
public class ButtonClickListener implements ActionListener {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 */
	private int row, column;
	private AirplaneSeats seats;
	
	/**
	 * @param row		Identifies the row in which the seat is being booked
	 * @param column	Identifies the column in which the seat is being booked
	 * @param seats		AirplaneSeats object that is being manipulated by the user click
	 */
	public ButtonClickListener(int row, int column, AirplaneSeats seats){
		this.row = row;
		this.column = column;
		this.seats = seats;
	}
	/**
	 * Book the seat at row and column specified by the row and column attributes.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			this.seats.book(row, column, "ID 3");
		}
		catch(AlreadyBooked ex){
			System.out.println(ex);
		}
		
	}

}
