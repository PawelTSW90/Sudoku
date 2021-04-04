package pawelDyjak.sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    BoardChecker boardChecker;
    SoundClass soundClass;
    Button button;
    ButtonsTemplateCreator buttonsTemplateCreator;
    SudokuGenerator sudokuGenerator;
    SudokuBoard sudokuBoard;


    public ButtonInteract(ButtonsTemplateCreator buttonsTemplateCreator, SudokuGenerator sudokuGenerator, SoundClass sound, SudokuBoard sudokuBoard, BoardChecker boardChecker) {
        this.buttonsTemplateCreator = buttonsTemplateCreator;
        this.sudokuGenerator = sudokuGenerator;
        this.soundClass = sound;
        this.sudokuBoard = sudokuBoard;
        this.boardChecker = boardChecker;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        findButtonByHashCode(e);
        if (button.getName().contains("Keypad")) {
            keypadButtonAction(soundClass);
        } else
            boardButtonAction(soundClass);

    }

    //method responsible for interacting with board buttons by clicking
    public void boardButtonAction(SoundClass sound) {
        //interaction with not editable buttons not allowed
        if (button.getBackground().equals(new Color(225, 199, 149))) {

            //if erase function is active, erase clicked button
        } else if (sudokuBoard.isEraseOn()) {
            if (!button.getLabel().equals("")) {
                sound.erase(sudokuBoard);
            }
            button.setLabel("");

            button.setBackground(null);
            button.setFocusable(false);
            sudokuBoard.disableBackground(1);


        } else

            //if keypad button is active, set it's value to clicked board button and set both buttons as no-active
            if (isKeypadButtonHighlighted() && !isBoardButtonHighlighted()) {

                button.setLabel(buttonsTemplateCreator.getButtonValueHolder());
                for (int y = 0; y < 9; y++) {
                    if (buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                        buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().setFocusable(false);
                        buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().setBackground(new Color(245, 232, 211));
                        if (sudokuBoard.isHelpOn()) {
                            boardChecker.checkIfThereAreErrors(buttonsTemplateCreator, sound, sudokuBoard.getTimerClass(), sudokuBoard.getErrorLabelThread());
                        }


                    }
                }
                sound.tick(sudokuBoard);
                if (boardChecker.isBoardCompleted(buttonsTemplateCreator.sudokuBoard, buttonsTemplateCreator)) {
                    if (boardChecker.isBoardCompletedCorrectly(buttonsTemplateCreator, sudokuBoard, sound)) {

                    }
                }

                //if none of the buttons are active, set clicked board button as active
            } else if (!isBoardButtonHighlighted()) {
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                button.addKeyListener(new ButtonKeyListener(button, this, buttonsTemplateCreator, boardChecker));


            } else {
                //if clicked button is active, set it as no-active

                if (button.isFocusable()) {
                    button.setBackground(null);
                    button.setFocusable(false);

                } else {

                    // if other board button is active, set it as no-active and set clicked button as active
                    for (int x = 0; x < 81; x++) {
                        if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().isFocusable()) {
                            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(null);
                        }
                    }
                    button.setBackground(Color.getHSBColor(80, 80, 80));
                    button.setFocusable(true);
                    button.requestFocus();
                    button.addKeyListener(new ButtonKeyListener(button, this, buttonsTemplateCreator, boardChecker));
                }
            }
    }

    //method responsible for keypad buttons
    public void keypadButtonAction(SoundClass sound) {

        //if none of the buttons are active, set clicked button as active and save it's value
        if (!isBoardButtonHighlighted() && !isKeypadButtonHighlighted()) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            buttonsTemplateCreator.setButtonValueHolder(button.getLabel());
            button.requestFocus();
            button.addKeyListener(new ButtonKeyListener(button, this, buttonsTemplateCreator, boardChecker));


            //if clicked button is active, set it as no-active
        } else if (isKeypadButtonHighlighted()) {
            if (button.isFocusable()) {
                button.setBackground(new Color(245, 232, 211));
                button.setFocusable(false);

                //if other keypad button is active, set it as no-active and set clicked button as active and save its value
            } else {
                for (int y = 0; y < 9; y++) {
                    if (buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                        buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().setFocusable(false);
                        buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().setBackground(new Color(245, 232, 211));
                    }

                }
                buttonsTemplateCreator.setButtonValueHolder(button.getLabel());
                button.addKeyListener(new ButtonKeyListener(button, this, buttonsTemplateCreator, boardChecker));
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();

            }
            //if board button is active, set its value to clicked keypad button value, and set it as no-active
        } else {
            buttonsTemplateCreator.setButtonValueHolder(button.getLabel());

            for (int x = 0; x < 81; x++) {
                if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().isFocusable()) {
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(buttonsTemplateCreator.getButtonValueHolder());
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(null);
                    if (sudokuBoard.isHelpOn()) {
                        boardChecker.checkIfThereAreErrors(buttonsTemplateCreator, sound, sudokuBoard.getTimerClass(), sudokuBoard.getErrorLabelThread());
                    }

                }

            }
            sound.tick(sudokuBoard);
            if (boardChecker.isBoardCompleted(sudokuBoard, buttonsTemplateCreator)) {
                if (boardChecker.isBoardCompletedCorrectly(buttonsTemplateCreator, sudokuBoard, sound)) {


                }
            }

        }
    }

    //method is checking if any board button is active
    public boolean isBoardButtonHighlighted() {

        for (int x = 0; x < 81; x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().isFocusable()) {
                return true;


            }


        }
        return false;

    }

    //method is checking if any keypad button is active
    public boolean isKeypadButtonHighlighted() {
        for (int y = 0; y < 9; y++) {
            if (buttonsTemplateCreator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                return true;


            }

        }
        return false;
    }

    public void eraseButton() {
        soundClass.erase(sudokuBoard);
        button.setLabel("");
        button.setBackground(null);
        button.setFocusable(false);
    }

    public void findButtonByHashCode(ActionEvent e) {
        int hashCode = e.getSource().hashCode();
        for (int x = 0; x < buttonsTemplateCreator.getBoardButtonsTemplateList().size(); x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().hashCode() == hashCode) {
                button = buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton();
            }
        }
    }


}




