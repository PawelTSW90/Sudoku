import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    BacktrackingChecker checker = new BacktrackingChecker();

    public boolean generateFullBoard(ButtonsTemplateCreator creator) {
        Random randomValue = new Random();
        List<Integer> cellNumbersList = new ArrayList<>();
        for (int x = 0; x < 81; x++) {
            cellNumbersList.add(x);
        }
        while (cellNumbersList.size() > 49) {
            int value = randomValue.nextInt(9 - 1 + 1) + 1;
            int randomCell = cellNumbersList.get(randomValue.nextInt(cellNumbersList.size()));
            if (checker.isNumberAllowed(randomCell, value, creator)) {
                creator.getBoardButtonsTemplateList().get(randomCell).setValue(String.valueOf(value));
                creator.getBoardButtonsTemplateList().get(randomCell).getButton().setName("N");
                creator.getBoardButtonsTemplateList().get(randomCell).getButton().setLabel(String.valueOf(value));
                cellNumbersList.removeIf(s -> (s == randomCell));

            }
        }

        if (checker.checkBoard(creator)) {
            if (!checker.multipleSolvingChecker(creator)) {
                System.out.println("ONLY ONE SOLUTION!");
                return true;


            } else {


                checker.clearBoard(creator);
            }

        } else {
            checker.clearBoard(creator);


        }
        return false;

    }

}
