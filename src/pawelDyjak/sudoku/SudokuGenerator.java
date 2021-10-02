package pawelDyjak.sudoku;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class SudokuGenerator {
    private final char[] boardStartValues = new char[81];
    private final char[] fullBoardValues = new char[81];
    private final BoardCreator boardCreator;
    private final EncryptionClass encryptionClass;

    public SudokuGenerator(BoardCreator boardCreator, EncryptionClass encryptionClass) {
        this.boardCreator = boardCreator;
        this.encryptionClass = encryptionClass;


    }

    //method generates specified number of sudoku boards, with specified empty cell numbers
    public void generateFullBoard(ButtonsTemplateCreator buttonsTemplateCreator, int emptyCellsNumbers, int numberOfBoards) {
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
                    buttonsTemplateCreator.getBoardButtonsTemplateListForSudokuGenerator().get(randomCell).setValue(String.valueOf(value));
                    buttonsTemplateCreator.getBoardButtonsTemplateListForSudokuGenerator().get(randomCell).getButton().setName("N");
                    cellNumbersList.removeIf(s -> (s == randomCell));

                }
            }

            if (boardCreator.checkBoard()) {

                if (!boardCreator.multipleSolvingChecker()) {
                    boardCreated = true;


                }

            }
            if (boardCreated) {
                generateBoardsToFile(buttonsTemplateCreator);
                boardCreator.clearBoard();
                numberOfBoardsCreated++;


            }
            boardCreator.clearBoard();

        }

    }

    //method saves generated sudoku board to string
    public void generateBoardsToFile(ButtonsTemplateCreator buttonsTemplateCreator) {
        StringBuilder tmpContainer = new StringBuilder();
        String boardsContainer;

        for (int x = 0; x < buttonsTemplateCreator.getBoardButtonsTemplateListForSudokuGenerator().size(); x++) {
            if (buttonsTemplateCreator.getBoardButtonsTemplateListForSudokuGenerator().get(x).getButton().getName().contains("N")) {
                tmpContainer.append(buttonsTemplateCreator.getBoardButtonsTemplateListForSudokuGenerator().get(x).getValue());
            } else {
                tmpContainer.append(0);

            }
        }
        tmpContainer.append("*");


        for (int x = 0; x < boardCreator.getCreatedBoardSolution().length; x++) {
            tmpContainer.append(boardCreator.getCreatedBoardSolution()[x]);

        }
        tmpContainer.append("\r\n");
        boardsContainer = tmpContainer.toString();
        encryptBoardToFile(boardsContainer);

    }

    //method encrypt sudoku board from string to file
    public void encryptBoardToFile(String boardToEncrypt) {
        String pathString = MainMenu.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        pathString = pathString.substring(0, pathString.lastIndexOf("/") + 1);
        pathString = pathString + "encrypted_list.brd";
        pathString = pathString.substring(3);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathString, true));
            writer.write(Objects.requireNonNull(encryptionClass.encrypt("123", boardToEncrypt)));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //method picks random board from file and change it to string
    public String pickBoardToDecryptAndDisplay() {
        String pathString = MainMenu.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        pathString = pathString.substring(0, pathString.lastIndexOf("/") + 1);
        pathString = pathString + "encrypted_list.brd";
        pathString = pathString.substring(3);
        Random random = new Random();
        String boardToDisplay = null;
        StringBuilder boardAfterDelete = new StringBuilder();
        int randomLine = random.nextInt(countBoards()) + 1;
        try {

            Scanner scanner = new Scanner(new File(pathString));
            for (int x = 1; x <= countBoards(); x++) {


                if (x == randomLine) {
                    boardToDisplay = scanner.nextLine();


                } else {
                    boardAfterDelete.append(scanner.nextLine());
                    boardAfterDelete.append("\n");

                }

            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathString));
            bufferedWriter.write(boardAfterDelete.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boardToDisplay;
    }

    //method decrypts board from string
    public String decryptBoardForDisplay(String boardToDecrypt) {
        String decryptedBoard;
        decryptedBoard = encryptionClass.decrypt("123", boardToDecrypt);
        return decryptedBoard;
    }

    //method displays board from string
    public void displayBoard(ButtonsTemplateCreator buttonsTemplateCreator) {

        String boardValues = decryptBoardForDisplay(pickBoardToDecryptAndDisplay());
        StringBuilder builder = new StringBuilder(boardValues);
        for (int x = 1; x < 82; x++) {
            builder.getChars(x - 1, x, boardStartValues, x - 1);

        }

        for (int x = 83; x < 164; x++) {
            builder.getChars(x - 1, x, fullBoardValues, x - 83);
        }

        for (int x = 0; x < 81; x++) {
            if (String.valueOf(boardStartValues[x]).equals("0")) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
                continue;

            } else
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(boardStartValues[x]));


        }
        boardCreator.setCurrentBoardSolution(fullBoardValues);


    }

    //method resets button templates if user start sudoku board from beginning
    public void resetBoard(ButtonsTemplateCreator buttonsTemplateCreator) {
        for (int x = 0; x < 81; x++) {
            if (String.valueOf(boardStartValues[x]).equals("0")) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
                continue;

            } else
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(boardStartValues[x]));


        }

    }

    //method returns number of random boards ready to display
    public int countBoards() {
        String pathString = MainMenu.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        pathString = pathString.substring(0, pathString.lastIndexOf("/") + 1);
        pathString = pathString + "encrypted_list.brd";
        pathString = pathString.substring(3);
        int lines = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathString));

            while (bufferedReader.readLine() != null) {
                lines++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


}
