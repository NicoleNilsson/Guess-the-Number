import java.util.ArrayList;
import java.util.Collections;

//klass för att skapa scoreboards
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

    //constructor av en scoreboard med önskad längd
    public ScoreBoard(int listLength){
        this.listLength = listLength;
    }

    //metod som returnerar score i en sorterad lista av den maxlängd som angetts via constructorn
    public ArrayList<Integer> scoreList(int score){
        if (scoreList.size() == listLength){;
            listIndex = listLength -1;
            scoreList.set(listIndex, score);
        } else {
            scoreList.add(score);
        }

        Collections.sort(scoreList);

        return scoreList;
    }

    //metod som returnerar scoreList som en sträng
    public ArrayList<String> scoreListAsString (){
        
        //clear för annars sparas listan varje gång den printats ut i guessinggame
        scoreListAsString.clear();

        scoreListAsString.add("Low Scores:");

        for (Integer score : scoreList){
            scoreListAsString.add(score.toString());
        }

        return scoreListAsString;  
    }
}
