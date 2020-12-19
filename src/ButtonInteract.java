import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    private static boolean isBoardButtonHighlighted = false;
    private static boolean isKeypadButtonHighlighted = false;
    private static String valueToInput;
    Button button;

    public ButtonInteract(Button button) {
        this.button = button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getName().contains("Keypad")) {
            keypadHighlightButton();
        } else

            boardHighLightButton();

    }

    //method highlighting/un-highlighting or adding number to board button
    public void boardHighLightButton() {
        if (isKeypadButtonHighlighted && !isBoardButtonHighlighted) {
            button.setLabel(valueToInput);
            for (int y = 0; y < 9; y++) {
                if (Buttons.keyboardButtons.get(y).isFocusable()) {
                    Buttons.keyboardButtons.get(y).setFocusable(false);
                    Buttons.keyboardButtons.get(y).setBackground(null);

                }

            }
            isKeypadButtonHighlighted = false;


        } else if (!isBoardButtonHighlighted) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            button.requestFocus();
            isBoardButtonHighlighted = true;
        } else {

            if (button.isFocusable()) {
                button.setBackground(null);
                isBoardButtonHighlighted = false;
                button.setFocusable(false);

            } else {


                for (int x = 0; x < 81; x++) {
                    if (Buttons.boardButtons.get(x).isFocusable()) {
                        Buttons.boardButtons.get(x).setFocusable(false);
                        Buttons.boardButtons.get(x).setBackground(null);

                    }


                }
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                isBoardButtonHighlighted = true;


            }
        }


    }

    //method highlighting/un-highlighting keypad button
    public void keypadHighlightButton() {
        //both buttons group inactive
        if (!isBoardButtonHighlighted && !isKeypadButtonHighlighted) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            valueToInput = button.getLabel();
            button.requestFocus();
            isKeypadButtonHighlighted = true;
            //keypad button active
        } else if (isKeypadButtonHighlighted) {
            if (button.isFocusable()) {
                button.setBackground(null);
                isKeypadButtonHighlighted = false;
                button.setFocusable(false);

            } else {
                for (int y = 0; y < 9; y++) {
                    if (Buttons.keyboardButtons.get(y).isFocusable()) {
                        Buttons.keyboardButtons.get(y).setFocusable(false);
                        Buttons.keyboardButtons.get(y).setBackground(null);

                    }

                }
                valueToInput = button.getLabel();
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                isKeypadButtonHighlighted = true;
            }
            //board button active
        } else {
            for (int y = 0; y < 9; y++) {
                if (Buttons.keyboardButtons.get(y).isFocusable()) {
                    Buttons.keyboardButtons.get(y).setFocusable(false);
                    Buttons.keyboardButtons.get(y).setBackground(null);

                }

            }
            valueToInput = button.getLabel();
            isKeypadButtonHighlighted = false;


            for (int x = 0; x < 81; x++) {
                if (Buttons.boardButtons.get(x).isFocusable()) {
                    Buttons.boardButtons.get(x).setLabel(valueToInput);
                    Buttons.boardButtons.get(x).setFocusable(false);
                    Buttons.boardButtons.get(x).setBackground(null);

                }
                isBoardButtonHighlighted = false;


            }


            isKeypadButtonHighlighted = false;

        }

    }
}




