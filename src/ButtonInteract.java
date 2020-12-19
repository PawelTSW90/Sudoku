import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    private static boolean isButtonHighlighted = false;
    Button focusedButton;
    Button button;

    public ButtonInteract(Button button) {
        this.button = button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        highLightButton();

    }

    public void highLightButton() {
        if (!isButtonHighlighted) {
            button.setBackground(Color.getHSBColor(80, 80, 80));
            button.setFocusable(true);
            button.requestFocus();
            isButtonHighlighted = true;
        } else {

            if (button.isFocusable()) {
                button.setBackground(null);
                isButtonHighlighted = false;
                button.setFocusable(false);

            } else {

                for (int x = 0; x < 81; x++) {
                    if (Buttons.boardButtons.get(x).isFocusable()) {
                        Buttons.boardButtons.get(x).setFocusable(false);
                        Buttons.boardButtons.get(x).setBackground(null);
                    }
                }
                button.setBackground(Color.getHSBColor(80, 80, 80));
                button.setFocusable(true);
                button.requestFocus();
                isButtonHighlighted = true;



            }
        }


    }

    public boolean getIsButtonHighlighted() {
        return isButtonHighlighted;
    }

    public Button getFocusedButton() {
        return focusedButton;
    }

    public Button getButton(){
        return button;
    }


}


