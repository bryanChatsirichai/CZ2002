package CZ2002_Assignment;

/*
import CZ2002_Assignment.MenuItem; // TODO: java.awt.MenuItem ->  CZ2002_Assignment.MenuItem
import java.util.*;  
public class Order {
	private Staff staffName;
	private ArrayList<MenuItem> alacarte;
	private ArrayList<MenuItem> promo;
	private ArrayList<Integer> alacarteqty;
	private ArrayList<Integer> promoqty;
	private String timeStamp;
	private int tableNo;
	private float discount;
	private double totalPrice;
	public Order(Staff name, String ts, int table) {
		this.staffName = name;
		this.alacarte = new ArrayList<MenuItem>();
		this.promo = new ArrayList<MenuItem>();
		this.alacarteqty = new ArrayList<Integer>();
		this.promoqty = new ArrayList<Integer>();
		this.totalPrice = 0;
	}
	// TODO: CreateOrder() <- Add this function
	// TODO: Take menu as a parameter and use addAlacarte() and addPromo() to create order
	// TODO: Ask input from User for other attributes
	// TODO: Return Order (FULLY CREATED) to SystemManager 
	public void CreateOrder() 
	{
		int tempInt;
		String tempStr;
		Scanner sc = new Scanner(System.in);
		System.out.println("Order Time Stamp: ");
		tempStr = sc.nextLine();
		setTimeStamp(tempStr);
		System.out.println("Order Table: ");
		tempInt = sc.nextInt();
		setTable(tempInt);
		sc.nextLine();
	}
	public void setDiscount(float d) {
		this.discount = d;
	}
	public void addAlacarte(MenuItem dish, Integer qty) {
		int index = alacarte.indexOf(dish);
		if(index < 0) {
			alacarte.add(dish);
			alacarteqty.add(qty);
		}
		else {
			Integer cur = alacarteqty.get(index);
			alacarteqty.set(index, cur + qty);
		}
		totalPrice += (dish.getItemPrice() * qty);
	}
	public void removeAlacarte(MenuItem dish, Integer qty) {
		int index = alacarte.indexOf(dish);
		Integer cur = alacarteqty.get(index);
		alacarteqty.set(index, cur - qty);
		if(alacarteqty.get(index) == 0) {
			alacarte.remove(index);
			alacarteqty.remove(index);
		}
		totalPrice -= (dish.getItemPrice() * qty);
	}
	public void addPromo(MenuItem dish, Integer qty) {
		int index = promo.indexOf(dish);
		if(index < 0) {
			promo.add(dish);
			promoqty.add(qty);
		}
		else {
			Integer cur = promoqty.get(index);
			promoqty.set(index, cur + qty);
		}
		totalPrice += (dish.getItemPrice() * qty);
	}
	public void removePromo(MenuItem dish, Integer qty) {
		int index = promo.indexOf(dish);
		Integer cur = promoqty.get(index);
		promoqty.set(index, cur - qty);
		if(promoqty.get(index) == 0) {
			promo.remove(index);
			promoqty.remove(index);
		}
		totalPrice -= (dish.getItemPrice() * qty);
	}
	public void printOrderItems() {
		for(int i = 0; i<alacarte.size(); i++ ) {
			System.out.printf("%d. %s x%d/n", i+1, alacarte.get(i).getItemName(), alacarteqty.get(i));
		}
		for(int i = 0; i<promo.size(); i++ ) {
			System.out.println("PROMO ITEMS");
			System.out.printf("%d. %s x%d/n", i+1, promo.get(i).getItemName(), promoqty.get(i));
		}
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public int getTableNo() {
		return tableNo;
	}
	public float getDiscount() { // TODO: int -> float 
		return discount;
	}
	public void printInvoice() {
		System.out.println("-------------------");
		System.out.println("INVOICE");
		System.out.println("Table Number: " + getTableNo());
		System.out.println("");
		printOrderItems();
		System.out.println("");
		System.out.println("Discount Applied: " + getDiscount());
		System.out.println("Total Price: " + ((getTotalPrice()) * ((100 - getDiscount()) / 100 )));
		System.out.println("-------------------");
	}
	// TODO: setTimeStamp(String ts) <- Add this function 
	public void setTimeStamp(String ts)
	{
		this.timeStamp = ts;
	}
	// TODO: setTable(int table) <- Add this function 
	public void setTable(int t)
	{
		this.tableNo = t;
	}
}
 */

import CZ2002_Assignment.MenuItem;
import CZ2002_Assignment.Menu;

