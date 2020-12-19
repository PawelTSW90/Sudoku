import javax.swing.*;
import java.awt.*;



public class SudokuBoard extends JFrame {
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
        Buttons.refactorButtonList();
        this.setFocusable(true);
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
            button.setFocusable(false);
            button.setName("Keypad");
            button.addActionListener(new ButtonInteract(button));
            button.setFont(new Font(null, Font.BOLD, 20));
            Buttons.keyboardButtons.add(button);
            sudokuKeyboard.add(button);
            buttonNumber++;


        }
        return sudokuKeyboard;


    }

    public void drawSudokuBoard(JFrame frame) {
        int squareNumber = 0;
        for (int tmp = 0; tmp < 9; tmp++) {
            int x = 100;
            int y = 100;
            int width = 300;
            int height = 300;


            switch (tmp) {
                case 0 -> squareNumber = 1;
                case 1 -> {
                    squareNumber = 2;
                    y = 400;
                }
                case 2 -> {
                    squareNumber = 3;
                    y = 700;
                }
                case 3 -> {
                    squareNumber = 4;
                    x = 400;
                    y = 100;

                }
                case 4 -> {
                    squareNumber = 5;
                    x = 400;
                    y = 400;

                }
                case 5 -> {
                    squareNumber = 6;
                    x = 400;
                    y = 700;

                }
                case 6 -> {
                    squareNumber = 7;
                    x = 700;
                    y = 100;

                }
                case 7 -> {
                    squareNumber = 8;
                    x = 700;
                    y = 400;

                }
                case 8 -> {
                    squareNumber = 9;
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
                button.setName("S" + squareNumber);
                button.setFocusable(false);
                button.addActionListener(new ButtonInteract(button));
                Buttons.boardButtons.add(button);
                panel.add(button);


            }


            frame.add(panel);


        }


    }




}
