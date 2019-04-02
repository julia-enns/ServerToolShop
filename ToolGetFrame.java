import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToolGetFrame extends JFrame {

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

    /**
     * Constructs the gui frame
     */
    public ToolGetFrame(String info) {
        this.setTitle("Read");
        this.setSize(270, 215);
        this.add(createCenterPanel(info), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Getter for the input field
     * @return the input field
     */

    public JTextField getInput() {
        return input;
    }

    /**
     * Creates the center panel for the frame
     * @return center panel
     */
    public JPanel createCenterPanel(String info){
        JPanel panel = new JPanel();
        if(info.equals("NAME")) {
            JLabel label = new JLabel("Enter the tool name:\n");
            panel.add(label);
        }
        else{
            JLabel label = new JLabel("Enter the tool ID:\n");
            panel.add(label);
        }
        panel.add(this.input);
        return panel;
    }

    /**
     * Creates the bottom panel with required buttons
     * @return the bottom button panel
     */
    public  JPanel createButtonPanel(){
        JPanel p2 = new JPanel();
        p2.add(okButton);
        p2.add(cancelButton);
        return p2;
    }

    /**
     * Connects listeners to the frames buttons
     * @param listener listener to add
     */

    public void addListener(ActionListener listener){
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






