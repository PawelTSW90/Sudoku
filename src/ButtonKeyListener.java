import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonKeyListener implements KeyListener {
    Button button;
    ButtonInteract interact;
    BoardChecker checker;


    ButtonKeyListener(Button button, ButtonInteract interact, BoardChecker checker) {
        this.button = button;
        this.interact = interact;
        this.checker = checker;


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

        if (checkInput(value)) {
            if (Integer.parseInt(value) != 0) {
                button.setLabel(value);
                button.setName("N");
                button.setBackground(null);
                button.setFocusable(false);

            }
        //if backspace or delete has been pressed, remove current value
        } else if(code == delete || code == backspace){

            button.setLabel("");
            button.setBackground(null);
            button.setFocusable(false);
        } else if(code == esc){


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
