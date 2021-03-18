import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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
        JLabel position = new JLabel("<html>Your result is " +playerPlace+" on the list!<br/>Type your name!</html>");
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
                if (textField.getText().length() >= 15) // limit to 3 characters
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
                if(key ==10){
                    playerName = textField.getText();
                    System.out.println(playerPlace);
                    highScoresCreator.writeScore(playerName, board.timeCounter.toString(),playerPlace);

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
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBounds(screenWidth / 2 - 500 / 2, 300, 500, 700);
        JLabel firstName = new JLabel("1..........");
        JLabel secondName = new JLabel("2..........");
        JLabel thirdName = new JLabel("3..........");
        JLabel fourthName = new JLabel("4..........");
        JLabel fifthName = new JLabel("5..........");
        JLabel sixthName = new JLabel("6..........");
        JLabel seventhName = new JLabel("7..........");
        JLabel eightName = new JLabel("8..........");
        JLabel ninthName = new JLabel("9..........");
        JLabel tenthName = new JLabel("10..........");
        panel.add(firstName);
        panel.add(secondName);
        panel.add(thirdName);
        panel.add(fourthName);
        panel.add(fifthName);
        panel.add(sixthName);
        panel.add(seventhName);
        panel.add(eightName);
        panel.add(ninthName);
        panel.add(tenthName);
        setFont(firstName);
        setText(firstName, 0);
        setText(secondName, 1);
        setText(thirdName, 2);
        setText(fourthName, 3);
        setText(fifthName, 4);
        setText(sixthName, 5);
        setText(seventhName, 6);
        setText(eightName, 7);
        setText(ninthName, 8);
        setText(tenthName, 9);
        setFont(secondName);
        setFont(thirdName);
        setFont(fourthName);
        setFont(fifthName);
        setFont(sixthName);
        setFont(seventhName);
        setFont(eightName);
        setFont(ninthName);
        setFont(tenthName);
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
