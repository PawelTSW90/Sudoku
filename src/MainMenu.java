import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public MainMenu() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(null);
        mainMenuPanel.add(background());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(mainMenuPanel);
        this.setUndecorated(true);
        this.setVisible(true);


    }

    public JLabel background() {

        JLabel background = new JLabel();
        JLabel title = new JLabel();
        background.setBounds(0, 0, screenWidth, screenHeight);
        background.setIcon(new ImageIcon("sudoku-background.jpg"));
        title.setBounds(screenWidth / 2 - 451 / 2, screenHeight / 8, 451, 198);
        title.setIcon(new ImageIcon("logo.png"));
        background.add(title);
        background.add(startButton());
        background.add(exitButton());


        return background;

    }

    public JButton startButton(){
        JButton start = new JButton();
        start.setBounds(screenWidth / 2 - 177 / 2, screenHeight / 2, 177, 115);
        start.setIcon(new ImageIcon("start_button.png"));
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.addActionListener(e->{
            new SudokuBoard();
            this.dispose();
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




