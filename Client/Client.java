package Client;

import Client.GUI.GuiController;
import Client.GUI.MainFrame;
import Client.GUI.PrintOrderFrame;
import Client.GUI.ToolGetFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Client.Client reads an input from the user and sends an output back with the result.
 * Input is sent to server for processing.
 *
 * @author Julia Grab
 * @version 1.0
 * @since March 14, 2019
 */
public class Client {
    /**
     * The output to send data to the server
     */
    boolean running;
    private PrintWriter socketOut;
    /**
     * The socket data is being sent to and received from
     */
    private Socket palinSocket;
    /**
     * The input data the user is entering to the program
     */
    private BufferedReader stdIn;
    /**
     * The input received from the server
     */
    private BufferedReader socketIn;

    /**
     * Constructs a Client.Client object with the specified values.
     * @param serverName name of the server being connected to
     * @param portNumber number of the port being connected to
     */
    public Client(String serverName, int portNumber) {
        try {
            palinSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(palinSocket.getInputStream()));
            socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Communicates with the user to enter an input and sends input to the server.
     */
    public void communicate()  {
        MainFrame gui = new MainFrame();
        PrintOrderFrame printOrder = new PrintOrderFrame();
        ToolGetFrame toolID = new ToolGetFrame("ID");
        ToolGetFrame toolName = new ToolGetFrame("NAME");
        ToolGetFrame buyTool = new ToolGetFrame("NAME");
        ToolGetFrame checkQuantity = new ToolGetFrame("NAME");
        GuiController controller = new GuiController(gui, printOrder, toolName, toolID, checkQuantity, buyTool , this);
        gui.setVisible(true);
    /*    running=true;
        while(running){
            //TODO
            System.out.println("yes");
        }


         try {
            stdIn.close();
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }
        */

    }

    public void setRunning(boolean r){
        running=r;
    }
    public static void main(String[] args){
        Client aClient = new Client("localhost", 8899);
        aClient.communicate();
    }
}