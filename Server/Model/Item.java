package Server.Model;

/**
 * Information about a tool
 */
public class Item {
	/**
	 * Tool ID
	 */
	private int itemId;
	/**
	 * Tool Name
	 */
	private String itemName;
	/**
	 * Tool quantity
	 */
	private int itemQuantity;
	/**
	 * Tool price
	 */
	private double itemPrice;
	/**
	 * Checks if tool has already been ordered
	 */
	private boolean alreadyOrdered;
	/**
	 * Tool supplier
	 */
	private Supplier theSupplier;
	/**
	 * @value quantity being ordered
	 */
	private static final int ORDERQUANTITY = 40;
	/**
	 * @value minimum number before item ordered
	 */
	private static final int MINIMUMUMBER = 20;

	/**
	 * Constructs an object of type Item
	 * @param id tool ID
	 * @param name tool name
	 * @param quantity tool quantity
	 * @param price tool price
	 * @param sup tool supplier
	 */
	public Item (int id, String name, int quantity, double price, Supplier sup) {
		itemId = id;
		itemName = name;
		itemQuantity = quantity;
		itemPrice = price;
		theSupplier = sup;
		setAlreadyOrdered(false);
	}

	/**
	 * Decreases item quantity
	 * @return true if item was decreases
	 */
	public boolean decreaseItemQuantity () {
		if (itemQuantity > 0) {
			itemQuantity--;
		    return true;	
		}
		else
			return false;
			
	}

	/**
	 * Places an order for an item
	 * @return order line made for item
	 */
	public OrderLine placeOrder (){
		OrderLine ol;
		if (getItemQuantity() < MINIMUMUMBER && !alreadyOrdered){
			ol = new OrderLine (this, ORDERQUANTITY);
			alreadyOrdered = true;
			return ol;
		}
	    return null;
	}

	/**
	 * Returns item ID
	 * @return item ID
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Returns item name
	 * @return item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Returns item quantity
	 * @return item quantity
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * Makes a string of tool information
	 * @return string of tool information
	 */
	public String toStringList () {
		String price = String.format("%.2f", itemPrice);
		String print = String.format("%-10s %-20s %-15s %-15s %-20s", "ID: " + itemId, "Name: " + itemName,
				"Quantity: " + itemQuantity, "Price: $" + price, "Supplier: " + theSupplier.toStringList() + "\n");
		return print;
	}

	/**
	 * Makes a string of tool information
	 * @return string of tool information
	 */
	public String toString () {
		String price = String.format("%.2f", itemPrice);
		return "ID: " + itemId + ", Name: " + itemName + ", Quantity: " +
				itemQuantity + ", Price: $" + price + "\nSupplier: " + theSupplier.toString() + "\n";
	}

	/**
	 * Sets alreadyOrdered value
	 * @param alreadyOrdered value alreadyOrdered is changed to
 	 */
	public void setAlreadyOrdered(boolean alreadyOrdered) {
		this.alreadyOrdered = alreadyOrdered;
	}
}
