package Server.Model;

import java.util.ArrayList;

/**
 * Supplier of a tool
 */
public class Supplier {
	/**
	 * Supplier ID
	 */
	private int supId;
	/**
	 * Supplier name
	 */
	private String supName;
	/**
	 * Supplier address
	 */
	private String supAddress;
	/**
	 * Supplier contact name
	 */
	private String supContactName;
	/**
	 * List of items
	 */
	private ArrayList <Item> itemList;

	/**
	 * Constructs an object of type Supplier
	 * @param id supplier ID
	 * @param name supplier name
	 * @param address supplier address
	 * @param contactName supplier contact name
	 */
	public Supplier (int id, String name, String address, String contactName) {
		supId = id;
		supName = name;
		supAddress = address;
		supContactName = contactName;
		itemList = new ArrayList <>();
	}

	/**
	 * Returns supplier ID
	 * @return supplier ID
	 */
	public int getSupId() {
		return supId;
	}

	/**
	 * Converts supplier information into a string
	 * @return supplier string
	 */
	public String toStringList () {
		String print = String.format("%-30s %-10s", supName, "Suppier ID: " + supId);
		return print;
	}

	/**
	 * Converts supplier information into a string
	 * @return supplier string
	 */
	public String toString () {
		return supName + " Supplier ID: " + supId ;
	}


	/**
	 * Returns item list
	 * @return item list
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}
}
