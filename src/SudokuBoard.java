
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SudokuBoard extends JFrame {
    ArrayList<Object> textFields = new ArrayList<>();

    public SudokuBoard() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(null);
        drawSudokuBoard(this);
        this.add(background());
        this.setVisible(true);


    }

    //method setting background
    public JLabel background() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        backgroundLabel.setIcon(new ImageIcon("C:/Users/Paweł/Desktop/Sudoku/sudoku-background.jpg"));
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);

        return backgroundLabel;

    }

    public void drawSudokuBoard(JFrame frame) {
        for (int tmp = 0; tmp < 9; tmp++) {
            int x = 100;
            int y = 100;
            int width = 300;
            int height = 300;
            int textX = 0;
            int textY = 0;
            int textWidth = 100;
            int textHeight = 100;

            switch (tmp) {
                case 0 -> {
                    x = 100;
                    y = 100;

                }
                case 1 -> {
                    x = 100;
                    y = 400;
                }
                case 2 -> {
                    x = 100;
                    y = 700;
                }
                case 3 -> {
                    x = 400;
                    y = 100;
                }
                case 4 -> {
                    x = 400;
                    y = 400;
                }
                case 5 -> {
                    x = 400;
                    y = 700;
                }
                case 6 -> {
                    x = 700;
                    y = 100;
                }
                case 7 -> {
                    x = 700;
                    y = 400;
                }
                case 8 -> {
                    x = 700;
                    y = 700;
                }
            }
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBounds(x, y, width, height);
            for (int tmp2 = 0; tmp2 < 9; tmp2++) {
                Button button = new Button();
                button.setBounds(100, 100, 100, 100);
                frame.add(button);
            }
            frame.add(panel);


        }
    }

}
