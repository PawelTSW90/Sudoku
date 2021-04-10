package pawelDyjak.sudoku;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class SudokuGenerator {
    SudokuBoard sudokuBoard;
    char[] notSolvedValues = new char[81];
    char[] solvedValues = new char[81];

    BoardCreator boardCreator;
    BoardChecker boardChecker;
    EncryptionClass encryptionClass;

    public SudokuGenerator(SudokuBoard sudokuBoard, BoardCreator boardCreator, BoardChecker boardChecker, EncryptionClass encryptionClass) {
        this.boardCreator = boardCreator;
        this.sudokuBoard = sudokuBoard;
        this.boardChecker = boardChecker;
        this.encryptionClass = encryptionClass;


    }

    //method generates specified number of sudoku boards, with specified empty cell numbers
    public boolean generateFullBoard(ButtonsTemplateCreator buttonsTemplateCreator, int emptyCellsNumbers, int numberOfBoards) {
        int numberOfBoardsCreated = 0;
        while (numberOfBoardsCreated != numberOfBoards) {
            boolean boardCreated = false;
            Random randomCellValue = new Random();
            List<Integer> cellNumbersList = new ArrayList<>();
            for (int x = 0; x < 81; x++) {
                cellNumbersList.add(x);
            }
            //generating board with specified empty cells numbers
            while (cellNumbersList.size() > emptyCellsNumbers) {
                int value = randomCellValue.nextInt(9 - 1 + 1) + 1;
                int randomCell = cellNumbersList.get(randomCellValue.nextInt(cellNumbersList.size()));
                if (boardCreator.isNumberAllowed(randomCell, value)) {
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(randomCell).setValue(String.valueOf(value));
                    buttonsTemplateCreator.getBoardButtonsTemplateList().get(randomCell).getButton().setName("N");
                    cellNumbersList.removeIf(s -> (s == randomCell));

                }
            }

            if (boardCreator.checkBoard(boardChecker)) {

                if (!boardCreator.multipleSolvingChecker(boardChecker)) {
                    boardCreated = true;

                }

            }
            if (boardCreated) {
                for (int x = 0; x < 81; x++) {
                    if (!buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue().equals("")) {
                        buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(Color.lightGray);
                        buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                    }
                }

                generateBoardsToFile(buttonsTemplateCreator);
                boardCreator.clearBoard();
                numberOfBoardsCreated++;


            }
            boardCreator.clearBoard();

        }

        return true;
    }

    //method saves generated sudoku board to file
    public String generateBoardsToFile(ButtonsTemplateCreator buttonsTemplateCreator) {
        StringBuilder tmpContainer = new StringBuilder();
        String boardsContainer;
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("board_lists_template.brd", true));
            for (int x = 0; x < buttonsTemplateCreator.getBoardButtonsTemplateList().size(); x++) {
                if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                    writer.write(buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue());
                } else {
                    writer.write("0");

                }
            }
            writer.write("*");
            for (int x = 0; x < boardChecker.getBoardSolution().length; x++) {
                writer.write(boardChecker.getBoardSolution()[x]);

            }
            writer.write("\r\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < buttonsTemplateCreator.getBoardButtonsTemplateList().size(); x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                tmpContainer.append(buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getValue());
            } else {
                tmpContainer.append(0);

            }
        }
        tmpContainer.append("*");


        for (int x = 0; x < boardChecker.getBoardSolution().length; x++) {
            tmpContainer.append(boardChecker.getBoardSolution()[x]);

        }
        tmpContainer.append("\r\n");
        boardsContainer = tmpContainer.toString();
        encryptBoardToFile(boardsContainer);
        return boardsContainer;


    }

    //method saves sudoku boards from file into string for decryption
    public String stringCreator() {
        String boardsContainer = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("board_lists_template.brd"));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
                line = reader.readLine();
            }
            boardsContainer = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boardsContainer;
    }


    //method encrypt sudoku board to file
    public void encryptBoardToFile(String boardToEncrypt) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted_list.brd", true));
            writer.write(Objects.requireNonNull(encryptionClass.encrypt("123", boardToEncrypt)));
            writer.newLine();
            writer.close();
            writer = new BufferedWriter(new FileWriter("board_lists_template.brd"));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //method returns random board to display from encrypted sudoku boards file
    public String pickBoardToDecryptAndDisplay() {
        Random random = new Random();
        String boardToDisplay = null;
        StringBuilder boardAfterDelete = new StringBuilder();
        int randomLine = random.nextInt(countBoards()) + 1;
        try {

            Scanner scanner = new Scanner(new File("encrypted_list.brd"));
            for (int x = 1; x <= countBoards(); x++) {


                if (x == randomLine) {
                    boardToDisplay = scanner.nextLine();


                } else {
                    boardAfterDelete.append(scanner.nextLine());
                    boardAfterDelete.append("\n");

                }


            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("encrypted_list.brd"));
            bufferedWriter.write(boardAfterDelete.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return boardToDisplay;
    }

    public String decryptBoardForDisplay(String boardToDecrypt) {
        String decryptedBoard;
        decryptedBoard = encryptionClass.decrypt("123", boardToDecrypt);
        return decryptedBoard;
    }

    //method prepares board to be displayed
    public void displayBoard(ButtonsTemplateCreator buttonsTemplateCreator) {

        String boardValues = decryptBoardForDisplay(pickBoardToDecryptAndDisplay());
        StringBuilder builder = new StringBuilder(boardValues);
        for (int x = 1; x < 82; x++) {
            builder.getChars(x - 1, x, notSolvedValues, x - 1);

        }

        for (int x = 83; x < 164; x++) {
            builder.getChars(x - 1, x, solvedValues, x - 83);
        }

        for (int x = 0; x < 81; x++) {
            if (String.valueOf(notSolvedValues[x]).equals("0")) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
                continue;

            } else
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(notSolvedValues[x]));


        }
        boardChecker.setBoardSolution(solvedValues);
        boardChecker.setCurrentBoard(notSolvedValues);


    }

    //method resets button templates if user start sudoku board from beginning
    public void resetBoard(ButtonsTemplateCreator buttonsTemplateCreator) {
        for (int x = 0; x < 81; x++) {
            if (String.valueOf(boardChecker.getCurrentBoard()[x]).equals("0")) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
                continue;

            } else
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(boardChecker.getCurrentBoard()[x]));


        }

    }

    public int countBoards() {
        int lines = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("encrypted_list.brd"));

            while (bufferedReader.readLine() != null) {
                lines++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


}
