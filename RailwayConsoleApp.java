package RailwayBookingSystemModule;

import java.util.Scanner;

/**
 * Main console application (menu-driven). Uses switch-case and Scanner for
 * input. Simple, robust input reading with basic validation.
 */
public class RailwayConsoleApp {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("=== Railway Ticket Booking System ===");
		int capacity = requestCapacity(); // ask once at start (recommended 10-20)
		CoachManager coach = new CoachManager(capacity);

		boolean running = true;
		while (running) {
			printMenu();
			int choice = readInt("Enter choice: ");
			switch (choice) {
			case 1 -> displayFreeSeats(coach);
			case 2 -> handleBooking(coach);
			case 3 -> handleCancellation(coach);
			case 4 -> displayBooked(coach);
			case 5 -> {
				System.out.println("Exiting. Thank you!");
				running = false;
			}
			default -> System.out.println("Invalid choice, try again.");
			}
			System.out.println(); // blank line between operations
		}
		scanner.close();
	}

	private static int requestCapacity() {
		System.out.println("Enter coach capacity (recommended between 10 and 20):");
		while (true) {
			int n = readInt("Capacity: ");
			if (n >= 1)
				return n;
			System.out.println("Capacity must be at least 1.");
		}
	}

	private static void printMenu() {
		System.out.println("---- MENU ----");
		System.out.println("1. Check Available Seats");
		System.out.println("2. Book Seat");
		System.out.println("3. Cancel Seat");
		System.out.println("4. Display Booked Tickets");
		System.out.println("5. Exit");
	}

	private static void displayFreeSeats(CoachManager coach) {
		System.out.println("Free seats: " + coach.peekFreeSeats());
		System.out.printf("Booked: %d / %d%n", coach.totalBookedCount(), coach.getCapacity());
	}

	private static void handleBooking(CoachManager coach) {
		System.out.println("Enter passenger details to book a seat.");
		String name = readString("Name: ");
		int age = readInt("Age: ");
		int seat = readInt("Desired seat number: ");
		try {
			coach.reserveSeatAt(name, age, seat);
			System.out.println("Booking successful for seat " + seat + ".");
		} catch (SeatUnavailableException e) {
			System.out.println("Booking failed: " + e.getMessage());
		}
	}

	private static void handleCancellation(CoachManager coach) {
		int seat = readInt("Enter seat number to cancel: ");
		try {
			coach.vacateSeatAt(seat);
			System.out.println("Seat " + seat + " cancelled successfully.");
		} catch (SeatUnavailableException e) {
			System.out.println("Cancellation failed: " + e.getMessage());
		}
	}

	private static void displayBooked(CoachManager coach) {
		System.out.println("Booked tickets:");
		System.out.println(coach.showBookedPassengers());
	}

	// Utility input helpers
	private static int readInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			String line = scanner.nextLine().trim();
			try {
				return Integer.parseInt(line);
			} catch (NumberFormatException ex) {
				System.out.println("Invalid number. Try again.");
			}
		}
	}

	private static String readString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine().trim();
	}
}
