import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DisplayOptionsGUI extends JFrame implements ActionListener {
    private DisplayOptions displayOptions;
    private JLabel styleLabel, sizeLabel, colourLabel, animationLabel;
    private JPanel stylePanel, sizePanel, colourPanel, animationPanel;
    private JComboBox fontStyle;
    private JSlider fontSlider;
    private JButton colourButton, saveButton, animationButton, quitButton;
    private Boolean colourOn, animationsOn;
    private Font font;
    public DisplayOptionsGUI(DisplayOptions displayOptions){
        this.displayOptions = displayOptions;
        colourOn = false;
        animationsOn = true;
        font = null;

        //Creating the displays for the different options
        // in order of which they appear on the use case
        stylePanel = new JPanel();
        stylePanel.setLayout(new FlowLayout());
        styleLabel = new JLabel("Select your preferred font style");
        stylePanel.add(styleLabel);
        String[] fontStyles = {"Arial", "Times New Roman", "Comic Sans MS"};
        stylePanel.add(fontStyle = new JComboBox(fontStyles));

        sizePanel = new JPanel();
        sizePanel.setLayout(new FlowLayout());
        sizeLabel = new JLabel("Set your preferred font size");
        sizePanel.add(sizeLabel);
        sizePanel.add(fontSlider = new JSlider(0,100,31));
        fontSlider.setMajorTickSpacing(25);
        fontSlider.setMinorTickSpacing(5);
        fontSlider.setPaintTicks(true);
        fontSlider.setPaintLabels(true);

        colourPanel = new JPanel();
        colourPanel.setLayout(new FlowLayout());
        colourLabel = new JLabel("Click the button to change the colour theme");
        colourPanel.add(colourLabel);
        colourPanel.add(colourButton = new JButton("Off"));
        colourButton.addActionListener(this);

        animationPanel = new JPanel();
        animationPanel.setLayout(new FlowLayout());
        animationLabel = new JLabel("Click to toggle animations");
        animationPanel.add(animationLabel);
        animationPanel.add(animationButton = new JButton("On"));
        animationButton.setBackground(Color.CYAN);
        animationButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(quitButton = new JButton("Quit"));
        quitButton.addActionListener(this);
        buttonPanel.add(saveButton = new JButton("Save Changes"));
        saveButton.addActionListener(this);

        getContentPane().setLayout(new GridLayout(5,1));
        getContentPane().add(stylePanel);
        getContentPane().add(sizePanel);
        getContentPane().add(colourPanel);
        getContentPane().add(animationPanel);
        getContentPane().add(buttonPanel);


        setSize(430,265);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }
    public void toggleColourFilter(Boolean value){
        if(value == true){
            colourButton.setBackground(Color.CYAN);
            colourButton.setText("On");
            displayOptions.toggleColourFilter(true);
        }
        else{
            colourButton.setBackground(null);
            colourButton.setText("Off");
            displayOptions.toggleColourFilter(false);
        }
    }
    public void toggleAnimations(Boolean value){
        if(value == true){
            animationButton.setBackground(Color.CYAN);
            animationButton.setText("On");
            displayOptions.toggleAnimations(true);
        }
        else{
            animationButton.setBackground(null);
            animationButton.setText("Off");
            displayOptions.toggleAnimations(false);
        }
    }
    public void setFont(){
        String style = fontStyle.getSelectedItem().toString();
        Integer size = fontSlider.getValue();
        Integer newSize = (size/10) + 9;
        this.font = new Font(style, Font.BOLD, newSize);
        if(newSize >= 14){
            setSize(500,300);
        }
        else{
            setSize(430,270);
        }
        displayOptions.setFont(this.font);
    }
    public void updateLabels(){
        displayOptions.updateLabels();
        styleLabel.setFont(font);
        sizeLabel.setFont(font);
        colourLabel.setFont(font);
        animationLabel.setFont(font);
    }
    public void actionPerformed(ActionEvent aevt){
        Object selected = aevt.getSource();
        if(selected.equals(quitButton)){
            this.dispose();
        }
        else if(selected.equals(colourButton))
        {
            String text = colourButton.getText();
            if(text.equals("Off")){
                toggleColourFilter(true);
            }
            else{
                toggleColourFilter(false);
            }
        }
        else if(selected.equals(animationButton)){
            String text = animationButton.getText();
            if(text.equals("Off")){
                toggleAnimations(true);
            }
            else {
                toggleAnimations(false);
            }
        }
       
        else if(selected.equals(saveButton)){
            setFont();
            updateLabels();
        }
    }
}

