package CZ2002_Assignment;

import java.util.*;
/**
 *Represent a Promotion Set made up of MenuItem Stored in an ArrayList.
 *
 * 
 * @version 1.0
 * @since 2021-11-08
 *
 */

public class Promotion {
	/**
	 * Store MenuItem in an ArrayList refer as promotionSet.
	 */
	private ArrayList<MenuItem> promotionSet;
	/**
	 * The name of this instance of Promotion.
	 */
	private String promoName;
	/**
	 * The price of this instance of Promotion.
	 */
	private double price;
	
	/**
	 * Create a new Promotion.
	 * 
	 */
	public Promotion() {
		promotionSet = new ArrayList<>();
	}
	/**
	 * 
	 * @param mItem MenuItem to be added to PromotionSet ArrayList.
	 */
	public void addMenuItem(MenuItem mItem) {
		promotionSet.add(mItem);
	}
	
	/**
	 * Display each menuItem in the Promotion internal ArrayList.
	 * menuItem name,price and description are also display.
	 */
	public void displayPromotionItems() {
		int index =  0;
		
		for(int i = 0; i< promotionSet.size();i++) {
			System.out.print("item " + (i + 1) + ": ");
			System.out.println(promotionSet.get(i).getItemName());
			System.out.println(promotionSet.get(i).getItemPrice());
			System.out.println(promotionSet.get(i).getItemDescription());
		}
	}
	
	/**
	 * Set this Promotion's name.
	 * @param name, this Promotion's name to be.
	 */
	public void setPromoName(String name) {
		this.promoName = name;
	}
	
	/**
	 *  Set this Promotion's price.
	 * @param price, this Promotion's price to be.
	 */
	public void setPromoPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Gets the name of this Promotion.
	 * @return this Promotion's promoName.
	 */
	public String getPromoName() {
		return this.promoName;
	}
	/**
	 * Gets the price of this Promotion.
	 * @return this Promotion's price.
	 */
	public double getPromoPrice() {
		return this.price;
	}
	
	/**
	 * Gets the ArrayList of MenuItem in this Promotion's promoSet.
	 * @return this Promotion's promoSet, ArrayList of MenuItem
	 */
	public ArrayList<MenuItem> getPromoList(){
		return this.promotionSet;
	}
	
	/**
	 * Gets the specific MenuItem in this Promotion's promoSet ArrayList.
	 * @param i index of MenuItem in Promotion;s promoSet ArrayList
	 * @return MenuItem 
	 */
	public MenuItem getMenuItem(int i){
		return promotionSet.get(i);
	}
	
	/**
	 * Gets the sum of each MenuItem original price in Promotion's promoSet ArrayList 
	 * 
	 * @return Sum of all MenuItem original price in Promotion's promoSet ArrayList 
	 */
	public double getOriginalPrice() {
		double sum = 0;
		for(MenuItem mItem : promotionSet) {
			sum = sum + mItem.getItemPrice();
		}
		return sum;
	}
	
}
