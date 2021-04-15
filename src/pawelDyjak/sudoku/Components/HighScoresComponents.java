package pawelDyjak.sudoku.Components;

import pawelDyjak.sudoku.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HighScoresComponents {
    private final MainMenu mainMenu;

    public HighScoresComponents(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public JLabel highScore() {
        int labelNr = 1;
        JLabel panel = new JLabel();
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBounds(UtilityClass.getScreenWidth() / 2 - 600 / 2, 250, 800, 700);
        for (int x = 0; x < 10; x++) {
            JLabel label = new JLabel(labelNr + "..........");
            switch (labelNr) {
                case 1 -> label.setForeground(new Color(218, 165, 32));
                case 2 -> label.setForeground(new Color(169, 169, 169));
                case 3 -> label.setForeground(new Color(102, 51, 0));
            }
            label.setFont(new Font(null, Font.ITALIC, 50));
            setText(label, labelNr - 1);
            panel.add(label);
            labelNr++;

        }
        return panel;
    }

    //method draws back button for high score panel
    public JButton drawBackButton() {
        JButton back = new JButton("BACK");
        back.setFont(new Font(null, Font.PLAIN, 60));
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setBounds(UtilityClass.getScreenWidth() / 2 + 700, UtilityClass.getScreenHeight() / 2 + 400, 200, 100);
        back.addActionListener(e -> {
            back.setFocusable(false);
            new SoundClass().tick(null);
            mainMenu.getMainMenuFrame().getContentPane().remove(mainMenu.getMainMenuFrame().getContentPane().getComponents().length-1);
            mainMenu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(true);
            mainMenu.getMainMenuFrame().getContentPane().getComponent(1).setVisible(true);
        });
        back.addMouseListener(new MouseListenerClass(null) {
            @Override

            public void mouseEntered(MouseEvent e) {
                back.setForeground((new Color(80, 50, 10)));
            }

            @Override

            public void mouseExited(MouseEvent e) {
                back.setForeground(Color.BLACK);
            }


        });
        return back;
    }

    //method fills high scores board line with user name and time
    public void setText(JLabel label, int line) {
        String text = lineReturn(line);
        text = text.replace("*", "");
        label.setText(text);

    }

    //method returns line on high scores board corresponding with players position
    public String lineReturn(int line) {
        String lineToChange = null;
        Path path = Paths.get("./high_scores.brd");
        try {
            List<String> lines = Files.readAllLines(path);
            lineToChange = lines.get(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineToChange;


    }

    //method draws background label for high scores panel
    public JLabel drawBackground() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        backgroundLabel.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));


        return backgroundLabel;

    }

    //method draws title label for high scores panel
    public JLabel drawTitle() {
        JLabel title = new JLabel("HIGH SCORES");
        title.setFont(new Font(null, Font.PLAIN, 80));
        title.setBounds(UtilityClass.getScreenWidth() / 2 - 600 / 2, 50, 600, 115);
        return title;
    }
}
