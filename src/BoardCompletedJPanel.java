import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
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
        JLabel boardCompleted = new JLabel();
        boardCompleted.setBounds(screenWidth / 2 - 550 / 2, screenHeight / 2 - 1100 / 2, 550, 150);
        boardCompleted.setFont(new Font(null, Font.ITALIC, 80));
        boardCompleted.setText("WELL DONE!!!");
        this.boardCompleted.add(typeNamePanel());
        this.boardCompleted.add(boardCompleted);
        this.boardCompleted.add(time());
        this.boardCompleted.add(highScore());
        this.boardCompleted.add(newGameButton());
        this.boardCompleted.add(exitButton());
        this.boardCompleted.add(background());
        this.boardCompleted.setVisible(true);


        return this.boardCompleted;
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
        namePanel.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.PINK));
        namePanel.setVisible(false);
        namePanel.setLayout(new GridLayout(2, 0));
        namePanel.setBounds(screenWidth / 2 - 600 / 2 + 600, screenHeight / 2 - 500 / 2, 600, 400);
        namePanel.setBackground(new Color(245, 232, 211));
        JLabel position = new JLabel("<html>Your result is " + playerPlace + " on the list!<br/>Type your name!</html>");
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
                //limit name entry to 15 characters
                if (textField.getText().length() >= 15)
                    e.consume();
                //only letters and digits allowed
                char key = e.getKeyChar();
                if (!Character.isAlphabetic(key) && !Character.isDigit(key)) {
                    e.consume();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                //action when enter is pressed
                if (key == 10) {
                    playerName = textField.getText();
                    if (playerPlace <= 10) {
                        highScoresCreator.writeScore(playerName, board.timeCounter.toString(), playerPlace, highScoresCreator.updateResult(playerPlace));

                    }
                    boardCompleted.remove(6);
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


        return namePanel;
    }

    public JLabel highScore() {
        int labelNr = 1;
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBounds(screenWidth / 2 - 1200 / 2, 300, 800, 700);
        for (int x = 0; x < 10; x++) {
            JLabel label = new JLabel(labelNr + "..........");
            setFont(label);
            setText(label, labelNr - 1);
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

    public void setUserNameLabel() {
        playerPlace = highScoresCreator.checkUserTime(board);
        Component enterName = boardCompleted.getComponent(0);

        if (playerPlace <= 10) {
            enterName.setVisible(true);
        }

    }

    public JButton newGameButton() {
        JButton newGame = new JButton("MAIN MENU");
        setButtons(newGame);
        newGame.setFont(new Font(null, Font.PLAIN, 80));
        newGame.setBounds(screenWidth / 2 + 700 / 2, screenHeight / 2, 500, 115);
        newGame.addMouseListener(new MouseListenerClass(board) {
            @Override
            public void mouseEntered(MouseEvent e) {
                newGame.setForeground(new Color(80, 50, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGame.setForeground(Color.BLACK);
            }


        });

        newGame.addActionListener(e -> {
            board.mainFrame.getContentPane().getComponent(2).setVisible(false);
            board.mainFrame.getContentPane().getComponent(0).setVisible(true);
        });

        return newGame;

    }

    public JButton exitButton() {
        JButton exit = new JButton("EXIT");
        setButtons(exit);
        exit.setFont(new Font(null, Font.PLAIN, 80));
        exit.setBounds(screenWidth / 2 + 1000 / 2, screenHeight / 2 + (115 * 2), 220, 115);
        exit.addMouseListener(new MouseListenerClass(board) {
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
                board.sound.tick(board);
                System.exit(1);

            }
        });
        return exit;


    }

    public void setButtons(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

    }


}
