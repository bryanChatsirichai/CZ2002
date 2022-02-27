package CZ2002_Assignment;

import java.util.ArrayList;

// List of predefined menu items
/**
 * Base menu, a defined list of promotions and menu items to use
*/
public class BaseMenu {
	private ArrayList<MenuItem> baseItemList = new ArrayList<MenuItem> ();
	private ArrayList<Promotion> basePromoList = new ArrayList<Promotion> ();
	
	MenuItem m1 = new MenuItem("Hand Tossed Salad", "strawberries, dried cranberries, raisins, pineapple" , 5.00, MenuCategory.SIDES);
	MenuItem m2 = new MenuItem("Caesar Salad", "parmesan cheese, lemon juice" , 8.00, MenuCategory.SIDES);
	MenuItem m3 = new MenuItem("Fresh Juice", "orange / apple / watermelon" , 2.00, MenuCategory.DRINKS);
	MenuItem m4 = new MenuItem("Roast Beef with Mushroom Sauce", "Beef + Mushroom" , 17.00, MenuCategory.MAINS);

	/**
	 * Creates the base for menu items
	 * @return return the list created
	*/
	public ArrayList<MenuItem> CreateBaseItemList() {
		baseItemList.add(m1);
		baseItemList.add(m2);
		baseItemList.add(m3);
		baseItemList.add(m4);
		
		return baseItemList;
	}
	
	/**
	 * Creates the base for promotion items
	 * @return return the list created
	*/
	public ArrayList<Promotion> CreateBasePromoList() {
		
		return basePromoList;
	}
	
	/**
	 * Getter for base menu list
	 * @return return the list reference of menu items
	*/
	public ArrayList<MenuItem> getBaseItemList() {
		
		return baseItemList;
	}
	
	/**
	 * Getter for base promotion list
	 * @return return the list reference of promotion
	*/
	public ArrayList<Promotion> getBasePromoList() {
		
		return basePromoList;
	}
	
	/**
	 * Getter for size of base menu list
	 * @return return the size of menu item list
	*/
	public int baseMenuSize() {
		return baseItemList.size();
	}
	
	
	/**
	 * Getter for base promo list
	 * @return size of base promo list
	*/
	public int basePromoSize() {
		return basePromoList.size();
	}
}
