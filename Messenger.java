import Model.Item;

public class Messenger {
    private int id;
    private String inputText;
    private int inputNum;

    Messenger(int id, String text, int num, Item t){
        this.id = id;
        inputText = text;
        inputNum = num;
    }

    public int getId() {
        return id;
    }

    public int getInputNum() {
        return inputNum;
    }

    public String getInputText() {
        return inputText;
    }
}

