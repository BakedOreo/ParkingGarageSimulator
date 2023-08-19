class Ticket {
	private int carYear;
	private String carMake;
	private String carModel;
	private static int carNumber = 0;
	private int arrivalTime;
	private int departureTime;
	private int laneNumber;
	private int ticketNumber;
	private static int total;

	public Ticket (Car car, int arrival, int departure) {
		this.carYear = car.getYear();
		this.carMake = car.getMake();
		this.carModel = car.getModel();
		arrivalTime = arrival;
		departureTime = departure;
		carNumber++;
	}

	// Lane number can be changed in the case of displacements
	public void setLaneNumber(int n) {
		laneNumber = n;
	}

	// Car number to static to keep track
	public int getCarNumber() {
		return carNumber;
	}

	// Lane to depart from
	public int getLaneNumber() {
		return laneNumber;
	}

	public int getDeparture() {
		return departureTime;
	}
	
	public String getTicketId() {
		String id = "" + carNumber + laneNumber + arrivalTime + departureTime;
		return id;
	}

	public String toString() {
		return "Car " + carNumber + "(" + arrivalTime + ")" + " has entered lane " 
			+ laneNumber + "(" + departureTime + ")";
	}

	public String toString2() {
		return "Car " + carNumber + "(" + arrivalTime + ")" + " has been displaced to lane " 
			+ laneNumber + "(" + departureTime + ")";
	}


}