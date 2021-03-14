import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HighScoresCreator {

    public void writeScore(String name, String time, int position){
        StringBuffer buffer = new StringBuffer();
        int positionForEntryStart=0;
        int positionForEntryEnd =0;
        try {
            Path path = Paths.get("C:\\Users\\Pawel\\Desktop\\Sudoku\\HighScores.brd");
            List<String> lines = Files.readAllLines(path);
            for(int x = 0; x<lines.size(); x++){
                buffer.append(lines.get(x));
                buffer.append("\n");
            }
            for(int x = 0; x<buffer.length(); x++){
                if(String.valueOf(buffer.charAt(x)).equals(String.valueOf(position))){
                    positionForEntryStart = x+3;

                }
            }
            for (int y = positionForEntryStart; y<buffer.length(); y++){
                if(String.valueOf(buffer.charAt(y)).equals(String.valueOf(position+1))){
                    positionForEntryEnd = y;

                }
            }

            buffer.replace(positionForEntryStart, positionForEntryEnd-1, name+"  " + time);

            System.out.println(buffer);






        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
