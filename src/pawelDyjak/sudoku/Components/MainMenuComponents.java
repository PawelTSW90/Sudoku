package pawelDyjak.sudoku.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pawelDyjak.sudoku.*;

public class MainMenuComponents {
    private final MainMenu mainMenu;
    private final SoundClass soundClass;

    public MainMenuComponents(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        soundClass = new SoundClass();


    }

    //method draws exit Button for main menu
    public JButton exitButton() {
        JButton exit = new JButton("EXIT");
        exit.addMouseListener(new MouseListenerClass(null));
        exit.setFont(new Font(null, Font.PLAIN, UtilityClass.getScreenHeight()/15));
        UtilityClass.buttonConfigure(exit);
        exit.addActionListener(e -> {
            soundClass.tick(null);
            System.exit(0);
        });
        return exit;

    }

    //method draws highScores button for main menu
    public JButton highScoresButton() {
        JButton highScores = new JButton("HIGH SCORES");
        highScores.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soundClass.tick(null);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setForeground(new Color(80, 50, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setForeground(Color.black);
            }
        });
        highScores.setFont(new Font(null, Font.PLAIN, UtilityClass.getScreenHeight()/15));
        UtilityClass.buttonConfigure(highScores);
        highScores.addActionListener(e -> {
            soundClass.tick(null);
            mainMenu.getMainMenuFrame().add(new HighScoresJPanel(mainMenu, mainMenu.getHighScoresComponents()).drawHighScorePanel());
            mainMenu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(false);
            mainMenu.getMainMenuFrame().getContentPane().getComponent(1).setVisible(false);

        });

        return highScores;
    }

    //method draws start button for main menu
    public JButton startButton() {
        JButton start = new JButton("START");
        start.addMouseListener(new MouseListenerClass(null));
        start.setFont(new Font(null, Font.PLAIN, UtilityClass.getScreenHeight()/15));
        UtilityClass.buttonConfigure(start);
        start.addActionListener(e -> {
            new SoundClass().tick(null);
            mainMenu.generateNewBoard();

        });

        return start;

    }

    //method draws title label for main menu
    public JLabel titleLabel() {

        JLabel title = new JLabel("SUDOKU");
        title.setFont(new Font(null, Font.ITALIC, UtilityClass.getScreenHeight()/10));
        title.setForeground(new Color(80,44,17));

        return title;

    }

    //method draws background label for main menu
    public JLabel backgroundLabel() {
        JLabel background = new JLabel();
        background.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        background.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sudoku-background.jpg")));
        return background;
    }

    //method draws "created by" label for main menu
    public JLabel createdByLabel() {
        JLabel createdBy = new JLabel("Created by Pawel Dyjak", SwingConstants.RIGHT);
        createdBy.setFont(new Font(null, Font.ITALIC, UtilityClass.getScreenHeight()/25));
        return createdBy;
    }


}
