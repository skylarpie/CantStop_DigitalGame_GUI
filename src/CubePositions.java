import java.io.Serializable;
import java.util.HashMap;

/**
 * This class stores the positions of the colored cubes on the columns on the board in a HashMap. 
 */
public class CubePositions implements Serializable {

	public static class RedCubePositions{
		
		private static HashMap<String, Integer> redCubePositions;
		
		public static void initialize() {
	    	/**
	    	 * Here we use a HashMap to store the column number and the position of the red cube in that column. If there is no red cube
	    	 * in that column we set the position to -1
	    	 */
	    	redCubePositions = new HashMap<>();
	    	redCubePositions.put("col2", -1);
	    	redCubePositions.put("col3", -1);
	    	redCubePositions.put("col4", -1);
	    	redCubePositions.put("col5", -1);
	    	redCubePositions.put("col6", -1);
	    	redCubePositions.put("col7", -1);
	    	redCubePositions.put("col8", -1);
	    	redCubePositions.put("col9", -1);
	    	redCubePositions.put("col10", -1);
	    	redCubePositions.put("col11", -1);
	    	redCubePositions.put("col12", -1);
		}
		
	    public static void setPosition(String column, int position) {
	    	if (column.matches("col(1[0-2]|[2-9])")) {
	    		redCubePositions.put(column, position);
	    	} else {
	    		throw new IllegalArgumentException("The column is not between 2 and 12.");
	    	}
	    }
	    
	    /**
	     * Takes in the column number and returns the position of the red cube in that column
	     * 
	     * @param columnNumber
	     * @return the position of the red cube in that column
	     */
	    public static int getPosition(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	return redCubePositions.get(getterString);
	    }
	    
	    /**
	     * This method takes in a column number and returns a boolean value indicating whether a cube exists in the column or not
	     * 
	     * @param columnNumber
	     * @return a Boolean value indicated if a red cube exists in that given column number or not
	     */
	    public static Boolean cubeExistsInColumn(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	if (redCubePositions.get(getterString) != -1) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public static HashMap<String, Integer> returnRedCubePositions() {
	    	return redCubePositions;
	    }
	}
	
	public static class BlueCubePositions{
		
		private static HashMap<String, Integer> blueCubePositions;
		
	    
	    public static void initialize() {
	    	/**
	    	 * Here we use a HashMap to store the column number and the position of the blue cube in that column. If there is no blue cube
	    	 * in that column we set the position to -1
	    	 */
	    	blueCubePositions = new HashMap<>();
	    	blueCubePositions.put("col2", -1);
	    	blueCubePositions.put("col3", -1);
	    	blueCubePositions.put("col4", -1);
	    	blueCubePositions.put("col5", -1);
	    	blueCubePositions.put("col6", -1);
	    	blueCubePositions.put("col7", -1);
	    	blueCubePositions.put("col8", -1);
	    	blueCubePositions.put("col9", -1);
	    	blueCubePositions.put("col10", -1);
	    	blueCubePositions.put("col11", -1);
	    	blueCubePositions.put("col12", -1);
	    	
	    }
	    
	    public static void setPosition(String column, int position) {
	    	if (column.matches("col(1[0-2]|[2-9])")) {
	    		blueCubePositions.put(column, position);
	    	} else {
	    		throw new IllegalArgumentException("The column is not between 2 and 12.");
	    	}
	    }
	    
	    /**
	     * Takes in the column number and returns the position of the blue cube in that column
	     * 
	     * @param columnNumber
	     * @return the position of the blue cube in that column
	     */
	    public static int getPosition(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	return blueCubePositions.get(getterString);
	    }
	    
	    /**
	     * This method takes in a column number and returns a boolean value indicating whether a blue cube exists in the column or not
	     * 
	     * @param columnNumber
	     * @return a Boolean value indicated if a blue cube exists in that given column number or not
	     */
	    public static Boolean cubeExistsInColumn(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	System.out.println("getterString" + getterString);
	    	if (blueCubePositions.get(getterString) != -1) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public static HashMap<String, Integer> returnBlueCubePositions() {
	    	return blueCubePositions;
	    }
	}
	
