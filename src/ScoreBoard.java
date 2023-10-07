import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ScoreBoard {

    private ArrayList<Integer> scoreList = new ArrayList<>(Arrays.asList());
    private int listLength;
    private int listIndex = listLength - 1;

    public void setScoreList (ArrayList<Integer> scoreList){
        this.scoreList = scoreList;
    }

    public ArrayList<Integer> getScoreList(){
        return scoreList;
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

    //constructor
    public ScoreBoard(int listLength){
        this.listLength = listLength;
    }

    public ArrayList<Integer> scoreList(int score){
        if (scoreList.size() == listLength){;
            scoreList.set(listIndex, score);
        } else {
            scoreList.add(score);
        }

        //sorterar listan i storleksordning
        Collections.sort(scoreList);
        return scoreList;
    }

}
