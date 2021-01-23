import java.util.ArrayList;
import java.util.List;


public class SudokuGenerator {
    static List<Integer> cellsAvailable = new ArrayList<>();


    public void generateNewBoard() {
        for (int x = 1; x < 82; x++) {
            cellsAvailable.add(x);
        }

        fillRandomCell();

    }


    public void fillRandomCell() {
    /*
            while (Buttons.buttonsValues.contains("")){
                Random randomButton = new Random();
                Random randomNumber = new Random();
                try {
                    int randomCell = randomButton.nextInt(cellsAvailable.size() - 1);
                    int randomNumberInt = randomNumber.nextInt(10 - 1) + 1;
                    Buttons.buttonsValues.get(randomCell).setValue(String.valueOf(randomNumberInt));
                    String buttonDetails = Buttons.boardButtons.get(cellsAvailable.get(randomCell)).getName();
                    int square = Integer.parseInt(buttonDetails.substring(1, 2));
                    int column = Integer.parseInt(buttonDetails.substring(4, 5));
                    int row = Integer.parseInt(buttonDetails.substring(7, 8));
                    checkIfNumberIsAllowed(square, column, row, randomNumberInt, randomCell);


                } catch (IllegalArgumentException e) {

                }
            }





    }

    public void checkIfNumberIsAllowed(int square, int column, int row, int value, int location) {
        Random randomNumber = new Random();


        for (int x = 0; x < 9; x++) {
            if (Buttons.squaresList.get(square - 1).get(x).getLabel().contains(String.valueOf(value))) {
                value = randomNumber.nextInt(10 - 1) + 1;
                x = 0;

            } else if (Buttons.columnsList.get(column - 1).get(x).getLabel().contains(String.valueOf(value))) {
                value = randomNumber.nextInt(10 - 1) + 1;
                x = 0;
            } else if (Buttons.rowsList.get(row - 1).get(x).getLabel().contains(String.valueOf(value))) {
                value = randomNumber.nextInt(10 - 1) + 1;
                x = 0;
            }

        }



        Buttons.buttonsValues.get(location).setValue(String.valueOf(value));

        Buttons.boardButtons.get(cellsAvailable.get(location)).setLabel(String.valueOf(value));
        String buttonName = Buttons.boardButtons.get(cellsAvailable.get(location)).getName();
        Buttons.boardButtons.get(cellsAvailable.get(location)).setName(buttonName+"N");
        cellsAvailable.remove(location);
    }


     */

    }
}
