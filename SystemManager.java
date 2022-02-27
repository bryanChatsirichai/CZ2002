package CZ2002_Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;

/**
 * Main controller of the system
 * Collects information from other class and outputs to console application
 */
public class SystemManager
{

	static ArrayList<Table> tableList = new ArrayList<Table>();
	static ArrayList<Order> orderList = new ArrayList<Order>();
	static ArrayList<Order> paidOrderList = new ArrayList<Order>();
	static ArrayList<Customer> customerList = new ArrayList<Customer>();
	static ArrayList<Staff> staffList = new ArrayList<Staff>();
	static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();


	/**
	 * Main function of the program
	 * @param args args
	 */
	public static void main(String[] args)
	{
		SystemManager sys = new SystemManager();

		LocalTime currentTime = LocalTime.parse("09:00");

		String tempStr;

		Menu menu = new Menu(); // MENU

		int choice, numOfPax, tableIndex;
		Scanner sc = new Scanner(System.in);

		//////////////////////// DRIVER MENU \\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		// PRE MADE MENU; 		A menu that has pre-initialised menuItems 
		// NON PRE MADE MENU: 	An empty menu

		System.out.println("Do you want to use a pre-made menu (0/1)?");
		choice = sc.nextInt();

		if(choice == 1)
			menu.LoadBaseMenu();

		// Load Pre Made Staff	
		sys.LoadStaff(); 
		sys.LoadTable();

		sys.DisplaySystemOptions();
		choice = -1;

		// MENU LOOP //////////////////////////////////////////

		do
		{
			choice = sc.nextInt();
			switch(choice)
			{
			case 0: // DISPLAY SYSTEM OPTIONS //
				sys.DisplaySystemOptions();
				break;

				// --- MENU --- //

			case 1: // MENU CREATION //
				menu.Create();
				break;

			case 2: // MENU UPDATE //
				menu.Update();
				break;

			case 3: // MENU REMOVAL //
				menu.Remove();
				break;

			case 4: 
				menu.PrintMenu();
				break;

				// --- CUSTOMER --- //

			case 5: // CREATE CUSTOMER //
				Customer cst = new Customer("","",1,true,-1);
				cst.CreateCustomer();
				System.out.println(cst.getName() + " created.");
				sys.AppendCustomerToList(cst); 
				break;

				// --- ORDER --- //

			case 6: // CREATE ORDER // 
				System.out.println("=========================");
				sc.nextLine(); // Buffer
				System.out.println("Creating New Order...");
				sys.ViewAllStaff();
				System.out.println("Enter Staff Name: ");
				tempStr = sc.nextLine();

				for(int i = 0; i < staffList.size(); i++)
				{
					if(tempStr.equals(staffList.get(i).getName())) 
					{
						Order order = new Order(staffList.get(i),currentTime,-1,1);
						order.createOrder(menu);
						sys.AppendOrderToList(order);
						System.out.println("Order Created!");
						break;
					}
					if(i == staffList.size() - 1) System.out.println(tempStr + " not found in staffList.");
				}
				break;

			case 7: // UPDATE ORDER //
				System.out.println("=========================");
				System.out.println("Updating Order...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.getOrderToUpdate(tableIndex, menu);
				break;

			case 8: // VIEW ORDERS //
				System.out.println("=========================");
				System.out.println("Viewing Order...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.ViewOrder(tableIndex);
				break;

			case 9: // VIEW ALL ORDERS //
				System.out.println("=========================");
				System.out.println("Viewing All Orders...");
				sys.ViewAllOrders();
				break;

			case 10: // PRINT ORDER INVOICE //
				System.out.println("=========================");
				System.out.println("Viewing Order Invoice...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.ViewOrderInvoice(tableIndex);
				break;

			case 11: // MAKE PAYMENT //
				System.out.println("=========================");
				System.out.println("Making payment...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.MakePayment(tableIndex);
				break;


				// --- TABLE --- //

			case 12: // VIEW TABLE //
				sys.ViewTable();
				break;

				// --- RESERVATION  --- //

			case 13: // CREATE RESERVATION //
				sc.nextLine(); 
				System.out.println("=========================");
				sys.ViewAllCustomer();
				System.out.println("Creating New Reservation...");
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();

				for(int i = 0; i < customerList.size(); i++)
				{
					//System.out.println("Size: "+ customerList.size());
					//System.out.print("cNames:" + customerList.get(i).getName());
					if(tempStr.equals(customerList.get(i).getName())) 
					{
						System.out.println("Input Reservation Time (XX:XX):");
						String temp = sc.nextLine();
						System.out.println(LocalTime.parse(temp));
						Reservation rsv = new Reservation("",LocalTime.parse(temp),-1,customerList.get(i),-1);
						rsv.createReservation();
						//System.out.println("Time: " + rsv.getReservationTime());

						int index = sys.FindFreeTable(rsv.getReservationPax());

						if(index != -1) 
						{
							rsv.setReservationTable(index);
							sys.ReserveTable(index);
							sys.AppendReservationToList(rsv);
							System.out.println("Reservation for " + rsv.getReservationCustomer().getName() + " created!");
							break;
						}
						else
						{
							System.out.println("Reservation failed.");
							break;
						}

					}

				}
				// System.out.println(tempStr + " not found in customerList.");
				break;

			case 14: // REMOVE RESERVATION //
				sys.RemoveReservation();

				break;

			case 15:
				System.out.println("=========================");
				System.out.println("Viewing All Reservation...");
				sys.ViewAllReservation();

				break;




				// --- OCCUPY TABLE --- //

			case 16: // OCCUPY TABLE - FOR WALK IN CUSTOMERS //
				sc.nextLine();
				Boolean cstFound = false;
				System.out.println("=========================");
				sys.ViewAllCustomer();
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				for(int i = 0; i < customerList.size(); i++)
				{
					if(tempStr.equals(customerList.get(i).getName()))
					{
						System.out.println("Enter Pax: ");
						numOfPax = sc.nextInt();
						tableIndex = sys.OccupyFreeTable(numOfPax);
						customerList.get(i).setTableNo(tableIndex); 
						cstFound = true;
						break;
					}
					if(!cstFound & i == customerList.size() - 1)System.out.println("All tables occupied.");
				}
				break;

			case 17: // OCCUPY TABLE - FOR RESERVED CUSTOMERS //
				sc.nextLine();
				System.out.println("=========================");
				sys.ViewAllReservation();
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				for (int i=0; i < reservationList.size(); i++)
				{
					if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
					{
						tableList.get(reservationList.get(i).getReservationTable()).setOccupied(true);
						reservationList.remove(i);
						System.out.println("Fufilled Reservation for " + tempStr);
						break;
					}
				}
				break;

			case 18:
				currentTime = sys.TimeOptions(currentTime);
				break;

			case 19: // View Total Revenue
				sys.ViewTotalRevenue();
				break;

			case 20: // EXIT PROGRAM //
				System.out.println("Exiting Program...");
				break;

				/////////////////////////////////////////////

			}
		} while(choice < 19);
	}

	/**
	 * Appends Customer Object to customerList
	 * @param cst Customer Object to be appended
	 */
	public void AppendCustomerToList(Customer cst) 
	{
		customerList.add(cst);
		// System.out.println(cst.getName() + " appended to customerList!");
	}

	/**
	 * Appends Order Object to orderList
	 * @param order Order Object to be appended
	 */ 
	public void AppendOrderToList(Order order)
	{
		orderList.add(order);
		// System.out.println("Order appended to customerList!");
	}

	/**
	 * Finds a specific Order Object to be updated via Table Number
	 * @param tableIndex Table number of order to be updated 
	 * @param menu Menu Object to be use to update order
	 */
	public void getOrderToUpdate(int tableIndex, Menu menu)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).updateOrder(menu);
				System.out.println("Order updated.");
				return;
			}
		}
		System.out.println("Order not found.");
		return;
	}

