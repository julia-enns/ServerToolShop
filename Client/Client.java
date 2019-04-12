package Client;

import Client.GUI.*;
import Client.GUI.Controller.CustomerController;
import Client.GUI.Controller.MainController;
import Client.GUI.Controller.ToolController;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Client reads an input from the user and sends an output back with the result.
 * Input is sent to server for processing.
 *
 * @author Julia Grab, Kacper Porebski
 * @version 1.0
 * @since April 4, 2019
 */
public class Client {
    /**
     * The main window of the GUI
     */
    private MainFrame gui;
    /**
     * The customer window of the GUI
     */
    private CustomerFrame customer;
    /**
     * Listeners for main frame of GUI
     */
    private MainController mainController;
    /**
     * Listeners for tool frames
     */
    private ToolController toolController;
    /**
     * Listeners for customer frame
     */
    private CustomerController customerController;
    /**
     * List of tool frames
     */
    private ListToolFrame toolList;
    /**
     * The PrintWriter used to write into the socket.
     */
    private PrintWriter socketOut;
    /**
     * The socket used to link this client to the server.
     */
    private Socket aSocket;
    /**
     * The reader used to read from the socket.
     */
    private BufferedReader socketIn;
    /**
     * The buy window of the GUI
     */
    private BuyFrame buy;

    /**
     * Constructs the client.
     * @param serverName the name of the server.
     * @param portNumber the port of the server.
     */
    public Client(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter((aSocket.getOutputStream()), true);

            gui = new MainFrame();
            toolList = new ListToolFrame();
            customer = new CustomerFrame();
            buy = new BuyFrame();
            customerController = new CustomerController(this, customer, buy);
            mainController = new MainController(gui, toolList, this);
            toolController = new ToolController(customer, toolList, this);

        } catch (IOException e) {
            System.err.println("Error constructing client");
        }
    }

    /**
     * Communicates with the user to enter an input and sends input to the server.
     */
    public void communicate(String s) {
        StringBuilder content = new StringBuilder();
        String f;
        String[] number = s.split(",");
        try {
            socketOut.println(s);
            while (!(f = socketIn.readLine()).equals("\0")) {
                content.append(f);
                content.append(System.lineSeparator());
            }
            clientFunction(content.toString(), Integer.parseInt(number[0]));
        } catch (IOException e) {
            System.err.println("Error communicating in Client");
        }
    }

    /**
     * Updates the GUI or prints a message depending on which button was pressed
     * @param decode output received from the server
     * @param caseNum number of which case is being accessed
     */
    public void clientFunction(String decode, int caseNum) {
        if(caseNum == 0){
            if(decode.charAt(0)=='t')
                gui.setVisible(true);
            else {
                JOptionPane.showMessageDialog(null, "Wrong username or password", "Incorrect Login", JOptionPane.PLAIN_MESSAGE);
                toolList.getLoginFrame().setVisible(true);
            }
        }
        else if (caseNum == 1) {
            gui.getToolList().clear();
            String[] tools = decode.split("\n");
            for (String s : tools)
                gui.getToolList().addElement(s);
        }
        else if(caseNum == 7){
            customer.getToolList().clear();
            String[] tools = decode.split("\n");
            for (String s : tools)
                customer.getToolList().addElement(s);
        }
        else
            JOptionPane.showMessageDialog(null, decode, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args){
        Client aClient = new Client("10.13.96.123", 44612); //Used to connect two laptops
       // Client aClient = new Client("localhost", 9090);
        aClient.toolList.getChoiceFrame().setVisible(true);
    }
}