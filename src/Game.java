import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
/**
 * This class stores important information of the game and acts as sort of the information expert
 * Stores stuff like the list of players, the list of bots,
 */
public class Game implements Serializable{
    private String winner;
    private int numberOfPlayers;
    private ArrayList playerList, sortedPlayerList;
    private HashMap<String, HashSet<Integer>> columnsClaimed;
    private DisplayOptions displayOptions;
    private transient DisplayOptionsGUI displayOptionsGUI;
    private transient PlayerSettingsGUI playerSettingsGUI;
    private transient SaveGameGUI saveGameGUI;
    private transient LoadGameGUI loadGameGUI;
    private PlayerSettings playerSettings;
    private transient GameGUI gameGUI;
    private transient GameSetupGUI gameSetupGUI;
    private HashMap<String, Integer> redCubePositions, blueCubePositions, greenCubePositions, 
    yellowCubePositions, orangeCubePositions, pinkCubePositions;
    private Boolean difficulty;
    
    public Game(){
    	
        playerList = new ArrayList<>();
        columnsClaimed = new HashMap<String, HashSet<Integer>>();
        sortedPlayerList = new ArrayList<>();
        displayOptions = new DisplayOptions(this);
        playerSettings = new PlayerSettings();
        gameSetupGUI = new GameSetupGUI(this);
        displayOptionsGUI = new DisplayOptionsGUI(displayOptions);
        
    }
    public void startGame(String[] playerNames, String[] playerColours, Integer[] playerAges){
    	
    	/**
    	 * Initialize the positions of the coloured cubes everytime you start a new game
    	 */
    	CubePositions.RedCubePositions.initialize();
    	CubePositions.BlueCubePositions.initialize();
    	CubePositions.GreenCubePositions.initialize();
    	CubePositions.OrangeCubePositions.initialize();
    	CubePositions.PinkCubePositions.initialize();
    	CubePositions.YellowCubePositions.initialize();
    	
    	/**
    	 * Initialize the columns claimed
    	 */
    	for (int i = 0; i < playerNames.length; i++) {
    	    columnsClaimed.put(playerNames[i], new HashSet<>());
    	}
    	
    	
    	
        Color colour = null;
        for(int x = 0; x < playerNames.length; x++){
            //This seems to be the optimal way to convert String to Color
            switch(playerColours[x]){
                case "Blue":
                    colour = Color.BLUE;
                    break;
                case "Red":
                    colour = Color.RED;
                    break;
                case "Green":
                    colour = Color.GREEN;
                    break;
                case "Yellow":
                    colour = Color.YELLOW;
                    break;
                case "Orange":
                    colour = Color.ORANGE;
                    break;
                case "Pink":
                    colour = Color.PINK;
                    break;
            }
            //Line below means they are a bot
            if(playerNames[x].equals(""))
            {
                playerList.add(new Bot(playerColours[x], colour, playerSettings.getDifficulty()));
            }
            else{
                playerList.add(new Player(playerNames[x], colour, playerAges[x]));
            }
        }
        gameGUI = new GameGUI(this, displayOptions);
        gameGUI.setVisible(true);
        playerSettingsGUI = new PlayerSettingsGUI(playerSettings, displayOptions);
        sortPlayers();
        this.numberOfPlayers = playerList.size();
    }
    public void togglePlayerVisibility(){
        playerSettingsGUI.setVisible(true);
    }
    public void toggleDisplayVisibility(){
        displayOptionsGUI.setVisible(true);
    }
    public void showSaveGameDialog() {
    	saveGameGUI = new SaveGameGUI(this);
    }
    public void showLoadGameDialog() {
    	loadGameGUI = new LoadGameGUI(this);
    }

    /**
     * This method writes the information about each player to a .ser file.
     * 
     */
    public void saveGame(File file){
    	// TODO: Write the code to save the difficulty, the current status of the game, display options
    	saveCubePositions();
    	saveDifficiculty();
    	try {
    		//Create an output stream to write the game data to the file that needs to be saved
    		FileOutputStream fileOut = new FileOutputStream(file);
    		
    		//Write the game data to the output stream
    		ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            
            //Close the output stream
            out.close();
            fileOut.close();
         } catch (IOException i) {
            i.printStackTrace();
         }
    }
    /**
     * This method loads the game
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    public void loadGame(Game game) throws ClassNotFoundException, IOException{
    	loadCubePositions(game);
    	loadDifficulty(game.playerSettings, game.difficulty);
    	loadPlayerSettingsGUI(game.playerSettings, game.displayOptions);
    	gameGUI = new GameGUI(game, displayOptions);
    	playerList = game.playerList;
        sortedPlayerList = game.sortedPlayerList;
        this.displayOptions.setFont(game.displayOptions.getFont());
        this.displayOptions.toggleColourFilter(game.displayOptions.getColourOn());
        this.displayOptions.toggleAnimations(game.displayOptions.getAnimationsOn());
        this.numberOfPlayers = game.getNumberOfPlayers();
        this.gameGUI.updateLabels(this.displayOptions.getFont());
        this.playerSettingsGUI.updateLabels(this.displayOptions.getFont());
        this.displayOptionsGUI.updateLabels();
        this.gameGUI.checkForLabel();
    }

    public String playerTurnName(int x){
        if(sortedPlayerList.get(x) instanceof Player){
            Player player = (Player) sortedPlayerList.get(x);
            return player.getName();
        }
        else{
            Bot bot = (Bot) sortedPlayerList.get(x);
            return bot.getName();
        }
    }
    
    /**
     * 
     * 
     * @param int x
     * @return 
     */
    public Player getPlayer(int x) {
    	Player player = (Player) sortedPlayerList.get(x);
        return player;
    }
    
