package Server.Model;

public class Item {
	
	private int itemId;
	private String itemName;
	private int itemQuantity;
	private double itemPrice;
	private boolean alreadyOrdered;
	private Supplier theSupplier;
	private static final int ORDERQUANTITY = 40;
	private static final int MINIMUMUMBER = 20; 	
	
	
	public Item (int id, String name, int quantity, double price, Supplier sup) {
		
		itemId = id;
		itemName = name;
		itemQuantity = quantity;
		itemPrice = price;
		theSupplier = sup;
		setAlreadyOrdered(false);
	}
	
	public boolean decreaseItemQuantity () {
		if (itemQuantity > 0) {
			itemQuantity--;
		    return true;	
		}
		else
			return false;
			
	}
	public OrderLine placeOrder (){
		OrderLine ol;
		if (getItemQuantity() < MINIMUMUMBER && !alreadyOrdered){
			ol = new OrderLine (this, ORDERQUANTITY);
			alreadyOrdered = true;
			return ol;
		}
	    return null;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public String toString () {
		return "ID: " + itemId + ", Name: " + itemName + ", Quantity: " +
	           itemQuantity + "\n";
	}

	public void setAlreadyOrdered(boolean alreadyOrdered) {
		this.alreadyOrdered = alreadyOrdered;
	}

}
