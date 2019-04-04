package Server;

import Server.Model.Shop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private BufferedReader socketInput;
    private PrintWriter socketOutput;
    private ServerSocket serverSocket;
    private Socket aSocket;

    /**
     * Construct a Server with Port 9090
     */
    public Server() {
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
            //System.out.println(line.toString());
            if (line != null) {
                if (line.toString().equals("QUIT")) {
                    break;
                }
                if (line.toString().equals("DATE")) {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
                    socketOutput.println( sdf.format(cal.getTime()));
                } else if (line.toString().equals("TIME")) {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    socketOutput.println( sdf.format(cal.getTime()));
                }else {
                    socketOutput.println("Wrong input, please try again");
                }
            }
        }


    }

    /**
     * Run the Server.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Server ds = new Server();
        ds.getUserInput();
    }
}


