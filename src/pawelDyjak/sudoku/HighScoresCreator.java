package pawelDyjak.sudoku;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HighScoresCreator {
    SudokuBoard board;

    public HighScoresCreator(SudokuBoard board){
        this.board = board;
    }

    //method prepares HighScores list to enter new result
    public StringBuilder updateResult(int position) {
        Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
        String lineValue;
        StringBuilder[] scoreTableArray = new StringBuilder[10];
        StringBuilder scoreTable = new StringBuilder();
        for (int x = 0; x < scoreTableArray.length; x++) {
            scoreTableArray[x] = new StringBuilder();
        }
        try {
            List<String> lines = Files.readAllLines(path);
            if (position <= 9) {

                for (int x = 9; x >=position-1; x--) {
                    if(x == 9){
                        lineValue = lines.get(x).substring(0, 4);
                        scoreTableArray[x].append(lineValue);
                    } else {
                        lineValue = lines.get(x).substring(0, 3);
                        scoreTableArray[x].append(lineValue);
                    }
                    if (x > 0)
                        lineValue = lines.get(x - 1).substring(3);
                    scoreTableArray[x].append(lineValue);
                }

                for (int x = 0; x<position-1; x++){
                    scoreTableArray[x].append(lines.get(x));
                }


                for (int x = 0; x < scoreTableArray.length; x++) {
                    scoreTable.append(scoreTableArray[x]);
                    scoreTable.append("\n");

                }
                //if result is bigger than tenth, preparing list is not required
            } else {
                for (int x = 0; x < lines.size(); x++) {
                    scoreTableArray[x].append(lines.get(x));
                    scoreTable.append(scoreTableArray[x]);
                    scoreTable.append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return scoreTable;

    }


    //method saves user result into high score board
    public void writeScore(String name, String time, int position, StringBuilder scoreList) {
        int positionForEntryStart = 0;
        int positionForEntryEnd = 0;
        try {

            for (int x = 0; x < scoreList.length(); x++) {
                if (position == 10) {
                    if (String.valueOf(scoreList.charAt(x)).equals(("0")) && String.valueOf(scoreList.charAt(x + 1)).equals(".")) {
                        positionForEntryStart = x + 3;
                        break;

                    }
                } else {


                    if (String.valueOf(scoreList.charAt(x)).equals(String.valueOf(position)) && String.valueOf(scoreList.charAt(x + 1)).equals(".")) {
                        positionForEntryStart = x + 3;
                        break;

                    }
                }
            }
            if (position == 9) {
                for (int y = positionForEntryStart; y < scoreList.length(); y++) {
                    if (String.valueOf(scoreList.charAt(y)).equals(String.valueOf(0)) && String.valueOf(scoreList.charAt(y + 1)).equals(".")) {
                        positionForEntryEnd = y - 1;
                        break;

                    }
                }
            } else if (position == 10) {
                positionForEntryEnd = scoreList.length();

            } else {
                for (int y = positionForEntryStart; y < scoreList.length(); y++) {
                    if (String.valueOf(scoreList.charAt(y)).equals(String.valueOf(position + 1)) && String.valueOf(scoreList.charAt(y + 1)).equals(".")) {
                        positionForEntryEnd = y;
                        break;

                    }
                }
            }
            scoreList.replace(positionForEntryStart, positionForEntryEnd - 1, name + "  " + "*" + time);
            BufferedWriter writer = new BufferedWriter(new FileWriter("HighScores.brd", false));
            writer.write(scoreList.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method compares player time with other results, and returns player position
    public int checkUserTime(SudokuBoard board) {
        StringBuilder highScoresString = new StringBuilder();
        StringBuilder singleHighScoresTime;
        String userTime = board.getTimeCounter().toString();
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

    //method returns line on highScores board corresponding with players position
    public String lineReturn(int line) {
        String lineToChange = null;
        Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
        try {
            List<String> lines = Files.readAllLines(path);
            lineToChange = lines.get(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineToChange;


    }
    public JLabel highScore() {
        int labelNr = 1;
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBounds(UtilityClass.getScreenWidth() / 2 - 1200 / 2, 300, 800, 700);
        for (int x = 0; x < 10; x++) {
            JLabel label = new JLabel(labelNr + "..........");
            switch (labelNr) {
                case 1 -> label.setForeground(new Color(218, 165, 32));
                case 2 -> label.setForeground(new Color(169, 169, 169));
                case 3 -> label.setForeground(new Color(102, 51, 0));
            }
            label.setFont(new Font(null, Font.ITALIC, 50));
            setText(label, labelNr - 1);
            panel.add(label);
            labelNr++;

        }
        return panel;
    }
    public void setText(JLabel label, int line) {
        String text = lineReturn(line);
        text = text.replace("*", "");
        label.setText(text);

    }




}
