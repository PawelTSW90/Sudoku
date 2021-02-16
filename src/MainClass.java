import javax.naming.Context;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        new SudokuBoard().createSudokuBoard();
    }

    public static String handleRequest(Map<String,Object> input, Context context){
        new SudokuBoard().createSudokuBoard();
        return "hello";
    }
}

