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
    SoundClass sound = new SoundClass();


    public JPanel createSudokuBoard() {
        sudokuBoardPanel.setLayout(null);
        sudokuBoardPanel.add(exitQuestion());;
        sudokuBoardPanel.add(drawSudokuKeypad());
        sudokuBoardPanel.add(drawSudokuBoard());
        sudokuBoardPanel.add(timerLabel());
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
                if (code == 27) {
                    sudokuBoardPanel.getComponent(0).setVisible(true);
                    sudokuBoardPanel.setFocusable(false);


                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        new TimerClass(this).setTimer();
        return sudokuBoardPanel;

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
        buttonsTemplateCreator.createBoardTemplate(buttonCreator.getBoardButtons(), buttonCreator.getKeypadButtons(), generator, sound);
        return mainPanel;
    }

    public JPanel exitQuestion() {
        JPanel mainPanel = new JPanel();
        mainPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == 27) {
                    sudokuBoardPanel.setFocusable(true);
                    sudokuBoardPanel.getComponent(0).setVisible(false);


                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        JButton question = new JButton("Are you sure?");
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        question.setFont(new Font(null, Font.PLAIN, 40));
        yes.setFont(new Font(null, Font.PLAIN, 40));
        no.setFont(new Font(null, Font.PLAIN, 40));
        JButtonConfigure(no);
        JButtonConfigure(yes);
        JButtonConfigure(question);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBounds(screenWidth / 2 - 451 / 2, screenHeight / 3, 500, 200);
        mainPanel.setBackground(new Color(247, 240, 223));
        mainPanel.add(question, BorderLayout.NORTH);
        mainPanel.add(yes, BorderLayout.CENTER);
        mainPanel.add(no, BorderLayout.AFTER_LAST_LINE);
        mainPanel.setFocusable(true);
        yes.addActionListener(e -> System.exit(0));
        no.addActionListener(e -> {
            sudokuBoardPanel.setFocusable(true);
            mainPanel.setVisible(false);

        });
        mainPanel.setVisible(false);
        return mainPanel;
    }

    public void JButtonConfigure(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
    }

    public JLabel timerLabel(){


        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font(null, Font.PLAIN, 80));
        timerLabel.setBounds((screenWidth/2)+600,(screenHeight/2)+200 , 500, 500);
        timerLabel.setVisible(true);
        return timerLabel;

    }

    public void setTimerButton(Component component, String time){
        ((JLabel)component).setText(time);
    }
}

