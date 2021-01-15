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
            keypadButtonAction();
        } else

            boardButtonAction();

    }

    //method responsible for board buttons
    public void boardButtonAction() {

        //if keypad button is active, set it's value to clicked board button and set keypad button as no-active
        if (isKeypadButtonHighlighted && !isBoardButtonHighlighted) {
            button.setLabel(valueToInput);
            for (int y = 0; y < 9; y++) {
                if (Buttons.keypadButtons.get(y).isFocusable()) {
                    Buttons.keypadButtons.get(y).setFocusable(false);
                    Buttons.keypadButtons.get(y).setBackground(null);

                }

            }
            isKeypadButtonHighlighted = false;

        //if none of the buttons are active, set clicked button as active
        } else if (!isBoardButtonHighlighted) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            button.requestFocus();
            button.addKeyListener(new ButtonKeyListener(this));
            isBoardButtonHighlighted = true;
        } else {
            //if clicked button is active, set it as no-active

            if (button.isFocusable()) {
                button.setBackground(null);
                isBoardButtonHighlighted = false;
                button.setFocusable(false);

            } else {

                // if other board button is active, set it to no-active and set clicked button as active
                for (int x = 0; x < 81; x++) {
                    if (Buttons.boardButtons.get(x).isFocusable()) {
                        Buttons.boardButtons.get(x).setFocusable(false);
                        Buttons.boardButtons.get(x).setBackground(null);

                    }


                }
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                button.addKeyListener(new ButtonKeyListener(this));
                isBoardButtonHighlighted = true;


            }
        }


    }

    //method responsible for keypad buttons
    public void keypadButtonAction() {
        //if none of the buttons are active, set clicked button as active and save it's value
        if (!isBoardButtonHighlighted && !isKeypadButtonHighlighted) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            valueToInput = button.getLabel();
            button.requestFocus();
            isKeypadButtonHighlighted = true;
            //if clicked button is active, set it as no-active
        } else if (isKeypadButtonHighlighted) {
            if (button.isFocusable()) {
                button.setBackground(null);
                isKeypadButtonHighlighted = false;
                button.setFocusable(false);
            //if other keypad button is active, set it as no-active and set clicked button as active and save its value
            } else {
                for (int y = 0; y < 9; y++) {
                    if (Buttons.keypadButtons.get(y).isFocusable()) {
                        Buttons.keypadButtons.get(y).setFocusable(false);
                        Buttons.keypadButtons.get(y).setBackground(null);

                    }

                }
                valueToInput = button.getLabel();
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                isKeypadButtonHighlighted = true;
            }
            //if board button is active, set its value to clicked keypad button value, and set it as no-active
        } else {

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




