class Lane {
	private Ticket[] tickets;
	
	public Lane() {
		tickets = new Ticket[7];
	}

	// Clear all non-null items in lane
	public void emptyLane(int time) {
		for (int i = 0; i < 7; i++) {
			if (tickets[i] != null && tickets[i].getDeparture() == time) {
				tickets[i] = null;
			}
		}	
	}

	// Check if lane is full
	public boolean isFull() {
		for (int i = 0; i < 7; i++) {
			if (tickets[i] == null) {
				return false;
			}
		}
		return true;
	}

	// Add ticket to lane
	public void addTicket(Ticket ticket) {
		for (int i = 0; i < 7; i++) {
			if (tickets[i] == null) {
				tickets[i] = ticket;
				return;
			}
		}
	}

	public String toString() {
		String res = "{";
		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i] == null) {
				res += "_";
			}
			else {
				res += "■";
			}
			if (i != tickets.length - 1) {
				res += " ";
			}
		}
		res += "}";
		return res;
	}

	
}