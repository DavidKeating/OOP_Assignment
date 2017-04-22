package com.assignment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {
	

	
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JTextField wordAdd;
	private JButton compareFiles;
	private JTextArea textArea;
	private String found = "";
	private int count = 0;
	
	GridBagConstraints gbc = new GridBagConstraints();

	public GUI() {
		
		super("Abusive Language Detector");
		setLayout(new GridBagLayout());
		
		gbc.insets = new Insets(10, 10, 10, 10);
		
		title = new JLabel("Abusive Language Detector"); 
		title.setFont (title.getFont ().deriveFont (32f));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(title,gbc);
		
		compareFiles = new JButton("Press to scan text file for bad words."); 
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(compareFiles,gbc);
		
		textArea = new JTextArea(10, 40);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 1;
	    gbc.gridwidth = 3;
	    gbc.anchor = GridBagConstraints.NORTHWEST;
	    textArea.setEditable(false);
	    add(new JScrollPane(textArea), gbc);

		
		JLabel addVal = new JLabel("Enter a word to add to blacklist:"); 
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(addVal,gbc);
		
		wordAdd = new JTextField("",15); 
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		 
		add(wordAdd,gbc);
		
		
		
		
		thehandler handler = new thehandler(); 
		wordAdd.addActionListener(handler);
		compareFiles.addActionListener(handler);


		
		
		
	}
	
	private class thehandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String string = "";
			
			//method which compares both text files
			if(e.getSource() == compareFiles)
			{
				
				//try catch which will read from the file and 
				try 
				{	
					//resetting values for counters
					count=0;
					
					//set up scanner for badwords.txt
					File words = new File("badwords.txt");
					Scanner badWords = new Scanner(words);
					
					//set up scanner for tweets.txt
					File tweetFile = new File("tweets.txt");
					Scanner tweets = new Scanner(tweetFile);
				
					
					// add bad words into a set
					Set<String> blacklist = new HashSet<>();
					while (badWords.hasNextLine())
					{
						blacklist.add( badWords.nextLine() );
					}
					     
				    while (tweets.hasNext()) 
				    {
				    	String tweet = tweets.next();
					    if( blacklist.contains( tweet ))
					    {
					    			System.out.println( tweet );
					    			count++;
						}//end if
				    }//end while
				    
				    //close files
					tweets.close();
					badWords.close();
				}
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
					
				}//end try... catch
				
				string = String.format("Tweets have finished being scanned.");
				for(int i=0;i<10;i++)
				{
					found = count + " bad words have been found in the file\n";
				}
				
				textArea.setText(found);
				
				
				
			}//end if
			
			
			
			else if(e.getSource() == wordAdd)
				string = String.format("'%s' has been added to the blacklist.", e.getActionCommand());
			
			
			JOptionPane.showMessageDialog(null, string);
		}
	}
	
	

}
