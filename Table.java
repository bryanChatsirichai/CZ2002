package CZ2002_Assignment;

/**
* Entity Class for Tables in the Restaurant
* Tables store data for Seat Capacity, Occupation and Reservation Status
*/
public class Table {
	private int SeatCapacity;
	private boolean isOccupied, isReserved;
	/**
	* Constructor for Table Class
	* @param seatCapacity number of maximum seats the Table can contain
	* @param isOccupied boolean to indicate whether Table is occupied
	* @param isReserved boolean to indicate whether Table is reserved
	*/
	public Table(int seatCapacity, boolean isOccupied, boolean isReserved) {
		super();
		this.SeatCapacity = seatCapacity;
		this.isOccupied = isOccupied;
		this.isReserved = isReserved;
	}
	
	/**
	* Returns the seat capacity of the Table
	*/
	public int getSeatCapacity() {
		return SeatCapacity;
	}
	
	/**
	* Returns the occupation status of the Table
	*/
	public boolean isOccupied() {
		return isOccupied;
	}
	
	/**
	* Returns the reservation status of the Table
	*/
	public boolean isReserved() {
		return isReserved;
	}
	
	/**
	* Sets the seat capacity of the Table to the input
	* @para seatCapacity seat capacity of the Table
	*/
	public void setSeatCapacity(int seatCapacity) {
		this.SeatCapacity = seatCapacity;
	}
	
	/**
	* Sets the occupation status of the Table to the input
	* @para isOccupied occupation status of the Table
	*/
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	/**
	* Sets the reservation status of the Table to the input
	* @para isReserved reservation status of the Table
	*/
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	
}

