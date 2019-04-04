package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * GUI creation class.
 * Creates a message window for the program.
 */
public class MessageFrame extends JFrame {
    /**
     * The OK button in the frame.
     */
    private JButton okButton = new JButton("OK");

    private JLabel messageLabel = new JLabel();
    /**
     * The input field for the studentID.
     */

        public MessageFrame(){
            this.setTitle("Message");
            this.setSize(270, 215);
            JPanel panel = new JPanel();
            panel.add(messageLabel);
            JPanel panel2 = new JPanel();
            panel2.add(okButton);
            this.add(panel, BorderLayout.CENTER);
            this.add(panel2, BorderLayout.SOUTH);
        }

    public JButton getOkButton() {
        return okButton;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public void addListeners(ActionListener listener){
            okButton.addActionListener(listener);
    }
}
