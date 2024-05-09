import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import javax.swing.JOptionPane;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import java.util.Map;

public class Turn {
	private GameGUI gameGUI;
    private Boolean isBust;
    private Dice dice =  new Dice();
    private Set<List<List<Integer>>> combinations;
    private List<Integer> rolledNumbers;
    private Boolean hasLegalFreeCubes, cube1Free, cube2Free, cube3Free; // boolean value indicating if there are any legal moves
    private HashMap<String, int[]> positions, positionsCopy, newPositions;
    private WhiteCube whiteCubes;
    private int[] cube1Position, cube2Position, cube3Position;
    private int numberOfFreeWhiteCubes;
    private int columnPosition;
    private Color currentColor;
    private Game currentGame;
    private Set<Integer> columnsClaimedSet;
    public Turn(GameGUI gameGUI) {
    	this.gameGUI = gameGUI;
    }


    public Boolean getStatus() {
    	return true;
    }

    public void endTurn() {
		/**
		 This should be done in Turn class,
		 You can create a method to check if a column has been claimed by using the knowledge of the number of squares in each column
		 and the position of the white cubes in each column
		 After the user's turn ends you can replace the white cubes with the colored cubes.
		 Make sure to use CubePositions.(Color)CubePositions.setPosition(String column, int position) to replace the values in the
		 HashMap so we have an updated Hashmap after each turn until the game ends.
		 **/


		gameGUI.endTurn();
    }

    public List<Integer> roll() {
    	return dice.getDiceRolls();
    }
    
    public Set<List<List<Integer>>> getCombi(List<Integer> rolledNumbers) {
    	combinations = dice.getCombi(rolledNumbers);
    	return combinations;
    }
    
    public Boolean canMoveCube(int firstSum, int secondSum, Player currentPlayer, Game game, WhiteCube whiteCube) {
    	currentColor = currentPlayer.getColour();
    	currentGame = game;
    	
    	columnsClaimedSet = new HashSet<>();
    	for (HashSet<Integer> set : currentGame.getColumnsClaimed().values()) {
    		for (int element : set) {
    			columnsClaimedSet.add(element);
    		}
    	}
    	whiteCubes = whiteCube;
    	moveCube(firstSum, secondSum, currentPlayer);
    	if (positionsCopy.equals(positions)) {
    	    return false;
    	} else {
    		gameGUI.moveWhiteCubes(whiteCubes.getPositions());
    		setPosition();
    	    return true;
    	}
    }

