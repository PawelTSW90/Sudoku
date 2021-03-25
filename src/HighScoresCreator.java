import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HighScoresCreator {


    public void updateResult(int position){
        /*Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
        String lineValue;
        try {
            List<String> lines = Files.readAllLines(path);
            List<String> updatedList = new ArrayList<>();
            List<StringBuilder>linesTmp = new ArrayList<>();
            if(position<=9){
                lines.set(9, "");
                System.out.println(lines.get(9).length());
                lineValue = lines.get(9).substring(3);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    //method is saving user result into high score board
    public void writeScore(String name, String time, int position) {
        StringBuilder highScoresString = new StringBuilder();
        int positionForEntryStart = 0;
        int positionForEntryEnd = 0;
        try {
            Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                highScoresString.append(line);
                highScoresString.append("\n");
            }
            for (int x = 0; x < highScoresString.length(); x++) {
                if (position == 10) {
                    if (String.valueOf(highScoresString.charAt(x)).equals(("0")) && String.valueOf(highScoresString.charAt(x + 1)).equals(".")) {
                        positionForEntryStart = x + 3;
                        break;

                    }
                } else {


                    if (String.valueOf(highScoresString.charAt(x)).equals(String.valueOf(position)) && String.valueOf(highScoresString.charAt(x + 1)).equals(".")) {
                        positionForEntryStart = x + 3;
                        break;

                    }
                }
            }
            if(position ==9){
                for (int y = positionForEntryStart; y < highScoresString.length(); y++) {
                    if (String.valueOf(highScoresString.charAt(y)).equals(String.valueOf(0))&&String.valueOf(highScoresString.charAt(y+1)).equals(".")) {
                        positionForEntryEnd = y-1;
                        break;

                    }
                }
            }else if(position ==10){
                positionForEntryEnd = highScoresString.length();



            }



            else {
                for (int y = positionForEntryStart; y < highScoresString.length(); y++) {
                    if (String.valueOf(highScoresString.charAt(y)).equals(String.valueOf(position + 1)) && String.valueOf(highScoresString.charAt(y + 1)).equals(".")) {
                        positionForEntryEnd = y;
                        break;

                    }
                }
            }
            highScoresString.replace(positionForEntryStart, positionForEntryEnd - 1, name + "  " + "*" + time);
            System.out.println(highScoresString);
            BufferedWriter writer = new BufferedWriter(new FileWriter("HighScores.brd", false));
            writer.write(highScoresString.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method compares user time, with high scores
    public int checkUserTime(SudokuBoard board) {
        StringBuilder highScoresString = new StringBuilder();
        StringBuilder singleHighScoresTime;
        String userTime = board.timeCounter.toString();
        int userTimeInt;
        int[] highScores = new int[10];
        int place = 0;
        int scoreIndexStart;
        int resultPlace = 11;
        Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                highScoresString.append(line);
                highScoresString.append("\n");
            }
            for (int x = 0; x < highScoresString.length(); x++) {

                if (String.valueOf(highScoresString.charAt(x)).equals("*")) {
                    singleHighScoresTime = new StringBuilder();
                    scoreIndexStart = x + 1;
                    String hours = (highScoresString.substring(scoreIndexStart, scoreIndexStart + 2));
                    String minutes = (highScoresString.substring(scoreIndexStart + 3, scoreIndexStart + 5));
                    String seconds = (highScoresString.substring(scoreIndexStart + 6, scoreIndexStart + 8));
                    singleHighScoresTime.append(hours).append(minutes).append(seconds);
                    highScores[place] = Integer.parseInt(singleHighScoresTime.toString());
                    place++;
                }

            }
            singleHighScoresTime = new StringBuilder();
            String hours = userTime.substring(0, 2);
            String minutes = userTime.substring(3, 5);
            String seconds = userTime.substring(6, 8);
            singleHighScoresTime.append(hours).append(minutes).append(seconds);
            userTimeInt = Integer.parseInt(singleHighScoresTime.toString());

            for (int highScore : highScores) {
                if (userTimeInt < highScore) {
                    resultPlace--;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultPlace;
    }

    public String lineReturn(int line) {
        String lineToChange = null;
        Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
        try {
            List<String> lines = Files.readAllLines(path);
                lineToChange=lines.get(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineToChange;


    }


}
