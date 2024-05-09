import java.awt.*;
import java.io.Serializable;

/**
 * This class stores information of the options chosen from the DisplayOptionsGUI
 * This class gets passed into the GUIS, so they can retrieve
 */
public class DisplayOptions implements Serializable{
    private Font font;
    private Boolean colourOn, animationsOn;
    private Game game;
    public DisplayOptions(Game game){
        this.game = game;
        this.font = new Font("Arial", Font.BOLD, 12);
    }
    //Getters and Setters
    public void toggleColourFilter(Boolean value){
        this.colourOn = value;
    }
    public void toggleAnimations(Boolean value){
        this.animationsOn = value;
    }
    public void setFont(Font font){
        this.font = font;
    }
    public Boolean getColourOn(){
        return this.colourOn;
    }
    public Boolean getAnimationsOn(){
        return this.animationsOn;
    }
    public Font getFont(){
        return this.font;
    }
    public void updateLabels(){
        game.updateLabels();
    }
    
}
