package CZ2002_Assignment;

import java.util.Scanner;
import java.time.*;

/**
 * Reservation class for reservations made in the restaurant
 * Contains attributes and methods relevant to a reservation made
 */
public class Reservation {

	/**
	* The date of reservation
	*/
	private String date;
	
	/**
	* The time of reservation
	*/
	LocalTime time;
	
	/**
	* Number of customers who reserved seats
	*/
	private int pax;
	
	/**
	* The customer who made the reservation
	*/
	private Customer customer;
	
	/**
	* The table number allocated to the reservation
	*/
	private int tableNo;
	
	/**
	*Constructor for Class
	* @param date Date of Reservation
	* @param time Time of Reservation
	* @param pax Total number of customers
	* @param customer Customer who made the Reservation
	* @param tableNo Table Number of Reservation
	*/
	public Reservation(String date, LocalTime time, int pax, Customer customer, int tableNo)
	{
		this.date = date;
		this.time = time;
		this.pax = pax;
		this.customer = customer;
		this.tableNo = tableNo;
	}
	
	/**
	 * Gets the date of reservation
	 * @return date of reservation
	 */
	public String getReservationDate()
	{
		return date;
	}
	
	/**
	 * Sets the date of reservation
	 * @param string of date
	 */
	public void setReservationDate(String date)
	{
		this.date = date;
	}
	
	/**
	 * Gets the time of reservation
	 * @return time of reservation
	 */
	public LocalTime getReservationTime()
	{
		return time;
	}
	
	/**
	 * Sets the time of reservation
	 * @param string of time
	 */
	public void setReservationTime(LocalTime time)
	{
		this.time = time;
	}
	
	/**
	 * Gets the total number of people for the reservation
	 * @return total number of people
	 */
	public int getReservationPax()
	{
		return pax;
	}
	
	/**
	 * Sets the total number of people for the reservation
	 * @param int total number of people
	 */
	public void setReservationPax(int pax)
	{
		this.pax = pax;
	}
	
	/**
	 * Gets Customer who made reservation
	 * @return Customer who made reservation
	 */
	public Customer getReservationCustomer()
	{
		return customer;
	}
	
	/**
	 * Sets customer who made reservation
	 * @param customer who made reservation
	 */
	public void setReservationCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	/**
	 * Gets table number of reservation
	 * @return table number of reservation
	 */
	public int getReservationTable()
	{
		return tableNo;
	}
	
	/**
	 * Sets the table number of this reservation
	 * @param int of table number
	 */
	public void setReservationTable(int tableNo)
	{
		this.tableNo = tableNo;
	}
	
	/**
	* Method which allows the user to create a reservation
	* User inputs number of pax and date of reservation
	*/
	public void createReservation()
	{
		Scanner sc = new Scanner(System.in);
		
		int tempIntPax;
		String tempStringDate;
		
		System.out.println("Number of Pax (including customer): ");
		tempIntPax = sc.nextInt();
		this.setReservationPax(tempIntPax);
		sc.nextLine(); // Buffer
		System.out.println("Date of reservation: ");
		tempStringDate = sc.nextLine();
		this.setReservationDate(tempStringDate);
	}
}
