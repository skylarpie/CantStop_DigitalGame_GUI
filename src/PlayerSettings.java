import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerSettings implements Serializable{
    private Boolean difficulty;
    private ArrayList<Player> playerList;
    public PlayerSettings(){
        this.playerList = playerList;
        this.difficulty = false;
    }
    //true means hard difficulty is on
    //All functions below are simple setters and getters
    public void toggleDifficulty(Boolean value){
        difficulty = value;
    }
    public Boolean getDifficulty(){
        return difficulty;
    }
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public void setPlayerNames(ArrayList<String> playerNames){
        for(int x = 0; x < playerList.size(); x++){
            Player player = playerList.get(x);
            String name = playerNames.get(x);
            player.setName(name);
        }
    }
    public void setPlayerColours(ArrayList<Color> playerColours){
        for(int x = 0; x < playerList.size(); x++){
            Player player = playerList.get(x);
            Color colour = playerColours.get(x);
            player.setColour(colour);
        }
    }
}