import java.util.*;  
import java.time.*;

// ==========================================
// ASSUMPTIONS
// NAME OF ENTERED STAFF IS PRESENT IN STAFFLIST
// ASK USER TO TYPE -1 TO STOP ANY INPUTS
// ==========================================

/**
 * Order Class: attributes and methods that is relevent to a customer's order
 */
public class Order {

	/*
	 * The staff that is assigned to this order
	 */
	private Staff staffName;

	/*
	 * Stores the alacarte items that has been ordered in an ArrayList of MenuItems
	 */
	private ArrayList<MenuItem> alacarte;

	/*
	 * Stores the promotions that has been ordered in an ArrayList of Promotions
	 */
	private ArrayList<Promotion> promo;

	/*
	 * Stores the quantity of alacarte items that has been ordered in an ArrayList of Integers
	 * Index of alacarteqty corresponds to the alacarte item of the same index in alacarte.
	 */
	private ArrayList<Integer> alacarteqty;

	/*
	 * Stores the quantity of promotions that has been ordered in an ArrayList of Integers
	 * Index of promoqty corresponds to the promotion of the same index in promo.
	 */
	private ArrayList<Integer> promoqty;

	//private String timeStamp; // TODO
	LocalTime timeStamp;	// Changed String time to LocalTime together with its functions

	/*
	 * table number of this order
	 */
	private int tableNo;

	/*
	 * dscount applied to this order if any
	 */
	private float discount;

	/*
	 * total price of all ordered items
	 */
	private double totalPrice;

	/*
	 * taxes applied to this order ie. GST and/or service charge
	 */
	private float tax;
	private Scanner sc = new Scanner(System.in);

	/*
	 * Default constructor for Order
	 */
	public Order(Staff s, LocalTime ts, int tn, float d) {
		this.tax = 7;
		this.staffName = s;
		this.timeStamp = ts;
		this.tableNo = tn;
		this.discount = d;
		this.totalPrice = 0;
		this.alacarte = new ArrayList<MenuItem>();
		this.promo = new ArrayList<Promotion>();
		this.alacarteqty = new ArrayList<Integer>();
		this.promoqty = new ArrayList<Integer>();
	}

	/*
	// CREATES A NEW ORDER WITH INPUTS. CALLS Order() CONSTRUCTOR AND addItems()
	public Order createOrder(ArrayList<Staff> staffList, Menu menu) {
		Staff staffToAdd;
		String timestampToAdd;
		int tablenoToAdd;
		float discountToAdd;
		System.out.println("Creating New Order");
		System.out.println("Enter name of staff");
		String staffname = sc.next();
		Iterator iter = staffList.iterator();
		while (iter.hasNext()) {
			Staff cur = (Staff) iter.next(); 				// cast to staff
			if (staffname == cur.getName()) {
				staffToAdd = cur;
				break;
			}
		}
		System.out.println("Enter time stamp");
		timestampToAdd = sc.next();
		System.out.println("Enter table number");
		tablenoToAdd = sc.nextInt();
		System.out.println("Enter discount");
		discountToAdd = sc.nextFloat();
		Order neworder = new Order(staffToAdd, timestampToAdd, tablenoToAdd, discountToAdd);
		neworder.addItems(menu);
		return neworder;
	}
	 */

	/**
	 * Creates a new instance of order
	 * User will be asked to input table number and discount if any.
	 * Then calls addItems() which asks user to order items.
	 * @param menu, a list of all menuitems available 
	 */

	public void createOrder(Menu menu) {
		//String timestampToAdd;
		int tablenoToAdd;
		float discountToAdd;

		//System.out.println("Enter time stamp");
		//timestampToAdd = sc.next();
		//this.setTimeStamp(timestampToAdd);
		System.out.println("Enter table number");
		tablenoToAdd = sc.nextInt();
		this.setTableNo(tablenoToAdd);
		System.out.println("Enter discount");
		discountToAdd = sc.nextFloat();
		this.setDiscount(discountToAdd);

		this.addItems(menu);
	}

