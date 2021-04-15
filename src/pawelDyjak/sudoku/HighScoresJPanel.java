package pawelDyjak.sudoku;

import pawelDyjak.sudoku.Components.HighScoresComponents;

import javax.swing.*;
import java.awt.event.KeyEvent;


public class HighScoresJPanel {
    private final MainMenu mainMenu;
    private final HighScoresComponents highScoresComponents;

    public HighScoresJPanel(MainMenu mainMenu, HighScoresComponents highScoresComponents) {
        this.mainMenu = mainMenu;
        this.highScoresComponents = highScoresComponents;
    }

    //method draws high score panel
    public JPanel drawHighScorePanel() {
        JPanel highScorePanel = new JPanel();
        highScorePanel.setFocusable(true);
        highScorePanel.addKeyListener(new KeyListenerClass() {
            @Override

            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                int escape = 27;
                if (code == escape) {
                    highScorePanel.setFocusable(false);
                    mainMenu.getMainMenuFrame().getContentPane().remove(mainMenu.getMainMenuFrame().getContentPane().getComponents().length-1);
                    mainMenu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(true);
                    mainMenu.getMainMenuFrame().getContentPane().getComponent(1).setVisible(true);

                }
            }
        });
        highScorePanel.setLayout(null);
        highScorePanel.add(highScoresComponents.highScore());
        highScorePanel.add(highScoresComponents.drawBackButton());
        highScorePanel.add(highScoresComponents.drawTitle());
        highScorePanel.add(highScoresComponents.drawBackground());

        return highScorePanel;

    }


}
