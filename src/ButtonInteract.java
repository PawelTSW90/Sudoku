import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    private static String valueToInput;
    Button button;
    ButtonsTemplateCreator creator;



    public ButtonInteract(Button button, ButtonsTemplateCreator creator) {
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

        //if keypad button is active, set it's value to clicked board button and set both buttons as no-active
        if (isKeypadButtonHighlighted() && !isBoardButtonHighlighted()) {
            button.setLabel(valueToInput);
            for (int y = 0; y < 9; y++) {
                if (creator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                    creator.getKeypadButtonsTemplateList().get(y).getButton().setFocusable(false);
                    creator.getKeypadButtonsTemplateList().get(y).getButton().setBackground(null);

                }
            }

            //if none of the buttons are active, set clicked board button as active
        } else if (!isBoardButtonHighlighted()) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            button.requestFocus();
            button.addKeyListener(new ButtonKeyListener(button));

        } else {
            //if clicked button is active, set it as no-active

            if (button.isFocusable()) {
                button.setBackground(null);
                button.setFocusable(false);

            } else {

                // if other board button is active, set it as no-active and set clicked button as active
                for (int x = 0; x < 81; x++) {
                    if (creator.getButtonsTemplateList().get(x).getButton().isFocusable()) {
                        creator.getButtonsTemplateList().get(x).getButton().setFocusable(false);
                        creator.getButtonsTemplateList().get(x).getButton().setBackground(null);
                    }
                }
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                button.addKeyListener(new ButtonKeyListener(button));
            }
        }
    }

    //method responsible for keypad buttons
    public void keypadButtonAction() {

        //if none of the buttons are active, set clicked button as active and save it's value
        if (!isBoardButtonHighlighted() && !isKeypadButtonHighlighted()) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            valueToInput = button.getLabel();
            button.requestFocus();

            //if clicked button is active, set it as no-active
        } else if (isKeypadButtonHighlighted()) {
            if (button.isFocusable()) {
                button.setBackground(null);
                button.setFocusable(false);

                //if other keypad button is active, set it as no-active and set clicked button as active and save its value
            } else {
                for (int y = 0; y < 9; y++) {
                    if (creator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                        creator.getKeypadButtonsTemplateList().get(y).getButton().setFocusable(false);
                        creator.getKeypadButtonsTemplateList().get(y).getButton().setBackground(null);
                    }

                }
                valueToInput = button.getLabel();
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();

            }
            //if board button is active, set its value to clicked keypad button value, and set it as no-active
        } else {
            valueToInput = button.getLabel();

            for (int x = 0; x < 81; x++) {
                if (creator.getButtonsTemplateList().get(x).getButton().isFocusable()) {
                    creator.getButtonsTemplateList().get(x).getButton().setLabel(valueToInput);
                    creator.getButtonsTemplateList().get(x).getButton().setFocusable(false);
                    creator.getButtonsTemplateList().get(x).getButton().setBackground(null);

                }

            }
        }
    }

        //method is checking if any board button is active
    public boolean isBoardButtonHighlighted() {
        boolean buttonHighlighted = false;
        for (int x = 0; x < 81; x++) {
            if (creator.getButtonsTemplateList().get(x).getButton().isFocusable()) {
                buttonHighlighted = true;


            }


        }
        return buttonHighlighted;

    }
        //method is checking if any keypad button is active
    public boolean isKeypadButtonHighlighted() {
        boolean buttonHighlighted = false;
        for (int y = 0; y < 9; y++) {
            if (creator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                buttonHighlighted = true;


            }

        }
        return buttonHighlighted;
    }
}




