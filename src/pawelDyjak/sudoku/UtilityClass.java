package pawelDyjak.sudoku;

import javax.swing.*;
import java.awt.*;

public final class UtilityClass {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int screenHeight = screenSize.height;
    private static final int screenWidth = screenSize.width;


    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void buttonConfigure(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

}
