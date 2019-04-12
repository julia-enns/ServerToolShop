package Client.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class for the main GUI window in the program.
 */
public class MainFrame extends JFrame {
    /**
     * Quit button
     */
    private JButton quitButton = new JButton("Quit");
    /**
     * List All button
     */
    private JButton listButton = new JButton("List All Tools");
    /**
     * Tool Name button
     */
    private JButton toolNameButton = new JButton("Search by Tool Name");
    /**
     * Tool ID button
     */
    private JButton toolIDButton = new JButton("Search by Tool ID");
    /**
     * Quantity button
     */
    private JButton quantityButton = new JButton("Check Quantity");
    /**
     * Print Order button
     */
    private JButton printOrderButton = new JButton("Print Order");
    /**
     * Buy Item button
     */
    private JButton buyButton = new JButton("Decrease Stock");
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
    public MainFrame() {
        this.setTitle("Main Window");
        this.setSize(450, 550);
        this.add(createButtonPanel(), BorderLayout.WEST);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createUpperPanel(), BorderLayout.NORTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    /**
     * Creates the a panel with required buttons.
     * @return the button panel.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 100, 10));
        buttonPanel.add(listButton);
        buttonPanel.add(toolNameButton);
        buttonPanel.add(toolIDButton);
        buttonPanel.add(quantityButton);
        buttonPanel.add(printOrderButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(quitButton);
        return buttonPanel;
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
     * Creates upper panel with a label describing the program.
     * @return the upper panel.
     */
    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel();
        JLabel label = new JLabel("Tool Shop Main Menu");
        upperPanel.add(label);
        return upperPanel;
    }

    /**
     * Adds all the listeners to each button
     * @param listener the listener being added to the buttons
     */
    public void addAllListeners(ActionListener listener){
        listButton.addActionListener(listener);
        toolNameButton.addActionListener(listener);
        toolIDButton.addActionListener(listener);
        quantityButton.addActionListener(listener);
        printOrderButton.addActionListener(listener);
        buyButton.addActionListener(listener);
        quitButton.addActionListener(listener);
    }

    /**
     * Returns the quit button
     * @return the quit button
     */
    public JButton getQuitButton() {
        return quitButton;
    }

    /**
     * Returns the list button
     * @return list button
     */
    public JButton getListButton() {
        return listButton;
    }

    /**
     * Returns the print order button
     * @return print order button
     */
    public JButton getPrintOrderButton() {
        return printOrderButton;
    }

    /**
     * Returns the quantity button
     * @return quantity button
     */
    public JButton getQuantityButton() {
        return quantityButton;
    }

    /**
     * Returns the tool ID button
     * @return tool ID button
     */
    public JButton getToolIDButton() {
        return toolIDButton;
    }

    /**
     * Returns the tool name button
     * @return tool name button
     */
    public JButton getToolNameButton() {
        return toolNameButton;
    }

    /**
     * Returns the buy button
     * @return buy button
     */
    public JButton getBuyButton() {
        return buyButton;
    }

    /**
     * Returns the list of tools
     * @return tool list
     */
    public DefaultListModel<String> getToolList() {
        return toolList;
    }
}
