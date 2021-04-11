package pawelDyjak.sudoku;

public class SudokuGeneratorThread extends Thread {
    SudokuBoard sudokuBoard;

    public SudokuGeneratorThread(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;


    }

    public void run() {
        generateSudokuBoards();
    }

    public void generateSudokuBoards() {
        sudokuBoard.getSudokuGenerator().generateFullBoard(sudokuBoard.getButtonsTemplateCreator(), 49, 5);
    }

}