    public void moveCube(int firstSum, int secondSum, Player currentPlayer) {
    	
    	positions = whiteCubes.getPositions();
  
    	/**
    	 * positionsCopy is used at the end to compare if we have made any changes to the positions of the white cubes to check if a cube
    	 * has moved
    	 */
    	positionsCopy = new HashMap<>(positions);
    	hasLegalFreeCubes = hasLegalFreeCubes(firstSum, secondSum);
    	
    	if (hasLegalFreeCubes) {
    		moveFreeCubes(firstSum, secondSum, currentPlayer);
    	} else {
    		// This will be executed only when all the white cubes are on the board. In other words when there are no free white cubes
    		moveCubesOnBoard(firstSum, secondSum, currentPlayer);
    	}
    	


    	HashMap<String, HashSet<Integer>> columnsClaimedWithPlayers = currentGame.getColumnsClaimed();
    	
    	
    	for (Map.Entry<String, HashSet<Integer>> entry : columnsClaimedWithPlayers.entrySet()) {
    		String key = entry.getKey();
    		HashSet<Integer> value = entry.getValue();
    		int size = value.size();
    		if (size == 3) {
    			// end game
    			WinnerDialog winnerDialog = new WinnerDialog();
    			winnerDialog.show(key);
				try{
					Thread.sleep(5000);
				}
				catch (InterruptedException ex){
					ex.printStackTrace();
				}
				gameGUI.dispose();
    		}
    	}

		/**
		 * Method for the computer to select the cubes on the board and change the color of it to show that we have moved
		 * the white cubes. You can access the positions of the white cubes using whiteCubes.getPositions()
		 * The positions of the white cubes will be stored in a HashMap in WhiteCube class.
		 * After you get it, the HashMap will store the cube name as a string  as the key and the position as an array for the value.
		 * The position will be stored in the format [column, position in column], position in column will always start from 1.
		 **/
    }
    public void updateColumnsClaimed(Player currentPlayer, Game gameInProgress) {
    	String playerName = currentPlayer.getName();
    	if (currentPlayer.getColour().equals(Color.RED)) {
    		for (Map.Entry<String, Integer> entry : CubePositions.RedCubePositions.returnRedCubePositions().entrySet()) {
        	    addToColumnsClaimed(entry, playerName, gameInProgress);
        	}
    	} else if (currentPlayer.getColour().equals(Color.BLUE)) {
    		for (Map.Entry<String, Integer> entry : CubePositions.BlueCubePositions.returnBlueCubePositions().entrySet()) {
        	    addToColumnsClaimed(entry, playerName, gameInProgress);
        	}
    	} else if (currentPlayer.getColour().equals(Color.GREEN)) {
    		for (Map.Entry<String, Integer> entry : CubePositions.GreenCubePositions.returnGreenCubePositions().entrySet()) {
        	    addToColumnsClaimed(entry, playerName, gameInProgress);
        	}
    	} else if (currentPlayer.getColour().equals(Color.YELLOW)) {
    		for (Map.Entry<String, Integer> entry : CubePositions.YellowCubePositions.returnYellowCubePositions().entrySet()) {
        	    addToColumnsClaimed(entry, playerName, gameInProgress);
        	}
    	} else if (currentPlayer.getColour().equals(Color.ORANGE)) {
    		for (Map.Entry<String, Integer> entry : CubePositions.OrangeCubePositions.returnOrangeCubePositions().entrySet()) {
        	    addToColumnsClaimed(entry, playerName, gameInProgress);
        	}
    	} else if (currentPlayer.getColour().equals(Color.PINK)) {
    		for (Map.Entry<String, Integer> entry : CubePositions.PinkCubePositions.returnPinkCubePositions().entrySet()) {
        	    addToColumnsClaimed(entry, playerName, gameInProgress);
        	}
    	}
    	
    	
    }
    
    public void addToColumnsClaimed(Map.Entry<String, Integer> entry, String playerName, Game gameInProgress) {
    	String key = entry.getKey();
	    Integer value = entry.getValue();
	    int colNumber = Integer.parseInt(key.substring(3));
	    if (isClaimed(colNumber, value)) {
	    	gameInProgress.addToColumnsClaimed(playerName, colNumber);
	    }
	}
    
    
    
