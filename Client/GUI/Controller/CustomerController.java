package Client.GUI.Controller;


import Client.Client;
import Client.GUI.BuyFrame;
import Client.GUI.CustomerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Customer listeners in the GUI
 */
public class CustomerController {
    private Client client;
   private CustomerFrame gui;
   private String input;
   private BuyFrame buyFrame;

    /**
     * Constructs an object of type CustomerController
     * @param c the client that connects to the GUI
     * @param customer the CustomerFrame being connected to
     * @param buy the buy frame
     */
   public CustomerController(Client c, CustomerFrame customer, BuyFrame buy){
       client = c;
       gui = customer;
       buyFrame = buy;
       gui.addAllListeners(new MainListener());
       buyFrame.addAllListeners(new MainListener());
    }

    /**
     * Creates listeners for the customer GUI buttons
     */
    class MainListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gui.getBrowseButton()) {
                input = "7";
                client.communicate(input);
            }

            if(e.getSource() == gui.getBuyButton()){
                buyFrame.setVisible(true);
            }

            if (e.getSource() == gui.getQuitButton()) {
                gui.setVisible(false);
                System.exit(1);
            }

            if(e.getSource()==buyFrame.getOkButton()){
                buyFrame.setVisible(false);
                if(!buyFrame.getInput().getText().equals("") && !buyFrame.getQuantity().getText().equals("")) {
                    input = "8," + buyFrame.getInput().getText() + "," + buyFrame.getQuantity().getText();
                    client.communicate(input);
                    buyFrame.getInput().setText("");
                    buyFrame.getQuantity().setText("");
                }
            }
            if(e.getSource()==buyFrame.getCancelButton()){
                buyFrame.setVisible(false);
            }
        }
    }

}
