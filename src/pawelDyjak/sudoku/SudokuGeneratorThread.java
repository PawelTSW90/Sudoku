package pawelDyjak.sudoku;

public class SudokuGeneratorThread extends Thread {
    private final SudokuBoard sudokuBoard;

    public SudokuGeneratorThread(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public void run() {
        generateSudokuBoard();
    }

    public void generateSudokuBoard() {
        if (sudokuBoard.getSudokuGenerator().countBoards() == 0) {
            sudokuBoard.getSudokuBoardPanel().getComponent(10).setVisible(true);
            sudokuBoard.getSudokuGenerator().generateFullBoard(sudokuBoard.getButtonsTemplateCreator(), 49, 1);
            sudokuBoard.getSudokuBoardPanel().getComponent(10).setVisible(false);
            sudokuBoard.getSudokuBoardPanel().getComponent(4).setEnabled(true);
            sudokuBoard.disableBackground(1);
            sudokuBoard.getSudokuGenerator().displayBoard(sudokuBoard.getButtonsTemplateCreator());
            sudokuBoard.getTimerClass().resumeThread();

        }
        while (sudokuBoard.getSudokuGenerator().countBoards() < 100) {
            sudokuBoard.getSudokuGenerator().generateFullBoard(sudokuBoard.getButtonsTemplateCreator(), 49, 1);
        }

    }


}
