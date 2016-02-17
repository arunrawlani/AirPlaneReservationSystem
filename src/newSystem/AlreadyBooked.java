package newSystem;

/**
 * @author Arun Rawlani
 * <p>
 * AlreadyBooked is the class that deals with scenarios when more than one of the threads compete
 * for a single AirplaneSeat object</p>
 *
 */
public class AlreadyBooked extends Exception {
	
	private String errorMessage;

	/**
	 * @param message - Generates the message for the specified error
	 */
	public AlreadyBooked(String message) {
		super(message);
		this.errorMessage = message;
	}
	
	/** 
	 * @return String	- The error message of the Exception object
	 * @see java.lang.Throwable#toString()
	 */
	public String toString(){
		return this.errorMessage;
	}

}
