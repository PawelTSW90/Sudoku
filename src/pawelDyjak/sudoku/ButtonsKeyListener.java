package pawelDyjak.sudoku;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonsKeyListener implements KeyListener {
    private final Button button;
    private final ButtonInteract interact;
    private final SoundClass sound = new SoundClass();
    private final BoardChecker boardChecker;
    private final SudokuBoard sudokuBoard;


    ButtonsKeyListener(SudokuBoard sudokuBoard, Button button, ButtonInteract buttonInteract, BoardChecker boardChecker) {
        this.button = button;
        this.interact = buttonInteract;
        this.boardChecker = boardChecker;
        this.sudokuBoard = sudokuBoard;


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
                button.setBackground(new Color(245, 232, 211));
                button.setFocusable(false);
            }
        } else {

            if (checkInput(value)) {
                sound.tick(sudokuBoard);
                if (Integer.parseInt(value) != 0) {
                    button.setLabel(value);
                    button.setBackground(null);
                    button.setFocusable(false);
                    if (sudokuBoard.isHelpOn()) {
                        boardChecker.checkIfThereAreErrors();
                    }
                    if (boardChecker.isBoardCompleted()) {
                        boardChecker.isBoardCompletedCorrectly();


                    }

                }
                //if backspace or delete has been pressed, remove current value
            } else if (code == delete || code == backspace) {
                if (!button.getLabel().equals("")) {
                    sound.erase(sudokuBoard);
                }
                interact.eraseButton();
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
