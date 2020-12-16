
import javax.swing.*;
import java.awt.*;


public class SudokuBoard extends JFrame {

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
            switch (tmp) {
                case 0:
                    x = 100;
                    y = 100;
                    break;

                case 1:
                    x = 100;
                    y = 400;
                    break;
                case 2:
                    x = 100;
                    y = 700;
                    break;
                case 3:
                    x = 400;
                    y = 100;
                    break;
                case 4:
                    x = 400;
                    y = 400;
                    break;
                case 5:
                    x = 400;
                    y = 700;
                    break;
                case 6:
                    x = 700;
                    y = 100;
                    break;
                case 7:
                    x = 700;
                    y = 400;
                    break;
                case 8:
                    x = 700;
                    y = 700;


            }
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBounds(x, y, width, height);
            frame.add(panel);


        }
    }

}
