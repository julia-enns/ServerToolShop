import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Client reads an input from the user and sends an output back with the result.
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
     * Constructs a Client object with the specified values.
     * @param serverName name of the server being connected to
     * @param portNumber number of the port being connected to
     */
    public Client(String serverName, int portNumber) {
        try {
            palinSocket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(
                    palinSocket.getInputStream()));
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
                gui.setVisible(true);


        try {
            stdIn.close();
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }

    }

    public static void main(String[] args){
        Client aClient = new Client("localhost", 8898);
        aClient.communicate();
    }
}