package Server.Model;

import java.util.ArrayList;

/**
 * Class that stores the inventory of all the items of a tool shop
 */
public class Inventory {
	/**
	 * List of tools
	 */
	private ArrayList <Item> itemList;
	/**
	 * Order of new items
	 */
	private Order myOrder;

	/**
	 * Constructs an object of type Inventory
	 * @param itemList the list of tools
	 */
	public Inventory (ArrayList <Item> itemList) {
		this.itemList = itemList;
		myOrder = new Order ();
	}

	/**
	 * Manages an item
	 * @param name tool being managed
	 * @return tool
	 */
	public Item manageItem (String name){
		Item theItem = decreaseItem (name);
		
		if (theItem != null){
			placeOrder (theItem);
		}
		return theItem;
	}

	/**
	 * Places order for an item
	 * @param theItem item being ordered
	 */
	public void placeOrder (Item theItem){
		OrderLine ol = theItem.placeOrder();
		if (ol !=null){
			myOrder.addOrderLine(ol);
		}
	}

	/**
	 * Decreases the item specified
	 * @param name tool being decreased
	 * @return item info
	 */
	private Item decreaseItem (String name) {
		
		Item theItem = searchForItem (name);
		
		if (theItem == null)
			return null;
		
		if (theItem.decreaseItemQuantity()){
			
			return theItem;
		}
		return null;
	}

	/**
	 * Returns item quantity
	 * @param name tool being checked
	 * @return tool quantity
	 */
	public int getItemQuantity (String name){
		Item theItem = searchForItem (name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}

	/**
	 * Searches for a certain item
	 * @param name tool being searched
	 * @return tool info
	 */
	public Item searchForItem (String name) {
		for (Item i: itemList) {
			if (i.getItemName().toLowerCase().equals(name.toLowerCase()))
				return i;
		}
		return null;
	}

	/**
	 * Creates a string of inventory
	 * @return String of inventory
	 */
	public String toString () {
		String str = "";
		for (Item i: itemList) {
			str += i;
		}
		return str;
	}

	/**
	 * Searches for an item by ID
	 * @param id tool ID being searched
	 * @return tool info
	 */
	public Item searchForItem(int id) {
		for (Item i: itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}

	/**
	 * Prints the order
	 * @return order string
	 */
	public String printOrder() {
		return myOrder.toString();
	}
}
