import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonKeyListener implements KeyListener {
    Button button;
    ButtonsTemplateCreator creator;
    ButtonInteract interact;

    ButtonKeyListener(Button button, ButtonsTemplateCreator creator, ButtonInteract interact) {
        this.button = button;
        this.creator = creator;
        this.interact = interact;

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
                if(interact.isBoardCompleted()){
                    if(interact.isBoardCompletedCorrectly()){
                        System.out.println("GRATULACJE!!!!");
                    } else{
                        System.out.println("Dałeś Ciała!!!");
                    }

                }
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
