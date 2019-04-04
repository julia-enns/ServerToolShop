package Server;

import Server.Model.Shop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
    Socket mySocket;
    /**
     * The input file receiving the word from the client
     */
    BufferedReader in;


    /**
     * Constructs an object of type Server.Server with the specified value
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

        String input = null;
        String output = null;
        while(true) {
            try {
                input = in.readLine();
                String[] in = input.split(",");

                switch (Integer.parseInt(in[0])) {
                    case 1:
                        output = theShop.listAllItems();
                        break;
                    case 2:
                        output = theShop.getItem(in[1]);
                        break;
                    case 3:
                        output = theShop.getItem(Integer.parseInt(in[1]));
                        break;
                    case 4:
                        output = theShop.getItemQuantity(in[1]);
                        break;
                    case 5:
                        output = theShop.decreaseItem(in[1]);
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
        }
    }

    public static void main(String[] args) throws IOException{
        Server server = new Server(8899);
        System.out.println("Server.Server is now running.");
        server.communicate();
    }
}


