import java.awt.*;

public class BacktrackingChecker {
    int square;
    int column;
    int row;

    public void checkIfSolvable() {
        boolean increaseNumber = false;
        int numberToInput = 1;


        for (int x = 0; x < 82; x++) {
            //skip buttons not for edit
            if (Buttons.boardButtons.get(x).getName().contains("N")) {
                x++;
            }


            int square = getButtonPosition(Buttons.boardButtons.get(x)).square;
            int column = getButtonPosition(Buttons.boardButtons.get(x)).column;
            int row = getButtonPosition(Buttons.boardButtons.get(x)).row;
            for (int y = 0; y < 9; y++) {

                if (Buttons.squaresList.get(square - 1).get(y).getLabel().contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseNumber = true;
                    break;
                } else if (Buttons.columnsList.get(column - 1).get(y).getLabel().contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseNumber = true;
                    break;
                } else if (Buttons.rowsList.get(row - 1).get(y).getLabel().contains(String.valueOf(numberToInput))) {
                    x--;
                    increaseNumber = true;
                    break;
                }
            }
            if (!increaseNumber) {
                Buttons.boardButtons.get(x).setLabel(String.valueOf(numberToInput));
                if(numberToInput ==9){
                    numberToInput = 1;
                }
            } else {
                numberToInput = numberToInput + 1;
                increaseNumber = false;


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
