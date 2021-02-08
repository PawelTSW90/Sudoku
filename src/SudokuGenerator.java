import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    BacktrackingChecker checker = new BacktrackingChecker();

    public boolean generateFullBoard(ButtonsTemplateCreator creator) {
        boolean boardCreated = false;
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
                boardCreated = true;

            }

        }
        if(boardCreated){
            for(int x = 0; x<81; x++){
                if(!creator.getBoardButtonsTemplateList().get(x).getValue().equals("")){
                    creator.getBoardButtonsTemplateList().get(x).getButton().setBackground(Color.lightGray);
                    creator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
                }
            }
            return true;
        }
        checker.clearBoard(creator);
        return false;
    }

    public void turnButtonFocusableOff(ButtonsTemplateCreator creator){
        for(int x = 0; x<81; x++){
            if(creator.getBoardButtonsTemplateList().get(x).getButton().getBackground().equals(Color.lightGray)){
                creator.getBoardButtonsTemplateList().get(x).getButton().setFocusable(false);
            }
        }

    }

}
