import java.util.ArrayList;

public class Inventory {
	
	private ArrayList <Item> itemList;
	private Order myOrder;
	
	
	public Inventory (ArrayList <Item> itemList) {
		this.itemList = itemList;
		myOrder = new Order ();
	}

	public ArrayList <Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	public Item manageItem (String name){
		Item theItem = decreaseItem (name);
		
		if (theItem != null){
			placeOrder (theItem);
		}
		return theItem;
	}
	public void placeOrder (Item theItem){
		OrderLine ol = theItem.placeOrder();
		if (ol !=null){
			myOrder.addOrderLine(ol);
		}
	}
	private Item decreaseItem (String name) {
		
		Item theItem = searchForItem (name);
		
		if (theItem == null)
			return null;
		
		if (theItem.decreaseItemQuantity() == true){
			
			return theItem;
		}
		return null;
		
	}
	
	public int getItemQuantity (String name){
		Item theItem = searchForItem (name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}
	public Item searchForItem (String name) {
		for (Item i: itemList) {
			if (i.getItemName().equals(name))
				return i;
		}
		return null;
	}
	
	public String toString () {
		String str = "";
		for (Item i: itemList) {
			str += i;
		}
		return str;
	}

	public Item searchForItem(int id) {
		// TODO Auto-generated method stub
		for (Item i: itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}

	public String printOrder() {
		// TODO Auto-generated method stub
		return myOrder.toString();
	}

}
