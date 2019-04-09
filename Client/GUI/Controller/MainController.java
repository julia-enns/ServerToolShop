package Client.GUI.Controller;

import Client.Client;
import Client.GUI.ListToolFrame;
import Client.GUI.MainFrame;
import Client.GUI.ToolGetFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main listeners in the GUI
 */
public class MainController {
    /**
     * The client the controller connects to
     */
    private Client client;
    /**
     * The main GUI the controller connects to
     */
    private MainFrame gui;
    /**
     * The input that is sent to the client from GUI
     */
    private String input;
    /**
     * The frame created when check quantity button is pressed
     */
    private ToolGetFrame quantityFrame;
    /**
     * The frame created when the search name button is pressed
     */
    private ToolGetFrame nameFrame;
    /**
     * The frame created when the buy tool button is pressed
     */
    private ToolGetFrame buyFrame;
    /**
     * The frame created when the search ID button is pressed
     */
    private ToolGetFrame idFrame;

    /**
     * Constructs an object of type MainController
     * @param g the main frame of the GUI
     * @param t the list of tool frames in the GUI
     * @param c the client that connects to the GUI
     */
    public MainController(MainFrame g, ListToolFrame t, Client c){
        gui = g;
        nameFrame = t.getNameFrame();
        idFrame = t.getIdFrame();
        quantityFrame = t.getQuantityFrame();
        buyFrame = t.getBuyFrame();
        gui.addAllListeners(new MainListener());
        client = c;
    }

    /**
     * Creates listeners for the main GUI buttons
     */
    class MainListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gui.getListButton()) {
                input = "1";
                client.communicate(input);
            }
            if (e.getSource() == gui.getPrintOrderButton()) {
                input = "6";
                client.communicate(input);
            }
            if (e.getSource() == gui.getQuantityButton()) {
                quantityFrame.setVisible(true);
            }
            if (e.getSource() == gui.getToolIDButton()) {
                idFrame.setVisible(true);
            }
            if (e.getSource() == gui.getToolNameButton()) {
                nameFrame.setVisible(true);
            }
            if (e.getSource() == gui.getBuyButton()) {
                buyFrame.setVisible(true);
            }
            if (e.getSource() == gui.getQuitButton()) {
                gui.setVisible(false);
                System.exit(1);
            }
        }
    }
}
