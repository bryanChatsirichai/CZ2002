package CZ2002_Assignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menu class for all menu related methods
 */
public class Menu {
	private ArrayList<MenuItem> itemList = new ArrayList<MenuItem> ();
	private ArrayList<Promotion> menuPromoList = new ArrayList<Promotion> ();

	/**
	 * Loads the base menu and concatenates the list of base menu items to current item list.
	 */
	public void LoadBaseMenu() {
		BaseMenu bm = new BaseMenu();
		itemList.addAll(bm.CreateBaseItemList());
		menuPromoList.addAll(bm.CreateBasePromoList());

		PrintMenu();
	}

	/**
	 * Serves as an interface to create items for both menu and promotion lists
	 */
	public void Create() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Create New Item");
		System.out.println("(2) Create New Promotion");
		InputChecker ic = new InputChecker();

		int choice = -1;
		choice = ic.intChecker(choice, sc, -1, 100000);

		if(choice == -1) {
			System.out.println("Exceeded number of tries, exiting");
			return;
		}

		if(choice == 1) {
			CreateNewMenuItem();
		}
		else {
			CreateNewPromotion();
		}
	}

	/**
	 * Key in number of menu items you want to add to the menu and adds them, values keyed must be case specific
	 */
	public void CreateNewMenuItem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many items you wish to add?");
		int numItems = sc.nextInt();
		InputChecker ic = new InputChecker();

		if(numItems <= 0) {
			System.out.println("Invalid size declaration. Exiting.......");
			return;
		}

		// Clears Buffer
		sc.nextLine();

		// Ask for input for each item to be added.
		for(int i =0; i < numItems; i++) {
			System.out.printf("Item %d\n",i + 1);

			// Read Name
			System.out.println("Name:");
			String itemName = sc.nextLine();

			// If a duplicate name was keyed to be added, ask for another name again
			if(CheckList(itemName)) {
				System.out.println("(1) Try Again");
				System.out.println("(2) Exit");
				int choice = sc.nextInt();
				sc.nextLine();
				if(choice == 1) {
					i -= 1;
					continue;
				}
				else {
					System.out.println("Exiting, sucessful previous items added will remain in the list");
					break;
				}
			}

			// Read Description
			System.out.println("Description:");
			String desp = sc.nextLine();
			// Read Price
			System.out.println("Price:");
			double price = 0;
			price = ic.doubleChecker(price, sc, 0.1, 10000);
			
			if(price == -1) {
				System.out.println("Exceeded number of tries, exiting");
				break;
			}

			// Clears buffer 
			sc.nextLine();

			// Read category 
			System.out.println("Category (MAINS/ SIDES/ DESSERTS/ DRINKS):");
			
			MenuCategory category = null;
			category = ic.categoryChecker(category, sc);

			if(category == null) {
				System.out.println("Exceeded number of tries, exiting");
				break;
			}

			// Creates menuItem to be added
			MenuItem m = new MenuItem(itemName,desp,price,category);
			AddToItemList(m, true);
		}
		//sc.close();
	}
	
	/**
	 * Interface to create new instance of a promotion object in the menu
	 */
	public void CreateNewPromotion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many Promotional Sets you wish to add?");
		int numOfSets = sc.nextInt();

		if(numOfSets <= 0) {
			System.out.println("Invalid size declaration. Exiting.......");
			return;
		}

		// Clears Buffer
		sc.nextLine();

		// Ask for input for each item to be added.
		for(int i =0; i < numOfSets; i++) {
			System.out.printf("New Promotion Set %d\n",i + 1);

			System.out.println("Promotion Name:");
			String promoName = sc.nextLine();
			// If a duplicate name was keyed to be added, ask for another name again
			if(checkPromoList(promoName)) {

				System.out.println("(1) Try Again");
				System.out.println("(2) Exit");

				int choice = sc.nextInt();
				sc.nextLine();
				if(choice == 1) {
					i-=1;
					continue;
				}
				else {
					System.out.println("Exiting, sucessful previous Promotions Set added will remain in the list");
					return;
				}
			}
			System.out.println("Size of new Promotional Set");
			int size = sc.nextInt();

			Promotion curPromoSet = new Promotion();


			addItemsToPromoSet(curPromoSet, size);

			//After finishing current promotional set add to promotion set menu
			curPromoSet.setPromoName(promoName);
			AddToMenuPromoList(curPromoSet, true);
			sc = new Scanner(System.in);//reset buffer

		}
	}

	/**
	 * Add items to a newly create promo set
	 * @param curPromoset current promo set for item to be added to
	 * @param size the number of items to be added to the set
	 */
	private void addItemsToPromoSet(Promotion curPromoSet,int size) {
		Scanner sc = new Scanner(System.in);
		InputChecker ic = new InputChecker();

		for(int j = 0;j<size;j++) {
			System.out.printf("Current Promotion Set item no. %d\n",j + 1);
			System.out.println("Add Menu Item(s) to Promotion Set or Unique Promotional Item(s)");
			System.out.println("(1) Add Selected Item(s) from menu to Promotion Set");
			System.out.println("(2) Unique Promotional Item(s)");
			int option = sc.nextInt();
			if(option == 1) {
				//display menu and add item to promotional Set
				printItemsMenu();
				System.out.println("Select Menu Item number to add to current Promotion Set");
				int itemNo = sc.nextInt();
				//0 base index recall
				MenuItem itemTobeAdded = itemList.get(itemNo -1);
				curPromoSet.addMenuItem(itemTobeAdded);
			}
			else if (option == 2) {
				System.out.println("Enter Unique Item");
				System.out.println("Name:");
				sc.nextLine();
				String itemName = sc.nextLine();

				// If item enter already in menu
				if(CheckList(itemName)) {

					System.out.println("Item already in menu, not unique please use option 1 for this item!!!");
					j = j - 1; //no count so can retry
					continue;
				}
				//unique item only for promotion not in menu
				// Read Description
				System.out.println("Description:");
				String desp = sc.nextLine();

				// Read Price
				System.out.println("Price:");
				//double price = sc.nextDouble();
				double price = -1;
				price = ic.doubleChecker(price, sc, 0.1, 10000);

				if(price == -1) {
					System.out.println("Exceeded number of tries, exiting");
					break;
				}
				// Clears buffer 
				sc.nextLine();

				// Read category 
				System.out.println("Category (MAINS/ SIDES/ DESSERTS/ DRINKS):");
				MenuCategory category = null;
				category = ic.categoryChecker(category, sc);

				if(category == null) {
					System.out.println("Exceeded number of tries, exiting");
					break;
				}
				// Creates menuItem to be added
				MenuItem itemTobeAdded = new MenuItem(itemName,desp,price,category);
				curPromoSet.addMenuItem(itemTobeAdded);
			}
		}
		//Set the current promotional set price 
		System.out.println("Current promotion set price base on original item price: $%.2f" + curPromoSet.getOriginalPrice());
		System.out.println("Set current promotion Set price: ");
		double curPromoPrice = sc.nextDouble();
		curPromoSet.setPromoPrice(curPromoPrice);

	}

	/**
	 * Checks through mennu item list to see if there are any duplicates of name == parameter in both Lists
	 * @param name reference name of item to search duplicate of
	 * @return returns wether a duplicate is found or not
	 */
	private boolean CheckList(String name) {
		for(int i = 0; i < itemList.size();i++) {
			String itemName = itemList.get(i).getItemName();
			if(itemName.compareToIgnoreCase(name) == 0) {
				System.out.println("Duplicate detected in List of Items. Please try again.");

				return true;
			}
		}
		return false;
	}

	/**
	 * Check promoList if have any duplicate promotional names
	 * @param name reference name of item to search duplicate of
	 * @return returns wether a duplicate is found or not
	 */
	private boolean checkPromoList(String name) {
		for(Promotion pSet : menuPromoList) {
			if(pSet.getPromoName().equals(name)) {
				System.out.println("Duplicate Promotion-Set in List of Promotinal Items. Please try again.");
				return true;
			}
		}

		return false;
	}


	/**
	 * Adds menu item to its list data structure
	 * @param m menuitem to be added to the list of menu items
	 * @param print if a notification should be printed after adding
	 */
	public void AddToItemList(MenuItem m, boolean print) {
		itemList.add(m);

		if(print) {
			int latestIndex = itemList.lastIndexOf(m);
			System.out.println("");
			System.out.println("=====================ADDED ITEM===========================");
			PrintFromMenuListItem(latestIndex);
			System.out.println("=========================================================");
			System.out.println("");
			PrintMenu();
		}
	}

	/**
	 * Adds promotion to its list data structure
	 * @param p object to be added to the list
	 * @param print if a notification should be printed after adding
	 */
	public void AddToMenuPromoList(Promotion p, boolean print) {
		menuPromoList.add(p);
		if(print) {
			int latestIndex = menuPromoList.lastIndexOf(p);
			System.out.println("");
			System.out.println("=====================ADDED PROMOTIONAL SET===========================");
			PrintFromPromoListItem(latestIndex);
			System.out.println("=========================================================");
			System.out.println("");
		}
	}

	/**
	 * Serves as an interface to update for both promotion and menu item
	 */
	public void Update() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Update a Menu Item");
		System.out.println("(2) Update a Promotion");

		int choice = sc.nextInt();

		if(choice == 1) {
			//PrintMenu();
			//display all menu excludind promotion
			printItemsMenu();
			System.out.println("Select Item Number to update:");
			int index = sc.nextInt();
			UpdateMenuItem(index - 1);
		}
		else {
			//display all promotion and their items
			printPromotionMenu();
			System.out.println("Select Promotion Number to update:");
			int index = sc.nextInt();
			UpdatePromotion(index -1);		
		}
	}

	/**
	 * Updates selected menu item of index
	 * @param index index of item to be updated
	 */
	public void UpdateMenuItem(int index) {	

		Scanner sc = new Scanner(System.in);

		MenuItem m = itemList.get(index);

		System.out.println("");
		System.out.println("================ SELECTED ITEM ===================");
		PrintFromMenuListItem(index);
		System.out.println("==================================================");

		System.out.println("(1)Change Name");
		System.out.println("(2)Change Price");
		System.out.println("(3)Change Description");
		System.out.println("(4)Change Category");

		int option = sc.nextInt();

		switch(option) {
		case 1: 
			sc.nextLine();
			System.out.println("New Name");
			String newName = sc.nextLine();
			m.setItemName(newName, false);
			break;
		case 2: 
			System.out.println("New Price");
			double newPrice = sc.nextDouble();
			m.setItemPrice(newPrice, false);
			break;
		case 3: 
			sc.nextLine();
			System.out.println("New Description");
			String newDescript = sc.nextLine();
			m.setItemDescription(newDescript, false);
			break;
		case 4: 
			sc.nextLine();
			System.out.println("New Category");
			MenuCategory newCate = MenuCategory.valueOf(sc.nextLine().toUpperCase());
			m.setMenuCategory(newCate, false);
			break;
		}
		System.out.println("");
		System.out.println("======================= UPDATED =========================");
		PrintFromMenuListItem(index);
		System.out.println("=========================================================");
		System.out.println("");
	}

	/**
	 * Updates selected promotion of index
	 * @param index index of promotion to be updated
	 */
	public void UpdatePromotion(int index) {	
		Scanner sc = new Scanner(System.in);
		Promotion p = menuPromoList.get(index);

		System.out.println("(1) Change Name");
		System.out.println("(2) Change Price");
		System.out.println("(3) Add Item");
		System.out.println("(4) Remove Item");
		int option = sc.nextInt();

		switch(option) {
		case 1: 
			sc.nextLine();
			System.out.println("New Name");
			String newName = sc.nextLine();
			p.setPromoName(newName);
			break;
		case 2: 
			System.out.println("New Price");
			double newPrice = sc.nextDouble();
			p.setPromoPrice(newPrice);
			break;
		case 3: 
			System.out.println("Number of items to add");
			int num = sc.nextInt();
			addItemsToPromoSet(p, num);
			break;
		case 4:
			p.displayPromotionItems();
			System.out.println("Select which item to be remove from current promotion set");
			int itemIndex = sc.nextInt();
			p.getPromoList().remove(itemIndex -1);
			//Set the current promotional set price 
			System.out.println("Current promotion set price base on original item price: $%.2f" + p.getOriginalPrice());
			System.out.println("Set current promotion Set price: ");
			double curPromoPrice = sc.nextDouble();
			p.setPromoPrice(curPromoPrice);
			break;
		}
		System.out.println("");
		System.out.println("=====================UPDATED PROMO========================");
		PrintFromPromoListItem(index);
		System.out.println();
		System.out.println("=========================================================");
		System.out.println("");
	}

	/**
	 * Interface for removing from any of the promotion and menu items list
	 */
	public void Remove() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Remove a Menu Item");
		System.out.println("(2) Remove a Promotion");

		int choice = sc.nextInt();

		if(choice == 1) {
			PrintMenu();
			System.out.println("Item Number:");
			int index = sc.nextInt();
			RemoveFromItemList(index - 1);
			PrintMenu();
		}
		else {
			printPromotionMenu();
			System.out.println("Promotion Number:");
			int index = sc.nextInt();
			RemoveFromPromoList(index - 1);
		}
	}

	/**
	 * Removes selected item of index from menu item list
	 * @param index index of menu item to be removed
	 */
	public void RemoveFromItemList(int index) {
		MenuItem m = itemList.get(index);
		itemList.remove(index);

		System.out.println("");
		System.out.println("======================= REMOVED ITEM =========================");
		PrintItem(m);
		System.out.println("=========================================================");
		System.out.println("");
	}

	/**
	 * Remove entire promotion Set from list
	 * @param index index of promotion to be removed
	 */
	public void RemoveFromPromoList(int index) {
		Promotion p = menuPromoList.get(index);
		menuPromoList.remove(index);
		System.out.println("");
		System.out.println("======================= REMOVED PROMOTION SET =========================");
		PrintPromotion(p);
		System.out.println("=========================================================");
		System.out.println("");
	}


	/**
	 * Prints entire current itemList and promotion set
	 */
	public void PrintMenu() {
		System.out.println("");
		System.out.println("==================== MENU ========================");

		System.out.println("");
		System.out.println("==================== ITEMS ========================");
		for(int i =0; i < itemList.size();i++) {
			PrintFromMenuListItem(i);
		}

		System.out.println("");
		System.out.println("==================== PROMOTIONS ========================");

		printPromotionMenu();

		System.out.println("");
		System.out.println("=========================================================");		
		System.out.println("");
	}


	/**
	 * Prints only the selected index from item list
	 * @param index index of item from list to be printed
	 */
	public void PrintFromMenuListItem(int index) {
		System.out.println("Item: " + (index+1));
		PrintItem(itemList.get(index));
	}

	/**
	 * Prints only the item portion of the menu
	 */
	public void printItemsMenu() {
		System.out.println("");
		System.out.println("==================== MENU ========================");

		System.out.println("");
		System.out.println("==================== ITEMS ========================");
		for(int i =0; i < itemList.size();i++) {
			PrintFromMenuListItem(i);
		}

		System.out.println("");
	}


	/**
	 * Prints only the promotion portion of the menu
	 */	
	public void printPromotionMenu() {
		System.out.println("");
		System.out.println("==================== PROMOTION MENU ========================");

		System.out.println("");

		//System.out.println("");
		System.out.println("==================== PROMOTIONS SETS ========================");

		// Prints all promotion items in MENU
		for(int i =0; i < menuPromoList.size();i++) {
			PrintFromPromoListItem(i);
		}

		System.out.println("");
		System.out.println("=========================================================");		
		System.out.println("");
	}

	/**
	 * Prints selected index from promotion list
	 * @param index to be printed
	 */
	public void PrintFromPromoListItem(int index) {
		System.out.println("");
		System.out.println("Promotion: " + (index+1));
		PrintPromotion(menuPromoList.get(index));
		System.out.println("");
	}

	/**
	 * Prints Menu Item in stated format
	 * @param m object to be printed
	 */
	public void PrintItem(MenuItem m) {
		System.out.println("");
		System.out.println("Name: " + m.getItemName());
		System.out.printf("Price: $%.2f\n" ,m.getItemPrice());
		System.out.println("Description: " + m.getItemDescription());
		System.out.println("Category: " + m.getMenuCategory());
		System.out.println("");
	}

	/**
	 * Prints the specific promotion 
	 * @param p object to be printed
	 */
	public void PrintPromotion(Promotion p) {
		System.out.println("");
		System.out.println("Promotion set: " +p.getPromoName());
		System.out.println("Promotion set price: $%.2f" + p.getPromoPrice());
		System.out.println("Ala cart price");
		p.displayPromotionItems();
		System.out.println("");
	}

	/**
	 * Prints current size of itemList & promoList
	 */
	public void PrintSize() {
		System.out.println("---------------------------------------------------");
		System.out.println("ITEM SIZE = " + getMainMenuSize());
		System.out.println("PROMOTION SIZE = " + getPromotionMenuSize());
		System.out.println("---------------------------------------------------");
	}


	/**
	 * Gets the size of the menu list
	 * @return size of menu list
	 */
	public int getMainMenuSize() {
		return itemList.size();
	}

	/**
	 * Gets the size of the promotion list
	 * @return size of promotion list
	 */
	public int getPromotionMenuSize() {
		return menuPromoList.size();
	}


	/**
	 * Gets the list reference of the menu item list
	 * @return menu item list
	 */
	public ArrayList<MenuItem> getItemList() {
		return itemList;
	}


	/**
	 * Get specific menu item from list
	 * @param index index reference
	 * @return menu item to be returned
	 */
	public MenuItem getMenuItem(int index) {
		//get item from array,array 0 base index
		//item display from 1 onwards but start from 0 base index
		return itemList.get(index);
	}

	/**
	 * Gets the list reference of the promotion list
	 * @return promotion list
	 */
	public ArrayList<Promotion> getMenuPromoList() {
		return menuPromoList;
	}

	/**
	 * Get specific promotion from list
	 * @param index index reference
	 * @return promotion to be returned
	 */
	public Promotion getPromotion(int index) {  
		//index 0 is first promotion 
		//but on display 1)promo 1 is index 0  
		//so typing 1 will give promotion 1 at index 0 
		return menuPromoList.get(index); 
	}
}