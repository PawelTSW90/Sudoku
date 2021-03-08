import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListenerClass implements java.awt.event.MouseListener {
    JPanel panel;

    MouseListenerClass(JPanel panel){
        this.panel = panel;

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        panel.getComponent(2).setForeground(Color.BLACK);
        new SoundClass().tick();


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        panel.getComponent(2).setForeground(new Color(144, 44, 19));

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
