package pawelDyjak.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HighScoresPanel {
    MainMenu menu;

    public HighScoresPanel(MainMenu menu) {
        this.menu = menu;
    }


    public JPanel highScorePage() {
        JPanel highScorePanel = new JPanel();
        highScorePanel.setFocusable(true);
        highScorePanel.addKeyListener(new KeyListenerClass(){
            @Override

            public void keyPressed(KeyEvent e){
                int code = e.getKeyCode();
                int escape = 27;
                if(code == escape){
                    highScorePanel.setFocusable(false);
                    menu.getMainMenuFrame().getContentPane().getComponent(1).setVisible(false);
                    menu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(true);
                }
            }
        });
        int labelNr = 1;
        highScorePanel.setLayout(null);
        JPanel highScores = new JPanel();
        highScores.setOpaque(false);
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        highScores.setLayout(layout);
        highScores.setBounds(UtilityClass.getScreenWidth() / 2 - 500 / 2-50, UtilityClass.getScreenHeight() / 2 - 600 / 2, 800, 700);
        for (int x = 0; x < 10; x++) {
            JLabel label = new JLabel(labelNr + "..........");
            switch (labelNr) {
                case 1 -> label.setForeground(new Color(218, 165, 32));
                case 2 -> label.setForeground(new Color(169, 169, 169));
                case 3 -> label.setForeground(new Color(102, 51, 0));
            }
            label.setFont(new Font(null, Font.ITALIC, 50));
            setText(label, labelNr - 1);
            highScores.add(label);
            labelNr++;

        }

        highScorePanel.add(highScores);
        highScorePanel.add(title());
        highScorePanel.add(back());
        highScorePanel.add(background());

        return highScorePanel;

    }

    public void setText(JLabel label, int line) {
        String text = lineReturn(line);
        text = text.replace("*", "");
        label.setText(text);

    }

    public String lineReturn(int line) {
        String lineToChange = null;
        Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
        try {
            List<String> lines = Files.readAllLines(path);
            lineToChange = lines.get(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineToChange;


    }

    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        backgroundLabel.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));


        return backgroundLabel;

    }

    public JLabel title() {
        JLabel title = new JLabel("HIGHSCORES");
        title.setFont(new Font(null, Font.PLAIN, 80));
        title.setBounds(UtilityClass.getScreenWidth() / 2 - 600 / 2, 50, 600, 115);
        return title;
    }

    public JButton back() {
        JButton back = new JButton("BACK");
        back.setFont(new Font(null, Font.PLAIN, 60));
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setBounds(UtilityClass.getScreenWidth() / 2 + 700, UtilityClass.getScreenHeight() / 2 + 400, 200, 100);
        back.addActionListener(e -> {
            back.setFocusable(false);
            new SoundClass().tick(null);
            menu.getMainMenuFrame().getContentPane().getComponent(1).setVisible(false);
            menu.getMainMenuFrame().getContentPane().getComponent(0).setVisible(true);
        });
        back.addMouseListener(new MouseListenerClass(null){
            @Override

            public void mouseEntered(MouseEvent e){
                back.setForeground((new Color(80, 50, 10)));
            }

            @Override

            public void mouseExited(MouseEvent e){
                back.setForeground(Color.BLACK);
            }



        });
        return back;
    }


}
