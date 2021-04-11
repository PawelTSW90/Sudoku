package pawelDyjak.sudoku;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonCreator {
    private int keypadButtonNumber = 1;
    private int square;
    private int column;
    private int row;
    private String value;
    private Button button;
    private final List<Button> boardButtons = new ArrayList<>();
    private final List<Button> keypadButtons = new ArrayList<>();
    private final List<Button> boardButtonsForGenerator = new ArrayList<>();
    private final List<Button> keypadButtonsForGenerator = new ArrayList<>();


        //method is creating boardButtons
    public Button createBoardButtons(){
        Button button = new Button();
        button.setFont(new Font(null, Font.ITALIC, 30));
        button.setFocusable(false);
        boardButtons.add(button);
        return button;
    }
        //method is creating keypadButtons
    public Button createKeypadButtons(){
        Button button = new Button(String.valueOf(keypadButtonNumber));
        button.setBackground(new Color(245,232,211));
        button.setFocusable(false);
        button.setName("Keypad");
        button.setFont(new Font(null, Font.ITALIC, 30));
        keypadButtons.add(button);
        keypadButtonNumber++;
        return button;
    }

    public void createBoardButtonsForGenerator(){
        for(int x = 0; x<81; x++) {
            Button button = new Button();
            button.setFont(new Font(null, Font.ITALIC, 30));
            button.setFocusable(false);
            boardButtonsForGenerator.add(button);
        }

    }
    //method is creating keypadButtons
    public void createKeypadButtonsForGenerator(){
        for(int x = 0; x<9; x++) {
            Button button = new Button(String.valueOf(keypadButtonNumber));
            button.setBackground(new Color(245, 232, 211));
            button.setFocusable(false);
            button.setName("Keypad");
            button.setFont(new Font(null, Font.ITALIC, 30));
            keypadButtonsForGenerator.add(button);
            keypadButtonNumber++;
        }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Button> getBoardButtons() {
        return boardButtons;
    }

    public List<Button> getKeypadButtons() {
        return keypadButtons;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public List<Button> getBoardButtonsForGenerator() {
        return boardButtonsForGenerator;
    }

    public List<Button> getKeypadButtonsForGenerator() {
        return keypadButtonsForGenerator;
    }
}
