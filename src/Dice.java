import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dice extends JButton{
    private Random rand;
    private Integer number;

    /**
     * Initializes a new instance of the Dice class.
     */
    public Dice() {
        rand = new Random();
    }

    /**
     * Rolls a single die and returns the result.
     *
     * @return the result of rolling a single die.
     */
    public Integer getNumber() {
        number = rand.nextInt(6) + 1;
        return number;
    }

    /**
     * Returns all possible combinations of pairs of numbers.
     *
     * @return a list of all possible combinations of pairs of numbers obtained by rolling four dice.
     */
    public Set<List<List<Integer>>> getCombi(List<Integer> diceRolls) {
    	
    	// HashSet is used to remove duplicates
        Set<List<List<Integer>>> combinations = new HashSet<>();
        
        // Duplicating the lsit that was passed into the method to avoid changes to the original list
    	List<Integer> duplicatedDiceRolls = new ArrayList<>();
    	duplicatedDiceRolls.addAll(diceRolls);
    	
    	// First dice roll is removed from the duplicatedDiceRolls list. The remaining dice rolls are used to form pairs with the firstRoll
    	Integer firstRoll = duplicatedDiceRolls.remove(0);
    	
    	for (Integer roll : duplicatedDiceRolls) {
    		
    		// To avoid changes to duplicatedDiceRolls during iteration, the list is copied to a new list called list2
    		List<Integer> list2 = new ArrayList<>();
    		list2.addAll(duplicatedDiceRolls);
    		
    		// element is removed from list2 and paired with the firstRoll. Every element apart from firstRoll will be paired with firstRoll
    		int indexToRemove = list2.indexOf(Integer.valueOf(roll));
    		int elementToRemove = list2.remove(indexToRemove);
    		
    		// new list is created and the pair is added to that list
    		List<Integer> pair1 = new ArrayList<>();
    		pair1.add(firstRoll);
    		pair1.add(elementToRemove);
    		Collections.sort(pair1); // the list is sorted to make sure that the HashSet will remove the duplicated pairs
    		List<Integer> pair2 = new ArrayList<>();
    		pair2.addAll(list2);
    		Collections.sort(pair2);
    		
    		// A HashSet is created to hold the two pairs. This will be one combination. 
    		List<List<Integer>> combination = new ArrayList<>();
    		
    		// The below if statement is to make sure that the pairs are added in a sorted manner to recognize for duplicates
    		if (pair1.get(0) + pair1.get(1) > pair2.get(0) + pair2.get(1)) {
    			combination.add(pair1);
        		combination.add(pair2);
    		} else {
    			combination.add(pair2);
    			combination.add(pair1);
    		}
    		
    		combinations.add(combination);
    	}
    		
        return combinations;
    }
    
    /**
     * Rolls four dice and returns the 4 numbers obtained by rolling each die
     * 
     * @return a list of the numbers obtained by rolling each die
     */
    public List<Integer> getDiceRolls() {
    	List<Integer> diceRolls = new ArrayList<>();
    	
    	//Roll 4 dice and store the results in a list
        for (int i=0; i<4; i++) {
            int diceRoll = getNumber();
            diceRolls.add(diceRoll);
        }
        
        return diceRolls;
    }
}



