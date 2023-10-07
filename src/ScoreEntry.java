import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ScoreEntry {

    private ArrayList<Integer> lowScore = new ArrayList<>(Arrays.asList());

    public void setLowScore (ArrayList<Integer> lowscore){
        this.lowScore = lowscore;
    }

    public ArrayList<Integer> getLowScore(){
        return lowScore;
    }

    public ScoreEntry(){
    }

    public ArrayList<Integer> lowScore(int score){
        if (lowScore.size() == 5){;
            lowScore.set(4, score);
        } else {
            lowScore.add(score);
        }
        
        //sorterar listan i storleksordning
        Collections.sort(lowScore);
        return lowScore;
    }

}
