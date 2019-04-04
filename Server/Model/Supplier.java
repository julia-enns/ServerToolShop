package Server.Model;

import java.util.ArrayList;

public class Supplier {
	
	private int supId;
	private String supName;
	private String supAddress;
	private String supContactName;
	private ArrayList <Item> itemList;
	
	
	public Supplier (int id, String name, String address, String contactName) {
		
		supId = id;
		supName = name;
		supAddress = address;
		supContactName = contactName;
		itemList = new ArrayList <>();
	}

	public int getSupId() {
		return supId;
	}

	public String toString () {
		return supName + " Server.Model.Supplier ID: " + supId+ "\n";
		
	}

	public ArrayList <Item> getItemList() {
		return itemList;
	}
}
