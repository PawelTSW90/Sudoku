import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonKeyListener implements KeyListener {
    ButtonInteract interact;

    ButtonKeyListener(ButtonInteract interact) {
        this.interact = interact;

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
    //if key pressed is a number, input allowed
    @Override
    public void keyPressed(KeyEvent e) {
        String value = String.valueOf(e.getKeyChar());


        if (checkInput(value)) {
            interact.button.setLabel(value);
            String buttonName = interact.button.getName();
            interact.button.setName(buttonName+"N");
            interact.button.setBackground(null);
            ButtonInteract.setIsBoardButtonHighlighted(false);
            interact.button.setFocusable(false);

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
