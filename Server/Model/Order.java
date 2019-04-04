package Server.Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private Date today;
	private int orderId;
	private ArrayList <OrderLine> orderLines;

	public Order () {
		today = Calendar.getInstance().getTime();
		orderLines = new ArrayList <> ();
	}

	public void addOrderLine (OrderLine ol) {
		orderLines.add(ol);
	}

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
