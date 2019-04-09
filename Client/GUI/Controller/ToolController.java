package Client.GUI.Controller;

import Client.Client;
import Client.GUI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Connects the buttons on the tool frames to their listeners and actions
 */
public class ToolController {
    /**
     * The client the controller connects to
     */
    private Client client;
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
     * The frame created to login to Tool Shop
     */
    private UserFrame loginFrame;

    private UserTypeFrame choiceFrame;

    private CustomerFrame customerFrame;

    /**
     * Constructs an object of type ToolController
     * @param t list of tool frames in the GUI
     * @param c the client that connects to the GUI
     */
    public ToolController(CustomerFrame cust, ListToolFrame t, Client c){
        nameFrame = t.getNameFrame();
        idFrame = t.getIdFrame();
        quantityFrame = t.getQuantityFrame();
        buyFrame = t.getBuyFrame();
        loginFrame = t.getLoginFrame();
        choiceFrame = t.getChoiceFrame();
        customerFrame = cust;

        nameFrame.addAllListeners(new ToolListener());
        idFrame.addAllListeners(new ToolListener());
        buyFrame.addAllListeners(new ToolListener());
        quantityFrame.addAllListeners(new ToolListener());
        loginFrame.addAllListeners(new ToolListener());
        choiceFrame.addAllListeners(new ToolListener());

        client = c;
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

            if(e.getSource()==loginFrame.getOkButton()){
                loginFrame.setVisible(false);
                if(loginFrame.getUsername().getText().equals("") || loginFrame.getPassword().getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Wrong username or password", "Incorrect Login", JOptionPane.PLAIN_MESSAGE);
                    loginFrame.setVisible(true);
                } else {
                    input = "0," + loginFrame.getUsername().getText() + "," + loginFrame.getPassword().getText();
                    client.communicate(input);
                }
            }
            if(e.getSource()==loginFrame.getCancelButton()){
                loginFrame.setVisible(false);
                System.exit(1);
            }
            //END OF LOGIN BUTTONS FUNCTIONS

            if(e.getSource()==choiceFrame.getEmpButton()){
                choiceFrame.setVisible(false);
                loginFrame.setVisible(true);
            }
            if(e.getSource()==choiceFrame.getCustButton()){
                choiceFrame.setVisible(false);
                customerFrame.setVisible(true);
            }
            //END OF CHOICE BUTTONS FUNCTIONS
        }
    }
}
