import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class for the main GUI window in the program.
 */

public class MainFrame extends JFrame {

    private JButton listButton = new JButton("List All");

    private JButton toolNameButton = new JButton("Tool Name");

    private JButton toolIDButton = new JButton("Tool ID");

    private JButton quantityButton = new JButton("Quantity");

    private JButton printOrderButton = new JButton("Print Order");

    private JButton buyButton = new JButton("Buy Item");

    private DefaultListModel<String> toolList;

    private JList<String> listArea;

    private JScrollPane listScrollPane;

    /**
     * Constructs the main gui window.
     */

    public MainFrame() {
        this.setTitle("Main Window");
        this.setSize(700, 400);
        this.add(createButtonPanel(), BorderLayout.WEST);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createUpperPanel(), BorderLayout.NORTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    /**
     * Creates the a panel with required buttons.
     *
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
        return buttonPanel;
    }

    /**
     * Creates the center panel with required list.
     *
     * @return the center panel.
     */

    private JScrollPane createCenterPanel() {
        toolList = new DefaultListModel<String>();
        listArea = new JList<String>(toolList);
        String width = "1234567890123456789012345678901234567890";
        listArea.setPrototypeCellValue(width);
        listArea.setFont(new Font("Courier New", Font.BOLD, 12));
        listArea.setVisibleRowCount(20);
        listScrollPane = new JScrollPane(listArea);
        return listScrollPane;
    }

    /**
     * Creates upper panel with a label describing the program.
     *
     * @return the upper panel.
     */
    private JPanel createUpperPanel() {
        JPanel upperPanel = new JPanel();
        JLabel label = new JLabel("Tool Shop Main Menu");
        upperPanel.add(label);
        return upperPanel;
    }

    /**
     * Conncets a listener to the list button
     *
     * @param listen listener to connect.
     */
    public void addListListener(ActionListener listen) {
        listButton.addActionListener(listen);
    }

    /**
     * Conncets a listener to the toolName button
     *
     * @param listen listener to connect.
     */
    public void addToolNameListener(ActionListener listen) {
        toolNameButton.addActionListener(listen);
    }

    /**
     * Conncets a listener to the toolID button
     *
     * @param listen listener to connect.
     */
    public void addToolIDListener(ActionListener listen) {
        toolIDButton.addActionListener(listen);
    }

    /**
     * Conncets a listener to the quantity button
     *
     * @param listen listener to connect.
     */
    public void addQuantityListener(ActionListener listen) {
        quantityButton.addActionListener(listen);
    }

    public void addOrderListener(ActionListener listen) {
        printOrderButton.addActionListener(listen);
    }

    public JButton getListButton() {
        return listButton;
    }

    public JButton getPrintOrderButton() {
        return printOrderButton;
    }

    public JButton getQuantityButton() {
        return quantityButton;
    }

    public JButton getToolIDButton() {
        return toolIDButton;
    }

    public JButton getToolNameButton() {
        return toolNameButton;
    }

    public DefaultListModel<String> getToolList() {
        return toolList;
    }
}