    public void setColoredCubePositions(String column, int columnPosition ) {
    	
    	
    	if (currentColor.equals(Color.RED)) {
    		CubePositions.RedCubePositions.setPosition(column, columnPosition);
		} else if (currentColor.equals(Color.BLUE)) {
			CubePositions.BlueCubePositions.setPosition(column, columnPosition);
		} else if (currentColor.equals(Color.GREEN)) {
			CubePositions.GreenCubePositions.setPosition(column, columnPosition);
		} else if (currentColor.equals(Color.ORANGE)) {
			CubePositions.OrangeCubePositions.setPosition(column, columnPosition);
		} else if (currentColor.equals(Color.YELLOW)) {
			CubePositions.YellowCubePositions.setPosition(column, columnPosition);
		} else if (currentColor.equals(Color.PINK)) {
			CubePositions.PinkCubePositions.setPosition(column, columnPosition);
		}
    	
    }
    
    
    /**
     * This method sets the position adds the colored cubes to the places where the white cubes are placed. The color
     * of the cubes will be decided by the setColoredCubePositions() method.
     */
    public void setPosition() {
    	
    	int[] cube1 = whiteCubes.getPositions().get("cube1");
    	
    	int[] cube2 = whiteCubes.getPositions().get("cube2");
    	
    	int[] cube3 = whiteCubes.getPositions().get("cube3");
    	
    	
    	int[][]cubes = {cube1, cube2, cube3};
    	int[] columnPosition;
    	
    	for (int i=0 ; i < cubes.length; i++) {
    		
    		columnPosition = cubes[i];
    		
	    	if (columnPosition[0] == 2) {
	    		setColoredCubePositions("col2", columnPosition[1]);
	    	} else if (columnPosition[0] == 3) {
	    		setColoredCubePositions("col3", columnPosition[1]);
	    	} else if (columnPosition[0] == 4) {
	    		setColoredCubePositions("col4", columnPosition[1]);
	    	} else if (columnPosition[0] == 5) {
	    		setColoredCubePositions("col5", columnPosition[1]);
	    	} else if (columnPosition[0] == 6) {
	    		setColoredCubePositions("col6", columnPosition[1]);
	    	} else if (columnPosition[0] == 7) {
	    		setColoredCubePositions("col7", columnPosition[1]);
	    	} else if (columnPosition[0] == 8) {
	    		setColoredCubePositions("col8", columnPosition[1]);
	    	} else if (columnPosition[0] == 9) {
	    		setColoredCubePositions("col9", columnPosition[1]);
	    	} else if (columnPosition[0] == 10) {
	    		setColoredCubePositions("col10", columnPosition[1]);
	    	} else if (columnPosition[0] == 11) {
	    		setColoredCubePositions("col11", columnPosition[1]);
	    	} else if (columnPosition[0] == 12) {
	    		setColoredCubePositions("col12", columnPosition[1]);
	    	}
	    	
    	}
    }
    
    /**
     * This method checks if the user can move a free cube to the board
     * 
     * @param firstSum : sum of the first pair
     * @param secondSum : sum of the second pair
     * @return a boolean value indicating whether the use can move a free cube to the board
     */
    public Boolean hasLegalFreeCubes(int firstSum, int secondSum) {
    	cube1Position = positions.get("cube1");
    	cube2Position = positions.get("cube2");
    	cube3Position = positions.get("cube3");
    	
    	numberOfFreeWhiteCubes = 0;
    	/**
    	 * the cubePosition is in the form [column, position in column]
    	 * if the column is -1 then the cube hasn't been placed in a column yet
    	 * first check for how many cubes column is -1. numberOfFreeWhiteCubes store how many cubes are free.
    	 */
    	if (cube1Position[0] == -1) {
    		cube1Free = true;
    		numberOfFreeWhiteCubes += 1;
    	} else {
    		cube1Free = false;
    	}
    	if (cube2Position[0] == -1) {
    		cube2Free = true;
    		numberOfFreeWhiteCubes += 1;
    	} else {
    		cube2Free = false;
    	}
    	if (cube3Position[0] == -1) {
    		cube3Free = true;
    		numberOfFreeWhiteCubes += 1;
    	} else {
    		cube3Free = false;
    	}
    	
    	
    	// If both the columns representing the sums are claimed then the user cannot move any free white cubes to the board
    	if (columnsClaimedSet.contains(firstSum) && columnsClaimedSet.contains(secondSum)) {
			return false;
		}
    	
    	/**
    	 * If number of free white cubes = 2 or 3 then the user definitely has legal moves, so we can exit the method early
    	 */
    	if (numberOfFreeWhiteCubes >= 2) {
    		return true;
    	} else if (numberOfFreeWhiteCubes == 0) {
    		return false;
    	
    	/**
    	 * if number of free white cubes = 1, then we have to check if the sum of each pair does not fall onto one of the columns
    	 * the current white cubes that are not free are placed
    	 */
    	} else {
    		/**
    		 * If the first sum is equal to the second sum, then we only have one column to move a free cube to so we have check
    		 * if either of the cubes remaining on board falls on that column
    		 */
    		if (firstSum == secondSum) {
    			if (cube1Free) {
    				if (cube2Position[0] == firstSum || cube3Position[0] == firstSum) {
    					return false;
    				}
    			}
    			if (cube2Free) {
    				if (cube1Position[0] == firstSum || cube3Position[0] == firstSum) {
    					return false;
    				}
    			}
    			if (cube3Free) {
    				if (cube2Position[0] == firstSum || cube1Position[0] == firstSum) {
    					return false;
    				}
    			}
    		}
    		if (cube1Free) {
    			/**
    			 * There are only two instances where a user has a free cube but cannot move the cube to a column. That is when
    			 * the sum of the first pair and sum of second pair falls on the columns representing the cubes already placed.
    			 * In this case the only option for the user is to move the existing cubes.
    			 */
    			if ((cube2Position[0] == firstSum && cube3Position[0] == secondSum) || 
    					(cube2Position[0] == secondSum && cube3Position[0] == firstSum)) {
    				return false;
    			}
    		}
    		if (cube2Free) {
    			if ((cube1Position[0] == firstSum && cube3Position[0] == secondSum) || 
    					(cube1Position[0] == secondSum && cube3Position[0] == firstSum)) {
    				return false;
    			}
    		}
    		if (cube3Free) {
    			if ((cube2Position[0] == firstSum && cube1Position[0] == secondSum) || 
    					(cube2Position[0] == secondSum && cube1Position[0] == firstSum)) {
    				return false;
    			}
    		}
    		return true;
    	}
    }
    
