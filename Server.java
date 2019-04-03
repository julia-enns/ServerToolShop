import Model.Shop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The server class to the game of Tic Tac Toe that connects the players together
 */
public class Server {
    /**
     * The server socket the players connect to
     */
    private ServerSocket serverSocket;
    /**
     * The pool of different games of Tic Tac Toe
     */
    private ExecutorService pool;
    PrintWriter out;
    private ObjectInputStream input;
    private Shop theShop;
    private Scanner scan;

    /**
     * Constructs an object of type Server with the specified value
     * @param portNumber the port number being connected to
     */
    public Server(int portNumber){
        try{
            serverSocket = new ServerSocket(portNumber);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e){
            e.printStackTrace();
        }
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

    /**
     * Communicates with the client to start a game of Tic Tac Toe
     */
    public void communicate(){
        while(input==null) {
            try {
                input = new ObjectInputStream(new FileInputStream("message.ser"));
            } catch (IOException e) {

            }
        }


        String output = null;
        while(true) {
            try {
                Messenger message = (Messenger) input.readObject();

                switch (message.getId()) {
                    case 1:
                        output = theShop.listAllItems();
                        break;
                    case 2:
                        output = theShop.getItem(message.getInputText());
                        break;
                    case 3:
                        output = theShop.getItem(message.getInputNum());
                        break;
                    case 4:
                        output = theShop.getItemQuantity(message.getInputText());
                        break;
                    case 5:
                        output = theShop.decreaseItem(message.getInputText());
                        break;
                    case 6:
                        output = theShop.printOrder();
                        break;
                    case 7:
                        output = "Good Bye!";
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                pool.shutdown();
            }

            out.println(output);


            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                System.out.println("Error closing file.");
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Server server = new Server(8899);
        System.out.println("Server is now running.");
        server.communicate();
    }
}


