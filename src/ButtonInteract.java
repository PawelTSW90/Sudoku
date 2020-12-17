import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInteract implements ActionListener {
    boolean isButtonHighlighted = false;
    Button button;

    public ButtonInteract(Button button){
        this.button = button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        highLightButton();

    }

    public void highLightButton(){
        if(!isButtonHighlighted){
            button.setBackground(Color.getHSBColor(80,80,80));
            isButtonHighlighted = true;
        } else{
            button.setBackground(null);
            isButtonHighlighted = false;

        }



    }

    public void keyboardInput(){

    }


}
