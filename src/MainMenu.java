import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {

    public MainMenu() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(null);
        Button startButton = new Button("Start");
        startButton.addActionListener(e->{
            new SudokuBoard();
            this.dispose();
        });
        startButton.setBounds(200,200,200,200);
        Button exitButton = new Button("Exit");
        exitButton.addActionListener(e->{
            System.exit(0);
        });
        exitButton.setBounds(800,200,200,200);
        mainMenuPanel.add(exitButton, BorderLayout.CENTER);
        mainMenuPanel.add(startButton, BorderLayout.NORTH);
        mainMenuPanel.setBackground(Color.GRAY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(mainMenuPanel);
        this.setUndecorated(true);
        this.setVisible(true);


    }


}
