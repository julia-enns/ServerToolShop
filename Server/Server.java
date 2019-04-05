package Server;

import Server.Model.Inventory;
import Server.Model.Item;
import Server.Model.Shop;
import Server.Model.Supplier;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

/**
 * The Server reads an input from the cleint and sends an output back to the client
 *
 * @author Julia Grab, Kacper Porebski
 * @version 1.0
 * @since April 4, 2019
 */
public class Server {
    /**
     *Buffered reader that reads from the server
     */
    private BufferedReader socketInput;
    /**
     * Print Writer the sends output to server
     */
    private PrintWriter socketOutput;
    /**
     * Server socket the server is connected to
     */
    private ServerSocket serverSocket;
    /**
     * The socket that connects the client and the server
     */
    private Socket aSocket;
    /**
     * List of the suppliers
     */
    private ArrayList<Supplier> suppliers;
    /**
     * Inventory of the tool shop
     */
    private Inventory theInventory;
    /**
     * The tool shop
     */
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

    /**
     * Chooses what is sent to the client depending on which button was pressed
     * @param decode choice number made and input sent in
     */
    private void serverFunction(String[] decode){
        switch(Integer.parseInt(decode[0])){
            case 1:
                socketOutput.println(theShop.listAllItems() + "\n\0");
                break;
            case 2:
                socketOutput.println(theShop.getItem(decode[1]) + "\n\0");
                break;
            case 3:
                try {
                    socketOutput.println(theShop.getItem(Integer.parseInt(decode[1])) + "\n\0");
                    break;
                } catch(NumberFormatException e){
                    socketOutput.print("");
                }
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

    /**
     * Reads in the list of suppliers to the shop
     */
    private void serverReadSuppliers() {

        try {
            FileReader fr = new FileReader("src\\suppliers.txt");
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

    /**
     * Reads in the list of items to the shop
     * @return list of items
     */
    private ArrayList<Item> serverReadItems() {

        ArrayList<Item> items = new ArrayList<>();

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
            System.out.println("Error reading in the list of items");
        }
        return items;
    }

    /**
     * Searches through the list of the suppliers
     * @param supplierId supplied ID being searched for
     * @return the supplier information
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

    public static void main(String[] args){
        Server ds = new Server();
        ds.getUserInput();
    }
}


