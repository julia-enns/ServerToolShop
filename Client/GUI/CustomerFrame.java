package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerFrame extends JFrame{

    /**
     * Quit button
     */
    private JButton quitButton = new JButton("Quit");
    /**
     * List All button
     */
    private JButton browseButton = new JButton("Browse Tools");
    /**
     * Tool Name button
     */
    private JButton buyButton = new JButton("Select a Tool to Buy");

    /**
     * List shown when List All Tool button is pressed
     */
    private DefaultListModel<String> toolList;
    /**
     * Area in which the list of tools is shown
     */
    private JList<String> listArea;
    /**
     * Scroll pane that allows the user to scroll through the list of tools
     */
    private JScrollPane listScrollPane;

    /**
     * Constructs the main GUI window.
     */
    public CustomerFrame() {
        this.setTitle("Customer Window");
        this.setSize(650, 450);
        this.add(createUpperPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.WEST);
    }

    /**
     * Creates upper panel with a label describing the program.
     * @return the upper panel.
     */
    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel();
        JLabel label = new JLabel("Tool Shop");
        upperPanel.add(label);
        return upperPanel;
    }

    /**
     * Creates the center panel with required list.
     * @return the center panel.
     */
    private JScrollPane createCenterPanel() {
        toolList = new DefaultListModel<>();
        listArea = new JList<>(toolList);
        String width = "1234567890123456789012345678901234567890";
        listArea.setPrototypeCellValue(width);
        listArea.setFont(new Font("Courier New", Font.BOLD, 12));
        listArea.setVisibleRowCount(20);
        listScrollPane = new JScrollPane(listArea);
        return listScrollPane;
    }


    /**
     * Creates the a panel with required buttons.
     * @return the button panel.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 100, 10));
        buttonPanel.add(browseButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(quitButton);
        return buttonPanel;
    }

    /**
     * Adds all the listeners to each button
     * @param listener the listener being added to the buttons
     */
    public void addAllListeners(ActionListener listener){
        browseButton.addActionListener(listener);
        buyButton.addActionListener(listener);
        quitButton.addActionListener(listener);
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getBuyButton() {
        return buyButton;
    }


    public JButton getQuitButton() {
        return quitButton;
    }

    public DefaultListModel<String> getToolList() {
        return toolList;
    }
}
