import java.util.HashMap;

class Garage {
	private Lane[] lanes;
	private int currentLane;
	
	// Instead of having to loop through every lane for displaced cars, by using a hashmap I 		can check only for lanes with displaced cars
	private HashMap<Integer, Integer> displaced;
	private int counter;
	private int displaceCount;

	public Garage() {
		currentLane = 0;
		counter = 0;
		displaced = new HashMap<Integer, Integer>();
		lanes = new Lane[48];
		for (int i = 0; i < 48; i++) {
			lanes[i] = new Lane();
		}
	}

	// Helper function used to convert time to number of minutes since 12
	public int timeToMins(int time) {
		String sTime = Integer.toString(time);
		int hours = 0;
		int minutes = 0;
		if (sTime.length() == 4) {
			hours = Integer.parseInt(sTime.substring(0, 2));
			minutes = Integer.parseInt(sTime.substring(sTime.length() - 2));
		} else if (sTime.length() == 3) {
			hours = Integer.parseInt(sTime.substring(0, 1));
			minutes = Integer.parseInt(sTime.substring(sTime.length() - 2));
		} else {
			minutes = Integer.parseInt(sTime);
		}
		int total = (hours * 60) + minutes;
		return total;
	}

	// Calculates stay time. Used for keeping stats
	public int calculateTime(int arrival, int departure) {
		int arrivalMin = timeToMins(arrival);
		int departureMin = timeToMins(departure);
		if (departureMin < arrivalMin) {
			departureMin += 1440;
		}
		return departureMin - arrivalMin;
	}

	// Calculates lane to go to
	public int calculateLane(int departure) {
		int minDeparture = timeToMins(departure);
		if (minDeparture >= 720) {
			minDeparture -= 720;
		}
		return minDeparture/15;
	}

	// Empties current lane and those with displaced cars
	public void depart(int time) {
		lanes[currentLane].emptyLane(time);
		for (int i : displaced.keySet()) {
			lanes[displaced.get(i)].emptyLane(time);
		}
		displaced.remove(time);
	}

	// Returns number of cars per hour
	public int readCars() {
		int res = 0;
		res = counter;
		counter = 0;
		return res;
	}

	public int getDisplaceCount() {
		return displaceCount;
	}
	
	// I'm aware that this code is REALLY ugly but I'm too lazy to better organize it
	public void sortTicket(Ticket ticket) {
		if (!lanes[ticket.getLaneNumber()].isFull()) {
			lanes[ticket.getLaneNumber()].addTicket(ticket);
			System.out.println(ticket.toString());
			System.out.println(lanes[ticket.getLaneNumber()].toString());
			counter++;
			return;
		}
		else {
			int i;
			// If lane full go to next
			for (i = ticket.getLaneNumber(); i < 48; i++) {
				if (!lanes[i].isFull()) {
					ticket.setLaneNumber(i);
					lanes[i].addTicket(ticket);
					System.out.println(ticket.toString2());
					System.out.println(lanes[ticket.getLaneNumber()].toString());
					displaced.put(ticket.getDeparture(), ticket.getLaneNumber());
					displaceCount++;
					counter++;
					return;
				}
			}
			// If lanes up to 47 are full go back to 0
			for (int j = 0; j < ticket.getLaneNumber(); j++) {
				if (!lanes[j].isFull()) {
					ticket.setLaneNumber(j);
					lanes[j].addTicket(ticket);
					System.out.println(ticket.toString2());
					System.out.println(lanes[ticket.getLaneNumber()].toString());
					displaced.put(ticket.getDeparture(), ticket.getLaneNumber());
					displaceCount++;
					counter++;
					return;
				}
			}
		// Car turned away
		System.out.println("Car " + ticket.getCarNumber() + "(" + ticket.getDeparture()
				+ ") has been turned away from the garage");
		}
	}


	// Lanes cycled by simulation
	public void cycle() {
		if (currentLane == 47) {
			currentLane = 0;
		} else {
			currentLane++;
		}
	}
}