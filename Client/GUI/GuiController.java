package Client.GUI;

import Client.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiController {

    private Client client;
    private MainFrame gui;
    private String input;

    private ToolGetFrame quantityFrame;
    private ToolGetFrame nameFrame;
    private ToolGetFrame buyFrame;
    private ToolGetFrame  idFrame;
    private MessageFrame message;

    public GuiController(MainFrame g , ToolGetFrame name , ToolGetFrame id , ToolGetFrame quantity, ToolGetFrame buy, Client c, MessageFrame m){
        gui = g;
        message=m;
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

    class ToolListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==nameFrame.getOkButton()){
                nameFrame.setVisible(false);
                input = "2," + nameFrame.getInput().getText();
                client.communicate(input);
                nameFrame.getInput().setText("");
            }
            if(e.getSource()==nameFrame.getCancelButton()){
                nameFrame.setVisible(false);
            }
            // END OF FIND BY TOOL NAME BUTTON FUNCTIONS


            if(e.getSource()==idFrame.getOkButton()){
                idFrame.setVisible(false);
                input = "3," + idFrame.getInput().getText();
                client.communicate(input);
                idFrame.getInput().setText("");
            }
            if(e.getSource()==idFrame.getCancelButton()){
                idFrame.setVisible(false);
            }
            //END OF FIND BY TOOL ID BUTTON FUNCTIONS


            if(e.getSource()==quantityFrame.getOkButton()){
                quantityFrame.setVisible(false);
                input = "4," + quantityFrame.getInput().getText();
                client.communicate(input);
                quantityFrame.getInput().setText("");
            }
            if(e.getSource()==quantityFrame.getCancelButton()){
                quantityFrame.setVisible(false);
            }
            //END OF CHECK QUANTITY TOOL BUTTON FUNCTIONS


            if(e.getSource()==buyFrame.getOkButton()){
                buyFrame.setVisible(false);
                input = "5," + buyFrame.getInput().getText();
                client.communicate(input);
                buyFrame.getInput().setText("");
            }
            if(e.getSource()==buyFrame.getCancelButton()){
                buyFrame.setVisible(false);
            }
            //END OF BUY BUTTONS FUNCTIONS


            if(e.getSource()==message.getOkButton()){
                message.setVisible(false);
            }
            //END OF MESSAGE OK FUNCTION
        }
    }
}
