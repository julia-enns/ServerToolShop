package Client.GUI;

import Client.Client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiController {

    private Client client;
    private MainFrame gui;
    private String input;

    private PrintOrderFrame orderFrame;

    private ToolGetFrame quantityFrame;
    private ToolGetFrame nameFrame;
    private ToolGetFrame buyFrame;
    private ToolGetFrame  idFrame;
    private MessageFrame message;

    public GuiController(MainFrame g , PrintOrderFrame o, ToolGetFrame name , ToolGetFrame id , ToolGetFrame quantity, ToolGetFrame buy, Client c, MessageFrame m){
        gui = g;
        orderFrame = o;
        message=m;
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
                input = "DATE\n";
               client.communicate(input);
                System.out.println(input);
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
              //  client.setRunning(false);
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
                nameFrame.setVisible(false);
                input = "2," + nameFrame.getInput().toString();
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
                idFrame.setVisible(false);
                input = "3," + idFrame.getInput().toString();
                //TODO

            }
            //END OF FIND BY TOOL ID BUTTON FUNCTIONS

            if(e.getSource()==quantityFrame.getOkButton()){
                quantityFrame.setVisible(false);
                input = "4," + quantityFrame.getInput().toString();
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
                buyFrame.setVisible(false);
                input = "5," + buyFrame.getInput().toString();
                //TODO
            }

            //END OF BUY BUTTONS FUNCTIONS

            if(e.getSource()==message.getOkButton()){
                message.setVisible(false);
            }

            //END OF MESSAGE OK FUNCTION


        }
    }

    public String getInput() {
        return input;
    }
}
