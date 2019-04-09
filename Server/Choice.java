package Server;

import Server.Model.Shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Makes a choice depending on the clients input
 */
public class Choice implements Runnable {
    /**
     * List of logins
     */
    private String[][] login;
    /**
     * PrintWriter that writes to the client
     */
    private PrintWriter socketOutput;
    /**
     * Socket that the server is connected to
     */
    private Socket aSocket;
    /**
     *Buffered reader that reads from the server
     */
    private BufferedReader socketInput;
    /**
     * The tool shop
     */
    private Shop theShop;

    /**
     * Constructs an object of type Choice
     * @param log list of logins
     * @param shop the tool shop
     * @param serverSocket socket the server is connected to
     */
    Choice(String[][] log, Shop shop, ServerSocket serverSocket){
        try {
            aSocket = serverSocket.accept();
            socketInput = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOutput = new PrintWriter(aSocket.getOutputStream(), true);
            login = log;
            theShop = shop;
        } catch (IOException e){
            System.err.println("Error making choice");
        }
    }

    /**
     * Makes a choice to send to Client
     */
    @Override
    public void run() {
        try {
            while(true) {
                StringBuffer line = new StringBuffer(socketInput.readLine());
                String message = line.toString();
                String[] decode = message.split(",");

                switch (Integer.parseInt(decode[0])) {
                    case 0:
                        int k = 0;
                        for (int i = 0; i < 2; i++) {
                            if (decode[1].equals(login[i][0])) {
                                if (decode[2].equals(login[i][1])) {
                                    socketOutput.println("true" + "\n\0");
                                    k = 1;
                                }
                            }
                        }
                        if(k == 1)
                            break;
                        socketOutput.println("false" + "\n\0");
                        break;
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
                        } catch (NumberFormatException e) {
                            socketOutput.print("");
                        }
                    case 4:
                        socketOutput.println(theShop.getItemQuantity(decode[1]) + "\n\0");
                        break;
                    case 5:
                        socketOutput.println(theShop.decreaseItem(decode[1]) + "\n\0");
                        break;
                    case 6:
                        socketOutput.println(theShop.printOrder() + "\n\0");
                        break;
                    case 7:
                        socketOutput.println(theShop.listAllItems() + "\n\0");
                        break;
                    case 8:
                        try {
                            socketOutput.println(theShop.buyItem(decode[1], Integer.parseInt(decode[2])) + "\n\0");
                            break;
                        } catch (NumberFormatException e){
                            socketOutput.println(theShop.buyItem("", 0) + "\n\0");
                        }
                }
            }
        } catch (IOException e){ }
    }
}