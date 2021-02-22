import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        createdBy.setBounds(screenWidth-screenWidth/7, screenHeight-screenHeight/6, 355, 93);
        createdBy.setIcon(new ImageIcon("created_by.png"));
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setIcon(new ImageIcon("sudoku-background.jpg"));
        title.setBounds(screenWidth / 2 - 451 / 2, screenHeight / 8, 451, 198);
        title.setIcon(new ImageIcon("logo.png"));
        mainMenuPanel.add(title);
        mainMenuPanel.add(startButton());
        mainMenuPanel.add(exitButton());
        mainMenuPanel.add(createdBy);
        mainMenuPanel.add(background);
        mainMenuPanel.setLayout(null);
        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainMenuFrame.setVisible(true);



    }

    public JButton startButton(){
        JButton start = new JButton();
        start.setBounds(screenWidth / 2 - 177 / 2, screenHeight / 2, 177, 115);
        start.setIcon(new ImageIcon("start_button.png"));
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.addActionListener(e->{
            mainMenuFrame.add(new SudokuBoard().createSudokuBoard());
            mainMenuFrame.getContentPane().getComponent(0).setVisible(false);


        });
        return start;

    }

    public JButton exitButton(){
        JButton exit = new JButton();
        exit.setBounds(screenWidth / 2 - 147 / 2, screenHeight-(screenHeight/4), 147, 115);
        exit.setIcon(new ImageIcon("exit_button.png"));
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(e-> System.exit(0));
        return exit;

    }

}




