package pawelDyjak.sudoku;

import pawelDyjak.sudoku.Components.HighScoresComponents;

import javax.swing.*;
import java.awt.event.KeyEvent;


public class HighScoresJPanel {
    private final MainMenu menu;
    private final HighScoresComponents highScoresComponents;

    public HighScoresJPanel(MainMenu menu, HighScoresComponents highScoresComponents) {
        this.menu = menu;
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
                    menu.getMainMenuFrame().getContentPane().getComponent(1).setVisible(false);
                    menu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(true);
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
