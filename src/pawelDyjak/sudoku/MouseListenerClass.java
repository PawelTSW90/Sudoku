package pawelDyjak.sudoku;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListenerClass implements java.awt.event.MouseListener {
    SudokuBoard sudokuBoard;

    public MouseListenerClass(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        sudokuBoard.getSoundClass().tick(sudokuBoard);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setForeground(new Color(80, 50, 10));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setForeground(Color.black);
    }
}
