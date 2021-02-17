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

    //if key pressed is a number different than 0, input allowed
    @Override
    public void keyPressed(KeyEvent e) {

        String value = String.valueOf(e.getKeyChar());

        if (checkInput(value)) {
            if (Integer.parseInt(value) != 0) {
                button.setLabel(value);
                button.setName("N");
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
