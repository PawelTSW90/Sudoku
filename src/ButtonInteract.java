import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    private static boolean isBoardButtonHighlighted = false;
    private static boolean isKeypadButtonHighlighted = false;
    private static String valueToInput;
    Button button;
    ButtonCreator creator;


    public ButtonInteract(Button button, ButtonCreator creator) {
        this.button = button;
        this.creator = creator;

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
        System.out.println(creator.getButtonsList().size());
        //if keypad button is active, set it's value to clicked board button and set keypad button as no-active
        if (isKeypadButtonHighlighted && !isBoardButtonHighlighted) {
            button.setLabel(valueToInput);
            for (int y = 0; y < 9; y++) {
                if (ButtonsTemplateCreator.keypadButtons.get(y).isFocusable()) {
                    ButtonsTemplateCreator.keypadButtons.get(y).setFocusable(false);
                    ButtonsTemplateCreator.keypadButtons.get(y).setBackground(null);

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
                    if (creator.getButtonsList().get(x).isFocusable()) {
                        creator.getButtonsList().get(x).setFocusable(false);
                        creator.getButtonsList().get(x).setBackground(null);

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
                    if (ButtonsTemplateCreator.keypadButtons.get(y).isFocusable()) {
                        ButtonsTemplateCreator.keypadButtons.get(y).setFocusable(false);
                        ButtonsTemplateCreator.keypadButtons.get(y).setBackground(null);

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
                if (creator.getButtonsList().get(x).isFocusable()) {
                    creator.getButtonsList().get(x).setLabel(valueToInput);
                    creator.getButtonsList().get(x).setFocusable(false);
                    creator.getButtonsList().get(x).setBackground(null);

                }
                isBoardButtonHighlighted = false;


            }


            isKeypadButtonHighlighted = false;

        }

    }

    public static void setIsBoardButtonHighlighted(boolean isBoardButtonHighlighted) {
        ButtonInteract.isBoardButtonHighlighted = isBoardButtonHighlighted;
    }


}




