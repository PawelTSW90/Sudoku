import java.awt.*;

public class BacktrackingChecker {
    int square;
    int column;
    int row;

    public void checkIfSolvable() {
        boolean increaseCurrentCell = false;
        boolean changePreviousCell = false;
        int numberToInput = 1;
        int currentValue;


        for (int x = 0; x < 81; x++) { //increase RANGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //skip buttons not for edit
            if (Buttons.boardButtons.get(x).getName().contains("N")) {
                x++;
            }

            if (changePreviousCell) {
                currentValue = Integer.parseInt(Buttons.boardButtons.get(x).getLabel());

                if (Buttons.boardButtons.get(x).getName().contains("N")) {
                    x--;
                } else if (currentValue == 9) {
                    Buttons.boardButtons.get(x).setLabel("");
                    x--;
                } else {
                    numberToInput = currentValue + 1;
                    Buttons.boardButtons.get(x).setLabel("");
                    changePreviousCell = false;
                    increaseCurrentCell = false;
                }


            }


            int square = getButtonPosition(Buttons.boardButtons.get(x)).square;
            int column = getButtonPosition(Buttons.boardButtons.get(x)).column;
            int row = getButtonPosition(Buttons.boardButtons.get(x)).row;
            for (int y = 0; y < 9; y++) {


                if (Buttons.squaresList.get(square - 1).get(y).getLabel().contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseCurrentCell = true;
                    break;
                } else if (Buttons.columnsList.get(column - 1).get(y).getLabel().contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseCurrentCell = true;
                    break;
                } else if (Buttons.rowsList.get(row - 1).get(y).getLabel().contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseCurrentCell = true;
                    break;
                }
            }
            if (!increaseCurrentCell) {
                Buttons.boardButtons.get(x).setLabel(String.valueOf(numberToInput));
                numberToInput = 1;
            } else {


                if (numberToInput == 9) {
                    x--;
                    changePreviousCell = true;


                } else {
                    numberToInput = numberToInput + 1;
                    increaseCurrentCell = false;
                }


            }


        }
    }


    public BacktrackingChecker getButtonPosition(Button button) {
        BacktrackingChecker checker = new BacktrackingChecker();
        String buttonName = button.getName();
        int square = Integer.parseInt(buttonName.substring(1, 2));
        int column = Integer.parseInt(buttonName.substring(4, 5));
        int row = Integer.parseInt(buttonName.substring(7, 8));
        checker.square = square;
        checker.column = column;
        checker.row = row;
        return checker;
    }
}
