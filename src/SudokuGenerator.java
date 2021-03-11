import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SudokuGenerator {
    SoundClass sound = new SoundClass();
    BoardCreator checker = new BoardCreator();
    StringBuilder tmpContainer = new StringBuilder();
    char[] boardSolution = new char[81];
    char[] currentBoard = new char[81];

    public boolean generateFullBoard(ButtonsTemplateCreator creator) {

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

            generateBoardsToFile(creator);


            return true;
        }
        checker.clearBoard(creator);
        return false;
    }

    public String generateBoardsToFile(ButtonsTemplateCreator creator) {
        try {



            String path = File.separator + "BoardsList.nr " + ".brd";
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
            for (int x = 0; x < BoardCreator.boardSolution.length; x++) {
                writer.write(BoardCreator.boardSolution[x]);

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


        for (int x = 0; x < BoardCreator.boardSolution.length; x++) {
            tmpContainer.append(BoardCreator.boardSolution[x]);

        }
        tmpContainer.append("\r\n");
        String boardsContainer;
        boardsContainer = tmpContainer.toString();
        return boardsContainer;


    }

    public String stringCreator(){
        String boardsContainer = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("BoardsList.brd"));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null){
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
                line  = reader.readLine();
            }
            boardsContainer = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return boardsContainer;
    }



    public void writeToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted_file.brd"));
            writer.write(EncryptionClass.encrypt("123", stringCreator()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


//method returns random board line from decrypted String
    public String decryptToString(){
        Random random = new Random();
        int randomLine = random.nextInt(100-2+2)+2;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted_list.brd"));
            writer.write(EncryptionClass.decrypt("123",EncryptionClass.encrypt("123", stringCreator())));
            Scanner scanner = new Scanner(new File("decrypted_list.brd"));
            for(int x = 1; x<101; x++){


                if(x == randomLine){
                    return scanner.next();

                } else{
                    scanner.nextLine();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }



return null;
    }

    public void displayBoard(ButtonsTemplateCreator creator){
        char[] notSolvedValues = new char[81];
        char[] solvedValues = new char[81];
       String boardValues = decryptToString();
       StringBuilder builder = new StringBuilder(boardValues);
       for(int x = 1; x<82; x++) {
           builder.getChars(x-1, x, notSolvedValues, x-1);

       }

       for(int x = 83; x<164; x++){
           builder.getChars(x-1, x, solvedValues, x-83);
       }

       for(int x = 0; x<81; x++){
           if(String.valueOf(notSolvedValues[x]).equals("0")){
            creator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
            continue;

           }else
               creator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
           creator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(notSolvedValues[x]));


       }
        boardSolution = solvedValues;
       currentBoard = notSolvedValues;



    }

    public void resetBoard(ButtonsTemplateCreator creator){
        for(int x = 0; x<81; x++){
            if(String.valueOf(currentBoard[x]).equals("0")){
                creator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
                continue;

            }else
                creator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
            creator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(currentBoard[x]));


        }

    }

    public boolean isBoardCompleted(ButtonsTemplateCreator creator){

        for (int x = 0; x < 81; x++) {
            if (creator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals("")) {
                return false;

            }
        }
        return true;

    }

    public boolean isBoardCompletedCorrectly(ButtonsTemplateCreator creator, SudokuBoard board){
        for(int x = 0; x<81; x++){
            if(!creator.getBoardButtonsTemplateList().get(x).getButton().getLabel().equals(String.valueOf(boardSolution[x]))){
                sound.boardCompletedWrong(board);
                board.disableBackground(0);
                board.time.pauseThread();
                board.sudokuBoardPanel.getComponent(1).setVisible(true);
                board.sudokuBoardPanel.setFocusable(false);

                return false;
            }

        }
        sound.boardCompletedCorrectly(board);
        return true;
    }


}
