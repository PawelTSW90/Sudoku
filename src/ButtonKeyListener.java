import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonKeyListener implements KeyListener {
    ButtonInteract interact;
    BacktrackingChecker checker = new BacktrackingChecker(null);

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
            for (int x = 0; x < 81; x++) {
                String boardButtonLocation = interact.button.getName();
                String square = String.valueOf(ButtonsTemplateCreator.buttonsValues.get(x).getSquare());
                String column = String.valueOf(ButtonsTemplateCreator.buttonsValues.get(x).getColumn());
                String row = String.valueOf(ButtonsTemplateCreator.buttonsValues.get(x).getRow());
                String buttonsValuesLocation = "S" + square + " " + "C" + column + " " + "R" + row;
                if (boardButtonLocation.equals(buttonsValuesLocation)) {
                    ButtonsTemplateCreator.buttonsValues.get(x).setName("N");
                    ButtonsTemplateCreator.buttonsValues.get(x).setValue(value);
                    checker.displayCells();
                }
            }


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
