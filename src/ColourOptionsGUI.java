import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Write a description of class ColourOptionsGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColourOptionsGUI extends JFrame implements ActionListener
{
    private ColourOptions colourOptions;
    private JButton toggleButton;
    private JPanel content;
    private JFrame window;
    private Game game;

    public ColourOptionsGUI(ColourOptions colourOptions)
    {
        this.colourOptions = colourOptions;
        toggleButton = new JButton("Toggle Color Vision Mode");
        window = new JFrame("Color Vision Settings");
        //content = game.getGameGUI().getBoardPanel();
        window.setContentPane(content);
        
        toggleButton.addActionListener(new ActionListener(){
            private boolean colorVisionModeEnabled = false;
            
            public void actionPerformed(ActionEvent e){
                colorVisionModeEnabled = !colorVisionModeEnabled;
                 if (colorVisionModeEnabled) {
                    // Set colors for color vision deficiency, the colors are TBD
                    content.setBackground(Color.WHITE);
                    toggleButton.setBackground(Color.BLACK);
                    toggleButton.setForeground(Color.WHITE);
                } else {
                    // Set default colors, the colors are TBD
                    content.setBackground(Color.LIGHT_GRAY);
                    toggleButton.setBackground(Color.WHITE);
                    toggleButton.setForeground(Color.BLACK);
                }
            }

        });
        window.setSize(430, 265);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(false);
    
    }
    
    public void actionPerformed(ActionEvent aevt)
    {
        
    }
    


    
    
}
