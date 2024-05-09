import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
public class GameSetupGUI extends JFrame implements ActionListener{
    private JPanel topPanel, middlePanel, bottomPanel;
    private JLabel informationLabel, questionLabel, oneLabel, twoLabel, threeLabel, fourLabel;
    private JComboBox playerTwoBox, playerThreeBox, playerFourBox, oneColour, twoColour, threeColour, fourColour;
    private JTextField oneField, twoField, threeField, fourField;
    private JSpinner oneAge, twoAge, threeAge, fourAge;
    private Integer counter, numberOfOpponents, numberOfPlayers;
    private Boolean botsActive, notSelected;
    private JButton backButton, nextButton, yesButton, noButton, twoButton, threeButton, fourButton;
    private JComboBox[] playerList, colourList;
    private JSpinner[] ageList;
    private JLabel[] labelList;
    private JTextField[] nameList;
    private Game game;
    /*
    You can pretty much ignore this bloated class, it is designed in a way where once the information comes out of it,
    into the Game class, no more calls should have to be made to this class
     */
    public GameSetupGUI(Game game){
        this.setSize(400, 200);
        this.counter = 0;
        this.notSelected = true;
        this.game = game;

        // Creating Panels
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        // Creating components
        informationLabel = new JLabel("Welcome");

        questionLabel = new JLabel("Would you like to play against bots");
        yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        noButton = new JButton("No");
        noButton.addActionListener(this);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        // Adding to panels
        topPanel.add(informationLabel);

        bottomPanel.add(backButton);
        bottomPanel.add(nextButton);

        counter -= 1;

        // Adding panels to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        //Creating components that are used later on
        twoButton = new JButton("2");
        twoButton.addActionListener(this);
        threeButton = new JButton("3");
        threeButton.addActionListener(this);
        fourButton = new JButton("4");
        fourButton.addActionListener(this);

        String[] playerOptions = {"Player", "Bot"};
        playerTwoBox = new JComboBox(playerOptions);
        playerThreeBox = new JComboBox(playerOptions);
        playerFourBox = new JComboBox(playerOptions);

        oneLabel = new JLabel("Player 1");
        twoLabel = new JLabel("Player 2");
        threeLabel = new JLabel("Player 3");
        fourLabel = new JLabel("Player 4");
        String[] playerColours = {"Blue", "Green", "Yellow", "Orange", "Pink"};
        oneColour = new JComboBox(playerColours);
        twoColour = new JComboBox(playerColours);
        threeColour = new JComboBox(playerColours);
        fourColour = new JComboBox(playerColours);
        oneField = new JTextField();
        twoField = new JTextField();
        threeField = new JTextField();
        fourField = new JTextField();

        oneAge = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        twoAge = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        threeAge = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        fourAge = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));

        JComboBox[] playerList = {playerTwoBox, playerThreeBox, playerFourBox};
        JTextField[] nameList = {oneField, twoField, threeField, fourField};
        JSpinner[] ageList = {oneAge, twoAge, threeAge, fourAge};
        JComboBox[] colourList = {oneColour, twoColour, threeColour, fourColour};
        JLabel[] labelList = {oneLabel, twoLabel, threeLabel, fourLabel};

        askBot();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
    public void askBot()
    {
        informationLabel.setText("Welcome");
        questionLabel = new JLabel("Would you like to play against bots");
        middlePanel.removeAll();
        middlePanel.revalidate();
        middlePanel.repaint();
        middlePanel.setLayout(new FlowLayout());
        middlePanel.add(questionLabel);
        middlePanel.add(yesButton);
        middlePanel.add(noButton);
        counter += 1;
    }
    public void askPlayerCount()
    {
        this.setSize(420, 140);
        informationLabel.setText("Select the number of players");
        middlePanel.removeAll();
        middlePanel.revalidate();
        middlePanel.repaint();
        middlePanel.setLayout(new FlowLayout());
        middlePanel.add(twoButton);
        middlePanel.add(threeButton);
        middlePanel.add(fourButton);
        counter += 1;
        notSelected = true;
    }
    public void askBotCount()
    {
        counter += 1;
        middlePanel.removeAll();
        middlePanel.revalidate();
        middlePanel.repaint();
        middlePanel.setLayout(new FlowLayout());
        JLabel[] labelList = {oneLabel, twoLabel, threeLabel, fourLabel};
        JComboBox[] playerList = {playerTwoBox, playerThreeBox, playerFourBox};
        for(int x = 1; x <= numberOfOpponents; x++)
        {
            middlePanel.add(labelList[x]);
            int y = x - 1;
            middlePanel.add(playerList[y]);
        }
        informationLabel.setText("Choose whether these are players or AI");
        nextButton.setText("Next");
        pack();
    }
    public void askPlayerName()
    {
        counter += 1;
        informationLabel.setText("Please enter in the player names and colours");
        middlePanel.removeAll();
        middlePanel.revalidate();
        middlePanel.repaint();
        middlePanel.setLayout(new GridLayout(numberOfPlayers, 3));
        JLabel[] labelList = {oneLabel, twoLabel, threeLabel, fourLabel};
        JTextField[] nameList = {oneField, twoField, threeField, fourField};
        JComboBox[] colourList = {oneColour, twoColour, threeColour, fourColour};
        JComboBox[] playerList = {playerTwoBox, playerThreeBox, playerFourBox};
        for(int x = 0; x < numberOfPlayers; x++)
        {
            middlePanel.add(labelList[x]);
            labelList[x].setHorizontalAlignment(SwingConstants.CENTER);
            if(x == 0)
            {
                middlePanel.add(nameList[x]);
            }
            else
            {
                int y = x -1;
                if(playerList[y].getSelectedItem().toString().equals("Bot"))
                {
                    middlePanel.add(new JLabel("Bot"));
                }
                else
                {
                    middlePanel.add(nameList[x]);
                }
            }
            middlePanel.add(colourList[x]);
        }
        nextButton.setText("Next");
        pack();
    }
    public void askPlayerAge()
    {
        counter += 1;
        informationLabel.setText("Please enter your ages to determine who goes first");
        middlePanel.removeAll();
        middlePanel.revalidate();
        middlePanel.repaint();
        middlePanel.setLayout(new FlowLayout());
        JLabel[] labelList = {oneLabel, twoLabel, threeLabel, fourLabel};
        JSpinner[] ageList = {oneAge, twoAge, threeAge, fourAge};
        JComboBox[] playerList = {playerTwoBox, playerThreeBox, playerFourBox};
        for(int x = 0; x < numberOfPlayers; x++)
        {
            middlePanel.add(labelList[x]);
            if(x == 0)
            {
                middlePanel.add(ageList[x]);
            }
            else
            {
                int y = x -1;
                if(playerList[y].getSelectedItem().toString().equals("Bot"))
                {
                    middlePanel.add(new JLabel("Bot"));
                }
                else
                {
                    middlePanel.add(ageList[x]);
                }
            }
        }
        nextButton.setText("Start Game");
        pack();
    }
    public void startGame(){
        JTextField[] nameList = {oneField, twoField, threeField, fourField};
        JSpinner[] ageList = {oneAge, twoAge, threeAge, fourAge};
        JComboBox[] colourList = {oneColour, twoColour, threeColour, fourColour};
        String[] playerNames = new String[numberOfPlayers];
        String[] playerColours = new String[numberOfPlayers];
        Integer[] playerAges = new Integer[numberOfPlayers];
        for(int x = 0; x < numberOfPlayers; x++){
            playerNames[x] = nameList[x].getText();
            playerColours[x] = colourList[x].getSelectedItem().toString();
            int value = (int) ageList[x].getValue();
            playerAges[x] = value;
        }
        game.startGame(playerNames, playerColours, playerAges);
        dispose();
    }
    public Integer getNumberOfPlayers(){
        return this.numberOfPlayers;
    }
    public void actionPerformed(ActionEvent aevt) {

        // Get the object that was selected in the gui
        Object selected = aevt.getSource();
        // Getting difficulty
        if(selected.equals(yesButton))
        {
            botsActive = true;
            yesButton.setBackground(Color.cyan);
            noButton.setBackground(null);
        }
        else if(selected.equals(noButton))
        {
            botsActive = false;
            yesButton.setBackground(null);
            noButton.setBackground(Color.cyan);
        }
        else if(selected.equals(twoButton))
        {
            twoButton.setBackground(Color.cyan);
            threeButton.setBackground(null);
            fourButton.setBackground(null);
            numberOfOpponents = 1;
            numberOfPlayers = 2;
        }
        else if(selected.equals(threeButton))
        {
            twoButton.setBackground(null);
            threeButton.setBackground(Color.cyan);
            fourButton.setBackground(null);
            numberOfOpponents = 2;
            numberOfPlayers = 3;
        }
        else if(selected.equals(fourButton))
        {
            twoButton.setBackground(null);
            threeButton.setBackground(null);
            fourButton.setBackground(Color.cyan);
            numberOfOpponents = 3;
            numberOfPlayers = 4;
        }
        else if(selected.equals(nextButton))
        {
            if(counter >= 0 && counter < 5)
            {
                Component[] components = middlePanel.getComponents();
                for(Component component : components)
                {
                    if(component.getBackground() == Color.cyan)
                    {
                        notSelected = false;
                    }
                }
                if(notSelected == true)
                {
                    informationLabel.setText("You must select an option");
                }
                else if(notSelected == false)
                {
                    if(counter == 0)
                    {
                        askPlayerCount();
                    }
                    else if(counter == 1)
                    {
                        if(botsActive == true)
                        {
                            askBotCount();
                        }
                        else if(botsActive == false)
                        {
                            askPlayerName();
                            counter += 1;
                        }

                    }
                    else if(counter == 2)
                    {
                        askPlayerName();
                    }
                    else if(counter == 3)
                    {
                        components = middlePanel.getComponents();
                        String[] colourCheckList = {oneColour.getSelectedItem().toString(),twoColour.getSelectedItem().toString(),threeColour.getSelectedItem().toString(),fourColour.getSelectedItem().toString()};
                        Boolean colourCheck = true;
                        Set tempSet = new HashSet();
                        for(Component component : components){
                            if(component instanceof JComboBox){
                                JComboBox tempCombo = (JComboBox) component;
                                if(!tempSet.add(tempCombo.getSelectedItem().toString())){
                                    informationLabel.setText("You cannot choose duplicate colours");
                                    colourCheck = false;
                                }
                            }
                        }
                        Boolean nameCheck = true;
                        for(Component component : components)
                        {
                            if(component instanceof JTextField)
                            {
                                JTextField tempField = (JTextField) component;
                                if (tempField.getText().isEmpty()){
                                    informationLabel.setText("You must enter in a name");
                                    nameCheck = false;
                                }
                            }
                        }
                        if (colourCheck && nameCheck){
                            askPlayerAge();
                        }
                    }
                    else if(counter == 4)
                    {
                        Boolean ageCheck = true;
                        for(Component component : components)
                        {
                            if(component instanceof JSpinner)
                            {
                                JSpinner tempSpinner = (JSpinner) component;
                                Integer value = (Integer) tempSpinner.getValue();
                                if (!(value instanceof Integer)){
                                    informationLabel.setText("You must enter in a valid age");
                                    ageCheck = false;
                                }
                            }
                        }
                        if (ageCheck){
                            startGame();
                        }
                    }
                }
            }
        }
        else if(selected.equals(backButton))
        {
            if(counter == 0)
            {
                informationLabel.setText("You cannot go back!");
            }
            else if(counter == 1)
            {
                counter -= 2;
                askBot();
            }
            else if(counter == 2)
            {
                counter -= 2;
                askPlayerCount();
            }
            else if(counter == 3)
            {
                if(botsActive == true)
                {
                    counter -= 2;
                    askBotCount();
                }
                else
                {
                    counter -= 3;
                    askPlayerCount();
                }
            }
            else if(counter == 4)
            {
                counter -= 2;
                askPlayerName();
            }
        }
    }
}