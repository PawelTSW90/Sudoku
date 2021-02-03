import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    BacktrackingChecker checker = new BacktrackingChecker();



    public void generateSudoku(ButtonsTemplateCreator creator){
        checker.generateBoard(creator);
        Random random = new Random();
        List<Integer> randomCell = new ArrayList<>();
        for(int x = 0; x<81;x++){
            randomCell.add(x);

        }

        int randomValue = randomCell.get(random.nextInt(randomCell.size()));
        System.out.println(randomValue);

        creator.getBoardButtonsTemplateList().get(randomValue).setValue("");
        checker.displayNumbers(creator);





    }



}
