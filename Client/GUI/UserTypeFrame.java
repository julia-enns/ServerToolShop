package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Makes a frame to ask user to select what type of person they are
 */
public class UserTypeFrame extends JFrame {
    /**
     * OK button
     */
    private JButton custButton = new JButton("I am a Customer");
    /**
     * Cancel button
     */
    private JButton empButton  = new JButton("I am an Employee");

    /**
     * Constructs an object of type UserFrame
     */
    public UserTypeFrame(){
        this.setTitle("Selection");
        this.setSize(400, 200);
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(), BorderLayout.CENTER);
    }

    /**
     * Creates the north panel for the frame
     * @return north panel
     */
    public JPanel createNorthPanel(){
        JPanel panel = new JPanel();
        JLabel labelLog = new JLabel("Please select one of the following options:");
        panel.add(labelLog);

        return panel;
    }

    /**
     * Creates the bottom panel with required buttons
     * @return the bottom button panel
     */
    public JPanel createButtonPanel(){
        JPanel p2 = new JPanel(new GridLayout(1,2,10,10));
        p2.add(custButton);
        p2.add(empButton);
        return p2;
    }

    /**
     * Connects listeners to the frames buttons
     * @param listener listener to add
     */
    public void addAllListeners(ActionListener listener){
        custButton.addActionListener(listener);
        empButton.addActionListener(listener);
    }

    /**
     * Returns the customer button
     * @return customer button
     */
    public JButton getCustButton() {
        return custButton;
    }

    /**
     * Returns the employee button
     * @return employee button
     */
    public JButton getEmpButton() {
        return empButton;
    }
}
