import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    BoardChecker checker = new BoardChecker();
    StringBuilder tmpContainer = new StringBuilder();

    public boolean generateFullBoard(ButtonsTemplateCreator creator, int randomValue) {

        boolean boardCreated = false;
        Random randomCellValue = new Random();
        List<Integer> cellNumbersList = new ArrayList<>();
        for (int x = 0; x < 81; x++) {
            cellNumbersList.add(x);
        }
        while (cellNumbersList.size() > 49) {
            int value = randomCellValue.nextInt(9 - 1 + 1) + 1;
            int randomCell = cellNumbersList.get(randomCellValue.nextInt(cellNumbersList.size()));
            if (checker.isNumberAllowed(randomCell, value, creator)) {
                creator.getBoardButtonsTemplateList().get(randomCell).setValue(String.valueOf(value));
                creator.getBoardButtonsTemplateList().get(randomCell).getButton().setName("N");
                //creator.getBoardButtonsTemplateList().get(randomCell).getButton().setLabel(String.valueOf(value));
                cellNumbersList.removeIf(s -> (s == randomCell));

            }
        }

        if (checker.checkBoard(creator)) {
            if (!checker.multipleSolvingChecker(creator)) {
                boardCreated = true;

            }

        }
        if (boardCreated) {
            for (int x = 0; x < 81; x++) {
                if (!creator.getBoardButtonsTemplateList().get(x).getValue().equals("")) {
                    creator.getBoardButtonsTemplateList().get(x).getButton().setBackground(Color.lightGray);
                    creator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                }
            }

            generateBoardsToFile(creator, randomValue);


            return true;
        }
        checker.clearBoard(creator);
        return false;
    }

    public String generateBoardsToFile(ButtonsTemplateCreator creator, int randomValue) {
        try {



            String path = File.separator + "mnt" + File.separator + "BoardsList.nr " + randomValue +".brd";
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (int x = 0; x < creator.getBoardButtonsTemplateList().size(); x++) {
                if (creator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                    writer.write(creator.getBoardButtonsTemplateList().get(x).getValue());
                } else {
                    writer.write("0");

                }
            }
            writer.write("*");
            for (int x = 0; x < BoardChecker.boardSolution.length; x++) {
                writer.write(BoardChecker.boardSolution[x]);

            }
            writer.write("\r\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < creator.getBoardButtonsTemplateList().size(); x++) {
            if (creator.getBoardButtonsTemplateList().get(x).getButton().getName().contains("N")) {
                tmpContainer.append(creator.getBoardButtonsTemplateList().get(x).getValue());
            } else {
                tmpContainer.append(0);

            }
        }
        tmpContainer.append("*");


        for (int x = 0; x < BoardChecker.boardSolution.length; x++) {
            tmpContainer.append(BoardChecker.boardSolution[x]);

        }
        tmpContainer.append("\r\n");
        String boardsContainer;
        boardsContainer = tmpContainer.toString();
        return boardsContainer;


    }

    public String getTmp(){
        String tmp;
        tmp = tmpContainer.toString();
        return tmp;
    }


}
