package RailwayBookingSystemModule;

// Passenger model class holds name, age,a and seat number
public class Passenger {
	private final String name;
	private final int age;
	private final int seatNumber;

	public Passenger(String name, int age, int seatNumber) {
		this.name = name;
		this.age = age;
		this.seatNumber = seatNumber;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	@Override
	public String toString() {
		return "Passenger [name=" + name + ", age=" + age + ", seatNumber=" + seatNumber + "]";
	}

}
