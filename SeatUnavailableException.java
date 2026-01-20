package RailwayBookingSystemModule;

// custom exception thrown when invalid seat operation occur.

public class SeatUnavailableException extends Exception {

	public SeatUnavailableException(String message) {
		super(message);
	}

}
