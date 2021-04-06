package pawelDyjak.sudoku;

import java.awt.*;

public class BoardChecker {
    private char[] boardSolution = new char[81];
    private char[] currentBoard = new char[81];


    public void checkIfThereAreErrors(ButtonsTemplateCreator buttonsTemplateCreator, SoundClass soundClass, TimerClass timerClass, Thread thread) {

        int mistakesNumber = 0;
        for (int x = 0; x < 81; x++) {
            String buttonValue = buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getLabel();
            if (!buttonValue.equals(String.valueOf(boardSolution[x])) && !buttonValue.equals("")) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.red);
                soundClass.error(buttonsTemplateCreator.sudokuBoard);
                timerClass.minutes++;
                if (timerClass.minutes >= 60) {
                    timerClass.hours++;
                    timerClass.minutes = timerClass.minutes - 60;
                }
                mistakesNumber++;
            } else {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
            }


        }
        thread = new Thread(new ErrorLabelThread(timerClass.board, mistakesNumber));
        thread.start();


    }

    public void restoreButtonsColors(ButtonsTemplateCreator buttonsTemplateCreator) {
        for (int x = 0; x < 81; x++) {
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
        }
    }

    public boolean isBoardCompleted(SudokuBoard sudokuBoard, ButtonsTemplateCreator buttonsTemplateCreator) {
        for (int x = 0; x < 81; x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals("")) {
                return false;

            }
        }
        sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(false);
        return true;

    }

    public boolean isBoardCompletedCorrectly(ButtonsTemplateCreator buttonsTemplateCreator, SudokuBoard sudokuBoard, SoundClass soundClass) {
        BoardCompletedJPanel boardCompletedJPanel = new BoardCompletedJPanel(sudokuBoard, sudokuBoard.getHighScoresCreator());
        //message when board is completed wrong
        for (int x = 0; x < 81; x++) {
            if (!buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals(String.valueOf(boardSolution[x]))) {
                soundClass.boardCompletedWrong(sudokuBoard);
                sudokuBoard.disableBackground(0);
                sudokuBoard.getTimerClass().pauseThread();
                sudokuBoard.getSudokuBoardPanel().getComponent(1).setVisible(true);
                sudokuBoard.getSudokuBoardPanel().setFocusable(false);

                return false;
            }

        }
        //message when board is completed correct
        soundClass.boardCompletedCorrectly(sudokuBoard);
        sudokuBoard.getTimerClass().pauseThread();
        sudokuBoard.getMainFrame().add(boardCompletedJPanel.boardCompletedMessage());
        boardCompletedJPanel.setUserNameLabel();
        sudokuBoard.getMainFrame().getContentPane().getComponent(1).setVisible(false);
        //show cursor in text field automatically
        Component component = sudokuBoard.getMainFrame().getContentPane().getComponent(2);
        Component component1 = ((Container) component).getComponent(0);
        ((Container) component1).getComponent(1).requestFocusInWindow();
        return true;
    }

    public char[] getBoardSolution() {
        return boardSolution;
    }

    public void setBoardSolution(char[] boardSolution) {
        this.boardSolution = boardSolution;
    }

    public char[] getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(char[] currentBoard) {
        this.currentBoard = currentBoard;
    }
}







