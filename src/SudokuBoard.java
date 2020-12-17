import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;


public class SudokuBoard extends JFrame {
    Button[][] buttonList = new Button[9][9];
    Button[] sudokuKeyboardList = new Button[9];
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public SudokuBoard() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(null);
        drawSudokuBoard(this);
        this.add(sudokuInputKeyboard());
        this.add(background());
        this.setVisible(true);


    }

    //method setting background
    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        backgroundLabel.setIcon(new ImageIcon("C:/Users/Paweł/Desktop/Sudoku/sudoku-background.jpg"));
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);

        return backgroundLabel;

    }

    public JPanel sudokuInputKeyboard() {
        JPanel sudokuKeyboard = new JPanel();
        int buttonNumber = 1;
        sudokuKeyboard.setLayout(new GridLayout(3, 3));
        sudokuKeyboard.setBounds(1600, 100, 300, 300);
        for (int x = 0; x < 9; x++) {
            Button button = new Button(String.valueOf(buttonNumber));
            button.setFont(new Font(null, Font.BOLD, 20));
            sudokuKeyboard.add(button);
            sudokuKeyboardList[x] = button;
            buttonNumber++;


        }
        return sudokuKeyboard;





    }

    public void drawSudokuBoard(JFrame frame) {
        int paneNumber = 0;
        for (int tmp = 0; tmp < 9; tmp++) {
            int x = 100;
            int y = 100;
            int width = 300;
            int height = 300;


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
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBounds(x, y, width, height);
            for (int buttons = 0; buttons < 9; buttons++) {
                Button button = new Button();
                panel.add(button);
                buttonList[paneNumber][buttons] = button;


            }

            paneNumber++;
            frame.add(panel);


        }

    }


}
