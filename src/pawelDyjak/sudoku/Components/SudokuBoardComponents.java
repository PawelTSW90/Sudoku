package pawelDyjak.sudoku.Components;

import pawelDyjak.sudoku.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SudokuBoardComponents {
    SudokuBoard sudokuBoard;

    public SudokuBoardComponents(SudokuBoard sudokuBoard){
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
                sudokuBoard.setErase(true);
                sudokuBoard.disableBackground(0);
                sudokuBoard.getSoundClass().tick(sudokuBoard);
            } else {

                    sudokuBoard.getSoundClass().tick(sudokuBoard);
                    sudokuBoard.disableBackground(1);
                    sudokuBoard.setErase(false);

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
                    sudokuBoard.getBoardChecker().restoreButtonsColors(sudokuBoard.getButtonsTemplateCreator());
                } else {
                    help.setForeground(new Color(0, 102, 0));
                    sudokuBoard.setHelpOn(true);
                    sudokuBoard.getBoardChecker().checkIfThereAreErrors(sudokuBoard.getButtonsTemplateCreator(), sudokuBoard.getSoundClass(), sudokuBoard.getTimerClass(), sudokuBoard.getErrorLabelThread());

                }
            }
            @Override
            public void mouseEntered(MouseEvent e){

            }

            @Override
            public void mouseExited(MouseEvent e){

            }
            @Override
            public void mouseClicked(MouseEvent e){

            }
        });
        return help;

    }
}
