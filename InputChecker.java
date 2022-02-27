package CZ2002_Assignment;

import java.util.Scanner;

/**
 * Class to check for input 
 */
public class InputChecker {
	private int numOfTries = 4;

	/**
	 * Method that returns a boolean based on the min and max value defined compared against the value
	 * @param value value to be compared to
	 * @param min minimum value to compare with
	 * @param max maximum value to compare with
	 * @return boolean if the value exceeds the min/ max value 
	 */
	private boolean inRange(double value, double min, double max) {
		if(value >= min && value <= max)
			return true;
		else
			return false;
	}

	/**
	 * Checks against a double and returns a double if it can be assigned to c
	 * @param c value to be tested against
	 * @param sc scanner reference to read input
	 * @param min minimum value range for c
	 * @param max maximum value range for c
	 * @return double value to assigned to the variable, return -1 if unsuccessful
	 */
	public double doubleChecker(double c, Scanner sc, double min, double max) {
		for(int i = 0; i < numOfTries; i++) {
			try {
				c = sc.nextDouble();
			} catch(Exception e) {
				System.out.println("Invalid input, Number of tries: " + (i + 1));
				sc.nextLine();
				continue;
			}
			if(!inRange(c, min, max)) {
				continue;
			}
			else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * Checks against an int and returns an int if it can be assigned to c
	 * @param c value to be tested against
	 * @param sc scanner reference to read input
	 * @param min minimum value range for c
	 * @param max maximum value range for c
	 * @return int value to assigned to the variable, return -1 if unsuccessful
	 */
	public int intChecker(int c, Scanner sc, int min, int max) {
		for(int i = 0; i < numOfTries; i++) {
			try {
				c = sc.nextInt();
			} catch(Exception e) {
				System.out.println("Invalid input, Number of tries: " + (i + 1));
				System.out.println("Ensure only numerical inputs");
				sc.nextLine();
				continue;
			}
			if(!inRange(c, min, max)) {
				continue;
			}
			else {
				return c;
			}
		}
		return -1;
	}
	
	/**
	 * Checks against an MenuCategory and returns an category if it can be assigned to c
	 * @param c value to be tested against
	 * @param sc scanner reference to read input
	 * @return category value to assigned to the variable, return null if unsuccessful
	 */
	public MenuCategory categoryChecker(MenuCategory c, Scanner sc) {
		for(int i = 0; i < numOfTries; i++) {
			try {
				c = MenuCategory.valueOf(sc.nextLine().toUpperCase());
				return c;
			} catch(Exception e) {
				System.out.println("Invalid input, Number of tries: " + (i + 1));
				System.out.println("Ensure category is written correctly.");
				sc.nextLine();
				continue;
			}
		}
		return null;
	}

}