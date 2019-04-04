package Server.Model;

public class OrderLine {
	
	private Item theItem;
	private int orderQuantity;

	public OrderLine (Item item, int quantity) {
		theItem = item;
		setOrderQuantity(quantity);
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String toString (){
		return  "Name: " + theItem.getItemName() +
				", ID: " + theItem.getItemId()+ "\n" +
				"Quantity: " + orderQuantity + "\n";
	}

}
