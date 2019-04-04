package Server;

import Server.Model.Inventory;
import Server.Model.Item;
import Server.Model.Shop;
import Server.Model.Supplier;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;


public class Server {
    private BufferedReader socketInput;
    private PrintWriter socketOutput;
    private ServerSocket serverSocket;
    private Socket aSocket;
    private ArrayList<Supplier> suppliers;
    private Inventory theInventory;
    private Shop theShop;

    /**
     * Construct a Server with Port 9090
     */
    public Server() {
        suppliers = new ArrayList<>();
        serverReadSuppliers();
        theInventory = new Inventory(serverReadItems());
        theShop = new Shop(theInventory, suppliers);

        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("Server is now running.");
            aSocket = serverSocket.accept();
            socketInput = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOutput = new PrintWriter(aSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error constructing Server");
        }
    }

    /**
     * Get input from Client.
     *
     */
    public void getUserInput(){
        StringBuffer line;
        try {
            while (true) {
                line = new StringBuffer(socketInput.readLine());
                String message = line.toString();
                String[] decodedMsg = message.split(",");
                serverFunction(decodedMsg);
            }
        } catch (IOException e){
            System.err.println("Error getting user input");
        }
    }


    private void serverFunction(String[] decode){
        switch(Integer.parseInt(decode[0])){
            case 1:
                socketOutput.println(theShop.listAllItems() + "\n\0");
                break;
            case 2:
                socketOutput.println(theShop.getItem(decode[1]) + "\n\0");
                break;
            case 3:
                socketOutput.println(theShop.getItem(Integer.parseInt(decode[1]))+"\n\0");
                break;
            case 4:
                socketOutput.println(theShop.getItemQuantity(decode[1])+"\n\0");
                break;
            case 5:
                socketOutput.println(theShop.decreaseItem(decode[1])+"\n\0");
                break;
            case 6:
                socketOutput.println(theShop.printOrder()+"\n\0");
                break;
        }
    }

    private void serverReadSuppliers() {

        try {
            FileReader fr = new FileReader("milestone-1\\suppliers.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                suppliers.add(new Supplier(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3]));
            }
        } catch (Exception e) {
            System.out.println("Error reading in the list of suppliers");
        }
    }

    private ArrayList<Item> serverReadItems() {

        ArrayList<Item> items = new ArrayList<>();

        try {
            FileReader fr = new FileReader("milestone-1\\items.txt");
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
            System.out.println("Error reading in the list of items");
        }
        return items;
    }

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

    public static void main(String[] args){
        Server ds = new Server();
        ds.getUserInput();
    }
}


