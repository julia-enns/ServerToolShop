package Server.Model;

public class OrderLine {
	
	private Item theItem;
	private int orderQuantity;
	
	
	public OrderLine (Item item, int quantity) {
		theItem = item;
		setOrderQuantity(quantity); 
		
	}

	public Item getTheItem() {
		return theItem;
	}

	public void setTheItem(Item theItem) {
		this.theItem = theItem;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String toString (){
		return  "Server.Model.Item Name: " + theItem.getItemName() +
				", Server.Model.Item ID: " + theItem.getItemId()+ "\n" +
				"Server.Model.Order Quantity: " + orderQuantity + "\n";
	}

}