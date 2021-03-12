import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonKeyListener implements KeyListener {
    Button button;
    ButtonInteract interact;
    ButtonsTemplateCreator creator;
    SoundClass sound = new SoundClass();
    ErrorChecker error = new ErrorChecker();


    ButtonKeyListener(Button button, ButtonInteract interact, ButtonsTemplateCreator creator) {
        this.button = button;
        this.interact = interact;
        this.creator = creator;


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //if pressed key is a number different than 0, input allowed
    @Override
    public void keyPressed(KeyEvent e) {


        int delete = 127;
        int backspace = 8;
        int esc = 27;


        String value = String.valueOf(e.getKeyChar());
        int code = e.getKeyCode();

        if (button.getName().contains("Keypad")) {
            if (code == esc) {
                button.setBackground(null);
                button.setFocusable(false);
            }
        } else {

            if (checkInput(value)) {
                if (Integer.parseInt(value) != 0) {
                    button.setLabel(value);
                    button.setBackground(null);
                    button.setFocusable(false);
                    sound.tick(interact.board);
                    if(interact.board.isHelpOn()){
                        error.checkIfThereAreErrors(creator, interact.generator, sound);
                    }
                    if (interact.generator.isBoardCompleted(creator)) {
                        interact.generator.isBoardCompletedCorrectly(creator, interact.board);


                    }

                }
                //if backspace or delete has been pressed, remove current value
            } else if (code == delete || code == backspace) {

                button.setLabel("");
                button.setBackground(null);
                button.setFocusable(false);
            } else if (code == esc) {
                button.setBackground(null);
                button.setFocusable(false);


            }


        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    //method check if keyboard input is a number
    public boolean checkInput(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
