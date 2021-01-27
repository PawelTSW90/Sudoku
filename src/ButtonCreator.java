
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonCreator {
    int keypadButtonNumber = 1;
    private int square;
    private int column;
    private int row;
    private String name;
    private String value;
    private Button button;
    private List<Button> buttonsList = new ArrayList<>();
    static List<Button> keypadButtons = new ArrayList<>();




    public Button createBoardButtons(){
        Button button = new Button();
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFocusable(false);
        getButtonsList().add(button);
        return button;
    }

    public Button createKeypadButtons(){

        Button button = new Button(String.valueOf(keypadButtonNumber));
        button.setFocusable(false);
        button.setName("Keypad");
        button.setFont(new Font(null, Font.BOLD, 20));
        button.addActionListener(new ButtonInteract(button, null, this));
        keypadButtons.add(button);
        keypadButtonNumber++;
        return button;
    }


    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Button> getButtonsList() {
        return buttonsList;
    }

    public void setButtonsList(List<Button> buttonsList) {
        this.buttonsList = buttonsList;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
