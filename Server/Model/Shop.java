package Server.Model;

import java.util.ArrayList;

public class Shop {
	
	private Inventory theInventory;
	private ArrayList <Supplier> supplierList;
	
	public Shop (Inventory inventory, ArrayList <Supplier> suppliers) {
		theInventory = inventory;
		supplierList = suppliers;
	}

	public String listAllItems() {
		return theInventory.toString();
	}

	public String decreaseItem (String name) {
		if (theInventory.manageItem(name) == null)
			return "Couldn't not decrease item quantity!\n";
		else
			return name + " quantity was decreased!\n";
	}

	public String getItem(String name) {

		Item theItem = theInventory.searchForItem(name);
		if (theItem == null)
		     return "Item " + name + " could not be found!";
		else
			 return outputItem (theItem);
			
	}

	public String getItem(int id) {

		Item theItem = theInventory.searchForItem(id);
		if (theItem == null)
		     return "Item number " + id + " could not be found!";
		else
			return outputItem (theItem);
			 
		
	}
	
	private String outputItem (Item theItem){
		return "The item information is as follows: \n" + theItem;
	}

	public String getItemQuantity(String name) {

		int quantity = theInventory.getItemQuantity(name);
		if (quantity < 0)
		    return "Item " + name + " could not be found!";
		else
			return "The quantity of " + name + " is: " + quantity + "\n";
	}

	public String printOrder() {
		return theInventory.printOrder();
	}

	

}
