class Main {
  public static void main(String[] args) {
	  Simulation simulation = new Simulation();
	  try {
		  for (int i = 0; i < 1440; i++) {
			  simulation.run();
		  }
	  }
	  catch (InterruptedException e) {}
	  simulation.getStats();
	}
}