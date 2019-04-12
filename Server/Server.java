package Server;

import Server.Database.MySQL;
import Server.Database.OrderSQL;
import Server.Database.SupplierSQL;
import Server.Database.ToolSQL;
import Server.Model.Inventory;
import Server.Model.Item;
import Server.Model.Shop;
import Server.Model.Supplier;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Server reads an input from the client and sends an output back to the client
 *
 * @author Julia Grab, Kacper Porebski
 * @version 1.0
 * @since April 4, 2019
 */
public class Server{
    private ToolSQL toolSQL = new ToolSQL();
    private OrderSQL orderSQl = new OrderSQL();
    private SupplierSQL supplierSQL = new SupplierSQL();
    /**
     * List of logins
     */
    private String[][] login;
    /**
     * Server socket the server is connected to
     */
    private ServerSocket serverSocket;
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
     * Thread pool
     */
    private ExecutorService pool;


    /**
     * Construct a Server with Port 9090
     */
    public Server() {

        try {
            serverSocket = new ServerSocket(44612); //Used to connect two laptops
           // serverSocket = new ServerSocket(9090);
            System.out.println("Server is now running.");
            pool = Executors.newCachedThreadPool();
            makeLogin();
        } catch (IOException e) {
            System.err.println("Error constructing Server");
        }
    }

    /**
     * Executes a thread pool
     *
     */
    public void getUserInput(){
        try {
           while (true) {
                Choice choose = new Choice(login, this, serverSocket);
                pool.execute(choose);
            }
        } catch (Exception e){
            pool.shutdown();
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
                supplierSQL.insertSupplier(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3]);
            }
        } catch (Exception e) {
            System.out.println("Error reading in the list of suppliers");
        }
    }

    /**
     * Reads in the list of items to the shop
     * @return list of items
     */
    private void serverReadItems() {

        try {
            FileReader fr = new FileReader("src\\items.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                 Integer.parseInt(temp[4]);
                 toolSQL.insertToolPrepared(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
                            Double.parseDouble(temp[3]), Integer.parseInt(temp[4]));
                }
            }
         catch (Exception e) {
            System.out.println("Error reading in the list of items");
        }
    }

    /**
     * Creates user names and passwords
     */
    private void makeLogin(){
        login = new String[2][2];
        login[0][0] = "kkporebski";
        login[0][1] = "1";
        login[1][0] = "julia_grab";
        login[1][1] = "2";
    }

    /**
     * Returns Order database
     * @return order database
     */
    public OrderSQL getOrderSQl() {
        return orderSQl;
    }

    /**
     * Returns supplier database
     * @return suppleir database
     */
    public SupplierSQL getSupplierSQL() {
        return supplierSQL;
    }

    /**
     * Returns tool database
     * @return tool database
     */
    public ToolSQL getToolSQL() {
        return toolSQL;
    }

    /**
     * Connects to database
     */
    public void connectToDatabase(){
        orderSQl.connect();
        supplierSQL.connect();
        toolSQL.connect();
    }

    /**
     * Creates tables
     */
    public void createTables(){
        orderSQl.createOrderTable();
        supplierSQL.createSupplierTable();
        toolSQL.createToolTable();
    }

    public static void main(String[] args){
        Server ds = new Server();
        ds.connectToDatabase();
        ds.createTables();
        ds.serverReadItems();
        ds.serverReadSuppliers();
        ds.getUserInput();

    }
}


