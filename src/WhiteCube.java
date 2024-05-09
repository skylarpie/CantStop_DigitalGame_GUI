import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageTypeSpecifier;

public class WhiteCube extends Cube implements Serializable{
	
	//TODO : The cubes are stored as strings for now but we need to store the key as an instance of the Cube class
	private HashMap<String, int[]> cubePositions;
	
    public WhiteCube(){
    	cubePositions = new HashMap<>();
    	// -1 indicates that it's not on the board
    	cubePositions.put("cube1", new int[] {-1,-1});
    	cubePositions.put("cube2", new int[] {-1,-1});
    	cubePositions.put("cube3", new int[] {-1,-1});
    	
    }
    
    
    /**
     * Getter method for the positions of the white cubes
     * 
     * @return A hashMap which consists of key-value pairs where each key is the string "cube1", "cube2", "cube3" and the value is
     * an array which consists of the positions of the white cubes in the form [column, position in column]
     */
    public HashMap<String, int[]> getPositions() {
    	return cubePositions;
    }
    
    /**
     * This is the setter method for the Hashmap cubePositions. If the key of @param newPositions is equal to -2
     * the values remain unchanged.
     * 
     * @param newPositions
     */
    public void setPosition(HashMap<String, int[]> newPositions) {
    	int[] cube1Position = newPositions.get("cube1");
    	int[] cube2Position = newPositions.get("cube2");
    	int[] cube3Position = newPositions.get("cube3");
    	
    	if (cube1Position[0] != -2) {
    		cubePositions.put("cube1", cube1Position);
    	}
    	if (cube2Position[0] != -2) {
    		cubePositions.put("cube2", cube2Position);
    	}
    	if (cube3Position[0] != -2) {
    		cubePositions.put("cube3", cube3Position);
    	}
    			
    }
}

