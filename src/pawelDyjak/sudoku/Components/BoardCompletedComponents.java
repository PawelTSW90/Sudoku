package pawelDyjak.sudoku.Components;

import pawelDyjak.sudoku.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BoardCompletedComponents {
    private final SudokuBoard sudokuBoard;
    private final BoardCompletedJPanel boardCompletedJPanel;

    public BoardCompletedComponents(SudokuBoard sudokuBoard, BoardCompletedJPanel boardCompletedJPanel) {
        this.sudokuBoard = sudokuBoard;
        this.boardCompletedJPanel = boardCompletedJPanel;
    }

    //method draws exit button for board completed panel
    public JButton drawExitButton() {
        JButton exit = new JButton("EXIT");
        exit.setVisible(false);
        UtilityClass.buttonConfigure(exit);
        exit.setFont(new Font(null, Font.PLAIN, 80));
        exit.setBounds(UtilityClass.getScreenWidth() / 2 + 1000 / 2, UtilityClass.getScreenHeight() / 2 + (115 * 2), 220, 115);
        exit.addMouseListener(new MouseListenerClass(sudokuBoard) {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setForeground(new Color(80, 50, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setForeground(Color.BLACK);
            }

            @Override

            public void mousePressed(MouseEvent e) {
                sudokuBoard.getSoundClass().tick(sudokuBoard);
                System.exit(1);

            }
        });
        return exit;


    }

    //method draws main menu button for board completed panel
    public JButton drawMainMenuButton() {
        JButton mainMenu = new JButton("MAIN MENU");
        mainMenu.setVisible(false);
        UtilityClass.buttonConfigure(mainMenu);
        mainMenu.setFont(new Font(null, Font.PLAIN, 80));
        mainMenu.setBounds(UtilityClass.getScreenWidth() / 2 + 700 / 2, UtilityClass.getScreenHeight() / 2, 500, 115);
        mainMenu.addMouseListener(new MouseListenerClass(sudokuBoard) {
            @Override
            public void mouseEntered(MouseEvent e) {
                mainMenu.setForeground(new Color(80, 50, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainMenu.setForeground(Color.BLACK);
            }


        });

        mainMenu.addActionListener(e -> {
            sudokuBoard.getSoundClass().tick(sudokuBoard);
            sudokuBoard.getMainFrame().getContentPane().getComponent(2).setVisible(false);
            sudokuBoard.getMainFrame().getContentPane().getComponent(0).setVisible(true);
        });

        return mainMenu;

    }

    //method draws type name panel for board completed panel
    public JPanel drawTypeNamePanel() {
        boardCompletedJPanel.setPlayerPlace(sudokuBoard.getHighScoresCreator().checkUserTime(sudokuBoard));
        JPanel namePanel = new JPanel();
        namePanel.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.PINK));
        namePanel.setVisible(false);
        namePanel.setLayout(new GridLayout(2, 0));
        namePanel.setBounds(UtilityClass.getScreenWidth() / 2 - 600 / 2 + 600, UtilityClass.getScreenHeight() / 2 - 500 / 2, 600, 400);
        namePanel.setBackground(new Color(245, 232, 211));
        JLabel position = new JLabel("<html>Your result is " + boardCompletedJPanel.getPlayerPlace() + " on the list!<br/>Type your name!</html>");
        position.setFont(new Font(null, Font.ITALIC, 50));
        namePanel.add(position);
        JTextField textField = new JTextField();
        textField.setBackground(new Color(245, 232, 211));
        textField.setFont(new Font(null, Font.ITALIC, 50));
        textField.setBorder(null);
        textField.requestFocusInWindow();
        namePanel.add(textField);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                //only letters, space and digits allowed
                if (!Character.isAlphabetic(key) && !Character.isDigit(key)) {
                    e.consume();
                }
                //limit name entry to 15 characters
                if (textField.getText().length() >= 15)
                    e.consume();

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                //when enter is pressed, hide board completed components, display updated high scores board
                if (key == 10) {
                    boardCompletedJPanel.setPlayerName(textField.getText());
                    sudokuBoard.getHighScoresCreator().writeScore(boardCompletedJPanel.getPlayerName(), sudokuBoard.getTimeCounter().toString(), boardCompletedJPanel.getPlayerPlace(), sudokuBoard.getHighScoresCreator().updateResult(boardCompletedJPanel.getPlayerPlace()));
                    boardCompletedJPanel.getBoardCompleted().getComponent(4).setVisible(true);
                    boardCompletedJPanel.getBoardCompleted().getComponent(5).setVisible(true);
                    boardCompletedJPanel.getBoardCompleted().remove(6);
                    boardCompletedJPanel.getBoardCompleted().remove(3);
                    boardCompletedJPanel.getBoardCompleted().add(sudokuBoard.getBoardCompletedComponents().highScore());
                    boardCompletedJPanel.getBoardCompleted().add(drawBackground());
                    namePanel.setVisible(false);

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        return namePanel;
    }

    //method draws time label for board completed panel
    public JLabel drawTime() {
        JLabel timeLabel = new JLabel("Total Time:" + "      " + sudokuBoard.getTimeCounter());
        timeLabel.setBounds(UtilityClass.getScreenWidth() / 2 - 1000 / 2, UtilityClass.getScreenHeight() / 2 - 1100 / 2, 1000, 400);
        timeLabel.setFont(new Font(null, Font.ITALIC, 80));
        return timeLabel;
    }

    //method draws background label for board completed panel
    public JLabel drawBackground() {
        JLabel background = new JLabel();
        background.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        background.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));
        return background;
    }

    //method draws high score label for board completed panel
    public JLabel highScore() {
        int labelNr = 1;
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBounds(UtilityClass.getScreenWidth() / 2 - 800 / 2, 300, 800, 700);
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

    //method returns
    public void setText(JLabel label, int line) {
        String text = lineReturn(line);
        text = text.replace("*", "");
        label.setText(text);

    }

    //method returns line on highScores board corresponding with players position
    public String lineReturn(int line) {
        String lineToChange = null;
        Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\high_scores.brd");
        try {
            List<String> lines = Files.readAllLines(path);
            lineToChange = lines.get(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineToChange;


    }
}
