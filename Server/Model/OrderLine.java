package Server.Model;

/**
 * Order line of an item
 */
public class OrderLine {
	/**
	 * Item being ordered
	 */
	private Item theItem;
	/**
	 * Amount being ordered
	 */
	private int orderQuantity;

	/**
	 * Constructs an object of type OrderLine
	 * @param item item being ordered
	 * @param quantity amount being ordered
	 */
	public OrderLine (Item item, int quantity) {
		theItem = item;
		setOrderQuantity(quantity);
	}

	/**
	 * Sets quantity being ordered
	 * @param orderQuantity amount being ordered
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * Makes order line to string
	 * @return string of order line
	 */
	public String toString (){
		return  "Name: " + theItem.getItemName() +
				", ID: " + theItem.getItemId()+ "\n" +
				"Quantity: " + orderQuantity + "\n";
	}

}
