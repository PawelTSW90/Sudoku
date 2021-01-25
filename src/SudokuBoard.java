import javax.swing.*;
import java.awt.*;


public class SudokuBoard extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator();
    BacktrackingChecker backtrackingChecker = new BacktrackingChecker();
    ButtonCreator buttonCreator = new ButtonCreator();

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
        button.addActionListener(e -> backtrackingChecker.checkIfSolvable(buttonsTemplateCreator));
        this.add(buttonPanel);
        this.add(background());
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
            button.addActionListener(new ButtonInteract(button,buttonCreator));
            button.setFont(new Font(null, Font.BOLD, 20));
            ButtonsTemplateCreator.keypadButtons.add(button);
            sudokuKeyboard.add(button);
            buttonNumber++;


        }
        return sudokuKeyboard;
    }
    //method is creating sudoku board
    public void drawSudokuBoard(JFrame frame) {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.setBounds(140, 90, 1000, 900);

        for (int panelNr = 0; panelNr < 9; panelNr++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            for (int buttonNr = 0; buttonNr < 9; buttonNr++) {
                panel.add(buttonCreator.createButton());
            }
            mainPanel.add(panel);
        }
        frame.add(mainPanel);
        buttonsTemplateCreator.createBoardTemplate(buttonCreator);
    }
}

