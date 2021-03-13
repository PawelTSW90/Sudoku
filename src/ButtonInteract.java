import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    ErrorChecker error = new ErrorChecker();
    SoundClass sound;
    Button button;
    ButtonsTemplateCreator creator;
    SudokuGenerator generator;
    SudokuBoard board;


    public ButtonInteract(Button button, ButtonsTemplateCreator creator, SudokuGenerator generator, SoundClass sound, SudokuBoard board) {
        this.button = button;
        this.creator = creator;
        this.generator = generator;
        this.sound = sound;
        this.board = board;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getName().contains("Keypad")) {
            keypadButtonAction(sound);
        } else
            boardButtonAction(sound);
    }

    //method responsible for interacting with board buttons by clicking
    public void boardButtonAction(SoundClass sound) {
        //interaction with not editable buttons not allowed
        if (button.getBackground().equals(new Color(225, 199, 149))) {

        //if erase function is active, erase clicked button
        } else if (board.getIsErase()) {
                if(!button.getLabel().equals("")){
                    sound.erase(board);
                }
            button.setLabel("");

            button.setBackground(null);
            button.setFocusable(false);
            board.disableBackground(1);


        } else

            //if keypad button is active, set it's value to clicked board button and set both buttons as no-active
            if (isKeypadButtonHighlighted() && !isBoardButtonHighlighted()) {

                button.setLabel(creator.getButtonValueHolder());
                for (int y = 0; y < 9; y++) {
                    if (creator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                        creator.getKeypadButtonsTemplateList().get(y).getButton().setFocusable(false);
                        creator.getKeypadButtonsTemplateList().get(y).getButton().setBackground(new Color(245,232,211));
                        if (board.isHelpOn()) {
                            error.checkIfThereAreErrors(creator, generator, sound);
                        }


                    }
                }
                sound.tick(board);
                if (generator.isBoardCompleted(creator)) {
                    if (generator.isBoardCompletedCorrectly(creator, board)) {

                    }
                }

                //if none of the buttons are active, set clicked board button as active
            } else if (!isBoardButtonHighlighted()) {
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                button.addKeyListener(new ButtonKeyListener(button, this, creator));


            } else {
                //if clicked button is active, set it as no-active

                if (button.isFocusable()) {
                    button.setBackground(null);
                    button.setFocusable(false);

                } else {

                    // if other board button is active, set it as no-active and set clicked button as active
                    for (int x = 0; x < 81; x++) {
                        if (creator.getBoardButtonsTemplateList().get(x).getButton().isFocusable()) {
                            creator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                            creator.getBoardButtonsTemplateList().get(x).getButton().setBackground(null);
                        }
                    }
                    button.setBackground(Color.getHSBColor(80, 80, 80));
                    button.setFocusable(true);
                    button.requestFocus();
                    button.addKeyListener(new ButtonKeyListener(button, this, creator));
                }
            }
    }

    //method responsible for keypad buttons
    public void keypadButtonAction(SoundClass sound) {

        //if none of the buttons are active, set clicked button as active and save it's value
        if (!isBoardButtonHighlighted() && !isKeypadButtonHighlighted()) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            creator.setButtonValueHolder(button.getLabel());
            button.requestFocus();
            button.addKeyListener(new ButtonKeyListener(button, this, creator));



            //if clicked button is active, set it as no-active
        } else if (isKeypadButtonHighlighted()) {
            if (button.isFocusable()) {
                button.setBackground(new Color(245,232,211));
                button.setFocusable(false);

                //if other keypad button is active, set it as no-active and set clicked button as active and save its value
            } else {
                for (int y = 0; y < 9; y++) {
                    if (creator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                        creator.getKeypadButtonsTemplateList().get(y).getButton().setFocusable(false);
                        creator.getKeypadButtonsTemplateList().get(y).getButton().setBackground(new Color(245,232,211));
                    }

                }
                creator.setButtonValueHolder(button.getLabel());
                button.addKeyListener(new ButtonKeyListener(button, this, creator));
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();

            }
            //if board button is active, set its value to clicked keypad button value, and set it as no-active
        } else {
            creator.setButtonValueHolder(button.getLabel());

            for (int x = 0; x < 81; x++) {
                if (creator.getBoardButtonsTemplateList().get(x).getButton().isFocusable()) {
                    creator.getBoardButtonsTemplateList().get(x).getButton().setLabel(creator.getButtonValueHolder());
                    creator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                    creator.getBoardButtonsTemplateList().get(x).getButton().setBackground(null);
                    if (board.isHelpOn()) {
                        error.checkIfThereAreErrors(creator, generator, sound);
                    }

                }

            }
            sound.tick(board);
            if (generator.isBoardCompleted(creator)) {
                if (generator.isBoardCompletedCorrectly(creator, board)) {

                }
            }

        }
    }

    //method is checking if any board button is active
    public boolean isBoardButtonHighlighted() {

        for (int x = 0; x < 81; x++) {
            if (creator.getBoardButtonsTemplateList().get(x).getButton().isFocusable()) {
                return true;


            }


        }
        return false;

    }

    //method is checking if any keypad button is active
    public boolean isKeypadButtonHighlighted() {
        for (int y = 0; y < 9; y++) {
            if (creator.getKeypadButtonsTemplateList().get(y).getButton().isFocusable()) {
                return true;


            }

        }
        return false;
    }

}




