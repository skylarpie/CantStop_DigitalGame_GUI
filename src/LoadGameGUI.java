import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class LoadGameGUI extends JFrame {
	private JFrame loadGameFrame;
	private JLabel loadGamePanelLabel;
	private JButton loadButton;
	private JButton cancelButton;
	
	public LoadGameGUI(Game game) {
		
		//Create a JFrame
		loadGameFrame = new JFrame("Load Game");
		loadGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Create a JPanel for the content
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
        
        //Create a JButton for saving the game
        loadButton = new JButton("Load Game");
        loadButton.addActionListener(event -> {
        	
        	//Create a file picker using JFileChooser to let the user select the file to load
    		JFileChooser chooser = new JFileChooser();
    		
    		int returnVal = chooser.showOpenDialog(null);
    		if (returnVal == JFileChooser.APPROVE_OPTION) {
    			File loadedFile = chooser.getSelectedFile();
    			if (loadedFile != null) {
    				try (FileInputStream fileIn = new FileInputStream(loadedFile);
    					ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
    						Object obj = objectIn.readObject();
    					if (obj instanceof Game) {
    						Game selectedGame = (Game) obj;
    						game.loadGame(selectedGame);
    						JOptionPane.showMessageDialog(null, "Game loaded successfully");
    						loadGameFrame.dispose();
    					} else {
    						JOptionPane.showMessageDialog(null, "The selected file is not valid");
    					}
    				} catch (IOException | ClassNotFoundException ex) {
    					JOptionPane.showMessageDialog(null, "An error occured while loading the game:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    				}
    			}
    		}
        });
        
        //Create a JButton for cancelling the save
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> {
        	loadGameFrame.dispose();
        });
        
        //Add the components to the JPanel
        contentPane.add(loadButton);
        contentPane.add(cancelButton);
        
        //Set the content pane of the JFrame
        loadGameFrame.setContentPane(contentPane);
        
        //Pack the frame to size its components
        loadGameFrame.pack();
        
        //Set the JFrame to be visible
        loadGameFrame.setVisible(true);
        
	}
	}

