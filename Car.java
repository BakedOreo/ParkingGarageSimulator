import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Car {
	private String make;
	private String model;
	private int year;
	private ArrayList<String> makes = new ArrayList<String>();
	private ArrayList<String> models = new ArrayList<String>();
	private static Random rand = new Random(2);

	public Car() {
		readFile("Make.txt");
		readFile("Model.txt");
		this.make = makes.get(rand.nextInt(makes.size()));
		this.model = models.get(rand.nextInt(models.size()));
		this.year = rand.nextInt(23) + 2000;
	}

	// Read txt files to get sample makes and models
	public void readFile(String file) {
		try {
		File myObj = new File(file);
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if (file == "Make.txt") {
				makes.add(data);
			}
			else if (file == "Model.txt") {
				models.add(data);
			}
		}
		myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}
	
}