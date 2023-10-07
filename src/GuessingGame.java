import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    
    //initieras här för att komma åt dem i flera metoder
    private int randomNumber;
    private int turn = 1;
    private int userGuess;
    private String addToLowScore;
    private String choiceInMenu;
    private Scanner gameScanner = new Scanner(System.in);

    //skapar objekt av ScoreBoard och ger lowscorelistan längden 5
    ScoreBoard lowScore = new ScoreBoard(5);

    public GuessingGame(){
    }

    public void startGame(){
        createRandomNumber(100, 1);
        System.out.println(randomNumber);
        //bara så jag kan se det slumpade numret
        
        System.out.println("Gissa ett tal mellan 1 och 100.");

        while (userGuess != randomNumber){
            System.out.print("Gissning " + turn + ": ");
            tryTheNumber();
            guessTheNumber();
        }
    }

    //metod som skapar ett slumpmässigt tal mellan 0-bound + add
    private int createRandomNumber(int bound, int add){
        //kan man göra på ett annat sätt?/varför?
        randomNumber = new Random().nextInt(bound) + add;
        //+1 för annars blir det 0-99
        return randomNumber;
    }
    
    //tar in userGuess
    //kontrollerar om userGuess är en int
    //skickar meddelande om det inte är en int och försöker igen
    private int tryTheNumber(){
        while (true){
            try {
            userGuess = gameScanner.nextInt();
            return userGuess;

            } catch (InputMismatchException e) {
                System.out.println("Du kan bara skriva ett tal med siffror. Försök igen!");
                System.out.print("Gissning " + turn + ": ");
                gameScanner.nextLine();
            }
        } 
    }

    //metod som jämför userGuess mot randomNumber
    private void guessTheNumber(){
        if (userGuess < randomNumber){
            System.out.println("Talet är större.");
            turn++;
            
        } else if (userGuess > randomNumber){
            System.out.println("Talet är mindre.");
            turn++;

        } else if (userGuess == randomNumber) {
            System.out.println("Rätt! Du gissade rätt på " + turn + " försök.");
            gameScanner.nextLine();
            addToLowScore();
        }
    }

    //metod där antal gissningar kontrolleras 
    //och du får välja att lägga till på lowscore-listan om du klassar in
    //+felhantering om ej anger ja eller nej
    private void addToLowScore(){

        if (lowScore.getScoreList().size() < lowScore.getListLength() || turn < lowScore.getScoreList().get(lowScore.getListIndex())){

            System.out.println("Vill du lägga till ditt resultat på lowscore-listan (Ja/Nej)? ");
            addToLowScore = gameScanner.nextLine();

            while ((!addToLowScore.equalsIgnoreCase("ja")) && (!addToLowScore.equalsIgnoreCase("nej"))){
                System.out.println("Vänligen ange Ja eller Nej: ");
                addToLowScore = gameScanner.nextLine();
            }
    
            if (addToLowScore.equalsIgnoreCase("ja")){
                lowScore.scoreList(turn);
                menu();

            } else if (addToLowScore.equalsIgnoreCase("nej")){
                menu();
            }
            
        } else {
            menu();
        }
    }

    //metod för meny
    private void menu(){

        ArrayList<String> menuEntries = new ArrayList<>(Arrays.asList("Vad vill du göra?", "1. Spela igen", "2. Se Low Score-lista", "3. Avsluta spelet"));

        while (true) {

            CoolMenu gameMenu = new CoolMenu (menuEntries, '-', '|', '+');
            CoolMenu lowScoreBoard = new CoolMenu (lowScore.getScoreListAsString(), '-', '|', '+');
            
            lowScore.scoreListAsString();

            for (int i = 0; i < menuEntries.size(); i++){
                System.out.println(gameMenu.createDevitionLine(gameMenu.findLongestEntry(menuEntries) + 2));
                System.out.println(gameMenu.centerString(menuEntries.get(i), gameMenu.createPadding()));
            }
            System.out.println(gameMenu.createDevitionLine(gameMenu.findLongestEntry(menuEntries) + 2));

            choiceInMenu = gameScanner.nextLine();

                if (choiceInMenu.equals("1")){
                    turn = 1;
                    startGame();
                    break;
                    //för att stänga pågående spel
                } else if (choiceInMenu.equals("2")){                   

                    for (int i = 0; i < lowScore.getScoreListAsString().size(); i++) {
                        System.out.println(lowScoreBoard.createDevitionLine(lowScoreBoard.findLongestEntry(lowScore.getScoreListAsString()) + 2));
                        System.out.println(lowScoreBoard.centerString(lowScore.getScoreListAsString().get(i), lowScoreBoard.createPadding()));
                    }
                    System.out.println(lowScoreBoard.createDevitionLine(lowScoreBoard.findLongestEntry(lowScore.getScoreListAsString()) + 2));

                } else if (choiceInMenu.equals("3")){
                    System.out.println("Tack för den här gången!");
                    break;
                } else {
                    menuEntries.set(0, "Vänligen ange ett av följande alternativ!");
                }

        }
    }
}

