package pawelDyjak.sudoku;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SudokuGenerator {
    char[] notSolvedValues = new char[81];
    char[] solvedValues = new char[81];
    BoardCreator boardCreator = new BoardCreator(this);
    StringBuilder tmpContainer = new StringBuilder();
    BoardChecker boardChecker;

    public SudokuGenerator(BoardChecker boardChecker){
        this.boardChecker = boardChecker;
    }


    public boolean generateFullBoard(ButtonsTemplateCreator buttonsTemplateCreator) {

        boolean boardCreated = false;
        Random randomCellValue = new Random();
        List<Integer> cellNumbersList = new ArrayList<>();
        for (int x = 0; x < 81; x++) {
            cellNumbersList.add(x);
        }
        while (cellNumbersList.size() > 49) {
            int value = randomCellValue.nextInt(9 - 1 + 1) + 1;
            int randomCell = cellNumbersList.get(randomCellValue.nextInt(cellNumbersList.size()));
            if (boardCreator.isNumberAllowed(randomCell, value, buttonsTemplateCreator)) {
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(randomCell).setValue(String.valueOf(value));
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(randomCell).getButton().setName("N");
                cellNumbersList.removeIf(s -> (s == randomCell));

            }
        }

        if (boardCreator.checkBoard(buttonsTemplateCreator, boardChecker)) {
            if (!boardCreator.multipleSolvingChecker(buttonsTemplateCreator)) {
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


            return true;
        }
        boardCreator.clearBoard(buttonsTemplateCreator);
        return false;
    }

    public String generateBoardsToFile(ButtonsTemplateCreator buttonsTemplateCreator) {
        try {



            String path = File.separator + "BoardsList.nr " + ".brd";
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
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

    public void displayBoard(ButtonsTemplateCreator buttonsTemplateCreator){

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
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
            continue;

           }else
               buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
           buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(notSolvedValues[x]));


       }
       boardChecker.setBoardSolution(solvedValues);
       boardChecker.setCurrentBoard(notSolvedValues);



    }

    public void resetBoard(ButtonsTemplateCreator buttonsTemplateCreator){
        for(int x = 0; x<81; x++){
            if(String.valueOf(boardChecker.getCurrentBoard()[x]).equals("0")){
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel("");
                continue;

            }else
                buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setBackground(new Color(225, 199, 149));
            buttonsTemplateCreator.getBoardButtonsTemplateList().get(x).getButton().setLabel(String.valueOf(boardChecker.getCurrentBoard()[x]));


        }

    }






}
