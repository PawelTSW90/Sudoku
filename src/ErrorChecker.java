
import javax.swing.*;
import java.awt.*;

public class ErrorChecker {


    public void checkIfThereAreErrors(ButtonsTemplateCreator creator, SudokuGenerator generator, SoundClass sound, TimerClass timer, Thread thread) {
        int mistakesNumber = 0;
        for (int x = 0; x < 81; x++) {

            String buttonValue = creator.getBoardButtonsTemplateList().get(x).getButton().getLabel();
            if (!buttonValue.equals(String.valueOf(generator.boardSolution[x])) && !buttonValue.equals("")) {
                creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.red);
                sound.error(creator.board);
                timer.minutes++;
                mistakesNumber++;
            } else {
                creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
            }


        }
        thread = new Thread(new ErrorLabelThread(timer.board, mistakesNumber));
        thread.start();


    }

    public void restoreButtonsColors(ButtonsTemplateCreator creator) {
        for (int x = 0; x < 81; x++) {
            creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
        }
    }

    public void setMistakeLabel(SudokuBoard board, String text) {
        ((JLabel) board.sudokuBoardPanel.getComponent(8)).setText(text);


    }


}







