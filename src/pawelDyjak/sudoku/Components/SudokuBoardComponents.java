package pawelDyjak.sudoku.Components;

import pawelDyjak.sudoku.*;

import javax.swing.*;
import java.awt.*;

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
                    sudokuBoard.disableBackground(0);
                    sudokuBoard.getSudokuBoardPanel().getComponent(0).setVisible(true);
                    sudokuBoard.getTimerClass().pauseThread();
                } else {
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
}
