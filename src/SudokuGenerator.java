import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    BacktrackingChecker checker = new BacktrackingChecker();

    public void generateFullBoard(ButtonsTemplateCreator creator){
        Random randomValue = new Random();
        List<Integer> cellNumbersList = new ArrayList<>();
        for(int x = 0; x<81; x++){
            cellNumbersList.add(x);
        }

        while (cellNumbersList.size()>55){
            int value = randomValue.nextInt(9-1 + 1)+1;
            int randomCell = cellNumbersList.get(randomValue.nextInt(cellNumbersList.size()));
            if(checker.isNumberAllowed(randomCell, value, creator)){
                creator.getBoardButtonsTemplateList().get(randomCell).setValue(String.valueOf(value));
                creator.getBoardButtonsTemplateList().get(randomCell).getButton().setName("N");
                creator.getBoardButtonsTemplateList().get(randomCell).getButton().setLabel(String.valueOf(value));
                if(checker.checkBoard(creator)){
                    creator.getBoardButtonsTemplateList().get(randomCell).getButton().setLabel(String.valueOf(value));
                    cellNumbersList.removeIf(s->(s==randomCell));
                } else{
                    creator.getBoardButtonsTemplateList().get(randomCell).setValue("");
                    creator.getBoardButtonsTemplateList().get(randomCell).getButton().setName("");
                    creator.getBoardButtonsTemplateList().get(randomCell).getButton().setLabel("");

                }
            }



        }
    }




    public void generateSudokuTmp(ButtonsTemplateCreator creator){
        checker.checkBoard(creator);
        Random random = new Random();
        List<Integer> randomCell = new ArrayList<>();
        for(int x = 0; x<81;x++){
            randomCell.add(x);

        }
while(randomCell.size()>40) {
    int randomValue = randomCell.get(random.nextInt(randomCell.size()));
    creator.getBoardButtonsTemplateList().get(randomValue).setValue("");
    checker.displayNumbers(creator);
    setSudokuAsEasy(creator, randomCell, randomValue);
}







    }

    public void setSudokuAsEasy(ButtonsTemplateCreator creator, List<Integer> randomCell, int randomValue){
        String currentValue = creator.getBoardButtonsTemplateList().get(randomValue).getValue();
        if(!checker.multipleSolvingChecker(creator)){
            randomCell.removeIf(n->(n==randomValue));

        } else{
            creator.getBoardButtonsTemplateList().get(randomValue).setValue(String.valueOf(currentValue));
            checker.displayNumbers(creator);
        }


    }



}
