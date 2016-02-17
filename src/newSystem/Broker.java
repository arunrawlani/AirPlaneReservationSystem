package newSystem;

import java.util.Random;
/**
 * 
 * 
 * @author Arun Rawlani
 * 
 * 
 * 
 * <p>Broker is the responsible class for the automated threads that reserve seats in the system randomly.
 * The class implements the Runnable and is run on a thread object</p>
 */
public class Broker implements Runnable {

	private AirplaneSeats seats;
	private String name;

	/**
	 * @param name	The name/ID of the BookingAgent object
	 * * @param seats	Refers to the AirplaneSeat object to be reserved by Broker automated thread
	 */
	public Broker(AirplaneSeats seats, String name){
		this.seats = seats;
		this.name = name;
	}

	/** 
	 * Randomly selects the row and column of the AirplaneSeat object to be reserved
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Random rd = new Random();
		int x = 0;
		int y = x;
		try{
			while(!this.seats.isFull()){
				try{
					x = rd.nextInt(50);
					y = rd.nextInt(4);
					System.out.println(this.name +" is booking Seat "+(x+1)+", "+(y+1));
					this.seats.book(x, y, this.name);
					Thread.sleep(rd.nextInt(3000)+1000);
				}
				catch(AlreadyBooked ex){
					System.out.println(ex);
				}
			}
		}
		catch(InterruptedException ex){
			
		}
	}

}