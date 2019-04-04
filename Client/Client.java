package Client;

import Client.GUI.*;

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

    MainFrame gui = new MainFrame();
    PrintOrderFrame printOrder = new PrintOrderFrame();
    ToolGetFrame toolID = new ToolGetFrame("ID");
    ToolGetFrame toolName = new ToolGetFrame("NAME");
    ToolGetFrame buyTool = new ToolGetFrame("NAME");
    ToolGetFrame checkQuantity = new ToolGetFrame("NAME");
    MessageFrame message = new MessageFrame();
    GuiController controller = new GuiController(gui, printOrder, toolName, toolID, checkQuantity, buyTool , this, message);
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
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Communicates with the user to enter an input and sends input to the server.
     */
    public void communicate(String s)  {

        String response = "";
        String f= "";
        String[] number = s.split(",");
            try {
                socketOut.println(s);
                while(!socketIn.readLine().equals("\0") )
                {
                    f += socketIn.readLine() + "\n";
                    clientFunction(f,Integer.parseInt(number[0]));

                }

                //  System.out.println(response);
            }
            catch (IOException e){
                e.printStackTrace();
            }




            }





    public void clientFunction(String decode, int caseNum){
        System.out.print(caseNum);
        if(caseNum == 1) {
            gui.getToolList().clear();
            String[] tools = decode.split("\n");
            for (String s : tools) {
                gui.getToolList().addElement(s);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, decode, "Message", JOptionPane.PLAIN_MESSAGE);

        }



    }

       /* String input;
        while(true){
            try{
                if(controller.getInput() != null) {
                    socketOut.print(controller.getInput());
                    input = socketIn.readLine();
                    String[] arr = controller.getInput().split(",");
                    if (arr[0].equals("1")) {
                        gui.getToolList().clear();
                        String [] tools = input.split("\n");
                        for(String s : tools){
                            gui.getToolList().addElement(s);
                        }
                    } else if (arr[0].equals("6")){
                        printOrder.getOrderList().addElement(input);
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

            */



    public static void main(String[] args){
        Client aClient = new Client("localhost", 9090);
        aClient.gui.setVisible(true);


    }
}