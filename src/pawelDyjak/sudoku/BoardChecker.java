package pawelDyjak.sudoku;

import java.awt.*;

public class BoardChecker {
    private char[] boardSolution = new char[81];
    private char[] currentBoard = new char[81];


    public void checkIfThereAreErrors(ButtonsTemplateCreator creator, SoundClass sound, TimerClass timer, Thread thread) {

        int mistakesNumber = 0;
        for (int x = 0; x < 81; x++) {
            String buttonValue = creator.getBoardButtonsTemplateList().get(x).getButton().getLabel();
            if (!buttonValue.equals(String.valueOf(boardSolution[x])) && !buttonValue.equals("")) {
                creator.getBoardButtonsTemplateList().get(x).getButton().setForeground(Color.red);
                sound.error(creator.sudokuBoard);
                timer.minutes++;
                if(timer.minutes>=60){
                    timer.hours++;
                    timer.minutes=timer.minutes-60;
                }
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

    public boolean isBoardCompleted(ButtonsTemplateCreator creator) {
        for (int x = 0; x < 81; x++) {
            if (creator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals("")) {
                return false;

            }
        }

        return true;

    }

    public boolean isBoardCompletedCorrectly(ButtonsTemplateCreator creator, SudokuBoard board, SoundClass sound) {
        BoardCompletedJPanel boardCompletedJPanel = new BoardCompletedJPanel(board, board.highScoresCreator);
        //message when board is completed wrong
        for (int x = 0; x < 81; x++) {
            if (!creator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals(String.valueOf(boardSolution[x]))) {
                sound.boardCompletedWrong(board);
                board.disableBackground(0);
                board.getTimerClass().pauseThread();
                board.getSudokuBoardPanel().getComponent(1).setVisible(true);
                board.getSudokuBoardPanel().setFocusable(false);

                return false;
            }

        }
        //message when board is completed correct
        sound.boardCompletedCorrectly(board);
        board.getTimerClass().pauseThread();
        board.mainFrame.add(boardCompletedJPanel.boardCompletedMessage());
        boardCompletedJPanel.setUserNameLabel();
        board.mainFrame.getContentPane().getComponent(1).setVisible(false);
        //show cursor in text field automatically
        Component component = board.mainFrame.getContentPane().getComponent(2);
        Component component1 = ((Container) component).getComponent(0);
        ((Container) component1).getComponent(1).requestFocusInWindow();
        return true;
    }

    public char[] getBoardSolution() {
        return boardSolution;
    }

    public void setBoardSolution(char[] boardSolution){
        this.boardSolution = boardSolution;
    }

    public char[] getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(char[] currentBoard) {
        this.currentBoard = currentBoard;
    }
}







