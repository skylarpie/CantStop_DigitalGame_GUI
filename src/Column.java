import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Column extends JPanel {
    private Integer numberOfSquares, numberOfBlanks;
    private int columnNumber;
    private JButton labelColumn;
    private Font labelFont;
    private Square[] squares;

    public Column(int columnNumber) {

        this.labelFont = new Font("Arial", Font.BOLD, 30);

        this.setLayout(new GridLayout(13, 0, 1, 1));
        this.setBackground(Color.RED);

        this.columnNumber = columnNumber;

        numberOfSquares = setNumberOfSquares();
        numberOfSquares += 1;
        numberOfBlanks = (-1 * (numberOfSquares / 2)) + 6;
        for (int y = 0; y < numberOfBlanks; y++) {
            this.add(new JLabel());
        }
        //Adding the number above each column of squares
        //labelColumn = new JButton(Integer.toString(columnNumber));
        //labelColumn.setHorizontalAlignment(SwingConstants.CENTER);
        //labelColumn.setFont(labelFont);
        //labelColumn.setBackground(Color.lightGray);
        //this.add(labelColumn);

        squares = new Square[numberOfSquares];
        //Adds the right amount of squares to each column
        for (int x = 0; x < numberOfSquares; x++) {
            if(x == 0){
                squares[x] = new Square(x, true);
                squares[x].setLabel(columnNumber);
                this.add(squares[x]);
            }
            else{
                squares[x] = new Square(x, false);
                this.add(squares[x]);
            }
        }
    }

    public void setBotColour(int height, Color colour){
        int colouredCubePosition = numberOfSquares - height;
        for(Component comp : this.getComponents()){
            if(comp instanceof Square){
                int depth = ((Square) comp).getDepth();
                if(colouredCubePosition == depth){
                    comp.setBackground(colour);
                }
            }
        }
    }

    //This updates the position of the whitecubes with the colour of the player
    public void updateWhiteCubeColours(int height, Color colour){
        int colouredCubePosition = numberOfSquares - height;
        if (colouredCubePosition == 0) {
            for (Component comp : this.getComponents()) {
                if (comp instanceof Square) {
                    comp.setBackground(colour);
                }
            }
        }
        for(Component comp : this.getComponents()){
            if(comp instanceof Square){
                int depth = ((Square) comp).getDepth();
                if(colouredCubePosition == depth){
                    comp.setBackground(colour);
                }
            }
        }
    }

    public void playerIsBust(){
        for(Component comp : this.getComponents()){
            if(comp instanceof Square){
                if(comp.getBackground() == Color.white){
                    comp.setBackground(Color.lightGray);
                }
            }
        }
    }
    public void resetSquareColours(ArrayList<Integer> colouredCubePositions){
        int tempCounter = 0;
        for(Integer position : colouredCubePositions){
            if(position != -1){
                if(position == numberOfSquares){
                    Color colour = null;
                    if(tempCounter == 0){
                        colour = Color.BLUE;
                    }
                    else if(tempCounter == 1){
                        colour = Color.GREEN;
                    }
                    else if(tempCounter == 2){
                        colour = Color.ORANGE;
                    }
                    else if(tempCounter == 3){
                        colour = Color.YELLOW;
                    }
                    else if(tempCounter == 4){
                        colour = Color.PINK;
                    }
                    for(Component comp : this.getComponents()){
                        if(comp instanceof Square){
                            comp.setBackground(colour);
                        }
                    }
                }
                else{
                    for(Component comp : this.getComponents()){
                        if(comp instanceof Square){
                            int depth = ((Square) comp).getDepth();
                            int squareHeight = numberOfSquares - depth;
                            if(!colouredCubePositions.contains(squareHeight) && squareHeight != numberOfSquares){
                                comp.setBackground(Color.lightGray);
                            }
                        }
                    }
                }
            }
            tempCounter += 1;
        }
    }
    public void setWhiteCubePosition(int height, Color colour) {
        int whiteCubePositions = numberOfSquares - height;
        for (Component comp : this.getComponents()) {
            if (comp instanceof Square) {
                int depth = ((Square) comp).getDepth();
                if (whiteCubePositions == depth){
                    comp.setBackground(colour);
                    }
                }
            }
        }
    public int getColumnNumber(){
        return columnNumber;
    }
    public Color[] getBackgrounds() {
        Color[] buttonBackgrounds = new Color[numberOfSquares];
        for (int x = 0; x < numberOfSquares; x++) {
            buttonBackgrounds[x] = squares[x].getBackground();
        }
        return buttonBackgrounds;
    }

    public void setBackgrounds(Color[] buttonBackgrounds) {
        int tempCounter = 0;
        for (Component comp : this.getComponents()) {
            if (comp instanceof Square) {
                comp.setBackground(buttonBackgrounds[tempCounter]);
                tempCounter += 1;
            }
        }
    }

    //Does math to determine how many squares should be in each column
    private Integer setNumberOfSquares() {
        if (columnNumber <= 7) {
            return (columnNumber - 1) * 2;
        } else {
            return 10 - (columnNumber - 8) * 2;
        }

    }
}