	public static class GreenCubePositions{
		
		private static HashMap<String, Integer> greenCubePositions;
		
	    
	    public static void initialize() {

	    	/**
	    	 * Here we use a HashMap to store the column number and the position of the green cube in that column. If there is no green cube
	    	 * in that column we set the position to -1
	    	 */
	    	greenCubePositions = new HashMap<>();
	    	greenCubePositions.put("col2", -1);
	    	greenCubePositions.put("col3", -1);
	    	greenCubePositions.put("col4", -1);
	    	greenCubePositions.put("col5", -1);
	    	greenCubePositions.put("col6", -1);
	    	greenCubePositions.put("col7", -1);
	    	greenCubePositions.put("col8", -1);
	    	greenCubePositions.put("col9", -1);
	    	greenCubePositions.put("col10", -1);
	    	greenCubePositions.put("col11", -1);
	    	greenCubePositions.put("col12", -1);
	    	
	    }
	    
	    
	    public static void setPosition(String column, int position) {
	    	if (column.matches("col(1[0-2]|[2-9])")) {
	    		greenCubePositions.put(column, position);
	    	} else {
	    		throw new IllegalArgumentException("The column is not between 2 and 12.");
	    	}
	    }
	    
	    /**
	     * Takes in the column number and returns the position of the green cube in that column
	     * 
	     * @param columnNumber
	     * @return the position of the green cube in that column
	     */
	    public static int getPosition(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	return greenCubePositions.get(getterString);
	    }
	    
	    /**
	     * This method takes in a column number and returns a boolean value indicating whether a green cube exists in the column or not
	     * 
	     * @param columnNumber
	     * @return a Boolean value indicated if a green cube exists in that given column number or not
	     */
	    public static Boolean cubeExistsInColumn(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	if (greenCubePositions.get(getterString) != -1) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public static HashMap<String, Integer> returnGreenCubePositions() {
	    	return greenCubePositions;
	    }
	}
	
	public static class OrangeCubePositions{
		
		private static HashMap<String, Integer> orangeCubePositions;
		
	    
	    public static void initialize() {
	    	
	    	/**
	    	 * Here we use a HashMap to store the column number and the position of the orange cube in that column. If there is no orange cube
	    	 * in that column we set the position to -1
	    	 */
	    	orangeCubePositions = new HashMap<>();
	    	orangeCubePositions.put("col2", -1);
	    	orangeCubePositions.put("col3", -1);
	    	orangeCubePositions.put("col4", -1);
	    	orangeCubePositions.put("col5", -1);
	    	orangeCubePositions.put("col6", -1);
	    	orangeCubePositions.put("col7", -1);
	    	orangeCubePositions.put("col8", -1);
	    	orangeCubePositions.put("col9", -1);
	    	orangeCubePositions.put("col10", -1);
	    	orangeCubePositions.put("col11", -1);
	    	orangeCubePositions.put("col12", -1);
	    	
	    }
	    
	    
	    public static void setPosition(String column, int position) {
	    	if (column.matches("col(1[0-2]|[2-9])")) {
	    		orangeCubePositions.put(column, position);
	    	} else {
	    		throw new IllegalArgumentException("The column is not between 2 and 12.");
	    	}
	    }
	    
	    /**
	     * Takes in the column number and returns the position of the orange cube in that column
	     * 
	     * @param columnNumber
	     * @return the position of the orange cube in that column
	     */
	    public static int getPosition(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	return orangeCubePositions.get(getterString);
	    }
	    
	    /**
	     * This method takes in a column number and returns a boolean value indicating whether an orange cube exists in the column or not
	     * 
	     * @param columnNumber
	     * @return a Boolean value indicated if an orange cube exists in that given column number or not
	     */
	    public static Boolean cubeExistsInColumn(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	if (orangeCubePositions.get(getterString) != -1) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public static HashMap<String, Integer> returnOrangeCubePositions() {
	    	return orangeCubePositions;
	    }
	}
	
	public static class PinkCubePositions{
		
