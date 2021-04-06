package pawelDyjak.sudoku;

import javax.swing.*;

class ErrorLabelThread extends Thread {
    private final int mistakesNumber;
    private final SudokuBoard sudokuBoard;

    public ErrorLabelThread(SudokuBoard sudokuBoard, int mistakesNumber) {
        this.sudokuBoard = sudokuBoard;
        this.mistakesNumber = mistakesNumber;
    }

    public void run() {
        setMistakeLabel(sudokuBoard, mistakesNumber);

    }
        //method sets correct format and displaying time of mistakes label
    public void setMistakeLabel(SudokuBoard sudokuBoard, int mistakesNumber) {
        String text;
        if (mistakesNumber == 0) {
            text = "No mistakes found";
        } else if (mistakesNumber > 1) {
            if(sudokuBoard.getTimerClass().getHours()>=99){
                text = mistakesNumber + " mistakes found! ";
            } else
            text = mistakesNumber + " mistakes found! " + mistakesNumber + " minutes added!";
        } else
            if(sudokuBoard.getTimerClass().getHours()>=99){
                text = mistakesNumber + " mistake found! ";
            } else {
                text = mistakesNumber + " mistake found! " + mistakesNumber + " minute added!";
            }
        sudokuBoard.getSudokuBoardPanel().getComponent(8).setVisible(true);
        ((JLabel) sudokuBoard.getSudokuBoardPanel().getComponent(8)).setText(text);
        try {
            sleep(5000);
            sudokuBoard.getSudokuBoardPanel().getComponent(8).setVisible(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
