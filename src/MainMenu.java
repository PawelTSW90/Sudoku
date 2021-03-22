import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MainMenu {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    private final JPanel mainMenuPanel = new JPanel();
    private final JFrame mainMenuFrame = new JFrame();



    public MainMenu() {
        setMainMenu();
    }

    public void setMainMenu() {
        JLabel background = new JLabel();
        JLabel title = new JLabel();
        JLabel createdBy = new JLabel();
        createdBy.setBounds(screenWidth - screenWidth / 7, screenHeight - screenHeight / 6, 355, 93);
        createdBy.setIcon(new ImageIcon("./Visuals/created_by.png"));
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));
        title.setBounds(screenWidth / 2 - 451 / 2, screenHeight / 8, 451, 198);
        title.setIcon(new ImageIcon("./Visuals/logo.png"));
        mainMenuPanel.add(title);
        mainMenuPanel.add(startButton());
        mainMenuPanel.add(exitButton());
        mainMenuPanel.add(highScoresButton());
        mainMenuPanel.add(createdBy);
        mainMenuPanel.add(background);
        mainMenuPanel.setLayout(null);
        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrame.setVisible(true);
        mainMenuPanel.setFocusable(true);


    }

    public JButton startButton() {
        JButton start = new JButton("START");
        start.setFont(new Font(null, Font.PLAIN, 80));
        start.setBounds(screenWidth / 2 - 300 / 2, screenHeight / 2-150, 300, 115);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);
        start.addActionListener(e -> {
            SudokuBoard board = new SudokuBoard(mainMenuFrame);
            if(mainMenuFrame.getContentPane().getComponents().length>1){
                mainMenuFrame.getContentPane().remove(1);
            }
            mainMenuFrame.add(board.createSudokuBoard());
            mainMenuFrame.getContentPane().getComponent(0).setVisible(false);



        });
        return start;

    }

    public JButton exitButton() {
        JButton exit = new JButton("EXIT");
        exit.setFont(new Font(null, Font.PLAIN, 80));
        exit.setBounds(screenWidth / 2 - 300 / 2, screenHeight/2+300, 300, 115);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(e -> System.exit(0));
        return exit;

    }

    public JButton highScoresButton(){
        JButton highScores = new JButton("HIGHSCORES");
        highScores.setFont(new Font(null, Font.PLAIN, 80));
        highScores.setBounds(screenWidth/2-600/2, screenHeight-screenHeight/2+60, 600, 115);
        highScores.setBorderPainted(false);
        highScores.setFocusPainted(false);
        highScores.setContentAreaFilled(false);
        return highScores;
    }

}




