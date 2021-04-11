package pawelDyjak.sudoku.Components;

import pawelDyjak.sudoku.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SudokuBoardComponents {
    private final SudokuBoard sudokuBoard;

    public SudokuBoardComponents(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    //method draws exit button for sudoku board
    public JButton drawExitButton() {
        JButton exit = new JButton("X");
        exit.setFocusable(false);
        exit.setFont(new Font(null, Font.ITALIC, 60));
        exit.setBounds(10, 10, 75, 75);
        exit.addMouseListener(new MouseListenerClass(sudokuBoard));
        exit.addActionListener(e -> {
            if (sudokuBoard.isEraseOn()) {
                sudokuBoard.disableBackground(1);
            } else {

                if (!sudokuBoard.getSudokuBoardPanel().getComponent(0).isVisible()) {
                    sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(false);
                    sudokuBoard.disableBackground(0);
                    sudokuBoard.getSudokuBoardPanel().getComponent(0).setVisible(true);
                    sudokuBoard.getTimerClass().pauseThread();
                } else {
                    sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(true);
                    sudokuBoard.disableBackground(1);
                    sudokuBoard.getSudokuBoardPanel().getComponent(0).setVisible(false);
                    sudokuBoard.getTimerClass().resumeThread();
                }
            }

        });
        UtilityClass.buttonConfigure(exit);
        return exit;
    }

    //method draws error counter label for sudoku board
    public JLabel drawErrorCounterLabel() {
        JLabel errorCounter = new JLabel();
        errorCounter.setVisible(false);
        errorCounter.setFont(new Font(null, Font.ITALIC, 40));
        errorCounter.setForeground(new Color(102, 0, 0));
        errorCounter.setBounds((UtilityClass.getScreenWidth() / 2) - 800 / 2, (UtilityClass.getScreenHeight() / 2) + 450, 800, 100);
        return errorCounter;
    }

    //method draws erase button for sudoku board
    public JButton drawEraseButton() {
        JButton erase = new JButton();
        erase.setFocusable(false);
        erase.setBounds((UtilityClass.getScreenWidth() / 2) + 838, (UtilityClass.getScreenHeight() / 2) - 135, 100, 75);
        erase.setIcon(new ImageIcon("./Visuals/erase.png"));
        erase.setBorderPainted(false);
        erase.setContentAreaFilled(false);
        erase.addActionListener(e -> {

            if (!sudokuBoard.isEraseOn()) {
                if (sudokuBoard.getButtonInteract().isBoardButtonHighlighted()) {
                    sudokuBoard.getButtonInteract().eraseButton();
                } else {
                    sudokuBoard.setEraseOn(true);
                    sudokuBoard.disableBackground(0);
                    sudokuBoard.getSoundClass().tick(sudokuBoard);
                }
            } else {

                sudokuBoard.getSoundClass().tick(sudokuBoard);
                sudokuBoard.disableBackground(1);
                sudokuBoard.setEraseOn(false);

            }

        });
        return erase;
    }

    //method draws board completed wrong message panel for sudoku board
    public JPanel drawBoardCompletedWrongMessage() {
        JPanel wrong = new JPanel();
        wrong.setBackground(new Color(245, 232, 211));
        wrong.setLayout(new GridLayout(4, 1));
        wrong.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.PINK));
        wrong.setBounds(UtilityClass.getScreenWidth() / 2 - 500 / 2, UtilityClass.getScreenHeight() / 2 - 400 / 2, 500, 400);
        JButton oops = new JButton("Oops! Something went wrong! Would you like to:");
        oops.setBorderPainted(false);
        JButton startOver = new JButton("Start over");
        JButton goBack = new JButton("Continue");
        JButton quit = new JButton("Exit");
        startOver.addMouseListener(new MouseListenerClass(sudokuBoard));
        goBack.addMouseListener(new MouseListenerClass(sudokuBoard));
        quit.addMouseListener(new MouseListenerClass(sudokuBoard));
        quit.setFont(new Font(null, Font.PLAIN, 40));
        startOver.setFont(new Font(null, Font.PLAIN, 40));
        oops.setFont(new Font(null, Font.PLAIN, 40));
        goBack.setFont(new Font(null, Font.PLAIN, 40));
        UtilityClass.buttonConfigure(startOver);
        UtilityClass.buttonConfigure(oops);
        UtilityClass.buttonConfigure(quit);
        UtilityClass.buttonConfigure(goBack);
        wrong.add(oops);
        wrong.add(startOver);
        wrong.add(goBack);
        wrong.add(quit);
        wrong.setVisible(false);
        wrong.setFocusable(false);
        quit.addActionListener(e -> System.exit(0));
        goBack.addActionListener(e -> {
            sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(true);
            sudokuBoard.getSudokuBoardPanel().setFocusable(true);
            sudokuBoard.disableBackground(1);
            sudokuBoard.getTimerClass().resumeThread();
            sudokuBoard.getSudokuBoardPanel().getComponent(1).setVisible(false);

        });
        startOver.addActionListener(e -> {
            sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(true);
            sudokuBoard.getSudokuBoardPanel().setFocusable(true);
            sudokuBoard.getSudokuBoardPanel().getComponent(1).setVisible(false);
            sudokuBoard.disableBackground(1);
            sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(true);
            TimerClass timerClass = new TimerClass(sudokuBoard);
            sudokuBoard.setTimerClass(timerClass);
            sudokuBoard.getTimerClass().setTimer();
            sudokuBoard.getSudokuGenerator().resetBoard(sudokuBoard.getButtonsTemplateCreator());
        });
        return wrong;

    }

    //method draws help label panel for sudoku board
    public JLabel drawHelpLabel() {
        JLabel help = new JLabel("Help");
        help.setFocusable(false);
        help.setForeground(new Color(102, 0, 0));
        help.setFont(new Font(null, Font.ITALIC, 80));
        help.setBounds((UtilityClass.getScreenWidth() / 2) + 600, (UtilityClass.getScreenHeight() / 2) + 200, 400, 100);
        help.addMouseListener(new MouseListenerClass(sudokuBoard) {
            @Override
            public void mousePressed(MouseEvent e) {
                Component component = sudokuBoard.getSudokuBoardPanel().getComponent(6);
                if (sudokuBoard.isHelpOn()) {
                    component.setForeground(new Color(102, 0, 0));
                    sudokuBoard.setHelpOn(false);
                    sudokuBoard.getBoardChecker().restoreButtonsColors();
                } else {
                    help.setForeground(new Color(0, 102, 0));
                    sudokuBoard.setHelpOn(true);
                    sudokuBoard.getBoardChecker().checkIfThereAreErrors();

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        return help;

    }

    //method draws sound label panel for sudoku board
    public JLabel drawSoundLabel() {
        JLabel sound = new JLabel("Sounds");
        sound.setForeground(new Color(0, 102, 0));
        sound.setBackground(Color.BLACK);
        sound.setFocusable(false);
        sound.setFont(new Font(null, Font.ITALIC, 80));
        sound.setBounds((UtilityClass.getScreenWidth() / 2) + 600, (UtilityClass.getScreenHeight() / 2) + 50, 400, 100);
        sound.setVisible(true);
        sound.addMouseListener(new MouseListenerClass(sudokuBoard) {
            @Override
            public void mousePressed(MouseEvent e) {
                Component component = sudokuBoard.getSudokuBoardPanel().getComponent(5);
                if (sudokuBoard.isSoundOn()) {
                    component.setForeground(new Color(102, 0, 0));
                    sudokuBoard.setSoundOn(false);
                } else {
                    sound.setForeground(new Color(0, 102, 0));
                    sudokuBoard.setSoundOn(true);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        return sound;
    }

    //method draws timer label panel for sudoku board
    public JLabel drawTimerLabel() {


        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font(null, Font.PLAIN, 80));
        timerLabel.setBounds((UtilityClass.getScreenWidth() / 2) + 600, (UtilityClass.getScreenHeight() / 2) + 200, 500, 500);
        timerLabel.setVisible(true);
        return timerLabel;

    }

    //method draws exit question panel for sudoku board
    public JPanel drawExitQuestion() {
        JPanel exit = new JPanel();
        exit.setLayout(new BoxLayout(exit, BoxLayout.X_AXIS));
        JPanel question = new JPanel();
        question.setBackground(new Color(245, 232, 211));
        question.setLayout(new GridLayout(4, 1));
        question.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.PINK));
        question.setBounds(UtilityClass.getScreenWidth() / 2 - 500 / 2, UtilityClass.getScreenHeight() / 2 - 400 / 2, 500, 400);
        JButton questionButton = new JButton(" Would you like to:");
        questionButton.setBorderPainted(false);
        JButton startOver = new JButton("Start over");
        JButton goBack = new JButton("Continue");
        JButton quit = new JButton("Exit");
        startOver.addMouseListener(new MouseListenerClass(sudokuBoard));
        goBack.addMouseListener(new MouseListenerClass(sudokuBoard));
        quit.addMouseListener(new MouseListenerClass(sudokuBoard));
        quit.setFont(new Font(null, Font.PLAIN, 40));
        startOver.setFont(new Font(null, Font.PLAIN, 40));
        questionButton.setFont(new Font(null, Font.PLAIN, 40));
        goBack.setFont(new Font(null, Font.PLAIN, 40));
        UtilityClass.buttonConfigure(startOver);
        UtilityClass.buttonConfigure(questionButton);
        UtilityClass.buttonConfigure(quit);
        UtilityClass.buttonConfigure(goBack);
        question.add(questionButton);
        question.add(startOver);
        question.add(goBack);
        question.add(quit);
        question.setVisible(false);
        quit.addActionListener(e -> {
            quit.setFocusable(false);
            sudokuBoard.getSoundClass().tick(sudokuBoard);
            sudokuBoard.getMainFrame().getContentPane().getComponent(1).setVisible(false);
            sudokuBoard.getMainFrame().getContentPane().getComponent(0).setVisible(true);
        });
        goBack.addActionListener(e -> {
            sudokuBoard.getSoundClass().tick(sudokuBoard);
            sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(true);
            sudokuBoard.getSudokuBoardPanel().setFocusable(true);
            sudokuBoard.disableBackground(1);
            sudokuBoard.getTimerClass().resumeThread();
            sudokuBoard.getSudokuBoardPanel().getComponent(0).setVisible(false);

        });
        startOver.addActionListener(e -> {
            sudokuBoard.getSoundClass().tick(sudokuBoard);
            sudokuBoard.getSudokuBoardPanel().getComponent(3).setVisible(true);
            sudokuBoard.getSudokuBoardPanel().setFocusable(true);
            sudokuBoard.getSudokuBoardPanel().getComponent(0).setVisible(false);
            sudokuBoard.disableBackground(1);
            TimerClass timerClass = new TimerClass(sudokuBoard);
            sudokuBoard.setTimerClass(timerClass);
            timerClass.setTimer();
            sudokuBoard.getSudokuGenerator().resetBoard(sudokuBoard.getButtonsTemplateCreator());
        });
        return question;
    }

    //method draws sudoku board panel for sudoku board and creates sudoku board buttons
    public JPanel drawSudokuBoard() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.setBounds(140, 90, 1000, 900);

        for (int panelNr = 0; panelNr < 9; panelNr++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            for (int buttonNr = 0; buttonNr < 9; buttonNr++) {
                panel.add(sudokuBoard.getButtonCreator().createBoardButtons());
            }
            mainPanel.add(panel);
        }

        // create buttons template for sudoku board and buttons with buttons template  for sudoku generator
        sudokuBoard.getButtonCreator().createBoardButtonsForGenerator();
        sudokuBoard.getButtonsTemplateCreator().createBoardTemplate(sudokuBoard.getButtonCreator().getBoardButtons(), sudokuBoard.getButtonCreator().getKeypadButtons(), sudokuBoard.getButtonCreator().getBoardButtonsForGenerator());

        return mainPanel;
    }

    //method draws sudoku keypad panel for sudoku board
    public JPanel drawSudokuKeypad() {
        JPanel sudokuKeypad = new JPanel();
        sudokuKeypad.setLayout(new GridLayout(3, 3));
        sudokuKeypad.setBounds(1600, 100, 300, 300);
        for (int x = 0; x < 9; x++) {
            sudokuKeypad.add(sudokuBoard.getButtonCreator().createKeypadButtons());
        }
        return sudokuKeypad;
    }

    //method draws background label for sudoku board
    public JLabel drawBackground() {

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, UtilityClass.getScreenWidth(), UtilityClass.getScreenHeight());
        backgroundLabel.setIcon(new ImageIcon("./Visuals/sudoku-background.jpg"));


        return backgroundLabel;

    }

    //method draws "generating label" label for main menu
    public JLabel generatingBoardLabel() {
        JLabel generatingBoard = new JLabel("Board generating in progress...");
        generatingBoard.setVisible(false);
        generatingBoard.setFont(new Font(null, Font.PLAIN, 30));
        generatingBoard.setBounds(UtilityClass.getScreenWidth() - UtilityClass.getScreenWidth() / 2 - 410 / 2, UtilityClass.getScreenHeight() - UtilityClass.getScreenHeight() / 4, 410, 500);
        return generatingBoard;
    }
}
