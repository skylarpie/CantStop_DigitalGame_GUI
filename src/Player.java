import java.awt.Color;
import java.io.Serializable;
public class Player implements Serializable {
    private Color colour;
    private String name;
    private Integer age, columnsClaimed;
    public Player(String name, Color colour, Integer age){
        this.name = name;
        this.colour = colour;
        this.age = age;
    }
    public Integer getAge(){
        return this.age;
    }
    public String getName(){
        return this.name;
    }
    public Color getColour() {
        return this.colour;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColour(Color colour) {
        this.colour = colour;
    }
}

