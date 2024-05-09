
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
public class SaveGameGUI extends JFrame {
	private JFrame saveGameFrame;
	private JLabel saveGamePanelLabel;
	private JTextField fileNameInputField;
	private String userInput;
	private JButton saveButton;
	private JButton cancelButton;
	
	public SaveGameGUI(Game game) {
		
		//Create a JFrame
		saveGameFrame = new JFrame("Save Game");
		saveGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Create a JPanel for the content
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
        
		//Create a JLabel to display the prompt
        saveGamePanelLabel = new JLabel("Enter the name for your file");
        
        //Create the JTextField for user input
        fileNameInputField = new JTextField(15);
        
        //Create a JButton for saving the game
        saveButton = new JButton("Save Game");
        saveButton.addActionListener(event -> {
        	
        	//Access the text entered by the user and store it in a variable
            userInput = fileNameInputField.getText();
            
            //If the user the doesn't enter a name
            if (userInput.isEmpty()) {
            	JOptionPane.showMessageDialog(saveGameFrame, "Please enter a filename before saving the game.");
            } else {
            	try {
            		// Get the current working directory
            		String currentDirectory = System.getProperty("user.dir");
            		
            		
            		// Construct the file path using the current working directory and the file name. The file will be saved to the current folder
            		String filePath = currentDirectory + File.separator + userInput + ".ser";
            		
            		//Create a file object that represents the location where the game will be saved
            		File saveFile = new File(filePath);
            		
            		boolean created = saveFile.createNewFile();
            		if (created) {
            			
            			//Save the game by the filename input by the user
                        game.saveGame(saveFile);
                        
                        JPanel gameSavedPanel = new JPanel();
                        BoxLayout layout = new BoxLayout(gameSavedPanel, BoxLayout.Y_AXIS);
                        gameSavedPanel.setLayout(layout);
                        JLabel label1 = new JLabel( "Game saved successfully!");
                        JLabel label2 = new JLabel("Location " + currentDirectory);
                        gameSavedPanel.add(label1);
                        gameSavedPanel.add(label2);
                        
                        //Notify the user that the game has been saved
                        // JOptionPane.showMessageDialog(saveGameFrame,  "Game saved successfully!");
                        
                        JOptionPane.showMessageDialog(null, gameSavedPanel, "", JOptionPane.PLAIN_MESSAGE);
                        
                        //Dispose the frame after the file has been saved
                        saveGameFrame.dispose();
            		} else {
            			JOptionPane.showMessageDialog(saveGameFrame, "The specified filename is invalid or already exists.");
            		}
            	} catch (IOException ex) {
            		 JOptionPane.showMessageDialog(saveGameFrame, "An error occurred while saving the game: " + ex.getMessage());
                }
            }
            
            
        });
        
        //Create a JButton for cancelling the save
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> {
        	saveGameFrame.dispose();
        });
        
        //Add the components to the JPanel
        contentPane.add(saveGamePanelLabel);
        contentPane.add(fileNameInputField);
        contentPane.add(saveButton);
        contentPane.add(cancelButton);
        
        //Set the content pane of the JFrame
        saveGameFrame.setContentPane(contentPane);
        
        //Pack the frame to size its components
        saveGameFrame.pack();
        
        //Set the JFrame to be visible
        saveGameFrame.setVisible(true);
        
	}
	}
	
       


