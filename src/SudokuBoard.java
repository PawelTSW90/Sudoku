import javax.swing.*;
import java.awt.*;

public class SudokuBoard {
    JPanel sudokuBoardPanel = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    ButtonsTemplateCreator buttonsTemplateCreator = new ButtonsTemplateCreator();
    ButtonCreator buttonCreator = new ButtonCreator();
    SudokuGenerator generator = new SudokuGenerator();

    public JPanel createSudokuBoard() {
        sudokuBoardPanel.setLayout(null);
        sudokuBoardPanel.add(drawSudokuKeypad());
        sudokuBoardPanel.add(drawSudokuBoard());
        //temporary button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(1200, 500, 200, 200);
        Button button = new Button("Button");
        buttonPanel.add(button);
        button.addActionListener(e -> {
            while (!generator.generateFullBoard(buttonsTemplateCreator)) {

            }

        });

        //generate sudoku boards button
        JPanel generateButtonPanel = new JPanel();
        generateButtonPanel.setBounds(1200, 800, 200, 200);
        Button button1 = new Button("Generate");
        button1.addActionListener(e ->{
            for (int x = 0; x<2; x++){
                while (!generator.generateFullBoard(buttonsTemplateCreator));

            }
            System.out.println(EncryptionClass.encrypt("password", generator.getTmp()));
        });


        generateButtonPanel.add(button1);
        sudokuBoardPanel.add(buttonPanel);
        sudokuBoardPanel.add(generateButtonPanel);
        sudokuBoardPanel.add(background());

        return sudokuBoardPanel;



    }

    //method is setting background
    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        backgroundLabel.setIcon(new ImageIcon("sudoku-background.jpg"));


        return backgroundLabel;

    }

    //method is creating keypad
    public JPanel drawSudokuKeypad() {
        JPanel sudokuKeypad = new JPanel();
        sudokuKeypad.setLayout(new GridLayout(3, 3));
        sudokuKeypad.setBounds(1600, 100, 300, 300);
        for (int x = 0; x < 9; x++) {
            sudokuKeypad.add(buttonCreator.createKeypadButtons());
        }
        return sudokuKeypad;
    }

    //method is creating sudoku board
    public JPanel drawSudokuBoard() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.setBounds(140, 90, 1000, 900);

        for (int panelNr = 0; panelNr < 9; panelNr++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            for (int buttonNr = 0; buttonNr < 9; buttonNr++) {
                panel.add(buttonCreator.createBoardButtons());
            }
            mainPanel.add(panel);
        }

        // create buttons template
        buttonsTemplateCreator.createBoardTemplate(buttonCreator.getBoardButtons(), buttonCreator.getKeypadButtons());
        return mainPanel;
    }
}

