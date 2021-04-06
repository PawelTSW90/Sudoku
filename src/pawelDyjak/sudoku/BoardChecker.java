package pawelDyjak.sudoku;

import java.awt.*;

public class BoardChecker {
    private char[] boardSolution = new char[81];
    private char[] currentBoard = new char[81];
    private final SudokuBoard sudokuBoard;
    private final ButtonsTemplateCreator buttonsTemplateCreator;
    private final SoundClass soundClass;
    private final TimerClass timerClass;
    private Thread errorLabelThread;

    public BoardChecker(SudokuBoard sudokuBoard, ButtonsTemplateCreator buttonsTemplateCreator, SoundClass soundClass, TimerClass timerClass, Thread errorLabelThread){
        this.sudokuBoard = sudokuBoard;
        this.buttonsTemplateCreator = buttonsTemplateCreator;
        this.soundClass = soundClass;
        this.timerClass = timerClass;
        this.errorLabelThread = errorLabelThread;
    }

        //method checks if there are wrong numbers entered
    public void checkIfThereAreErrors() {

        int mistakesNumber = 0;
        for (int x = 0; x < 81; x++) {
            String buttonValue = buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getLabel();
            if (!buttonValue.equals(String.valueOf(boardSolution[x])) && !buttonValue.equals("")) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.red);
                soundClass.error(sudokuBoard);
                timerClass.setMinutes(timerClass.getMinutes()+1);
                if (timerClass.getMinutes() >= 60) {
                    timerClass.setHours(timerClass.getHours()+1);
                    timerClass.setMinutes(timerClass.getMinutes()-60);
                }
                mistakesNumber++;
            } else {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
            }


        }
        errorLabelThread = new Thread(new ErrorLabelThread(getSudokuBoard(), mistakesNumber));
        errorLabelThread.start();


    }
        //method restores wrong number color to default, when error detecting is turned off
    public void restoreButtonsColors() {
        for (int x = 0; x < 81; x++) {
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.black);
        }
    }
        //method checks if board is full
    public boolean isBoardCompleted() {
        for (int x = 0; x < 81; x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals("")) {
                return false;

            }
        }
        sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(false);
        return true;

    }
        //method checks if board is completed correctly and displays proper message
    public boolean isBoardCompletedCorrectly() {

        //message when board is completed wrong
        for (int x = 0; x < 81; x++) {
            if (!buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals(String.valueOf(boardSolution[x]))) {
                soundClass.boardCompletedWrong(getSudokuBoard());
                sudokuBoard.disableBackground(0);
                sudokuBoard.getTimerClass().pauseThread();
                sudokuBoard.getSudokuBoardPanel().getComponent(1).setVisible(true);
                sudokuBoard.getSudokuBoardPanel().setFocusable(false);

                return false;
            }

        }
        //message when board is completed correct
        soundClass.boardCompletedCorrectly(getSudokuBoard());
        sudokuBoard.getTimerClass().pauseThread();
        sudokuBoard.getMainFrame().add(sudokuBoard.getBoardCompletedJPanel().boardCompletedMessage());
        sudokuBoard.getBoardCompletedJPanel().setUserNameLabel();
        sudokuBoard.getMainFrame().getContentPane().getComponent(1).setVisible(false);
        //show cursor in user name text field automatically
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

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

}







