import java.awt.*;
import java.io.Serializable;

public class Bot implements Serializable {
    private String name;
    private String colourAsString;
    private Color colour;
    private Boolean isExist;
    private Integer age;
    private Boolean difficulty;
    public Bot(String colourAsString, Color colour, Boolean difficulty){
        this.colourAsString = colourAsString;
        this.colour = colour;
        this.name = colourAsString + " Bot";
        this.age = 2147483647;
        this.difficulty = difficulty;
    }

    public void makeMove(){

    }
    public void setExist(Boolean value){
        this.isExist = value;
    }
    public String getName(){
        return this.name;
    }
    public Color getColour(){
        return this.colour;
    }
    public Boolean getDifficulty(){
        return this.difficulty;
    }
    public String getColourAsString(){
        return colourAsString;
    }
}