	/**
	 * Asks user to update thier current
	 * Prints out the full menu and user will be asked to add or remove items
	 * Calls either addItems() or addItems().
	 * @param menu of all items available
	 */
	public void updateOrder(Menu menu) {
		printOrderItems();
		int updatechoice;
		do{
			System.out.println("1: Add items");
			System.out.println("2: Remove items");
			System.out.println("3: Finish Updating");
			updatechoice = sc.nextInt();
			switch(updatechoice) {
			case 1:
				addItems(menu);
				break;
			case 2:
				removeItems();
				break;
			case 3: 
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(updatechoice < 3);
	}

	// ------------------------------------------------------------ //

	// UPDATES THE CURRENT ORDER. IN CASE LOOP. 
	// CALLS addItems() AND removeItems()
	/*
	public void updateOrder(ArrayList<Staff> staffList, Menu menu) {
		printOrderItems();
		int updatechoice;
		do{
			System.out.println("1: Add items");
			System.out.println("2: Remove items");
			System.out.println("3: Finish Updating");
			updatechoice = sc.nextInt();
			switch(updatechoice) {
			case 1:
				addItems(menu);
				break;
			case 2:
				removeItems();
				break;
			case 3: 
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(updatechoice < 3);
	}
	 */

	/**
	 * Asks user to add items to their order
	 * User will be asked to add alacarte items or promotion.
	 * Calls either addPromoItems() or addAlacarteItems().
	 */
	public void addItems(Menu m) {
		int addchoice;
		m.PrintMenu();
		System.out.println("Select which menu to Order");
		do{
			System.out.println("1: Menu Items");
			System.out.println("2: Promotion Items");
			System.out.println("3: Finish Ordering");

			addchoice = sc.nextInt();
			switch(addchoice) {
			case 1: 
				addAlacarteItems(m);
				break;

			case 2: 
				addPromoItems(m);
				break;

			case 3:
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(addchoice < 3);
	}

	/**
	 * Asks user to remove items from their current order
	 * User will be asked to remove alacarte items or promotion.
	 * Calls either removePromoItems() or removeAlacarteItems().
	 */
	public void removeItems() {
		int removechoice;
		System.out.println("Select Item to remove");
		do{
			System.out.println("1: Menu Items");
			System.out.println("2: Promotion Items");
			System.out.println("3: Finish Removing");

			removechoice = sc.nextInt();
			switch(removechoice) {
			case 1:
				removeAlacarteItems();
				break;
			case 2:
				removePromoItems();
				break;
			case 3:
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(removechoice < 3);
	}

	/**
	 * Asks user to add new alacarte item(s) to order
	 * Menu for alacarte items will be printed and user will be asked to input which item and quantity of item they want to add.
	 * Calls the addAlacarte() function to add each item to order
	 * User will be in a loop that allows them to keep adding unitl they input -1 to stop.
	 */
	public void addAlacarteItems(Menu m) {
		int additemnumber;
		Integer additemqty;
		m.printItemsMenu();
		System.out.println("Enter menu item to add or type -1 to stop ordering");
		additemnumber = sc.nextInt();
		if(additemnumber == -1) {return;}
		do{
			MenuItem itemToAdd = m.getMenuItem(additemnumber-1);
			System.out.println("Enter quantity of Menu Item");
			additemqty = sc.nextInt();
			System.out.println(additemqty);
			addAlacarte(itemToAdd,additemqty);
			System.out.println("Added "+additemqty+" "+itemToAdd.getItemName()+" to your alacarte order");
			System.out.println("Enter menu item to add or type -1 to stop ordering");
			additemnumber = sc.nextInt();
		} while(additemnumber != -1);
	}

	/**
	 * Asks user to remove alacarte item(s) that has been ordered
	 * User will be asked to input which alacarte item and quantity of alacarte item they want to remove.
	 * Calls the removeAlaCarte() function to remove each item from order
	 * User will be in a loop that allows them to keep removing unitl they input -1 to stop.
	 */
	public void removeAlacarteItems() {
		int removeitemnumber;
		Integer removeitemqty;
		System.out.println("Enter menu item to remove or type -1 to stop removing");
		removeitemnumber = sc.nextInt();
		if(removeitemnumber == -1) {return;}
		do{
			MenuItem itemToRemove = alacarte.get(removeitemnumber - 1);
			System.out.println("Enter quanitity of menu item to remove");
			removeitemqty = sc.nextInt();
			removeAlacarte(itemToRemove, removeitemqty);
			System.out.println("Removed "+removeitemqty+" "+itemToRemove.getItemName()+" from your alacarte order");
			System.out.println("Enter menu item to remove or type -1 to stop removing");
			removeitemnumber = sc.nextInt();
		} while(removeitemnumber != -1);
	}

	/**
	 * Asks user to add new promotion(s) to order
	 * Menu for promotions will be printed and user will be asked to input which promotion and quantity of promotion they want to add.
	 * Calls the addPromo() function to add each item to order
	 * User will be in a loop that allows them to keep adding unitl they input -1 to stop.
	 */
	public void addPromoItems(Menu m) {
		int addpromonumber;
		Integer addpromoqty;
		m.printPromotionMenu();
		System.out.println("Enter promotion set to add or type -1 to stop adding");
		addpromonumber = sc.nextInt();
		do {
			Promotion promoToAdd = m.getPromotion(addpromonumber - 1);
			System.out.println("Enter quantity of promotions to add");
			addpromoqty = sc.nextInt();
			addPromo(promoToAdd, addpromoqty);
			System.out.println("Added "+addpromoqty+" "+promoToAdd.getPromoName()+" to your promotions order");;
			System.out.println("Enter promotion set to add or type -1 to srop adding");
			addpromonumber = sc.nextInt();
		} while (addpromonumber != -1);
	}

	/**
	 * Asks user to remove promotion(s) that has been ordered
	 * User will be asked to input which promotion and quantity of promotion they want to remove.
	 * Calls the removePromo() function to remove each item from order
	 * User will be in a loop that allows them to keep removing unitl they input -1 to stop.
	 */
	public void removePromoItems() {
		int removepromonumber;
		Integer removepromoqty;
		System.out.println("Enter promo item to remove or type -1 to stop removing");
		removepromonumber = sc.nextInt();
		if(removepromonumber == -1) {return;}
		do{
			Promotion promoToRemove = promo.get(removepromonumber - 1);
			System.out.println("Enter quanitity of promotions to remove");
			removepromoqty = sc.nextInt();
			removePromo(promoToRemove, removepromoqty);
			System.out.println("Removed "+removepromoqty+" "+promoToRemove.getPromoName()+" from your promotions order");
			System.out.println("Enter promotion set to remove or type -1 to stop removing");
			removepromonumber = sc.nextInt();
		} while(removepromonumber != -1);
	}

	/**
	 * Adds a alacarte item to this order
	 * If alacarte item is already ordered, just add the quantity ordered to alacarteqty ArrayList
	 * Else, add the new alacarte item to alacarte ArrayList as a new element and also add the quantity to alacarteqty ArrayList as a new element
	 * Then total price of alacarte items added will be added to TotalPrice of this order
	 * @param name of alacarte item to be added
	 * @param quantity of alacarte item to be added
	 */
	public void addAlacarte(MenuItem dish, Integer qty) {
		int index = alacarte.indexOf(dish);
		if(index < 0) {
			alacarte.add(dish);
			alacarteqty.add(qty);
		}
		else {
			Integer cur = alacarteqty.get(index);
			alacarteqty.set(index, cur + qty);
		}
		totalPrice += (dish.getItemPrice() * qty);
	}

	/**
	 * Removes an alacarte item that has been ordered
	 * This will first subtract the quantity to be removed from the alacarteqty ArrayList
	 * If resulting quanitity is 0, then the alacarte item itself will be removed from alacarte ArrayList
	 * Then total price of alacarte items removed will be subtracted from TotalPrice of this order
	 * @param name of alacarte item to be removed
	 * @param quantity of alacarte item to be removed
	 */
	public void removeAlacarte(MenuItem dish, Integer qty) {
		System.out.println("Enter");
		int index = alacarte.indexOf(dish);
		Integer cur = alacarteqty.get(index);
		alacarteqty.set(index, cur - qty);
		if(alacarteqty.get(index) == 0) {
			alacarte.remove(index);
			alacarteqty.remove(index);
		}
		totalPrice -= (dish.getItemPrice() * qty);
	}


	/**
	 * Adds a promotion to this order
	 * If promotion is already ordered, just add the quantity ordered to promoqty ArrayList
	 * Else, add the new promotion to promo ArrayList as a new element and also add the quantity to promoqty ArrayList as a new element
	 * Then total price of promotions added will be added to TotalPrice of this order
	 * @param name of promotion to be added
	 * @param quantity of promotion to be added
	 */
	public void addPromo(Promotion promotion, Integer qty) {
		int index = promo.indexOf(promotion);
		if(index < 0) {
			promo.add(promotion);
			promoqty.add(qty);
		}
		else {
			Integer cur = promoqty.get(index);
			promoqty.set(index, cur + qty);
		}
		totalPrice += (promotion.getOriginalPrice() * qty);
	}

	/**
	 * Removes a promotion that has been ordered
	 * This will first subtract the quantity to be removed from the promoqty ArrayList
	 * If resulting quanitity is 0, then the promotion itself will be removed from promo ArrayList
	 * Then total price of promotions removed will be subtracted from TotalPrice of this order
	 * @param name of promotion to be removed
	 * @param quantity of promotion to be removed
	 */
	public void removePromo(Promotion promotion, Integer qty) {
		int index = promo.indexOf(promotion);
		Integer cur = promoqty.get(index);
		promoqty.set(index, cur - qty);
		if(promoqty.get(index) == 0) {
			promo.remove(index);
			promoqty.remove(index);
		}
		totalPrice -= (promotion.getOriginalPrice() * qty);
	}

	/**
	 * Sets the name of the staff assigned to this order
	 * @param name of staff to add
	 */
	public void setStaffName(Staff s) {
		this.staffName = s;
	}

	/**
	 * Gets the name of the staff that is assigned to the order
	 * @return staff name
	 */
	public Staff getStaffName() {
		return staffName;
	}

	/**
	 * Sets the time stamp of this order
	 * @param string of time stamp
	 */
	public void setTimeStamp(LocalTime ts) {
		this.timeStamp = ts;
	}

	/**
	 * Gets the time stamp of the order
	 * @return time stamp of order
	 */
	public LocalTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the table number of this order
	 * @param int of table number
	 */
	public void setTableNo(int i) {
		this.tableNo = i;
	}	

	/**
	 * Gets the table number of the order
	 * @return table number of order
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * Sets the discount applied to this order
	 * @param float of discount
	 */
	public void setDiscount(float d) {
		this.discount = d;
	}

	/**
	 * Gets the discount of order if customer is a member
	 * @return discount of order
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * Sets the taxes applied to this order ie. GST and/or service charge
	 * @param float of tax
	 */
	public void setTax(float tax) {
		this.tax = tax;	
	}	

	/**
	 * Gets the tax applied to the order ie. GST and/or serivice charge
	 * @return tax applied
	 */
	public float getTax() {
		return tax;	
	}

	/**
	 * Gets the total price of the order
	 * @return total price of order
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Gets the total price of the order after discount is applied
	 * @return total price after discount
	 */
	public double getTotalPriceAfterCalculation() {
		return ((getTotalPrice()) * ((100 - getDiscount()) / 100 ));
	}

	/**
	 * Gets the total price of the order after tax is applied
	 * @return total price after taxes
	 */
	public double getTotalPriceAfterTax() {
		return ((getTotalPriceAfterCalculation()) * ( (100 + getTax()) / 100 ));
	}

	/*
	 * Prints out the full list of items that have been ordered 
	 */
	public void printOrderItems() {
		System.out.println("======== ORDERED ALACARTE ITEMS ==============");
		for(int i = 0; i<alacarte.size(); i++ ) {
			System.out.println(i+1+". "+alacarte.get(i).getItemName()+" x"+alacarteqty.get(i));
		}
		System.out.println("======== ORDERED PROMO ITEMS ==============");
		for(int i = 0; i<promo.size(); i++ ) {
			System.out.println(i+1+". "+promo.get(i).getPromoName()+" x"+promoqty.get(i));
		}
	}
	
	/*
	 * Prints out the full list of items that have been ordered without the print statements
	 */
	public void printOrderItemsWoList() {
		for(int i = 0; i<alacarte.size(); i++ ) {
			System.out.println(alacarte.get(i).getItemName()+" x"+alacarteqty.get(i));
		}
		for(int i = 0; i<promo.size(); i++ ) {
			System.out.println(promo.get(i).getPromoName()+" x"+promoqty.get(i));
		}
	}

	/*
	 * Prints out all the items ordered, which includes the staff assigned, table number, time stamp, discount applied and total price of order
	 */
	public void printOrder() {
		System.out.println("============ ORDER DETAILS ============");
		System.out.println("StaffName: "+getStaffName().getName()+"\nTimeStamp: "+getTimeStamp()+"\nTableNo: "+getTableNo()+"\nDiscount: "+getDiscount());
		printOrderItems();
		System.out.println("TotalPrice: "+getTotalPrice());
	}

	/**
	 * Prints out the invoice of this order, whcih includes table number, all items ordered and total price of order after discount and taxes are applied.
	 */
	public void printInvoice() {
		System.out.println("==================");
		System.out.println("INVOICE");
		System.out.println("Table Number: " + getTableNo());
		System.out.println("");
		printOrderItems();
		System.out.println("");
		System.out.println("Discount Applied: " + getDiscount());
		System.out.println("Total Price: " + (getTotalPriceAfterCalculation()));
		System.out.println("Total Price w GST: " + (getTotalPriceAfterTax()));
		System.out.println("===================");
	}


}