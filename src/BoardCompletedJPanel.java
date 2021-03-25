import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.regex.Pattern;


public class BoardCompletedJPanel {
    SudokuBoard board;
    HighScoresCreator highScoresCreator;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    JPanel boardCompleted = new JPanel();
    String playerName;
    int playerPlace;

    public BoardCompletedJPanel(SudokuBoard board, HighScoresCreator highScoresCreator) {
        this.board = board;
        this.highScoresCreator = highScoresCreator;
    }


    public JPanel boardCompletedMessage() {
        boardCompleted.setLayout(null);
        boardCompleted.setFocusable(true);
        JLabel congratulations = new JLabel();
        congratulations.setBounds(screenWidth / 2 - 550 / 2, screenHeight / 2 - 1100 / 2, 550, 150);
        congratulations.setFont(new Font(null, Font.ITALIC, 80));
        congratulations.setText("WELL DONE!!!");
        boardCompleted.add(typeNamePanel());
        boardCompleted.add(congratulations);
        boardCompleted.add(time());
        boardCompleted.add(highScore());
        boardCompleted.add(background());
        boardCompleted.setVisible(true);


        return boardCompleted;
    }

    public JLabel background() {
        JLabel background = new JLabel();
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));
        return background;
    }


    public JLabel time() {
        JLabel timeLabel = new JLabel("Total Time:" + "      " + board.timeCounter);
        timeLabel.setBounds(screenWidth / 2 - 1000 / 2, screenHeight / 2 - 1100 / 2, 1000, 400);
        timeLabel.setFont(new Font(null, Font.ITALIC, 80));
        return timeLabel;
    }

    public JPanel typeNamePanel() {
        playerPlace = highScoresCreator.checkUserTime(board);
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(2, 0));
        namePanel.setBounds(screenWidth / 2 - 600 / 2, screenHeight / 2 - 500 / 2, 600, 400);
        namePanel.setBackground(new Color(245, 232, 211));
        JLabel position = new JLabel("<html>Your result is " + playerPlace + " on the list!<br/>Type your name!</html>");
        position.setFont(new Font(null, Font.ITALIC, 50));
        namePanel.add(position);
        JTextField textField = new JTextField();
        textField.setBackground(new Color(245, 232, 211));
        textField.setFont(new Font(null, Font.ITALIC, 50));
        textField.setBorder(null);
        textField.requestFocusInWindow();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textField.getText().length() >= 15)  // limit to 3 characters
                    e.consume();

            }
        });
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == 10) {
                    playerName = textField.getText();
                    System.out.println(playerPlace);
                    if(playerPlace <=10) {
                        highScoresCreator.writeScore(playerName, board.timeCounter.toString(), playerPlace);
                    }
                    highScoresCreator.updateResult(8);
                    boardCompleted.remove(4);
                    boardCompleted.remove(3);
                    boardCompleted.add(highScore());
                    boardCompleted.add(background());
                    namePanel.setVisible(false);

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        namePanel.add(textField);


        return namePanel;
    }

    public JLabel highScore() {
        int labelNr = 1;
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBounds(screenWidth / 2 - 1200 / 2, 300, 800, 700);
        for(int x = 0; x<10; x++){
            JLabel label = new JLabel(labelNr+"..........");
            setFont(label);
            setText(label, labelNr-1);
            panel.add(label);
            labelNr++;

        }
        highScoresCreator.checkUserTime(board);
        return panel;
    }

    public void setFont(JLabel label) {
        label.setFont(new Font(null, Font.ITALIC, 50));
    }

    public void setText(JLabel label, int line) {
        String text = highScoresCreator.lineReturn(line);
        text = text.replace("*", "");
        label.setText(text);

    }


}
