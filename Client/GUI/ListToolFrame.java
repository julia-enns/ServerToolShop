package Client.GUI;

/**
 * Stores all the secondary frames
 */
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
     * Frame that allows user to select what type of person they are
     */
    private UserTypeFrame choiceFrame;

    /**
     * Constructs an object of type ListToolFrame
     */
    public ListToolFrame() {
        idFrame = new ToolGetFrame("ID");
        nameFrame = new ToolGetFrame("NAME");
        buyFrame = new ToolGetFrame("NAME");
        quantityFrame = new ToolGetFrame("NAME");
        loginFrame = new UserFrame();
        choiceFrame = new UserTypeFrame();
    }

    /**
     * Returns buy frame
     * @return buy frame
     */
    public ToolGetFrame getBuyFrame() {
        return buyFrame;
    }

    /**
     * Returns the ID frame
     * @return ID frame
     */
    public ToolGetFrame getIdFrame() {
        return idFrame;
    }

    /**
     * Returns the name frame
     * @return name frame
     */
    public ToolGetFrame getNameFrame() {
        return nameFrame;
    }

    /**
     * Returns the quantity frame
     * @return quantity frame
     */
    public ToolGetFrame getQuantityFrame() {
        return quantityFrame;
    }

    /**
     * Returns the login frame
     * @return login frame
     */
    public UserFrame getLoginFrame() {
        return loginFrame;
    }

    /**
     * Returns the choice frame
     * @return choice frame
     */
    public UserTypeFrame getChoiceFrame() {
        return choiceFrame;
    }
}