    public Boolean isClaimed(int columnNumber, int columnPosition) {
    	System.out.println("Column Number is " + columnNumber + "ColumnPosition is " + columnPosition);
    	if (columnNumber == 2 || columnNumber == 12) {
    		if (columnPosition == 3) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 3 || columnNumber == 11 ) {
    		if (columnPosition == 5) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 4 || columnNumber == 10) {
    		if (columnPosition == 7) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 5 || columnNumber == 9) {
    		if (columnPosition == 9) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 6 || columnNumber == 8) {
    		if (columnPosition == 11) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 7) {
    		if (columnPosition == 13) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    public Boolean isAboutToBeClaimed(int columnNumber, int columnPosition) {
    	if (columnNumber == 2 || columnNumber == 12) {
    		if (columnPosition == 2) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 3 || columnNumber == 11 ) {
    		if (columnPosition == 4) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 4 || columnNumber == 10) {
    		if (columnPosition == 6) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 5 || columnNumber == 9) {
    		if (columnPosition == 8) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 6 || columnNumber == 8) {
    		if (columnPosition == 10) {
    			return true;
    		} else {
    			return false;
    		}
    	} else if (columnNumber == 7) {
    		if (columnPosition == 12) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    
    public void moveCubesOnBoard(int firstSum, int secondSum, Player currentPlayer) {
    	String cubeToMove;
    	// Create a HashMap to store the new positions
    	newPositions = new HashMap<>();
    	newPositions.put("cube1", new int[] {-2,-2});
    	newPositions.put("cube2", new int[] {-2,-2});
    	newPositions.put("cube3", new int[] {-2,-2});
    	
    	positions = whiteCubes.getPositions();
    	
    	if (firstSum == secondSum) {
    		if (positions.get("cube1")[0] == firstSum) {
    			cubeToMove = "cube1";
    		} else if (positions.get("cube2")[0] == firstSum) {
    			cubeToMove = "cube2";
    		} else if (positions.get("cube3")[0] == firstSum) {
    			cubeToMove = "cube3";
    		} else {
    			cubeToMove = "noCube";
    		}
    		if (!cubeToMove.equals("noCube")) {
	    		int cubePosition = positions.get(cubeToMove)[1];
	    		if (isAboutToBeClaimed(firstSum, cubePosition)) {
	    				setPositionOfExistingWhiteCubes(firstSum, cubeToMove, 1);
				} else {
					if (!isClaimed(firstSum, cubePosition)) {
						setPositionOfExistingWhiteCubes(firstSum, cubeToMove, 2);
					}
				}
    		}
    	} else {
    		if (positions.get("cube1")[0] == firstSum) {
    			if(!isClaimed(firstSum, positions.get("cube1")[1])) {
    				setPositionOfExistingWhiteCubes(firstSum, "cube1", 1);
    			}
    			if (positions.get("cube2")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube2")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube2", 1);
    				}
    			} else if (positions.get("cube3")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube3")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube3", 1);
    				}
    			}
    		} else if (positions.get("cube2")[0] == firstSum) {
    			if (!isClaimed(firstSum, positions.get("cube2")[1])) {
    				setPositionOfExistingWhiteCubes(firstSum, "cube2", 1);
    			}
    			if (positions.get("cube1")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube1")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube1", 1);
    				}
    			} else if (positions.get("cube3")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube3")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube3", 1);
    				}
    			}
    		} else if (positions.get("cube3")[0] == firstSum) {
    			if (!isClaimed(firstSum, positions.get("cube3")[1])) {
    				setPositionOfExistingWhiteCubes(firstSum, "cube3", 1);
    			}
    			if (positions.get("cube2")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube2")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube2", 1);
    				}
    			} else if (positions.get("cube1")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube1")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube1", 1);
    				}
    			}
    		} else {
    			if (positions.get("cube1")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube1")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube1", 1);
    				}
    			} else if (positions.get("cube2")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube2")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube2", 1);
    				}
    			} else if (positions.get("cube3")[0] == secondSum) {
    				if (!isClaimed(secondSum, positions.get("cube3")[1])) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube3", 1);
    				}
    			}
    		}
    	}
    	
    	
    }
    
    /**
     * This method only moves the white cubes if there is at least one free cube which the player hasn't played. 
     * 
     * @param firstSum
     * @param secondSum
     * @param currentPlayer
     */
    public void moveFreeCubes(int firstSum, int secondSum, Player currentPlayer) {
    	
    	if (firstSum < 2 || firstSum > 12) {
		    throw new IllegalArgumentException("@param firstSum must be between 2 and 12.");
		}
    	
    	if (secondSum < 2 || secondSum > 12) {
		    throw new IllegalArgumentException("@param secondSum must be between 2 and 12.");
		}
    	
    	
    	String cubeToMove;
    	
    	// Create a HashMap to store the new positions
    	newPositions = new HashMap<>();
    	newPositions.put("cube1", new int[] {-2,-2});
    	newPositions.put("cube2", new int[] {-2,-2});
    	newPositions.put("cube3", new int[] {-2,-2});
    	
    	positions = whiteCubes.getPositions();
    	
    	
    	if (numberOfFreeWhiteCubes == 3) {
    		// if the firstSum is equal to the secondSum then we can only move one cube and we make two moves in that column
    		
    		if (firstSum == secondSum) {
    			
    			// Increments cube1 2 steps in the firstSum column.
    			incrementFreeCubesTwoSteps(firstSum, "cube1");
    			
    		} else {
    			
    			setPositionOfFreeCubes(firstSum, "cube1");
    			if (!columnsClaimedSet.contains(firstSum)) {
    				cubeToMove = "cube2";
    			} else {
    				cubeToMove = "cube1";
    			}
    			setPositionOfFreeCubes(secondSum, cubeToMove);
    			
    		}
    	} else if (numberOfFreeWhiteCubes == 2) {
    		
    		int cube1Position = positions.get("cube1")[1];
    		int columnToUse; // variable to keep track of which column to add the free cube if the cube already on board falls on 1st or 2nd sum
    		if (firstSum == secondSum) {
    			
    			/**
    			 *  check if the cube that is not free (which is cube 1 because we add cubes to the board in an orderly manner) is in
    			 *  firstSum column
    			 */
    			if (positions.get("cube1")[0] == firstSum) {
    				/**
    				 * Check if the cube is about to be claimed which means it's in the square below the last. If it is, we don't have to
    				 * move the white cube 2 spaces up
    				 */
    				if (isAboutToBeClaimed(firstSum, cube1Position)) {
    					setPositionOfExistingWhiteCubes(firstSum, "cube1", 1);
    				} else {
    					setPositionOfExistingWhiteCubes(firstSum, "cube1", 2);
    				}
    			} else {
    				incrementFreeCubesTwoSteps(firstSum, "cube2");
    			}
    			
    		} else {
    			
    			if (positions.get("cube1")[0] == firstSum) {
    				newPositions.put("cube1", new int[] {firstSum, cube1Position+1});
    				whiteCubes.setPosition(newPositions);
    				columnToUse = secondSum;
    				setPositionOfFreeCubes(secondSum, "cube2");
    				
    			} else if (positions.get("cube1")[0] == secondSum) {
    				newPositions.put("cube1", new int[] {secondSum, cube1Position+2});
    				whiteCubes.setPosition(newPositions);
    				columnToUse = firstSum; 
    				/**
    				 * We know that the cube 1 is in the column represented by the secondSum so we have to move cube 2 to the column 
    				 * represented by the first sum
    				 */
    				setPositionOfFreeCubes(firstSum, "cube2");
    				
    			} else {
    				
    				setPositionOfFreeCubes(firstSum, "cube2");
    				if (!columnsClaimedSet.contains(firstSum)) {
        				cubeToMove = "cube2";
        			} else {
        				cubeToMove = "cube1";
        			}
    				setPositionOfFreeCubes(firstSum, cubeToMove);
    				
    			}
    				
    		}
    	} else if (numberOfFreeWhiteCubes == 1) {
    		int cube1Position = positions.get("cube1")[1];
    		int cube2Position = positions.get("cube2")[1];
    		if (firstSum == secondSum) {

    			/**
    			 *  check if cube 1 is in firstSum column
    			 */
    			if (positions.get("cube1")[0] == firstSum) {
    				/**
    				 * Check if the cube is about to be claimed which means it's in the square below the last. If it is, we don't have to
    				 * move the white cube 2 spaces up
    				 */
    				if (isAboutToBeClaimed(firstSum, cube1Position)) {
    					setPositionOfExistingWhiteCubes(firstSum, "cube1", 1);
    				} else {
    					setPositionOfExistingWhiteCubes(firstSum, "cube1", 2);
    				}
    			} else if (positions.get("cube2")[0] == firstSum) {
    				/**
    				 * Check if the cube is about to be claimed which means it's in the square below the last. If it is, we don't have to
    				 * move the white cube 2 spaces up
    				 */
    				if (isAboutToBeClaimed(firstSum, cube2Position)) {
    					setPositionOfExistingWhiteCubes(firstSum, "cube2", 1);
    				} else {
    					setPositionOfExistingWhiteCubes(firstSum, "cube2", 2);
    				}
    			} else {
    				incrementFreeCubesTwoSteps(firstSum, "cube3");
    			}
    					
    		} else {
    			
    			if (positions.get("cube1")[0] == firstSum) {
					setPositionOfExistingWhiteCubes(firstSum, "cube1", 1);
					
					if (positions.get("cube2")[0] == secondSum) {
						setPositionOfExistingWhiteCubes(secondSum, "cube2", 1);
						
					} else {
						setPositionOfFreeCubes(secondSum, "cube3");
					}
					
    			} else {
    				if (positions.get("cube1")[0] == secondSum) {
    					setPositionOfExistingWhiteCubes(secondSum, "cube1", 1);
    					
    					if (positions.get("cube2")[0] == firstSum) {
    						setPositionOfExistingWhiteCubes(firstSum, "cube2", 1);
    					} else {
    						setPositionOfFreeCubes(firstSum, "cube3");
    					}
    				} else {
    					if (positions.get("cube2")[0] == firstSum) {
        					setPositionOfExistingWhiteCubes(firstSum, "cube2", 1);
        					setPositionOfFreeCubes(secondSum, "cube3");
    					} else {
    						if (positions.get("cube2")[0] == secondSum) {
    							setPositionOfExistingWhiteCubes(secondSum, "cube2", 1);
    							setPositionOfFreeCubes(firstSum, "cube3");
    						} else {
    							int selectedSum = SumDialogBox.showSumDialogBox(firstSum, secondSum);
    							if (selectedSum == firstSum) {
    								setPositionOfFreeCubes(firstSum, "cube3");
    							} else if (selectedSum == secondSum){
    								setPositionOfFreeCubes(secondSum, "cube3");
    							}
    						}
    					}
    				}
    				
    			}
    			
    		}
    	}
    }
    
    /**
     * This method sets the position of white cubes already in the board
     * 
     * @param sum - this is the column where the cube will be positioned
     * @param cubeNumber - this indicates if "cube1", "cube2", "cube3" will be positioned
     * @param numberOfPositions - number of steps the cube will be moved
     */
    public void setPositionOfExistingWhiteCubes(int sum, String cubeNumber, int numberOfPositions) {
    	
    	if (!cubeNumber.equals("cube1") && !cubeNumber.equals("cube2") && !cubeNumber.equals("cube3")) {
		    throw new IllegalArgumentException("@param cubeNumber must be equal to cube1, cube2, or cube3.");
		}
		
		if (sum < 2 || sum > 12) {
		    throw new IllegalArgumentException("@param sum must be between 2 and 12.");
		}
		
		if (numberOfPositions > 2 && numberOfPositions < 1) {
			throw new IllegalArgumentException("@numberOfPostions must be either 1 or 2");
		}
		
    	int cubePosition = positions.get(cubeNumber)[1];
    	newPositions.put(cubeNumber, new int[] {sum, cubePosition + numberOfPositions});
		whiteCubes.setPosition(newPositions);
    }
    

    
    /**
     * This method sets the position of free cubes. It checks the player's color, then check if the column in already claimed. If not,
     * it checks if the player already has a colored cube in that column. If the player does, then the white cube is placed on top
     * of the colored cube else, the white cube is placed at the bottom of the coloumn
     * 
     * @param sum - this is the column where the cube is placed. 
     * @param cubeNumber - this indicates if "cube1", "cube2", "cube3" will be placed in the column
     */
	public void setPositionOfFreeCubes(int sum, String cubeNumber) {
		
		if (!cubeNumber.equals("cube1") && !cubeNumber.equals("cube2") && !cubeNumber.equals("cube3")) {
		    throw new IllegalArgumentException("@param cubeNumber must be equal to cube1, cube2, or cube3.");
		}
		
		if (sum < 2 || sum > 12) {
		    throw new IllegalArgumentException("@param sum must be between 2 and 12.");
		}
		
		
		
		if (currentColor.equals(Color.RED)) {
			
			if (!columnsClaimedSet.contains(sum)) {
				if (CubePositions.RedCubePositions.cubeExistsInColumn(sum)) {
					columnPosition = CubePositions.RedCubePositions.getPosition(sum);
					System.out.println(columnPosition + 1);
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, 1});
				}
			}
			
			
		} else if (currentColor.equals(Color.BLUE)) {
			
			if (!columnsClaimedSet.contains(sum)) {
				if (CubePositions.BlueCubePositions.cubeExistsInColumn(sum)) {
					columnPosition = CubePositions.BlueCubePositions.getPosition(sum);
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, 1});
				}
				
			}
			
		} else if (currentColor.equals(Color.GREEN)) {
			
			if (!columnsClaimedSet.contains(sum)) {
				if (CubePositions.GreenCubePositions.cubeExistsInColumn(sum)) {
					columnPosition = CubePositions.GreenCubePositions.getPosition(sum);
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});	
				} else {
					newPositions.put(cubeNumber, new int[] {sum, 1});
				}
			}
			
			
		} else if (currentColor.equals(Color.ORANGE)) {
			
			if (!columnsClaimedSet.contains(sum)) {
				if (CubePositions.OrangeCubePositions.cubeExistsInColumn(sum)) {
					columnPosition = CubePositions.OrangeCubePositions.getPosition(sum);
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});	
				} else {
					newPositions.put(cubeNumber, new int[] {sum, 1});
				}
			}
			
			
		} else if (currentColor.equals(Color.YELLOW)) {
			
			if (!columnsClaimedSet.contains(sum)) {
				if (CubePositions.YellowCubePositions.cubeExistsInColumn(sum)) {
					columnPosition = CubePositions.YellowCubePositions.getPosition(sum);
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});	
				} else {
					newPositions.put(cubeNumber, new int[] {sum, 1});
				}
			}
			
			
		} else if (currentColor.equals(Color.PINK)) {
			
			if (!columnsClaimedSet.contains(sum)) {
				if (CubePositions.PinkCubePositions.cubeExistsInColumn(sum)) {
					columnPosition = CubePositions.PinkCubePositions.getPosition(sum);
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, 1});
				}
			}
		}
		
		whiteCubes.setPosition(newPositions);
	}
	
	public void incrementFreeCubesTwoSteps(int sum, String cubeNumber) {
		/**
		 * Within this if-condition we don't have to check if a column has been claimed because we have already checked for whether
		 * the user can make a valid move from a free white cube to the board. Since the first sum is equal to the second sum there
		 * is only one column which the user can make the move.
		 */
		
		
		/**
		 * I feel like we'll be able to fix logic error easily if we try to catch exceptions
		 */
		if (!cubeNumber.equals("cube1") && !cubeNumber.equals("cube2") && !cubeNumber.equals("cube3")) {
		    throw new IllegalArgumentException("@param cubeNumber must be equal to cube1, cube2, or cube3.");
		}
		
		if (sum < 2 || sum > 12) {
		    throw new IllegalArgumentException("@param sum must be between 2 and 12.");
		}
		
		if (currentColor.equals(Color.RED)) {
			// Below line checks if the user already has a red cube in the column
			if (CubePositions.RedCubePositions.cubeExistsInColumn(sum)) {
				columnPosition = CubePositions.RedCubePositions.getPosition(sum);
				/**
				 *  Checks if the column is about to be claimed which means the red cube is on the square below the last. If so
				 *  we don't increment the columnPosition by 2
				 */
				if (isAboutToBeClaimed(sum, columnPosition)) {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 2});
				}
			} else {
				newPositions.put(cubeNumber, new int[] {sum, 2});
			}
		} else if (currentColor.equals(Color.BLUE)) {
			if (CubePositions.BlueCubePositions.cubeExistsInColumn(sum)) {
				columnPosition = CubePositions.BlueCubePositions.getPosition(sum);
				if (isAboutToBeClaimed(sum, columnPosition)) {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 2});
				}
			} else {
				newPositions.put(cubeNumber, new int[] {sum, 2});
			}
			
		} else if (currentColor.equals(Color.GREEN)) {
			if (CubePositions.GreenCubePositions.cubeExistsInColumn(sum)) {
				columnPosition = CubePositions.GreenCubePositions.getPosition(sum);
				if (isAboutToBeClaimed(sum, columnPosition)) {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 2});
				}
			} else {
				newPositions.put(cubeNumber, new int[] {sum, 2});
			}
		} else if (currentColor.equals(Color.ORANGE)) {
			if (CubePositions.OrangeCubePositions.cubeExistsInColumn(sum)) {
				columnPosition = CubePositions.OrangeCubePositions.getPosition(sum);
				if (isAboutToBeClaimed(sum, columnPosition)) {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 2});
				}
			} else {
				newPositions.put(cubeNumber, new int[] {sum, 2});
			}
		} else if (currentColor.equals(Color.YELLOW)) {
			if (CubePositions.YellowCubePositions.cubeExistsInColumn(sum)) {
				columnPosition = CubePositions.YellowCubePositions.getPosition(sum);
				if (isAboutToBeClaimed(sum, columnPosition)) {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 2});
				}
			} else {
				newPositions.put(cubeNumber, new int[] {sum, 2});
			}
		} else if (currentColor.equals(Color.PINK)) {
			if (CubePositions.PinkCubePositions.cubeExistsInColumn(sum)) {
				columnPosition = CubePositions.PinkCubePositions.getPosition(sum);
				if (isAboutToBeClaimed(sum, columnPosition)) {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 1});
				} else {
					newPositions.put(cubeNumber, new int[] {sum, columnPosition + 2});
				}
			} else {
				newPositions.put(cubeNumber, new int[] {sum, 2});
			}
			
		}
		
		whiteCubes.setPosition(newPositions);
	}

}



 