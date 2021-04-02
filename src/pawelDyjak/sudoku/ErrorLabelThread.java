package pawelDyjak.sudoku;

import javax.swing.*;

class ErrorLabelThread extends Thread {
    int mistakesNumber;
    SudokuBoard board;

    public ErrorLabelThread(SudokuBoard board, int mistakesNumber) {
        this.board = board;
        this.mistakesNumber = mistakesNumber;
    }

    public void run() {
        setMistakeLabel(board, mistakesNumber);

    }

    public void setMistakeLabel(SudokuBoard board, int mistakesNumber) {
        String text;
        if (mistakesNumber == 0) {
            text = "No mistakes found";
        } else if (mistakesNumber > 1) {
            text = mistakesNumber + " mistakes found! " + mistakesNumber + " minutes added!";
        } else
            text = mistakesNumber + " mistake found! " + mistakesNumber + " minute added!";
        board.getSudokuBoardPanel().getComponent(8).setVisible(true);
        ((JLabel) board.getSudokuBoardPanel().getComponent(8)).setText(text);
        try {
            sleep(5000);
            board.getSudokuBoardPanel().getComponent(8).setVisible(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
