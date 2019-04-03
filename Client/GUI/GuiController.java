package Client.GUI;

import Client.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiController {

    private Client client;
    private MainFrame gui;

    private PrintOrderFrame orderFrame;

    private ToolGetFrame quantityFrame;
    private ToolGetFrame nameFrame;
    private ToolGetFrame buyFrame;
    private ToolGetFrame  idFrame;

    public GuiController(MainFrame g , PrintOrderFrame o, ToolGetFrame name , ToolGetFrame id , ToolGetFrame quantity, ToolGetFrame buy, Client c){
        gui = g;
        orderFrame = o;
        nameFrame = name;
        idFrame = id;
        quantityFrame=quantity;
        buyFrame=buy;
        gui.addAllListeners(new MainListener());
        orderFrame.addAllListeners(new OrderListener());
        nameFrame.addAllListeners(new ToolListener());
        idFrame.addAllListeners(new ToolListener());
        buyFrame.addAllListeners(new ToolListener());
        quantityFrame.addAllListeners(new ToolListener());
        client= c;
    }

    class MainListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == gui.getListButton()) {
                //TODO
            }
            if(e.getSource()==  gui.getPrintOrderButton()){
                orderFrame.setVisible(true);

            }
            if(e.getSource()== gui.getQuantityButton()){
                quantityFrame.setVisible(true);

            }
            if(e.getSource()== gui.getToolIDButton()){
                idFrame.setVisible(true);
            }
            if(e.getSource()== gui.getToolNameButton()){
                nameFrame.setVisible(true);
            }

            if(e.getSource()==gui.getBuyButton()){
                buyFrame.setVisible(true);
            }

            if(e.getSource()==gui.getQuitButton()){
                client.setRunning(false);
                gui.setVisible(false);
                System.exit(1);
            }

        }

    }

    class OrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==orderFrame.getOkButton()){
                orderFrame.setVisible(false);
            }

        }
    }

    class ToolListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==nameFrame.getOkButton()){
                //TODO
            }
            if(e.getSource()==nameFrame.getCancelButton()){
                nameFrame.setVisible(false);
            }
            // END OF FIND BY TOOL NAME BUTTON FUNCTIONS

            if(e.getSource()==idFrame.getCancelButton()){
                idFrame.setVisible(false);
            }
            if(e.getSource()==idFrame.getOkButton()){
                //TODO

            }
            //END OF FIND BY TOOL ID BUTTON FUNCTIONS

            if(e.getSource()==quantityFrame.getOkButton()){
                //TODO
            }
            if(e.getSource()==quantityFrame.getCancelButton()){
                quantityFrame.setVisible(false);
            }

            //END OF CHECK QUANTITY TOOL BUTTON FUNCTIONS

            if(e.getSource()==buyFrame.getCancelButton()){
                buyFrame.setVisible(false);
            }
            if(e.getSource()==buyFrame.getOkButton()){
                //TODO
            }


        }
    }


}
