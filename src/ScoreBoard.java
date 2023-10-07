import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoard {

    private ArrayList<Integer> scoreList = new ArrayList<>();
    private ArrayList<String> scoreListAsString = new ArrayList<>();
    private int listLength;
    private int listIndex;

    public void setScoreList (ArrayList<Integer> scoreList){
        this.scoreList = scoreList;
    }

    public ArrayList<Integer> getScoreList(){
        return scoreList;
    }

    public void setScoreListAsString (ArrayList<String> scoreListAsString){
        this.scoreListAsString = scoreListAsString;
    }

    public ArrayList<String> getScoreListAsString(){
        return scoreListAsString;
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
            listIndex = listLength -1;
            scoreList.set(listIndex, score);
        } else {
            scoreList.add(score);
        }

        //sorterar listan i storleksordning
        Collections.sort(scoreList);

        return scoreList;
    }

    public ArrayList<String> scoreListAsString (){
        
        scoreListAsString.clear();

        scoreListAsString.add("Low Scores:");

        for (Integer score : scoreList){
            scoreListAsString.add(score.toString());
        }

        return scoreListAsString;
            
    }


}
