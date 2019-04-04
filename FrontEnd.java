import Client.GUI.MainFrame;
import Client.GUI.PrintOrderFrame;
import Client.GUI.ToolGetFrame;
import Server.Model.Inventory;
import Server.Model.Item;
import Server.Model.Shop;
import Server.Model.Supplier;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FrontEnd {

	private ArrayList<Supplier> suppliers;
	private Inventory theInventory;
	private Shop theShop;
	private Scanner scan;

	FrontEnd() {
		suppliers = new ArrayList<Supplier>();
		readSuppliers();
		theInventory = new Inventory(readItems());
		theShop = new Shop(theInventory, suppliers);
		scan = new Scanner(System.in);
	}

	private ArrayList<Item> readItems() {

		ArrayList<Item> items = new ArrayList<Item>();

		try {
			FileReader fr = new FileReader("src\\items.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(";");
				int supplierId = Integer.parseInt(temp[4]);

				Supplier theSupplier = findSupplier(supplierId);
				if (theSupplier != null) {
					Item myItem = new Item(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
							Double.parseDouble(temp[3]), theSupplier);
					items.add(myItem);
					theSupplier.getItemList().add(myItem);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return items;
	}

	/*
	 * Finds the supplier which matches the supplierID
	 * @param supplierId
	 * @return theSupplier
	 */
	private Supplier findSupplier(int supplierId) {
		Supplier theSupplier = null;
		for (Supplier s : suppliers) {
			if (s.getSupId() == supplierId) {
				theSupplier = s;
				break;
			}

		}
		return theSupplier;
	}

	private void readSuppliers() {

		try {
			FileReader fr = new FileReader("src\\suppliers.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(";");
				suppliers.add(new Supplier(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void printMenuChoices() {
		System.out.println("Please choose from one of the following options: ");
		System.out.println("1. List all tools in the inventory.");
		System.out.println("2. Search for tool by tool name.");
		System.out.println("3. Search for tool by tool id.");
		System.out.println("4. Check item quantity.");
		System.out.println("5. Decrease item quantity.");
		System.out.println("6. Print today's order.");
		System.out.println("7. Quit.");
		System.out.println();
		System.out.print("Please enter your selection: ");
	}

	public void menu() {

		while (true) {

			printMenuChoices();

			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {

			case 1:
				theShop.listAllItems();
				break;
			case 2:
				searchForItemByName();
				break;
			case 3:
				searchForItemById();
				break;
			case 4:
				checkItemQuantity();
				break;
			case 5:
				decreaseItem();
				break;
			case 6:
				printOrder();
				break;
			case 7:
				System.out.println("\nGood Bye!");
				return;
			default:
				System.out.println("\nInvalid selection Please try again!");
				break;

			}

		}

	}

	private void printOrder() {
		System.out.println(theShop.printOrder());
	}

	private void decreaseItem() {
		String name = getItemName();
		System.out.println(theShop.decreaseItem(name));
	}

	private void checkItemQuantity() {
		String name = getItemName();
		System.out.println(theShop.getItemQuantity(name));
	}

	private String getItemName() {
		System.out.print("Please enter the name of the item: ");

		String line = scan.nextLine();
		return line;

	}

	private int getItemId() {
		System.out.print("Please enter the ID number of the item: ");
		return scan.nextInt();
	}

	private void searchForItemById() {

		int id = getItemId();
		System.out.println(theShop.getItem(id));

	}

	private void searchForItemByName() {


		String name = getItemName();
		System.out.println(theShop.getItem(name));

	}

	public static void main(String[] args) {
		MainFrame gui = new MainFrame();
		ToolGetFrame gui2 = new ToolGetFrame("ID");
		ToolGetFrame gui3 = new ToolGetFrame("NAME");
		PrintOrderFrame gui4 = new PrintOrderFrame();
		gui.setVisible(true);
		gui2.setVisible(true);
		gui3.setVisible(true);
		gui4.setVisible(true);
		FrontEnd app = new FrontEnd();
		app.menu();

	}

}
