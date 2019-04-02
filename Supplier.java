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
		itemList = new ArrayList <Item>();
	}


	public int getSupId() {
		return supId;
	}


	public void setSupId(int supId) {
		this.supId = supId;
	}


	public String getSupName() {
		return supName;
	}


	public void setSupName(String supName) {
		this.supName = supName;
	}


	public String getSupAddress() {
		return supAddress;
	}


	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}


	public String getSupContactName() {
		return supContactName;
	}


	public void setSupContactName(String supContactName) {
		this.supContactName = supContactName;
	}
	public String toString () {
		return supName + " Supplier ID: " + supId+ "\n";
		
	}


	public ArrayList <Item> getItemList() {
		return itemList;
	}


	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	

}
