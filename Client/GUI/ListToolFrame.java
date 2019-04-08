package Client.GUI;

public class ListToolFrame {
    /**
     * The frame created when check quantity button is pressed
     */
    private ToolGetFrame quantityFrame;
    /**
     * The frame created when the search name button is pressed
     */
    private ToolGetFrame nameFrame;
    /**
     * The frame created when the buy tool button is pressed
     */
    private ToolGetFrame buyFrame;
    /**
     * The frame created when the search ID button is pressed
     */
    private ToolGetFrame idFrame;
    /**
     * The frame created to login to Tool Shop
     */
    private UserFrame loginFrame;

    /**
     * Constructs an object of type ListToolFrame
     */
    public ListToolFrame() {
        idFrame = new ToolGetFrame("ID");
        nameFrame = new ToolGetFrame("NAME");
        buyFrame = new ToolGetFrame("NAME");
        quantityFrame = new ToolGetFrame("NAME");
        loginFrame = new UserFrame();
    }

    public ToolGetFrame getBuyFrame() {
        return buyFrame;
    }

    public ToolGetFrame getIdFrame() {
        return idFrame;
    }

    public ToolGetFrame getNameFrame() {
        return nameFrame;
    }

    public ToolGetFrame getQuantityFrame() {
        return quantityFrame;
    }

    public UserFrame getLoginFrame() {
        return loginFrame;
    }
}
