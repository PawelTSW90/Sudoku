package pawelDyjak.sudoku.Components;

import javax.swing.*;
import java.awt.*;
import pawelDyjak.sudoku.*;

public class MainMenuComponents {
   private final MainMenu mainMenu;

    public MainMenuComponents(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }
        //method draws exit Button for main menu
    public JButton exitButton() {
        JButton exit = new JButton("EXIT");
        exit.addMouseListener(new MouseListenerClass(null));
        exit.setFont(new Font(null, Font.PLAIN, 80));
        exit.setBounds(UtilityClass.getScreenWidth() / 2 - 300 / 2, UtilityClass.getScreenHeight() / 2 + 300, 300, 115);
        UtilityClass.buttonConfigure(exit);
        exit.addActionListener(e -> {
            new SoundClass().tick(null);
            System.exit(0);
        });
        return exit;

    }
        //method draws highScores button for main menu
    public JButton highScoresButton() {
        JButton highScores = new JButton("HIGHSCORES");
        highScores.addMouseListener(new MouseListenerClass(null));
        highScores.setFont(new Font(null, Font.PLAIN, 80));
        highScores.setBounds(UtilityClass.getScreenWidth() / 2 - 600 / 2, UtilityClass.getScreenHeight() - UtilityClass.getScreenHeight() / 2 + 60, 600, 115);
        UtilityClass.buttonConfigure(highScores);
        highScores.addActionListener(e -> {
            new SoundClass().tick(null);
            if (mainMenu.getMainMenuFrame().getContentPane().getComponents().length > 1) {
                while (mainMenu.getMainMenuFrame().getContentPane().getComponents().length>1)
                    mainMenu.getMainMenuFrame().getContentPane().remove(mainMenu.getMainMenuFrame().getContentPane().getComponents().length-1);
            }
            mainMenu.getMainMenuFrame().add(new HighScoresPanel(mainMenu).highScorePage());
            mainMenu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(false);

        });

        return highScores;
    }
        //method draws start button for main menu
    public JButton startButton() {
        JButton start = new JButton("START");
        start.addMouseListener(new MouseListenerClass(null));
        start.setFont(new Font(null, Font.PLAIN, 80));
        start.setBounds(UtilityClass.getScreenWidth() / 2 - 300 / 2, UtilityClass.getScreenHeight() / 2 - 150, 300, 115);
        UtilityClass.buttonConfigure(start);
        start.addActionListener(e -> {
            new SoundClass().tick(null);
            mainMenu.generateNewBoard();
        });

        return start;

    }
        //method draws title label for main menu
    public JLabel titleLabel(){
        JLabel title = new JLabel();
        title.setBounds(UtilityClass.getScreenWidth() / 2 - 451 / 2, UtilityClass.getScreenHeight() / 8, 451, 198);
        title.setIcon(new ImageIcon("./Visuals/logo.png"));
        return title;
    }
        //method draws background label for main menu
    public JLabel backgroundLabel(){
        JLabel background = new JLabel();
        background.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        background.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));
        return background;
    }
        //method draws created by label for main menu
    public JLabel createdByLabel(){
        JLabel createdBy = new JLabel();
        createdBy.setBounds(UtilityClass.getScreenWidth() - UtilityClass.getScreenWidth() / 7, UtilityClass.getScreenHeight() - UtilityClass.getScreenHeight() / 6, 355, 93);
        createdBy.setIcon(new ImageIcon("./Visuals/created_by.png"));
        return createdBy;
    }

}
