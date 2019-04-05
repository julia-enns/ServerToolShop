package Client;

import Client.GUI.*;

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
     * The frame that asks user to enter in a tool ID
     */
    private ToolGetFrame toolID;
    /**
     * The frame that asks user to enter in a tool name
     */
    private ToolGetFrame toolName;
    /**
     * The frame that asks user which tool they want to buy
     */
    private ToolGetFrame buyTool;
    /**
     * The frame that asks user which tool they want to check the quantity of
     */
    private ToolGetFrame checkQuantity;
    /**
     * The controller that connects all the buttons to their listeners
     */
    private GuiController controller;
    /**
     * The PrintWriter used to write into the socket.
     */
    private PrintWriter socketOut;
    /**
     * The socket used to link this client to the server.
     */
    private Socket aSocket;
    /**
     * The reader used to read from console.
     */
    private BufferedReader stdIn;
    /**
     * The reader used to read from the socket.
     */
    private BufferedReader socketIn;

    /**
     * Constructs the client.
     * @param serverName the name of the server.
     * @param portNumber the port of the server.
     */
    public Client(String serverName, int portNumber) {
        try {
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter((aSocket.getOutputStream()), true);

            gui = new MainFrame();
            toolID = new ToolGetFrame("ID");
            toolName = new ToolGetFrame("NAME");
            buyTool = new ToolGetFrame("NAME");
            checkQuantity = new ToolGetFrame("NAME");
            controller = new GuiController(gui, toolName, toolID, checkQuantity, buyTool , this);

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
        if (caseNum == 1) {
            gui.getToolList().clear();
            String[] tools = decode.split("\n");
            for (String s : tools)
                gui.getToolList().addElement(s);
        } else
            JOptionPane.showMessageDialog(null, decode, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args){
        Client aClient = new Client("localhost", 9090);
        aClient.gui.setVisible(true);
    }
}