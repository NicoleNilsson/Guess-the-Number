public class CoolMenu {

    private char horisontalChar;
    private char cornerChar;
    private char verticalChar;
    private String[] entries;
    
    public CoolMenu(String [] entries){

    }

    public CoolMenu(String [] entries, char horisontalChar, char verticalChar, char cornerChar){
        this.entries = entries;
        this.horisontalChar = horisontalChar;
        this.cornerChar = cornerChar;
        this.verticalChar = verticalChar;
    }

    public CoolMenu (String [] entries, char verticalChar){
        this.entries = entries;
        this.verticalChar = verticalChar;
    }
    
    
    public int calucalteMenuWidth(){
        int menuEntry = 0;

        for (String entry : entries) {

            if (menuEntry < entry.length()){
                menuEntry = entry.length();
            }
        }
        
        return menuEntry;
    }

    public String centerString (String nonCenteredString, int padding){

        if (nonCenteredString.length() % 2 == 0){
            String centeredString = String.format("%" + (padding - (nonCenteredString.length()/2)) + "s%s%" + ((padding+1) - (nonCenteredString.length()/2)) + "s", "", nonCenteredString, "");
        return verticalChar + centeredString + verticalChar;
        }

        String centeredString = String.format("%" + (padding - (nonCenteredString.length()/2)) + "s%s%" + (padding - (nonCenteredString.length()/2)) + "s", "", nonCenteredString, "");
        return verticalChar + centeredString + verticalChar;
    }

    public String createDevitionLine (int amountOfHorisontalChar){
        String devitionLine = "";

        for (int i = 0; i < amountOfHorisontalChar; i++){
            devitionLine = devitionLine + horisontalChar;
        }

        if (amountOfHorisontalChar % 2 == 0){
            devitionLine = devitionLine + horisontalChar; 
        }

        return cornerChar +  devitionLine + cornerChar;
    }
}