		private static HashMap<String, Integer> pinkCubePositions;
		
	    
	    public static void initialize() {
	    	
	    	/**
	    	 * Here we use a HashMap to store the column number and the position of the purple cube in that column. If there is no purple cube
	    	 * in that column we set the position to -1
	    	 */
	    	pinkCubePositions = new HashMap<>();
	    	pinkCubePositions.put("col2", -1);
	    	pinkCubePositions.put("col3", -1);
	    	pinkCubePositions.put("col4", -1);
	    	pinkCubePositions.put("col5", -1);
	    	pinkCubePositions.put("col6", -1);
	    	pinkCubePositions.put("col7", -1);
	    	pinkCubePositions.put("col8", -1);
	    	pinkCubePositions.put("col9", -1);
	    	pinkCubePositions.put("col10", -1);
	    	pinkCubePositions.put("col11", -1);
	    	pinkCubePositions.put("col12", -1);
	    	
	    }
	    
	    public static void setPosition(String column, int position) {
	    	if (column.matches("col(1[0-2]|[2-9])")) {
	    		pinkCubePositions.put(column, position);
	    	} else {
	    		throw new IllegalArgumentException("The column is not between 2 and 12.");
	    	}
	    }
	    
	    /**
	     * Takes in the column number and returns the position of the pink cube in that column
	     * 
	     * @param columnNumber
	     * @return the position of the pink cube in that column
	     */
	    public static int getPosition(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	return pinkCubePositions.get(getterString);
	    }
	    
	    /**
	     * This method takes in a column number and returns a boolean value indicating whether a purple cube exists in the column or not
	     * 
	     * @param columnNumber
	     * @return a Boolean value indicated if a purple cube exists in that given column number or not
	     */
	    public static Boolean cubeExistsInColumn(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	if (pinkCubePositions.get(getterString) != -1) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public static HashMap<String, Integer> returnPinkCubePositions() {
	    	return pinkCubePositions;
	    }
	}
	
	public static class YellowCubePositions{
		
		private static HashMap<String, Integer> yellowCubePositions;
		
	    
	    public static void initialize() {
	    	
	    	/**
	    	 * Here we use a HashMap to store the column number and the position of the yellow cube in that column. If there is no yellow cube
	    	 * in that column we set the position to -1
	    	 * 
	    	 */
	    	yellowCubePositions = new HashMap<>();
	    	yellowCubePositions.put("col2", -1);
	    	yellowCubePositions.put("col3", -1);
	    	yellowCubePositions.put("col4", -1);
	    	yellowCubePositions.put("col5", -1);
	    	yellowCubePositions.put("col6", -1);
	    	yellowCubePositions.put("col7", -1);
	    	yellowCubePositions.put("col8", -1);
	    	yellowCubePositions.put("col9", -1);
	    	yellowCubePositions.put("col10", -1);
	    	yellowCubePositions.put("col11", -1);
	    	yellowCubePositions.put("col12", -1);
	    	
	    }
	    
	    
	    public static void setPosition(String column, int position) {
	    	if (column.matches("col(1[0-2]|[2-9])")) {
	    		yellowCubePositions.put(column, position);
	    	} else {
	    		throw new IllegalArgumentException("The column is not between 2 and 12.");
	    	}
	    }
	    
	    /**
	     * Takes in the column number and returns the position of the yellow cube in that column
	     * 
	     * @param columnNumber
	     * @return the position of the yellow cube in that column
	     */
	    public static int getPosition(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	return yellowCubePositions.get(getterString);
	    }
	    
	    /**
	     * This method takes in a column number and returns a boolean value indicating whether a yellow cube exists in the column or not
	     * 
	     * @param columnNumber
	     * @return a Boolean value indicated if a yellow cube exists in that given column number or not
	     */
	    public static Boolean cubeExistsInColumn(int columnNumber) {
	    	String getterString = "col" + columnNumber;
	    	if (yellowCubePositions.get(getterString) != -1) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    public static HashMap<String, Integer> returnYellowCubePositions() {
	    	return yellowCubePositions;
	    }
	}
}
