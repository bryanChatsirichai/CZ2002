
/**
* Package for project
*/
package CZ2002_Assignment;

/**
* Class for definition of Menu Items for Menu
*/
public class MenuItem {

	private String name;
	private MenuCategory menuCategory;
	private String description;
	private double price;
	
	/**
	   * Constructor for class Menu Item
	   * @param itemName the name for the item
	   * @param itemDescription description need for the item
	   * @param itemPrice price of generate item
	   * @param menuCat category of item
	*/
	public MenuItem(String itemName, String itemDescription, double itemPrice, MenuCategory menuCat) {
		name = itemName;
		description = itemDescription;
		price = itemPrice;
		menuCategory = menuCat;
	}
	
	/**
	   * Getter for price
	   * @return price
	*/
	public double getItemPrice() {
		return price;
	}
	
	/**
	* Getter method for description
	* @return description
	*/
	public String getItemDescription() {
		return description;
	}
	
	/**
	* Getter method for name of item
	* @return name
	*/
	public String getItemName() {
		return name;
	}
	
	/**
	* Getter method for category of item
	* @return menuCategory
	*/
	public MenuCategory getMenuCategory() {
		return menuCategory;
	}
	
	/**
	* Setter method for price of item
	* @param nPrice new price to set to
	* @param print if the statement should be printed
	*/	
	public void setItemPrice(double nPrice, boolean print) {
		if(print)
			System.out.println("Previous price: $" + price + " -> changed to: $" + nPrice);
		price = nPrice;
	}
	
	/**
	* Setter method for description of item
	* @param nDescription newer description to be used
	* @param print if the statement should be printed
	*/
	public void setItemDescription(String nDescription, boolean print) {
		if(print)
			System.out.println("Previous description: " + description + "  -> changed to " + nDescription);
		description = nDescription;
	}
	
	/**
	* Setter method for name of item
	* @param nName newer name to be used
	* @param print if the statement should be printed
	*/
	public void setItemName(String nName, boolean print) {
		if(print)
			System.out.println("Previous name: " + name + "  -> changed to " + nName);
		name = nName;
	}
	
	/**
	* Setter method for category of item
	* @param category newer category to be used
	* @param print if the statement should be printed
	*/
	public void setMenuCategory(MenuCategory category, boolean print) {
		if(print)
			System.out.println("Previous name: " + menuCategory + "  -> changed to " + category);
		menuCategory = category;
	}
}