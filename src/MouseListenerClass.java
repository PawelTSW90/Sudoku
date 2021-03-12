import javax.swing.*;
import java.awt.event.MouseEvent;

public class MouseListenerClass implements java.awt.event.MouseListener {
    JPanel panel;
    SudokuBoard board;

    MouseListenerClass(JPanel panel, SudokuBoard board) {
        this.panel = panel;
        this.board = board;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        new SoundClass().tick(board);



    }

    @Override
    public void mouseReleased(MouseEvent e) {



    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