	/**
	 * Displays the order of a table via its table number 
	 * @param tableIndex Table number of order to be viewed 
	 */
	public void ViewOrder(int tableIndex)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).printOrder();
				return;
			}
		}
		System.out.println("Order not found.");
		return;
	}	

	/**
	 * Displays orders of every table 
	 */
	public void ViewAllOrders()
	{	
		for (int i = 0; i < orderList.size(); i++)
		{
			orderList.get(i).printOrder();
		}
		return;
	}

	/**
	 * Displays invoice of order via its table number
	 * @param tableIndex Table number of selected order 
	 */
	public void ViewOrderInvoice(int tableIndex)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).printInvoice();
				return;
			}	
		}
		System.out.println("Order not found.");
		return;
	}

	/**
	 * Makes payment of selected order via table number.
	 * Removes order from orderList.
	 * Appends order to paidOrderList.
	 * setOccupiedfalse() and setReserved(false) of table.
	 * @param tableIndex Table number of the order to be paid 
	 */
	public void MakePayment (int tableIndex)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).printInvoice();
				paidOrderList.add(orderList.get(i));
				orderList.remove(i);
				tableList.get(tableIndex-1).setOccupied(false);
				tableList.get(tableIndex-1).setReserved(false);
				System.out.println("Payment for " + tableIndex + " Made!");
				return;
			}	
		}
		System.out.println("Order not found.");
		return;
	}

	/**
	 * Finds a free table
	 * A free Table must be both not occupied and not reserved 
	 * @param numOfPax Capacity of Seats
	 * @return Table number of free table
	 */
	public int FindFreeTable(int numOfPax)
	{
		for(int i = 0; i < tableList.size() ; i++)
		{
			if(tableList.get(i).getSeatCapacity() >= numOfPax && !tableList.get(i).isReserved() && !tableList.get(i).isOccupied())
			{
				System.out.println("Table " + (i+1) + " is free");
				return i;
			}
		}
		System.out.println("No tables are free.");
		return -1;
	}

	/**
	 * Reserves a table
	 * setReserved(true) of table
	 * @param tableIndex Table number of table to be reserved 
	 */
	public void ReserveTable(int tableIndex)
	{
		tableList.get(tableIndex).setReserved(true);
		System.out.println("Table " + (tableIndex+1) + " reserved");
		return;
	}

	/**
	 * Occupies a table
	 * setOccupied(true) of table
	 * @param tableIndex Table number of table to be occupied 
	 */
	public void OccupyTable(int tableIndex)
	{
		tableList.get(tableIndex).setOccupied(true);
		System.out.println("Table " + (tableIndex+1) + " occupied!");
	}

	/**
	 * Occupies a free table based on number of pax
	 * Returns the table number that was occuped, if found.
	 * Otherwise, returns -1 if there are no free tables
	 * A free Table must be both not occupied and not reserved 
	 * @param numOfPax Capacity of Seats
	 * @return Table number that was occupied
	 */
	public int OccupyFreeTable(int numOfPax)
	{
		int tableIndex = FindFreeTable(numOfPax);

		if(tableIndex != -1)
		{
			OccupyTable(tableIndex);
			return tableIndex;
		}
		else
		{
			System.out.println("All tables occupied.");
			return -1;
		}	
	}

	/**
	 * Views all table and its parameters
	 */
	public void ViewTable() 
	{
		System.out.println("===================================");
		System.out.println("Viewing Tables...");
		for(int i = 0; i < tableList.size(); i++)
		{
			System.out.println("Table Number " + (i+1));
			System.out.println("Table Size: " + tableList.get(i).getSeatCapacity()) ;
			System.out.println("Reserved: " + tableList.get(i).isReserved() + " | Occupied: " + tableList.get(i).isOccupied());
			System.out.println("===================================");
		}
	}

	/**
	 * occupies the table that the customer has reserved
	 * @param name Name of customer that made the reservation
	 */
	public void fufilReservation(String name)
	{
		for (int i = 0; i < customerList.size(); i++)
		{
			if(name.equals(customerList.get(i).getName()))
			{
				OccupyTable(customerList.get(i).getTableNo());
				System.out.println("Reservation fulfilled for" + name + ".");
				return;
			}
		}
		System.out.println(name + " not found.");
		return;
	}

	/**
	 * View reservation of specific reservation and 
	 * @param tableIndex Table number of reservation
	 */
	public void ViewReservation(int tableIndex)
	{
		for(int i = 0; i < reservationList.size(); i++)
		{
			if(reservationList.get(i).getReservationTable() == tableIndex)
			{
				System.out.println("Table: " + (reservationList.get(i).getReservationTable() + 1));
				System.out.println("Reservation Date: " + reservationList.get(i).getReservationDate());
				System.out.println("Reservation Time: " + reservationList.get(i).getReservationTime());
				System.out.println("Customer: " + reservationList.get(i).getReservationCustomer().getName());
				System.out.println("Num of Pax: " + reservationList.get(i).getReservationPax());
				break;
			}
		}
	}

	/**
	 * View all reservation based on table numbers
	 */
	public void ViewAllReservation()
	{
		for(int i = 0; i < tableList.size(); i++) 
		{						
			ViewReservation(i);
			System.out.println("-------------------------");
		}
	}

	/**
	 * Prints all valid customers in a single line
	 */
	public void ViewAllCustomer()
	{
		System.out.println("Viewing Customer...");
		for (int i = 0; i < customerList.size(); i++)
		{
			System.out.print(customerList.get(i).getName() + ", ");
		}
		System.out.println();
		System.out.println("-------------------------");
	}

	/**
	 * Prints all valid staffs in a single line 
	 */
	public void ViewAllStaff()
	{
		System.out.println("Viewing Staff...");
		for (int i = 0; i < staffList.size(); i++)
		{
			System.out.print(staffList.get(i).getName() + ", ");
		}
		System.out.println();
		System.out.println("-------------------------");
	}

	/**
	 * Append Reservation Object to reservationList
	 * @param rsv Reservation Object to be appended
	 */
	public void AppendReservationToList(Reservation rsv)
	{
		reservationList.add(rsv);
		//System.out.println("Reservation for " + rsv.getReservationCustomer().getName() + " appended to List");
	}

	/**
	 * Remove Reservation Object of specific reservation from the reservation List
	 * setReserved(false) of specific reserved table
	 */
	public void RemoveReservation()
	{		
		String tempStr;
		Scanner sc = new Scanner(System.in);

		System.out.println("=========================");
		System.out.println("Removing Reservation...");
		System.out.println("Enter Customer Name: ");
		tempStr = sc.nextLine();
		for (int i=0; i < reservationList.size(); i++)
		{
			if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
			{
				tableList.get(reservationList.get(i).getReservationTable()).setReserved(false);;
				reservationList.remove(i);
				System.out.println("Reservation for " + tempStr + " Removed");
				break;
			}
			System.out.println(tempStr + " not found in customerList.");
		}	
	}

	/**
	 * Checks all Reservation Objects in reservationList and times out expired reservations
	 * Expired reservations are reservations that exceed the maxTime
	 * maxTime = Reservation Time + Max Time Limit
	 * @param currentTime Current Time of System
	 */
	public void CheckForTimeOut(LocalTime currentTime)
	{		
		int maxTimeDuration = 30;
		String tempStr;

		for(int i = 0; i < reservationList.size(); i++)
		{ 
			//System.out.println("i is " + i);
			//System.out.println("Reservation Time: " + reservationList.get(i).getReservationTime());
			LocalTime maxTime = reservationList.get(i).getReservationTime().plus(Duration.ofMinutes(maxTimeDuration));
			//System.out.println("Max Time: " + maxTime);
			if(currentTime.isAfter(maxTime))
			{
				tempStr = reservationList.get(i).getReservationCustomer().getName();

				for (int j=0; j < reservationList.size(); j++)
				{ 

					if(tempStr.equals(reservationList.get(j).getReservationCustomer().getName()))
					{
						//System.out.println("j is " + j);
						tableList.get(reservationList.get(j).getReservationTable()).setReserved(false);;
						reservationList.remove(j);
						System.out.println("Reservation for " + tempStr + " Removed.");
						i--;
						break;
					}
				}

			}
		}

	}
	/**
	 * Loops through paidOrderList and sums the total revenue generated.
	 */
	public void ViewTotalRevenue()
	{
		System.out.println("=========================");
		System.out.println("Calculating Total Revenue...");

		// ADD LOGIC HERE

		double totalRevenue = 0;

		for(int i = 0; i < paidOrderList.size(); i++)
		{
			paidOrderList.get(i).printOrderItemsWoList();
			totalRevenue += paidOrderList.get(i).getTotalPriceAfterTax();
		}

		System.out.println("Total Revenue = $" + totalRevenue ); 
	}
	/**
	 * Time options for currentTime variable
	 * Time can be view or modified
	 * Each time the currentTime is modified, CheckForTimeOut() will be triggered.
	 * @param currentTime Current Time of the System
	 * @return the modified time
	 */
	public LocalTime TimeOptions(LocalTime currentTime)
	{
		Scanner sc = new Scanner(System.in);
		String tempStr;
		int tempInt, choice;
		System.out.println("=========================");
		System.out.println("(1) View");
		System.out.println("(2) Set Time Manually");
		System.out.println("(3) Advance time by Minutes");
		System.out.println("(4) Advance time by Hours");
		System.out.println("(5) Exit");
		System.out.println("=========================");

		do
		{
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Time: " + currentTime);
				break;

			case 2:
				sc.nextLine(); // Buffer
				System.out.println("Input Time (XX:XX): ");
				tempStr = sc.nextLine();
				currentTime = LocalTime.parse(tempStr);
				System.out.println("Time: " + currentTime);
				CheckForTimeOut(currentTime);
				break;

			case 3:
				System.out.println("Advance time by (Minutes): ");
				tempInt = sc.nextInt();
				currentTime = currentTime.plus(Duration.ofMinutes(tempInt));
				System.out.println("Time: " + currentTime);
				CheckForTimeOut(currentTime);
				break;

			case 4:
				System.out.println("Advance time by (Hours): ");
				tempInt = sc.nextInt();
				currentTime = currentTime.plus(Duration.ofHours(tempInt));
				System.out.println("Time: " + currentTime);
				CheckForTimeOut(currentTime);
				break;

			case 5:
				System.out.println("Exiting Time Options");
				break;

			}
		} while(choice < 5);

		return currentTime;
	}

	/**
	 * Displays all valid  system options
	 */
	public void DisplaySystemOptions()
	{
		System.out.println("=========================");

		System.out.println("0:  Display System Options");

		// MENU //////////////////////////////////

		System.out.println("1:  Create Menu");
		System.out.println("2:  Update Menu");
		System.out.println("3:  Remove Menu");
		System.out.println("4:  View Menu");

		// CUSTOMER //////////////////////////////

		System.out.println("5:  Create Customer");

		// ORDER /////////////////////////////////

		System.out.println("6:  Create Order");
		System.out.println("7:  Update Order");
		System.out.println("8:  View Order");
		System.out.println("9:  View All Orders");

		System.out.println("10: Print Order Invoice");
		System.out.println("11: Make Payment");

		// TABLES /////////////////////////////////

		System.out.println("12: View Tables");

		// RESERVATION ///////////////////////////	

		System.out.println("13: Create Reservation");
		System.out.println("14: Remove Reservation");
		System.out.println("15: View All Reservations");
		System.out.println("16: Occupy Table (Walk-ins)");
		System.out.println("17: Occupy Table (Reservations)");

		System.out.println("18: Time Options");

		System.out.println("19: View Total Revenue");

		System.out.println("20: Quit");

		System.out.println("=========================");
		///////////////////////////////////////////////////////		
	}

	/**
	 * Loads a collection of pre-determined Staff Objects to staffList
	 */
	public void LoadStaff()
	{
		Staff staff1 = new Staff("Joan", "Female", "Waiter", 0);
		staffList.add(staff1);
		Staff staff2 = new Staff("Mary", "Female", "Waiter", 1);
		staffList.add(staff2);
		Staff staff3 = new Staff("Bill", "Male", "Cook", 2);
		staffList.add(staff3);
		Staff staff4 = new Staff("Johnson", "Male", "Cook", 3);
		staffList.add(staff4);
		Staff staff5 = new Staff("Emma", "Female", "Manager", 4);
		staffList.add(staff5);
	}

	/**
	 * Loads a collection of pre-determined Table Objects to tableList
	 */
	public void LoadTable()
	{
		Table table1 = new Table (2,false,false);
		tableList.add(table1);
		Table table2 = new Table (2,false,false);
		tableList.add(table2);
		Table table3 = new Table (4,false,false);
		tableList.add(table3);
		Table table4 = new Table (4,false,false);
		tableList.add(table4);
		Table table5 = new Table (4,false,false);
		tableList.add(table5);
		Table table6 = new Table (4,false,false);
		tableList.add(table6);
		Table table7 = new Table (6,false,false);
		tableList.add(table7);
		Table table8 = new Table (8,false,false);
		tableList.add(table8);
		Table table9 = new Table (10,false,false);
		tableList.add(table9);
	}

}
