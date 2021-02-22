import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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
        sudokuBoardPanel.add(exitQuestion());
        sudokuBoardPanel.add(drawSudokuKeypad());
        sudokuBoardPanel.add(drawSudokuBoard());
        sudokuBoardPanel.add(background());
        sudokuBoardPanel.setFocusable(true);
        generator.displayBoard(buttonsTemplateCreator);
        sudokuBoardPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == 27){
                    sudokuBoardPanel.getComponent(0).setVisible(true);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        return sudokuBoardPanel;


/*
        //temporary button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(1200, 500, 200, 200);
        Button button = new Button("Generate one board");
        buttonPanel.add(button);
        button.addActionListener(e -> {
            while (!generator.generateFullBoard(buttonsTemplateCreator)) {

            }

        });




        sudokuBoardPanel.add(buttonPanel);


 */




    }

    //method is setting background
    public JLabel background() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, screenWidth, screenHeight);
        backgroundLabel.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));


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
        buttonsTemplateCreator.createBoardTemplate(buttonCreator.getBoardButtons(), buttonCreator.getKeypadButtons(), generator);
        return mainPanel;
    }

    public JPanel exitQuestion(){
        JPanel mainPanel = new JPanel();
        JButton question = new JButton("Are you sure?");
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        question.setFont(new Font(null, Font.PLAIN, 40));
        yes.setFont(new Font(null, Font.PLAIN, 40));
        no.setFont(new Font(null, Font.PLAIN, 40));
        yes.setBorderPainted(false);
        yes.setContentAreaFilled(false);
        no.setBorderPainted(false);
        no.setContentAreaFilled(false);
        question.setBorderPainted(false);
        question.setContentAreaFilled(false);
        question.setFocusable(false);
        yes.setFocusable(false);
        no.setFocusable(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBounds(screenWidth / 2 - 451 / 2, screenHeight/3, 500, 200);
        mainPanel.setBackground(new Color(247,240,223));
        mainPanel.add(question, BorderLayout.NORTH);
        mainPanel.add(yes, BorderLayout.CENTER);
        mainPanel.add(no, BorderLayout.AFTER_LAST_LINE);
        yes.addActionListener(e-> System.exit(0));
        no.addActionListener(e-> mainPanel.setVisible(false));
        mainPanel.setVisible(false);
        return mainPanel;
    }
}

