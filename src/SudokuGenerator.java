import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    static List<Integer> cellsAvailable = new ArrayList<>();


    public static void generateNewBoard() {
        for (int x = 1; x < 82; x++) {
            cellsAvailable.add(x);
        }
        for(int y = 1; y<82; y++){
            fillRandomCell();
        }
    }


    public static void fillRandomCell() {
        Random random = new Random();
        int randomCell = random.nextInt(cellsAvailable.size()-1);
        cellsAvailable.removeIf(s->s.equals(randomCell));
        Buttons.boardButtons.get(randomCell).setLabel("Siema");
        Buttons.boardButtons.remove(randomCell);

    }
}
