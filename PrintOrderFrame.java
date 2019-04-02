import javax.swing.*;
import java.awt.*;

public class PrintOrderFrame extends JFrame {

    /**
     * OK button
     */
    private JButton okButton = new JButton("OK");
    /**
     * Cancel button
     */
    private JButton cancelButton  = new JButton("Cancel");

    private DefaultListModel<String> orderList;

    private JList<String> listArea;

    private JScrollPane listScrollPane;


    public PrintOrderFrame(){
        this.setTitle("Orders");
        this.setSize(700, 400);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    private JScrollPane createCenterPanel() {
        orderList = new DefaultListModel<String>();
        listArea = new JList<String>(orderList);
        String width = "1234567890123456789012345678901234567890";
        listArea.setPrototypeCellValue(width);
        listArea.setFont(new Font("Courier New", Font.BOLD, 12));
        listArea.setVisibleRowCount(20);
        listScrollPane = new JScrollPane(listArea);
        return listScrollPane;
    }

    public  JPanel createButtonPanel(){
        JPanel p2 = new JPanel();
        p2.add(okButton);
        p2.add(cancelButton);
        return p2;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public DefaultListModel<String> getOrderList() {
        return orderList;
    }
}
