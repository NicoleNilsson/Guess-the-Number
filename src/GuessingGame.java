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
    
        //String [] menuEntries = {"Vad vill du göra?", "1. Spela igen", "2. Se Low Score-lista", "3. Avsluta spelet"};

        ArrayList<String> menuEntries = new ArrayList<>(Arrays.asList("Vad vill du göra?", "1. Spela igen", "2. Se Low Score-lista", "3. Avsluta spelet"));

        while (true) {

            //skapa objekt och ange tecken för hörn, sidor och skiljelinje i menyn
            CoolMenu gameMenu = new CoolMenu (menuEntries, '-', '|', '+');
            CoolMenu lowScoreBoard = new CoolMenu (lowScore.getScoreListAsString(), '-', '|', '+');
            
            

            int gameMenuWidth = gameMenu.calucalteMenuWidth(menuEntries) + 2;
            int lowScoreBoardWidth = gameMenu.calucalteMenuWidth(lowScore.getScoreListAsString()) + 2;
            int padding = gameMenuWidth / 2;
            int padding2 = lowScoreBoardWidth / 2;
            
            lowScore.scoreListAsString();

            for (int i = 0; i < menuEntries.size(); i++){
                System.out.println(gameMenu.createDevitionLine(gameMenuWidth));
                System.out.println(gameMenu.centerString(menuEntries.get(i), padding));
            }
            System.out.println(gameMenu.createDevitionLine(gameMenuWidth));

            choiceInMenu = gameScanner.nextLine();

                if (choiceInMenu.equals("1")){
                    turn = 1;
                    startGame();
                    break;
                    //för att stänga pågående spel
                } else if (choiceInMenu.equals("2")){                   
                    System.out.println("|" + lowScoreBoard.centerString("Low Scores:", 10) + "|");
                    for (int i = 0; i < lowScore.getScoreListAsString().size(); i++) {
                        System.out.println(lowScoreBoard.centerString(lowScore.getScoreListAsString().get(i), padding2));
                    }
                } else if (choiceInMenu.equals("3")){
                    System.out.println("Tack för den här gången!");
                    break;
                } else {
                    menuEntries.set(0, "Vänligen ange ett av följande alternativ!");
                }

        }
    }

    // private int calucalteMenuWidth(String [] menuEntries){
    //     int menuEntry = 0;

    //     for (String entry : menuEntries) {

    //         if (menuEntry < entry.length()){
    //             menuEntry = entry.length();
    //         }
    //     }
        
    //     return menuEntry;
    // }

    // private String centerString (String nonCenteredString, int padding){

    //     if (nonCenteredString.length() % 2 == 0){
    //         String centeredString = String.format("%" + (padding - (nonCenteredString.length()/2)) + "s%s%" + ((padding+1) - (nonCenteredString.length()/2)) + "s", "", nonCenteredString, "");
    //     return centeredString;
    //     }

    //     String centeredString = String.format("%" + (padding - (nonCenteredString.length()/2)) + "s%s%" + (padding - (nonCenteredString.length()/2)) + "s", "", nonCenteredString, "");
    //     return centeredString;
    // }

    // private String createDevitionLine (char devitionChar, int lineLength){
    //     String devitionLine = "";

    //     for (int i = 0; i < lineLength; i++){
    //         devitionLine = devitionLine + devitionChar;
    //     }

    //     if (lineLength % 2 == 0){
    //         devitionLine = devitionLine + devitionChar; 
    //     }

    //     return devitionLine;
    // }
}

