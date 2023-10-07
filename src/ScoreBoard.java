import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ScoreBoard {

    private ArrayList<Integer> lowScore = new ArrayList<>(Arrays.asList());
    private int listLength;
    private int listIndex = listLength - 1;

    public void setLowScore (ArrayList<Integer> lowscore){
        this.lowScore = lowscore;
    }

    public ArrayList<Integer> getLowScore(){
        return lowScore;
    }

    public void setListLength (int listLength){
        this.listLength = listLength;
    }

    public int getListLength (){
        return listLength;
    }

    public void setListIndex(int listIndex){
        this.listIndex = listIndex;
    }

    public int getListIndex(){
        return listIndex;
    }

    public ScoreBoard(int listLength){
        this.listLength = listLength;
    }

    public ArrayList<Integer> lowScore(int score){
        if (lowScore.size() == listLength){;
            lowScore.set(listIndex, score);
        } else {
            lowScore.add(score);
        }

        //sorterar listan i storleksordning
        Collections.sort(lowScore);
        return lowScore;
    }

}
