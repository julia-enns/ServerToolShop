package Server.Model;

import java.util.ArrayList;

/**
 * Makes a tool shop with an inventory.
 */
public class Shop {
	/**
	 * The shops inventory
	 */
	private Inventory theInventory;
	/**
	 * List of suppliers
	 */
	private ArrayList <Supplier> supplierList;

	/**
	 * Constructs an object of type Shop
	 * @param inventory the shops inventory
	 * @param suppliers list of suppliers
	 */
	public Shop (Inventory inventory, ArrayList <Supplier> suppliers) {
		theInventory = inventory;
		supplierList = suppliers;
	}

	/**
	 * Lists items
	 * @return String of items
	 */
	public String listAllItems() {
		return theInventory.toString();
	}

	/**
	 * Decreases an item
	 * @param name item being decreased
	 * @return String of item quantity
	 */
	public String decreaseItem (String name) {
		if (theInventory.manageItem(name) == null)
			return "Couldn't decrease item quantity!\n";
		else
			return name + " quantity was decreased!\n";
	}

	/**
	 * Decreases an item
	 * @param name item being decreased
	 * @return String of item quantity
	 */
	public String buyItem (String name, int number) {
		Item item;
		if ((item =theInventory.manageBoughtItem(name, number)) == null)
			return "Couldn't buy item!\n";
		else {
			String output = String.format(" has been bought!\n$%.2f is the total of your purchase.\n", (item.getItemPrice() * number));
			return name + output;
		}
	}

	/**
	 * Returns item
	 * @param name name of the item
	 * @return String of item
	 */
	public String getItem(String name) {

		Item theItem = theInventory.searchForItem(name);
		if (theItem == null)
		     return "Item " + name + " could not be found!";
		else
			 return outputItem (theItem);
			
	}

	/**
	 * Returns item
	 * @param id id of the item
	 * @return item information
	 */
	public String getItem(int id) {

		Item theItem = theInventory.searchForItem(id);
		if (theItem == null)
		     return "Item number " + id + " could not be found!";
		else
			return outputItem (theItem);
			 
		
	}

	/**
	 * Outputs item information
	 * @param theItem item being outputted
	 * @return item information
	 */
	private String outputItem (Item theItem){
		return "The item information is as follows: \n" + theItem;
	}

	/**
	 * Returns item quantity
	 * @param name item
	 * @return item quantity
	 */
	public String getItemQuantity(String name) {

		int quantity = theInventory.getItemQuantity(name);
		if (quantity < 0)
		    return "Item " + name + " could not be found!";
		else
			return "The quantity of " + name + " is: " + quantity + "\n";
	}

	/**
	 * String of order being printed
	 * @return order
	 */
	public String printOrder() {
		return theInventory.printOrder();
	}
}
