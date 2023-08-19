import java.util.Random;
import java.util.ArrayList;

class Simulation {
	private Garage garage;
	private static int time;
	private static Random rand = new Random(2);
	private ArrayList<Integer> carsPerHour;
	private int totalTime;
	private int displaceCount;
	
	public Simulation() {
		displaceCount = 0;
		this.garage = new Garage();
		carsPerHour = new ArrayList<Integer>();
		time = 1200;
	}

	// Randomly generate car departure time
	public int generateDeparture() {
		int departure = time;
		int duration = rand.nextInt(720);
		departure += (duration / 60) * 100;
		departure += duration % 60;
		if ((departure % 100) > 60) {
			departure += 40;
		}
		if (departure >= 2360) {
			departure -= 2360;	
		}
		// System.out.println(time + " " + duration + " " + departure);
		return departure;
	}

	// Create ticket
	public Ticket generateTicket(Car car) {
		int departure = generateDeparture();
		int laneNumber = garage.calculateLane(departure);
		Ticket ticket = new Ticket(car, time, departure);
		ticket.setLaneNumber(laneNumber);
		totalTime += garage.calculateTime(time, ticket.getDeparture());
		return ticket;
	}

	// Stat trackers
	public void addDisplace() {
		displaceCount++;
	}

	public double getAverage() {
		double averageTime;
		Ticket t = new Ticket(new Car(), 0, 0);
		double numCars = t.getCarNumber() - 1;
		averageTime = (totalTime/numCars)/60;
		return averageTime;
	}
	
	public void getStats() {
		System.out.println("\nSTATISTICS");
		System.out.println("Cars displaced: " + garage.getDisplaceCount());
		for (int i = 1; i < 25; i++) {
			System.out.println("Hour " + i + ": " + carsPerHour.get(i) + " cars");
		}
		System.out.println("Average stay time: " + getAverage() + " hours");
	}

	// Garage is cycled ever 15 minutes and cars depart every minute
	// Cars have a 25% chance of spawning every minute for simplicity's sake
	public void run() throws InterruptedException {
		if (time % 15 == 0 && time % 1200 >= 15) {
			garage.cycle();
		}
		garage.depart(time);
		if (time % 100 == 0 || time == 1159) {
			carsPerHour.add(garage.readCars());
		}
		if (rand.nextInt(4) < 1) {
			garage.sortTicket(generateTicket(new Car()));
		}
		time++;
		if (time % 100 >= 60) {
			time += 40;
		}
		if (time == 2400) {
			time -= 2400;
		}
		Thread.sleep(10);
	}
}