    public Bot getBot(int x) {
    	Bot bot = (Bot) sortedPlayerList.get(x);
        return bot;
    }
    
    public Boolean isPlayer(int x) {
    	if(sortedPlayerList.get(x) instanceof Player){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void updateLabels(){
        gameGUI.updateLabels(displayOptions.getFont());
        playerSettingsGUI.updateLabels(displayOptions.getFont());
    }
    private void sortPlayers(){
        ArrayList tempPlayerList = new ArrayList<Player>();
        ArrayList tempBotList = new ArrayList<Bot>();
        for(Object player : playerList){
            if(player instanceof Player){
                tempPlayerList.add(player);
            }
            else {
                tempBotList.add(player);
            }
        }
        while(!tempPlayerList.isEmpty()){
            Player sortedPlayer = (Player) tempPlayerList.get(0);
            for (int x = 0; x < tempPlayerList.size(); x++){
                Player player = (Player) tempPlayerList.get(x);
                if (player.getAge() < sortedPlayer.getAge()){
                    sortedPlayer = player;
                }
            }
            sortedPlayerList.add(sortedPlayer);
            tempPlayerList.remove(sortedPlayer);
        }
        for (Object bot : tempBotList){
            sortedPlayerList.add(bot);
        }
    }
    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }
    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }
    
    public HashMap<String, HashSet<Integer>> getColumnsClaimed(){
    	return columnsClaimed;
    }
    
    public void addToColumnsClaimed(String name, Integer column) {
    	HashSet<Integer> set = columnsClaimed.get(name);
    	set.add(column);
    	columnsClaimed.put(name, set);
    }
    
    /**
     * Saves the cube positions as instances of the class before saving the file
     * 
     */
    public void saveCubePositions() {
    	this.redCubePositions = CubePositions.RedCubePositions.returnRedCubePositions();
    	this.blueCubePositions = CubePositions.BlueCubePositions.returnBlueCubePositions();
    	this.greenCubePositions = CubePositions.GreenCubePositions.returnGreenCubePositions();
    	this.yellowCubePositions = CubePositions.YellowCubePositions.returnYellowCubePositions();
    	this.orangeCubePositions = CubePositions.OrangeCubePositions.returnOrangeCubePositions();
    	this.pinkCubePositions = CubePositions.PinkCubePositions.returnPinkCubePositions();
    }
    
    /**
     * Loads the cube positions from the saved file
     * 
     * @param loaded game
     */
    public void loadCubePositions(Game game) {
    	for (Map.Entry<String, Integer> entry : game.redCubePositions.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    CubePositions.RedCubePositions.setPosition(key, value);
		}
    	for (Map.Entry<String, Integer> entry : game.blueCubePositions.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    CubePositions.BlueCubePositions.setPosition(key, value);
		}
    	for (Map.Entry<String, Integer> entry : game.greenCubePositions.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    CubePositions.GreenCubePositions.setPosition(key, value);
		}
    	for (Map.Entry<String, Integer> entry : game.yellowCubePositions.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    CubePositions.YellowCubePositions.setPosition(key, value);
		}
    	for (Map.Entry<String, Integer> entry : game.orangeCubePositions.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    CubePositions.OrangeCubePositions.setPosition(key, value);
		}
    	for (Map.Entry<String, Integer> entry : game.pinkCubePositions.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    CubePositions.PinkCubePositions.setPosition(key, value);
		}
    }
    
    public void saveDifficiculty() {
    	this.difficulty = playerSettings.getDifficulty();
    }
    
    public void loadDifficulty(PlayerSettings loadedPlayerSettings, Boolean loadedDifficulty) {
    	loadedPlayerSettings.toggleDifficulty(loadedDifficulty);
    }
    
    public void loadPlayerSettingsGUI(PlayerSettings loadedPlayerSettings, DisplayOptions loadedDisplayOptions) {
    	playerSettingsGUI = new PlayerSettingsGUI(loadedPlayerSettings, loadedDisplayOptions);
    }
    

}