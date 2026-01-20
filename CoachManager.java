package RailwayBookingSystemModule;

import java.util.StringJoiner;

/**
 * CoachManager: manages seats using a Passenger[] array (1-based seat numbers).
 * Unique method names chosen for clarity: - peekFreeSeats() -> shows free seat
 * numbers - reserveSeatAt(...) -> book a seat - vacateSeatAt(...) -> cancel a
 * booking - showBookedPassengers() -> display all booked passengers -
 * totalBookedCount() -> number of booked seats
 *
 * Uses SeatUnavailableException for invalid operations.
 */
public class CoachManager {
	private final Passenger[] seats; // seats[1..capacity] used, seats[0] ignored
	private final int capacity;

	public CoachManager(int capacity) {
		if (capacity < 1)
			throw new IllegalArgumentException("Capacity must be at least 1.");
		this.capacity = capacity;
		this.seats = new Passenger[capacity + 1]; // 1-based indexing
	}

	// Return a string listing free seats (or message if none)
	public String peekFreeSeats() {
		StringJoiner sj = new StringJoiner(", ");
		for (int i = 1; i <= capacity; i++) {
			if (!isOccupied(i))
				sj.add(String.valueOf(i));
		}
		String result = sj.length() == 0 ? "No seats free" : sj.toString();
		return result;
	}

	// Book: create Passenger object and place at seatNumber
	public void reserveSeatAt(String name, int age, int seatNumber) throws SeatUnavailableException {
		validateSeatIndex(seatNumber);
		if (isOccupied(seatNumber)) {
			throw new SeatUnavailableException("Seat " + seatNumber + " is already booked.");
		}
		seats[seatNumber] = new Passenger(name, age, seatNumber);
	}

	// Cancel: remove passenger at seatNumber (make seat free)
	public void vacateSeatAt(int seatNumber) throws SeatUnavailableException {
		validateSeatIndex(seatNumber);
		if (!isOccupied(seatNumber)) {
			throw new SeatUnavailableException("Seat " + seatNumber + " is not booked; nothing to cancel.");
		}
		seats[seatNumber] = null;
	}

	// Return single-line summary of booked passengers
	public String showBookedPassengers() {
		StringBuilder sb = new StringBuilder();
		boolean any = false;
		for (int i = 1; i <= capacity; i++) {
			if (isOccupied(i)) {
				sb.append(seats[i].toString()).append(System.lineSeparator());
				any = true;
			}
		}
		return any ? sb.toString().trim() : "No booked tickets.";
	}

	public boolean isOccupied(int seatNumber) {
		if (seatNumber < 1 || seatNumber > capacity)
			return false;
		return seats[seatNumber] != null;
	}

	public int getCapacity() {
		return capacity;
	}

	public int totalBookedCount() {
		int c = 0;
		for (int i = 1; i <= capacity; i++)
			if (seats[i] != null)
				c++;
		return c;
	}

	// internal helper: validate seat range
	private void validateSeatIndex(int seatNumber) throws SeatUnavailableException {
		if (seatNumber < 1 || seatNumber > capacity)
			throw new SeatUnavailableException(
					"Invalid seat number: " + seatNumber + ". Valid seats are 1 to " + capacity + ".");
	}
}
