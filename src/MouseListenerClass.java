import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListenerClass implements java.awt.event.MouseListener {
    SudokuBoard board;

    MouseListenerClass(SudokuBoard board) {
        this.board = board;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        board.sound.tick(board);
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
