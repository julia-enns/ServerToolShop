package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Creates a login frame for a user to access the Tool Shop information
 */
public class UserFrame extends JFrame {
    /**
     * OK button
     */
    private JButton okButton = new JButton("OK");
    /**
     * Cancel button
     */
    private JButton cancelButton  = new JButton("Cancel");
    /**
     * Field for input of username
     */
    private JTextField inputUsername = new JTextField(15);
    /**
     * Field for input of password
     */
    private JTextField inputPassword = new JTextField(15);

    /**
     * Constructs an object of type UserFrame
     */
    public UserFrame(){
        this.setTitle("Login");
        this.setSize(300, 200);
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Getter for the input username field
     * @return the input field
     */
    public JTextField getUsername() {
        return inputUsername;
    }

    /**
     * Getter for the input password field
     * @return the input field
     */
    public JTextField getPassword() {
        return inputPassword;
    }


    /**
     * Creates the north panel for the frame
     * @return north panel
     */
    public JPanel createNorthPanel(){
        JPanel panel = new JPanel();
        JLabel labelLog = new JLabel("Please login:");
        panel.add(labelLog);

        return panel;
    }

    /**
     * Creates the center panel for the frame
     * @return center panel
     */
    public JPanel createCenterPanel(){
        JPanel panel = new JPanel();

        JLabel labelUser = new JLabel("Username: ");
        panel.add(labelUser);
        panel.add(this.inputUsername);

        JLabel labelPassword = new JLabel("Password: ");
        panel.add(labelPassword);
        panel.add(this.inputPassword);

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
