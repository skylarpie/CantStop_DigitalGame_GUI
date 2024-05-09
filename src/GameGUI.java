import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameGUI extends JFrame implements ActionListener{
    private JMenuBar menuBar;
    private JMenu options;
    private JMenuItem playerMenu, displayMenu, save, load;
    private JPanel topPanel, boardPanel, bottomPanel,rolledNumbersLabelPanel, combinationButtonPanel;
    private JLabel playerTurn;
    private JDialog rollDiceDialog;
    private JButton startGameButton, rollDiceButton;
    private Integer rows, columns, counter;
    private DisplayOptions displayOptions;
    private Game game;
    private Boolean gameStarted, rollDiceDialogOpen;
    private Player currentPlayer;
    private Bot currentBot;
    private WhiteCube whiteCube;
    private Turn turn;
    private HashMap<String,Integer> redCubePositions, blueCubePositions, greenCubePositions, yellowCubePositions, orangeCubePositions, pinkCubePositions;
    public GameGUI(Game game, DisplayOptions displayOptions){
        this.game = game;
        this.displayOptions = displayOptions;
        this.counter = 0;
        gameStarted = false;
        rollDiceDialogOpen = false;
        rows = 1;
        columns = 11;
        //Creating the menu components
        menuBar = new JMenuBar();

        options = new JMenu("Options");

        playerMenu = new JMenuItem("Player Settings");
        playerMenu.addActionListener(this);
        displayMenu = new JMenuItem("Display Options");
        displayMenu.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        load = new JMenuItem("Load");
        load.addActionListener(this);

        options.add(playerMenu);
        options.add(displayMenu);
        options.addSeparator();
        options.add(save);
        options.add(load);

        menuBar.add(options);

        //Creating the top panel, this will say whose turn it is
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        playerTurn = new JLabel("");

        topPanel.add(playerTurn);

        //Creating the panel with the display of the actual board game
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, columns,1,1));
        boardPanel.setBackground(Color.RED);
        for(int x = 0; x < columns; x++)
        {
            int y = x + 2;
            boardPanel.add(new Column(y));
        }

        //Creating bottom panel which will show the options the player has/dice rolls
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(this);

        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (!rollDiceDialogOpen && gameStarted) {
            		rollDice();
            	}
            }});

        bottomPanel.add(startGameButton);
        bottomPanel.add(rollDiceButton);

        //Setting up the JFrame then adding the panels and menu
        getContentPane().setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setSize(800,800);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }
    public void takeTurn(){
        /**
         This should be done in Turn class,
         You can create a method to check if a column has been claimed by using the knowledge of the number of squares in each column
         and the position of the white cubes in each column
         After the user's turn ends you can replace the white cubes with the colored cubes.
         Make sure to use CubePositions.(Color)CubePositions.setPosition(String column, int position) to replace the values in the
         HashMap so we have an updated Hashmap after each turn until the game ends.
         **/

        whiteCube = new WhiteCube();
        startGameButton.setText(("End Turn"));
        if (!isPlayer()) {
            currentBot = getBot();
            Random rand = new Random();
            int n1 = rand.nextInt(6);
            n1 += 1;
            int n2 = rand.nextInt(6);
            n2 += 1;
            int n3 = rand.nextInt(6);
            n3 += 1;
            int n4 = rand.nextInt(6);
            n4 += 1;
            int sum1 = n1 + n2;
            int sum2 = n3 + n4;
            String currentColour = currentBot.getColourAsString();
            int col1Position = 0;
            int col2Position = 0;
            String col1 = null;
            String col2 = null;
            if(!currentBot.getDifficulty()){
                if (sum1 == 2){
                    col1 = "col2";
                }
                if(sum1 == 3){
                    col1 = "col3";
                }
                if(sum1 == 4){
                    col1 = "col4";
                }
                if(sum1 == 5){
                    col1 = "col5";
                }
                if(sum1 == 6){
                    col1 = "col6";
                }
                if(sum1 == 7){
                    col1 = "col7";
                }
                if(sum1 == 8){
                    col1 = "col8";
                }
                if(sum1 == 9){
                    col1 = "col9";
                }
                if(sum1 == 10){
                    col1 = "col10";
                }
                if(sum1 == 11){
                    col1 = "col11";
                }
                if(sum1 == 12){
                    col1 = "col12";
                }

                if(sum2 == 2){
                    col2 = "col2";
                }
                if(sum2 == 3){
                    col2 = "col3";
                }
                if(sum2 == 4){
                    col2 = "col4";
                }
                if(sum2 == 5){
                    col2 = "col5";
                }
                if(sum2 == 6){
                    col2 = "col6";
                }
                if(sum2 == 7){
                    col2 = "col7";
                }
                if(sum2 == 8){
                    col2 = "col8";
                }
                if(sum2 == 9){
                    col2 = "col9";
                }
                if(sum2 == 10){
                    col2 = "col10";
                }
                if(sum2 == 11){
                    col2 = "col11";
                }
                if(sum2 == 12){
                    col2 = "col12";
                }

                if(currentColour.equals("Blue")){
                    col1Position = CubePositions.BlueCubePositions.getPosition(sum1);
                    if(col1Position == -1){
                        col1Position += 2;
                    }
                    else{
                        col1Position += 1;
                    }
                    col2Position = CubePositions.BlueCubePositions.getPosition(sum2);
                    if(col2Position == -1){
                        col2Position += 2;
                    }
                    else{
                        col2Position += 1;
                    }
                    CubePositions.BlueCubePositions.setPosition(col1, col1Position);
                    CubePositions.BlueCubePositions.setPosition(col2, col2Position);
                }
                if(currentColour.equals("Green")){
                    col1Position = CubePositions.GreenCubePositions.getPosition(sum1);
                    if(col1Position == -1){
                        col1Position += 2;
                    }
                    else{
                        col1Position += 1;
                    }

                    col2Position = CubePositions.GreenCubePositions.getPosition(sum2);
                    if(col2Position == -1){
                        col2Position += 2;
                    }
                    else{
                        col2Position += 1;
                    }

                    CubePositions.GreenCubePositions.setPosition(col1, col1Position);
                    CubePositions.GreenCubePositions.setPosition(col2, col2Position);

                }

                if(currentColour.equals("Orange")){
                    col1Position = CubePositions.OrangeCubePositions.getPosition(sum1);
                    if(col1Position == -1){
                        col1Position += 2;
                    }
                    else{
                        col1Position += 1;
                    }

                    col2Position = CubePositions.OrangeCubePositions.getPosition(sum2);
                    if(col2Position == -1){
                        col2Position += 2;
                    }
                    else{
                        col2Position += 1;
                    }

                    CubePositions.OrangeCubePositions.setPosition(col1, col1Position);
                    CubePositions.OrangeCubePositions.setPosition(col2, col2Position);
                }
                if(currentColour.equals("Yellow")){
                    col1Position = CubePositions.YellowCubePositions.getPosition(sum1);
                    if(col1Position == -1){
                        col1Position += 2;
                    }
                    else{
                        col1Position += 1;
                    }

                    col2Position = CubePositions.YellowCubePositions.getPosition(sum2);
                    if(col2Position == -1){
                        col2Position += 2;
                    }
                    else{
                        col2Position += 1;
                    }

                    CubePositions.YellowCubePositions.setPosition(col1, col1Position);
                    CubePositions.YellowCubePositions.setPosition(col2, col2Position);
                }
                if(currentColour.equals("Pink")){
                    col1Position = CubePositions.PinkCubePositions.getPosition(sum1);
                    if(col1Position == -1){
                        col1Position += 2;
                    }
                    else{
                        col1Position += 1;
                    }

                    col2Position = CubePositions.PinkCubePositions.getPosition(sum2);
                    if(col2Position == -1){
                        col2Position += 2;
                    }
                    else{
                        col2Position += 1;
                    }

                    CubePositions.PinkCubePositions.setPosition(col1, col1Position);
                    CubePositions.PinkCubePositions.setPosition(col2, col2Position);
                }
            }
            int tempCounter = counter + 1;
            if(tempCounter < game.getNumberOfPlayers()){
                counter += 1;
            }
            else{
                counter = 0;
            }
            gameStarted = true;
            endTurnBot(sum1, col1Position);
            endTurnBot(sum2, col2Position);
            resetSquareColours(sum1);
            resetSquareColours(sum2);
            takeTurn();
        }
        else {
            rollDiceButton.setVisible(true);
        }
        playerTurn.setText("It is " + game.playerTurnName(counter) +"'s turn");

    }

    //This method sets all white squares back to gray
    public void playerIsBust(){
        for(Component column : boardPanel.getComponents()){
            if(column instanceof Column){
                Column col = (Column) column;
                col.playerIsBust();
            }
        }
    }

    public void endTurnBot(int columnNumber, int height){
        for(Component column : boardPanel.getComponents()){
            if(column instanceof Column){
                Column col = (Column) column;
                if(columnNumber == col.getColumnNumber()){
                    col.setBotColour(height, currentBot.getColour());
                }
            }
        }
    }

    public void endTurn(){
        //This updates the white cube position to the colour of the player
        HashMap<String, int[]> positions = whiteCube.getPositions();
        for(String cube : positions.keySet()) {
            int[] valuePositions = positions.get(cube);
            int columnNumber = valuePositions[0];
            int height = valuePositions[1];
            for(Component column : boardPanel.getComponents()){
                if(column instanceof Column){
                    Column col = (Column) column;
                    if(columnNumber == col.getColumnNumber()){
                        col.updateWhiteCubeColours(height, currentPlayer.getColour());
                    }
                }
            }
            resetSquareColours(columnNumber);
        }
    }


    //Sets cubes back to their colour if they were not default (lightgray)
    public void resetSquareColours(int columnNumber){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        if (columnNumber != -1) {
            if(CubePositions.BlueCubePositions.cubeExistsInColumn(columnNumber)){
                Integer bluePositions = CubePositions.BlueCubePositions.getPosition(columnNumber);
                positions.add(bluePositions);
            }
            if(!(CubePositions.BlueCubePositions.cubeExistsInColumn(columnNumber))){
                positions.add(-1);
            }
            if(CubePositions.GreenCubePositions.cubeExistsInColumn(columnNumber)){
                Integer greenPositions = CubePositions.GreenCubePositions.getPosition(columnNumber);
                positions.add(greenPositions);
            }
            if(!(CubePositions.GreenCubePositions.cubeExistsInColumn(columnNumber))){
                positions.add(-1);
            }
            if(CubePositions.OrangeCubePositions.cubeExistsInColumn(columnNumber)){
                Integer orangePositions = CubePositions.OrangeCubePositions.getPosition(columnNumber);
                positions.add(orangePositions);
            }
            if(!(CubePositions.OrangeCubePositions.cubeExistsInColumn(columnNumber))){
                positions.add(-1);
            }
            if(CubePositions.YellowCubePositions.cubeExistsInColumn(columnNumber)){
                Integer yellowPositions = CubePositions.YellowCubePositions.getPosition(columnNumber);
                positions.add(yellowPositions);
            }
            if(!(CubePositions.YellowCubePositions.cubeExistsInColumn(columnNumber))){
                positions.add(-1);
            }
            if(CubePositions.PinkCubePositions.cubeExistsInColumn(columnNumber)){
                Integer pinkPositions = CubePositions.PinkCubePositions.getPosition(columnNumber);
                positions.add(pinkPositions);
            }
            if(!(CubePositions.PinkCubePositions.cubeExistsInColumn(columnNumber))){
                positions.add(-1);
            }
        }
        for(Component column : boardPanel.getComponents()){
            if(column instanceof Column){
                Column col = (Column) column;
                if(columnNumber == col.getColumnNumber()){
                    col.resetSquareColours(positions);
                }
            }
        }
    }

    //Moves the white cubes on the board but does not update the colour of previous whitecubes
    public void moveWhiteCubes(HashMap<String, int[]> whiteCubePositions){
        for(String i : whiteCubePositions.keySet()){
            int[] positions = whiteCubePositions.get(i);
            int columnNumber = positions[0];
            if(columnNumber == -1){
            }
            else{
                int squareNumber = positions[1];
                for(Component column : boardPanel.getComponents()){
                    if(column instanceof Column){
                        Column col = (Column) column;
                        if(columnNumber == col.getColumnNumber()){
                            col.setWhiteCubePosition(squareNumber, Color.WHITE);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Returns the current player as an instance of Player
     * 
     * @return the current player
     */
    public Player getPlayer() {
    	return game.getPlayer(counter);
    }
    
    public Bot getBot() {
    	return game.getBot(counter);
    }
    
    public Boolean isPlayer() {
    	return game.isPlayer(counter);
    }
    
    public void checkForLabel(){
        if(playerTurn.getText() == "" && gameStarted == true){
            startGameButton.setText(("End Turn"));
            playerTurn.setText("It is " + game.playerTurnName(counter) +"'s turn");
        }
    }
    public void rollDice(){
        // create an instance of Turn class
        turn = new Turn(this);
        if (isPlayer()) {
        	// store the current player in a variable
        	currentPlayer = getPlayer();
        } else {
        	currentBot = getBot();
        }
        
        // call the roll() method on the instance of Turn class to get the rolled numbers
        List<Integer> rolledNumbers = turn.roll();

        // call the getCombi() method on the instance of Turn class to the get the combination of rolled numbers
        Set<List<List<Integer>>> combinations = turn.getCombi(rolledNumbers);

        rollDiceDialog = new JDialog();
        rollDiceDialog.setLayout(new BorderLayout());
        rollDiceDialog.setTitle("Combinations");
        rollDiceDialog.setSize(500,500);
        rollDiceDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        rollDiceDialog.setResizable(false);
        rollDiceDialogOpen = true; 
        
        // create a panel to hold the label telling the user the rolled numbers
        rolledNumbersLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // create a label which displays the rolled numbers to the user
        JLabel rolledNumbersLabel = new JLabel("These are the rolled numbers: ");
        for (int number : rolledNumbers) {
        	rolledNumbersLabel.setText(rolledNumbersLabel.getText() + number + " ");
        }
        rolledNumbersLabelPanel.add(rolledNumbersLabel);
        
        //create a panel to hold the label asking the user to select a combination
        JPanel selectACombinationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // create a label which asks the user to select a combination
        JLabel selectACombinationLabel = new JLabel("Please select a combination from the below options");
        
        selectACombinationPanel.add(selectACombinationLabel);
    
        // create a panel to hold the combination buttons
        combinationButtonPanel = new JPanel();
        combinationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        // create a button for each combination
        for (List<List<Integer>> combinationList : combinations) {
        	// List<List<Integer>> combinationList = new ArrayList<>(combinationSet);
            JButton button = new JButton(combinationList.get(0).toString() + " and " + combinationList.get(1).toString());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	String buttonText = button.getText();
                	String[] pairsString = buttonText.split(" and ");
                	String[] pair1String = pairsString[0].replaceAll("\\[|\\]", "").split(", ");
                	List<Integer> pair1List = new ArrayList<>();
                	for (String string : pair1String) {
                		pair1List.add(Integer.parseInt(string));
                	}
                	String[] pair2String = pairsString[1].replaceAll("\\[|\\]", "").split(", ");
                	List<Integer> pair2List = new ArrayList<>();
                	for (String string : pair2String) {
                		pair2List.add(Integer.parseInt(string));
                	}
                	int firstSum = pair1List.get(0) + pair1List.get(1); // firstSum stores the sum of the 1st pair
                	int secondSum = pair2List.get(0) + pair2List.get(1); // secondSum stores the sum of the 2nd pair
                	
                	Container contentPaneToRemove = rollDiceDialog.getContentPane();
                	
                	contentPaneToRemove.removeAll();
                	
                	JPanel chosenCombinationLabelPanel = new JPanel();
                	chosenCombinationLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                	JLabel chosenCombinationLabel = new JLabel();
                	chosenCombinationLabel.setText("You chose the combination: " + pair1List.toString() + " and " + pair2List.toString());
                	chosenCombinationLabelPanel.add(chosenCombinationLabel);
                	
                	JPanel confirmationButtonPanel = new JPanel();
                	confirmationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                	JButton confirmationButton = new JButton("Confirm");
                	confirmationButton.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			if (turn.canMoveCube(firstSum, secondSum, currentPlayer, game, whiteCube)) {
                				rollDiceDialog.dispose();
                    			rollDiceDialogOpen = false;
                			} else {
                				Container contentPaneToRemove = rollDiceDialog.getContentPane();
                				contentPaneToRemove.removeAll();
                            	
                                
                                JPanel chooseAnotherCombinationPanel = new JPanel();
                                chooseAnotherCombinationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                
                                JLabel chooseAnotherCombinationLabel = new JLabel();
                                
                                /**
                                 *  convert the button text back to the ArrayList. At the end if we have time we can create a 
                                 *  static class and add these kind of methods to it
                                 */
                                String buttonTextCopy = buttonText;
                                buttonTextCopy = buttonText.replace("[", "").replace("]", "").replace(" ", "").replace("and", ",");
                                System.out.println(buttonTextCopy);
                                
                                // Split the string into individual integers:
                                String[] arr = buttonTextCopy.split(",");
                                ArrayList<Integer> pair1 = new ArrayList<>();
                                ArrayList<Integer> pair2 = new ArrayList<>();
                                pair1.add(Integer.parseInt(arr[0]));
                                pair1.add(Integer.parseInt(arr[1]));
                                pair2.add(Integer.parseInt(arr[2]));
                                pair2.add(Integer.parseInt(arr[3]));

                                // Create a new ArrayList to store the integers:
                                ArrayList<ArrayList<Integer>> list = new ArrayList<>();

                                // Loop through the array of strings and add each integer to the ArrayList:
                                list.add(pair1);
                                list.add(pair2);
                                System.out.println(list.toString());
                                
                                combinations.remove(list);
                                if (combinations.isEmpty()) {
                                	resetTheCubePositions();
                                	chooseAnotherCombinationLabel.setText("You have no valid combinations. You are Bust!");
                                	JButton close = new JButton("Close");
                                    playerIsBust();
                                	
                                	JPanel closeButtonPanel = new JPanel();
                                	closeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                	closeButtonPanel.add(close);
                                	
                                	rollDiceDialog.add(chooseAnotherCombinationLabel, BorderLayout.NORTH);
                                	rollDiceDialog.add(closeButtonPanel, BorderLayout.SOUTH);
                                	rollDiceDialog.pack();
                                	
                                	close.addActionListener(new ActionListener() {
                                		public void actionPerformed(ActionEvent e) {
                                			// turn is over because player is bust
                                			
                                			rollDiceDialog.dispose();
                                			rollDiceDialogOpen = false;
                                			 int tempCounter = counter + 1;
                                             if(tempCounter < game.getNumberOfPlayers()){
                                                 counter += 1;
                                             }
                                             else{
                                                 counter = 0;
                                             }
                                             gameStarted = true;
                                             takeTurn();
                                		}
                                	});
                                } else {
                                	chooseAnotherCombinationLabel.setText("Choose Another Combination");
                                	rollDiceDialog.add(chooseAnotherCombinationLabel, BorderLayout.NORTH);
                                    rollDiceDialog.add(selectACombinationPanel, BorderLayout.CENTER);
                                    rollDiceDialog.add(combinationButtonPanel, BorderLayout.SOUTH);
                                    rollDiceDialog.pack();
                                }
                                
                      
                                rollDiceDialog.pack();
                			}
                		}
                	});
                	JButton cancelButton = new JButton("Cancel");
                	cancelButton.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			Container contentPaneToRemove = rollDiceDialog.getContentPane();
                        	contentPaneToRemove.removeAll();
                        	rollDiceDialog.add(rolledNumbersLabelPanel, BorderLayout.NORTH);
                            rollDiceDialog.add(selectACombinationPanel, BorderLayout.CENTER);
                            rollDiceDialog.add(combinationButtonPanel, BorderLayout.SOUTH);
                            rollDiceDialog.pack();
                		}
                	});
                	confirmationButtonPanel.add(confirmationButton);
                	confirmationButtonPanel.add(cancelButton);
                	rollDiceDialog.add(chosenCombinationLabelPanel, BorderLayout.NORTH);
                	rollDiceDialog.add(confirmationButtonPanel, BorderLayout.SOUTH);
                	rollDiceDialog.pack();
                }
            });
            combinationButtonPanel.add(button);
        }

        // add the panels to the dialog box and show it
        rollDiceDialog.add(rolledNumbersLabelPanel, BorderLayout.NORTH);
        rollDiceDialog.add(selectACombinationPanel, BorderLayout.CENTER);
        rollDiceDialog.add(combinationButtonPanel, BorderLayout.SOUTH);
        rollDiceDialog.pack();
        rollDiceDialog.setVisible(true);
    }
    
    public void getTheCubePositions() {
    	if (getPlayer().getColour().equals(Color.RED)) {
    		redCubePositions = new HashMap<>();
    		redCubePositions.putAll(CubePositions.RedCubePositions.returnRedCubePositions());
    	} else if (getPlayer().getColour().equals(Color.BLUE)) {
    		blueCubePositions = new HashMap<>();
    		blueCubePositions.putAll(CubePositions.BlueCubePositions.returnBlueCubePositions());
    	} else if (getPlayer().getColour().equals(Color.GREEN)) {
			greenCubePositions = new HashMap<>();
			greenCubePositions.putAll(CubePositions.GreenCubePositions.returnGreenCubePositions());
		} else if (getPlayer().getColour().equals(Color.ORANGE)) {
			orangeCubePositions = new HashMap<>();
			orangeCubePositions.putAll(CubePositions.OrangeCubePositions.returnOrangeCubePositions());
		} else if (getPlayer().getColour().equals(Color.YELLOW)) {
			yellowCubePositions = new HashMap<>();
			yellowCubePositions.putAll(CubePositions.YellowCubePositions.returnYellowCubePositions());
		} else if (getPlayer().getColour().equals(Color.PINK)) {
			pinkCubePositions = new HashMap<>();
			pinkCubePositions.putAll(CubePositions.PinkCubePositions.returnPinkCubePositions());
		}
    }
    
    public void resetTheCubePositions() {
    	if (getPlayer().getColour().equals(Color.RED)) {
    		for (Map.Entry<String, Integer> entry : redCubePositions.entrySet()) {
    		    String key = entry.getKey();
    		    Integer value = entry.getValue();
    		    CubePositions.RedCubePositions.setPosition(key, value);
    		}
    	} else if (getPlayer().getColour().equals(Color.BLUE)) {
    		for (Map.Entry<String, Integer> entry : blueCubePositions.entrySet()) {
    		    String key = entry.getKey();
    		    Integer value = entry.getValue();
    		    CubePositions.BlueCubePositions.setPosition(key, value);
    		}
    	} else if (getPlayer().getColour().equals(Color.GREEN)) {
    		for (Map.Entry<String, Integer> entry : greenCubePositions.entrySet()) {
    		    String key = entry.getKey();
    		    Integer value = entry.getValue();
    		    CubePositions.GreenCubePositions.setPosition(key, value);
    		}
		} else if (getPlayer().getColour().equals(Color.ORANGE)) {
			for (Map.Entry<String, Integer> entry : orangeCubePositions.entrySet()) {
    		    String key = entry.getKey();
    		    Integer value = entry.getValue();
    		    CubePositions.OrangeCubePositions.setPosition(key, value);
    		}
		} else if (getPlayer().getColour().equals(Color.YELLOW)) {
			for (Map.Entry<String, Integer> entry : yellowCubePositions.entrySet()) {
    		    String key = entry.getKey();
    		    Integer value = entry.getValue();
    		    CubePositions.YellowCubePositions.setPosition(key, value);
    		}
		} else if (getPlayer().getColour().equals(Color.PINK)) {
			for (Map.Entry<String, Integer> entry : pinkCubePositions.entrySet()) {
    		    String key = entry.getKey();
    		    Integer value = entry.getValue();
    		    CubePositions.PinkCubePositions.setPosition(key, value);
    		}
		}
    }
    
    public void updateLabels(Font font){
        playerTurn.setFont(displayOptions.getFont());
    }
    public void setCounter(int counter){
        this.counter = counter;
    }
    public Integer getCounter(){
        return this.counter;
    }
    public void setButtonBackgrounds(Color[][] colours){
        int tempCounter = 0;
        for(Component column : boardPanel.getComponents()){
            if(column instanceof Column){
                Column col = (Column) column;
                col.setBackgrounds(colours[tempCounter]);
                tempCounter += 1;
            }
        }
    }
    public Color[][] getButtonBackgrounds(){
        Color[][] colours = new Color[11][];
        int tempCounter = 0;
        for(Component column : boardPanel.getComponents()) {
            if (column instanceof Column) {
                Column col = (Column) column;
                colours[tempCounter] = col.getBackgrounds();
                tempCounter += 1;
            }
        }
        return colours;
    }
    public void setGameStarted(boolean value){
        gameStarted = value;
    }
    public Boolean getGameStarted(){
        return this.gameStarted;
    }
    public void actionPerformed(ActionEvent aevt)
    {
        Object selected = aevt.getSource();
        if(selected == playerMenu){
            game.togglePlayerVisibility();
        }
        else if(selected == displayMenu){
            game.toggleDisplayVisibility();
        }
        else if(selected == startGameButton){
            if(!rollDiceDialogOpen){
                if(startGameButton.getText().equals("End Turn")){
                    //Turn newTurn = new Turn(this);
                	//newTurn.updateColumnsClaimed(currentPlayer, game);
                	/**
                	 * For debugging. Printing the columns claimed
                	 */
                	HashMap<String, HashSet<Integer>> map = game.getColumnsClaimed();
                	for (Map.Entry<String, HashSet<Integer>> entry : map.entrySet()) {
                	    String key = entry.getKey();
                	    HashSet<Integer> value = entry.getValue();
                	    System.out.println("Key: " + key + ", Value: " + value);
                	}
                    int tempCounter = counter + 1;
                    if(tempCounter < game.getNumberOfPlayers()){
                        counter += 1;
                    }
                    else{
                        counter = 0;
                    }
                    gameStarted = true;
                    endTurn();
                    takeTurn();

                }
                else{
                	Turn newTurn = new Turn(this);
                	newTurn.updateColumnsClaimed(getPlayer(), game);
                    takeTurn();
                    gameStarted = true;
                }
            }
        }  
        if(selected == save) {
        	game.showSaveGameDialog();
        }
        else if (selected == load) {
        	if (gameStarted == true) {
        		JOptionPane.showMessageDialog(null, "Game has already started. Please finish before loading a new game.", "Game Already Started", JOptionPane.WARNING_MESSAGE);
        	} else {
        		game.showLoadGameDialog();
        	}
        }
    }
}

