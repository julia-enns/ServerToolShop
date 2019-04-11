package Server;

import Server.Database.MySQL;
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
    private MySQL database = new MySQL();
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
            //serverSocket = new ServerSocket(44612); //Used to connect two laptops
            serverSocket = new ServerSocket(9090);
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
                database.insertSupplier(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3]);
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
                 database.insertToolPrepared(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
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

    public MySQL getDatabase() {
        return database;
    }

    public static void main(String[] args){
        Server ds = new Server();
        ds.getDatabase().connect();
        ds.getDatabase().createTables();
        ds.serverReadItems();
        ds.serverReadSuppliers();
        ds.getDatabase().decreaseItem("Widgets");
        ds.getUserInput();

    }
}


