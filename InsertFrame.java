import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class that creates the Frame that pops up after insert button is pressed in the main window.
 */
public class InsertFrame extends JFrame {
    /**
     * Text field for user input.
     */
    private JTextField id = new JTextField(5);
    /**
     * Text field for user input.
     */
    private JTextField faculty = new JTextField(5);
    /**
     * Text field for user input.
     */
    private JTextField major = new JTextField(5);
    /**
     * Text field for user input.
     */
    private  JTextField year = new JTextField(5);
    /**
     * The OK Button
     */
    private JButton okButton = new JButton("OK");
    /**
     * The Cancel Button
     */
    private JButton cancelButton  = new JButton("Cancel");

    /**
     * Constructs the frame.
     */
    public InsertFrame(){
        this.setTitle("Insert");
        this.setSize(270, 215);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
        this.add(createInsertDataPanel(), BorderLayout.CENTER);
        this.add(createUpperTextPanel(), BorderLayout.NORTH);

    }

    /**
     * Creates the bottom panel with required buttons.
     * @return the bottom button panel.
     */
    public  JPanel createButtonPanel(){
        JPanel p2 = new JPanel();
        p2.add(okButton);
        p2.add(cancelButton);
        return p2;

    }

    /**
     * Creates the upper panel.
     * @return the upper panel.
     */
    public JPanel createUpperTextPanel() {
        JPanel upperPanel = new JPanel();
        JLabel label = new JLabel("Insert a new record");
        upperPanel.add(label);
        return upperPanel;
    }

    /**
     * Creates center panel which asks for user input and holds the text fields.
     * @return center panel.
     */
    public JPanel createInsertDataPanel(){
        JPanel p3 = new JPanel();
        p3.add(new JLabel("Enter the Student ID"));
        p3.add(id);
        p3.add(new JLabel("Enter Faculty"));
        p3.add(faculty);
        p3.add(new JLabel("Enter Student's Major"));
        p3.add(major);
        p3.add(new JLabel("Enter year"));
        p3.add(year);
        return p3;
    }

    /**
     * Connects a listener to the OK and Cancel buttons.
     * @param listener listener to connect.
     */
    public void addListener(ActionListener listener){
        okButton.addActionListener(listener);
        cancelButton.addActionListener(listener);

    }

    /**
     * OK button getter.
     * @return OK Button object.
     */
    public JButton getOkButton() {
        return okButton;
    }

    /**
     * Cancel button getter.
     * @return Cancel button object.
     */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * Getter for the ID text field
     * @return ID text field object.
     */
    public JTextField getId() {
        return id;
    }

    /**
     * Getter for the major text field
     * @return major text field object.
     */
    public JTextField getMajor() {
        return major;
    }

    /**
     * Getter for the faculty text field
     * @return faculty text field object.
     */
    public JTextField getFaculty() {
        return faculty;
    }

    /**
     * Getter for the year text field
     * @return year text field object.
     */
    public JTextField getYear() {
        return year;
    }
}


