package Server.Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Creates an order of an item.
 */
public class Order {
	/**
	 * Date of the order made
	 */
	private Date today;
	/**
	 * Order ID
	 */
	private int orderId;
	/**
	 * List of order lines
	 */
	private ArrayList <OrderLine> orderLines;

	/**
	 * Constructs an object of type Order
	 */
	public Order () {
		today = Calendar.getInstance().getTime();
		orderLines = new ArrayList <> ();
	}

	/**
	 * Adds order to orderline
	 * @param ol new order being made
	 */
	public void addOrderLine (OrderLine ol) {
		orderLines.add(ol);
	}

	/**
	 * Makes a string of the order
	 * @return string of the order
	 */
	public String toString (){
		String order = "Date: " + today.toString() + "\n\n";
		String str = "";
		for (OrderLine ol: orderLines) {
			str += ol;
			str += "------------------------\n";
		}
		if (str.equals(""))
			str = "There are currently no order lines made.";
		
		order += str;
		order += "\n";
		return order;
	}

}
