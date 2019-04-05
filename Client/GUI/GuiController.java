package Client.GUI;

import Client.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Connects all the buttons on the GUI to their listeners and actions
 */
public class GuiController {
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
     * Constructs an object of type GuiController
     * @param g the main frame of the GUI
     * @param name the frame that accesses the search by tool name
     * @param id the frame that accesses the search by tool ID
     * @param quantity the frame that accesses the quantity of a tool
     * @param buy the frame that decreases the quantity of a tool
     * @param c the client that connects to the GUI
     */
    public GuiController(MainFrame g, ToolGetFrame name, ToolGetFrame id , ToolGetFrame quantity, ToolGetFrame buy, Client c){
        gui = g;
        nameFrame = name;
        idFrame = id;
        quantityFrame=quantity;
        buyFrame=buy;
        gui.addAllListeners(new MainListener());
        nameFrame.addAllListeners(new ToolListener());
        idFrame.addAllListeners(new ToolListener());
        buyFrame.addAllListeners(new ToolListener());
        quantityFrame.addAllListeners(new ToolListener());
        client= c;
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

    /**
     * Creates listeners for secondary GUI buttons
     */
    class ToolListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==nameFrame.getOkButton()){
                nameFrame.setVisible(false);
                if(!nameFrame.getInput().getText().equals("")) {
                    input = "2," + nameFrame.getInput().getText();
                    client.communicate(input);
                    nameFrame.getInput().setText("");
                }
            }
            if(e.getSource()==nameFrame.getCancelButton()){
                nameFrame.setVisible(false);
            }
            // END OF FIND BY TOOL NAME BUTTON FUNCTIONS


            if(e.getSource()==idFrame.getOkButton()){
                idFrame.setVisible(false);
                if(!idFrame.getInput().getText().equals("")) {
                    input = "3," + idFrame.getInput().getText();
                    client.communicate(input);
                    idFrame.getInput().setText("");
                }
            }
            if(e.getSource()==idFrame.getCancelButton()){
                idFrame.setVisible(false);
            }
            //END OF FIND BY TOOL ID BUTTON FUNCTIONS


            if(e.getSource()==quantityFrame.getOkButton()){
                quantityFrame.setVisible(false);
                if(!quantityFrame.getInput().getText().equals("")) {
                    input = "4," + quantityFrame.getInput().getText();
                    client.communicate(input);
                    quantityFrame.getInput().setText("");
                }
            }
            if(e.getSource()==quantityFrame.getCancelButton()){
                quantityFrame.setVisible(false);
            }
            //END OF CHECK QUANTITY TOOL BUTTON FUNCTIONS


            if(e.getSource()==buyFrame.getOkButton()){
                buyFrame.setVisible(false);
                if(!buyFrame.getInput().getText().equals("")) {
                    input = "5," + buyFrame.getInput().getText();
                    client.communicate(input);
                    buyFrame.getInput().setText("");
                }
            }
            if(e.getSource()==buyFrame.getCancelButton()){
                buyFrame.setVisible(false);
            }
            //END OF BUY BUTTONS FUNCTIONS
        }
    }
}
