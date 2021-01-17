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


    public static Button fillRandomCell() {
        Random randomButton = new Random();
        int randomCell = randomButton.nextInt(cellsAvailable.size());
        return Buttons.boardButtons.get(randomCell);


        //cellsAvailable.remove(randomCell);
        //Buttons.boardButtons.remove(randomCell);

    }
}
