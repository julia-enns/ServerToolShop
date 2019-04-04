package Client;

import Client.GUI.GuiController;
import Client.GUI.MainFrame;
import Client.GUI.PrintOrderFrame;
import Client.GUI.ToolGetFrame;

import javax.swing.*;
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
    private Socket aSocket;
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
            aSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter((aSocket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace().toString());
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


        String input;
        while(true){
            try{
                if(controller.getInput() != null) {
                    socketOut.print(controller.getInput());
                    input = socketIn.readLine();
                    String[] arr = controller.getInput().split(",");
                    if (arr[0].equals("1")) {
                        gui.getToolList().clear();
                        String [] tools = arr[1].split("\n");
                        for(String s : tools){
                            gui.getToolList().addElement(s);
                        }
                    } else if (arr[0].equals("6")){
                        printOrder.getOrderList().addElement(arr[1]);
                    }
                    else{
                        JFrame frame = new JFrame("Message");

                    }
                }
           break;
            } catch (IOException e){

            }
        }


         try {
            stdIn.close();
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }


    }

    public void setRunning(boolean r){
        running=r;
    }
    public static void main(String[] args){
        Client aClient = new Client("localhost", 8899);
        aClient.communicate();
    }
}