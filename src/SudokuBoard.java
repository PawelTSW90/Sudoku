import javax.swing.*;
import java.awt.*;


public class SudokuBoard extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ButtonsTemplateCreator buttons = new ButtonsTemplateCreator();
    BacktrackingChecker checker = new BacktrackingChecker(buttons);
    ButtonCreator creator = new ButtonCreator();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    public SudokuBoard() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(null);
        this.add(setKeypad());
        drawSudokuBoard(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(1200, 500, 200, 200);
        Button button = new Button("Button");
        buttonPanel.add(button);
        button.addActionListener(e -> checker.checkIfSolvable());
        this.add(buttonPanel);
        this.add(background());
        buttons.refactorButtonList();
        this.setVisible(true);
        this.setFocusable(true);

    }

    //method is setting background
    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        backgroundLabel.setIcon(new ImageIcon("sudoku-background.jpg"));
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);

        return backgroundLabel;

    }

    //method is creating keypad
    public JPanel setKeypad() {
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
            ButtonsTemplateCreator.keypadButtons.add(button);
            sudokuKeyboard.add(button);
            buttonNumber++;


        }
        return sudokuKeyboard;
    }
    //method is creating sudoku board
    public void drawSudokuBoard(JFrame frame) {
        int square = 1;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.setBounds(140, 90, 1000, 900);

        for (int panelNr = 0; panelNr < 9; panelNr++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            for (int buttonNr = 0; buttonNr < 9; buttonNr++) {
                Button button = new Button();
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                button.setName("S"+ square);
                button.setFocusable(false);
                button.addActionListener(new ButtonInteract(button));
                buttons.boardButtons.add(button);
                //panel.add(button);
                panel.add(creator.createButton());


            }
            square++;
            mainPanel.add(panel);
        }
        frame.add(mainPanel);
        buttons.createBoardTemplate();
    }
}

