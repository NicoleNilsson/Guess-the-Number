import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ScoreEntry {
    private int score;
    private ArrayList<Integer> lowScore = new ArrayList<>(Arrays.asList());

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public void setLowScore (ArrayList<Integer> lowscore){
        this.lowScore = lowscore;
    }

    public ArrayList<Integer> getLowScore(){
        return lowScore;
    }

    public ScoreEntry(){

    }

    public ScoreEntry(int score){
        this.score = score;
        lowScore(score);
        for (int sc : lowScore) {
            System.out.println(sc);
        }
    }

    public ArrayList<Integer> lowScore(int score){
        if (lowScore.size() == 5){;
            lowScore.set(4, score);
        } else {
            lowScore.add(score);
        }
        Collections.sort(lowScore);
        return lowScore;
    }

}
