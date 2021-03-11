import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListenerClass implements java.awt.event.MouseListener {
    JPanel panel;
    SudokuBoard board;


    public MouseListenerClass(SudokuBoard board){
        this.board = board;
    }



    MouseListenerClass(JPanel panel) {
        this.panel = panel;

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
        panel.getComponent(2).setForeground(Color.black);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.getComponent(2).setForeground(new Color(144, 44, 19));
    }
}