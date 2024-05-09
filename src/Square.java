import java.awt.Color;
import javax.swing.*;
public class Square extends JButton{
    private int depth;
    private Boolean top;

    public Square(int depth, Boolean top){
        this.setBackground(Color.lightGray);
        this.depth = depth;
        this.top = top;
    }
    public int getDepth(){
        return depth;
    }
    public void setLabel(Integer x){
        this.setLabel(Integer.toString(x));
    }
}
