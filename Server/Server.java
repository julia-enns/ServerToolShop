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
        suppliers = new ArrayList<Supplier>();
        serverReadSuppliers();
        theInventory = new Inventory(serverReadItems());
        theShop = new Shop(theInventory, suppliers);
        //System.out.print(theShop.listAllItems());

        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("Date Server is now running.");
            aSocket = serverSocket.accept();
            socketInput = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOutput = new PrintWriter(aSocket.getOutputStream(), true);
        } catch (IOException e) {
        }
    }

    /**
     * Get input from Client.
     *
     * @throws IOException
     */
    public void getUserInput() throws IOException {
        StringBuffer line = null;
        while (true) {
            line = new StringBuffer(socketInput.readLine());
            System.out.println(line.toString());
            if (line != null) {
                String message = line.toString();
                String[] decodedMsg = message.split(",");
                serverFunction(decodedMsg);
                System.out.print(decodedMsg);
            }
        }

              /**  else if (line.toString().equals("TIME")) {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    socketOutput.println( sdf.format(cal.getTime()));
                }else {
                    socketOutput.println("Wrong input, please try again");
                }
            }
               */
    }


    private void serverFunction(String[] decode){
        switch(Integer.parseInt(decode[0])){
            case 1:
                //TODO LIST
                socketOutput.println(theShop.listAllItems() + "\n\0");
            break;

            case 2:
                //TODO SEARCH BY NAME
                socketOutput.println(theShop.getItem(decode[1]) +"\0");
                break;

            case 3:
                //TODO SAERCH BY ID
                socketOutput.println(theShop.getItem(Integer.parseInt(decode[1]))+"\0");
                break;
            case 4:
                //TODO SEARCH BY NAME BUT FIND QUANTITY
                socketOutput.println(theShop.getItemQuantity(decode[1])+"\n\0");
                break;
            case 5:
                //TODO DECREASE QUAN
                socketOutput.println(theShop.decreaseItem(decode[1])+"\0");
                break;
            case 6:
                //TODO PRINT ORDER
                socketOutput.println(theShop.printOrder()+"\n\0");
                break;

        }




    }

    private void serverReadSuppliers() {

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


    private ArrayList<Item> serverReadItems() {

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

    public static void main(String[] args) throws IOException {
        Server ds = new Server();
        ds.getUserInput();
    }
}


