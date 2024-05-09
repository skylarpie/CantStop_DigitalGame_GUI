import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.awt.*;
import java.awt.event.*;
public class PlayerSettingsGUI extends JFrame implements ActionListener{
    private PlayerSettings playerSettings;
    private DisplayOptions displayOptions;
    private JPanel playerInfoPanel, difficultyPanel, savePanel;
    private JLabel oneLabel, twoLabel, threeLabel, fourLabel, nameLabel, colourLabel, difficultyLabel;
    private JButton saveButton, difficultyButton, quitButton;
    private JComboBox oneColour, twoColour, threeColour, fourColour;
    private JTextField oneField, twoField, threeField, fourField;
    private Font font;
    private ArrayList<String> playerNames;
    private ArrayList<Color> playerColours;
    public PlayerSettingsGUI(PlayerSettings playerSettings, DisplayOptions displayOptions){
        this.playerSettings = playerSettings;
        this.font = displayOptions.getFont();

        //Creating the panels
        playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(5,3));

        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new FlowLayout());

        savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout());

        //Creating the components for the panels
        String[] playerColours = {"Blue", "Red", "Green", "Yellow", "Orange", "Purple"};
        oneColour = new JComboBox(playerColours);
        twoColour = new JComboBox(playerColours);
        threeColour = new JComboBox(playerColours);
        fourColour = new JComboBox(playerColours);
        colourLabel = new JLabel("Player Colour");

        oneLabel = new JLabel("Player 1");
        twoLabel = new JLabel("Player 2");
        threeLabel = new JLabel("Player 3");
        fourLabel = new JLabel("Player 4");

        oneField = new JTextField();
        twoField = new JTextField();
        threeField = new JTextField();
        fourField = new JTextField();
        nameLabel = new JLabel("Player Name");

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(this);
        difficultyLabel = new JLabel("Click to toggle difficulty");
        difficultyButton = new JButton("Easy");
        difficultyButton.addActionListener((this));

        //Adding the components to the panels
        playerInfoPanel.add(new JLabel(""));
        playerInfoPanel.add(nameLabel);
        playerInfoPanel.add(colourLabel);
        playerInfoPanel.add(oneLabel);
        playerInfoPanel.add(oneField);
        playerInfoPanel.add(oneColour);
        playerInfoPanel.add(twoLabel);
        playerInfoPanel.add(twoField);
        playerInfoPanel.add(twoColour);
        playerInfoPanel.add(threeLabel);
        playerInfoPanel.add(threeField);
        playerInfoPanel.add(threeColour);
        playerInfoPanel.add(fourLabel);
        playerInfoPanel.add(fourField);
        playerInfoPanel.add(fourColour);

        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyButton);

        savePanel.add(quitButton);
        savePanel.add(saveButton);

        //Adding panels to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(playerInfoPanel, BorderLayout.NORTH);
        getContentPane().add(difficultyPanel, BorderLayout.CENTER);
        getContentPane().add(savePanel, BorderLayout.SOUTH);

        setSize(400, 250);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }
    //Checks to see if duplicate colours have been selected
    public Boolean checkDuplicate(){
        String[] colourList = {oneColour.getSelectedItem().toString(),twoColour.getSelectedItem().toString(),threeColour.getSelectedItem().toString(),fourColour.getSelectedItem().toString()};
        Set tempSet = new HashSet();
        for(String str : colourList){
            if(!tempSet.add(str)){
                return true;
            }
        }
        return false;
    }
    //Checks if any JTextFields are empty and returns true if so
    public Boolean checkNameValidity(){
        JTextField[] nameList = {oneField, twoField, threeField, fourField};
        for(JTextField txt : nameList){
            if(txt.getText().equals("")){
                return true;
            }
        }
        return false;
    }
    //Updates the fonts of all of the JLabels on the JFrame with the selected font from the DisplaySettings class
    public void updateLabels(Font font){
        oneLabel.setFont(font);
        twoLabel.setFont(font);
        threeLabel.setFont(font);
        fourLabel.setFont(font);
        nameLabel.setFont(font);
        colourLabel.setFont(font);
        difficultyLabel.setFont(font);
    }
    public void actionPerformed(ActionEvent aevt){
        Object selected = aevt.getSource();
        if(selected.equals(quitButton)){
            this.dispose();
        }
        else if(selected.equals(difficultyButton)){
            if(difficultyButton.getText().equals("Easy")){
                difficultyButton.setBackground(Color.CYAN);
                difficultyButton.setText("Hard");
                playerSettings.toggleDifficulty(true);
            }
            else{
                difficultyButton.setBackground(null);
                difficultyButton.setText("Easy");
                playerSettings.toggleDifficulty(false);
            }
        }
        else if(selected.equals(saveButton)){
            //If there are duplicates give error message
            if(checkDuplicate()){
                //reusing difficulty label for these 2 errors messages
                difficultyLabel.setText("Duplicate colours not allowed");
            }
            //If names are blank give errors messages
            else if(checkNameValidity()){
                difficultyLabel.setText("Names cannot be blank");
            }
            //Else set the colours and names of the players
            else{
                //This is a very lazy solution at the moment, just trying to functionality for the iteration
                playerNames.clear();
                for(int x = 0; x < playerSettings.getPlayerList().size(); x++){
                    String name = playerNames.get(x);
                    //player.setName(name);
                }
                //playerSettings.setOneColour(oneColour.getSelectedItem().toString());
                //playerSettings.setTwoColour(twoColour.getSelectedItem().toString());
                //playerSettings.setThreeColour(threeColour.getSelectedItem().toString());
                //playerSettings.setFourColour(fourColour.getSelectedItem().toString());
                //playerSettings.setOneName(oneField.getText());
                //playerSettings.setTwoName(twoField.getText());
                //playerSettings.setThreeName(threeField.getText());
                //playerSettings.setFourName(fourField.getText());
                //difficultyLabel.setText("Changes Saved");
            }
        }
    }
}

