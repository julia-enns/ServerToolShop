package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuyFrame extends JFrame {

    /**
     * OK button
     */
    private JButton okButton = new JButton("OK");
    /**
     * Cancel button
     */
    private JButton cancelButton  = new JButton("Cancel");
    /**
     * Field for user input
     */
    private JTextField input = new JTextField(15);
    private JTextField quantity = new JTextField(15);

    /**
     * Constructs the GUI frame
     */
    public BuyFrame(){
        this.setTitle("Read");
        this.setSize(300, 175);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Getter for the input field
     * @return the input field
     */
    public JTextField getInput() {
        return input;
    }

    public JTextField getQuantity() {
        return quantity;
    }

    /**
     * Creates the center panel for the frame
     * @return center panel
     */
    public JPanel createCenterPanel(){
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please enter the tool name:\n");
        panel.add(label);
        panel.add(this.input);
        JLabel labelNum = new JLabel("Please enter the amount you want to order:\n");
        panel.add(labelNum);
        panel.add(this.quantity);
        return panel;
    }

    /**
     * Creates the bottom panel with required buttons
     * @return the bottom button panel
     */
    public JPanel createButtonPanel(){
        JPanel p2 = new JPanel();
        p2.add(okButton);
        p2.add(cancelButton);
        return p2;
    }

    /**
     * Connects listeners to the frames buttons
     * @param listener listener to add
     */
    public void addAllListeners(ActionListener listener){
        okButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
    }

    /**
     * OK button getter
     * @return OK Button
     */
    public JButton getOkButton() {
        return okButton;
    }

    /**
     * Cancel button getter
     * @return Cancel button
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
}


