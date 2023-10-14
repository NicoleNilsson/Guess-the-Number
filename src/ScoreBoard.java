import java.util.ArrayList;
import java.util.Collections;

//klass för att skapa scoreboards
public class ScoreBoard {

    //instansvariabler
    private ArrayList<Integer> scoreList = new ArrayList<>();
    private ArrayList<String> scoreListAsString = new ArrayList<>();
    private int maxSize;
    private int listIndex;
    
    //constructor för en scoreboard med önskad längd som parameter
    public ScoreBoard(int maxSize){
        this.maxSize = maxSize;
    }

        //getters (behöver inga setters i GuessingGame men är lätt att lägga till vid behov)
        public ArrayList<Integer> getScoreList(){
            return scoreList;
        }
        public ArrayList<String> getScoreListAsString(){
            return scoreListAsString;
        }
        public int getMaxSize (){
            return maxSize;
        }
        public int getListIndex(){
            return listIndex;
        }

    //metod som returnerar score i en sorterad lista av den maxlängd som angetts via constructorn
    public ArrayList<Integer> scoreList(int score){
        if (scoreList.size() == maxSize){;
            scoreList.set(listIndex, score);
        } else {
            scoreList.add(score);
        }
        listIndex = scoreList.size() - 1;
        Collections.sort(scoreList);
        return scoreList;
    }

    //metod som returnerar scoreList som en sträng för att kunna använda den i cooltableformat
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
