package com.assignment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;

import javax.sound.midi.MetaMessage;
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
import java.util.ArrayList;
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
	private int badCount = 0;
	private String shouted ;
	private String username;
	private String badUserName;
	private int wordCount = 0;
	private String abusiveTweet;
	private Boolean tweetRead = false;
	
	
	GridBagConstraints gbc = new GridBagConstraints();

	public GUI() throws FileNotFoundException {
		
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
		
		compareFiles = new JButton("Press to scan tweets for bad words."); 
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
		
		
		//instantiation of classes
		

		
		
	}
	
	private class thehandler implements ActionListener 
	{
		
		
	
		public void actionPerformed(ActionEvent e)
		{
			String string = "";
			
	
			
			//method which compares both text files
			if(e.getSource() == compareFiles)
			{
				//delete text from previous results.
				textArea.setText(null);
				
				//try catch which will read from the file and 
				try 
				{	
					
					//set up scanner for badwords.txt
					File words = new File("badwords.txt");
					Scanner badWords = new Scanner(words);
					
					//set up scanner for tweets.txt
					File tweetFile = new File("tweets.txt");
					Scanner tweets = new Scanner(tweetFile);
				
					
					// add bad words into a set
					ArrayList<String> blacklist = new ArrayList<String>();
					while (badWords.hasNextLine())
					{
						blacklist.add( badWords.nextLine() );
					}
					     
					//comparing the tweet's against my set of bad words
				    while (tweets.hasNext()) 
				    {
				    	String tweet = tweets.next();
				    	wordCount++;
				    	
				    	
				    	
				    	//nested if which checks usernames for profanity 
				    	//and resets counters for each tweet
				    	//also prints out results from each scan of a tweet.
				    	if(tweet.contains("@"))
				    	{
				    		
				    		
				    		
				    		
				    		//Only prints to text area if a tweet has been read first. 
				    		if(tweetRead)
				    		{
				    			//if...else which determines whether tweet is abusive depending on character count
					    		if(wordCount >= 100 & badCount > 3 || wordCount <=60 & badCount>= 2 || wordCount <=20 &badCount > 0)
					    		{
					    			abusiveTweet = "Tweet appears to be abusive.";
					    		}
					    		else 
					    		{
					    			abusiveTweet = "Tweet doesn't seem to be abusive.";
					    		}
					    		
					    		//writing to the textArea for results to be displayed on screen 
					    		textArea.append(badUserName + "\n");
					    		textArea.append(username + shouted + "\n"); 
					    		textArea.append(Integer.toString(badCount) + " out of " + Integer.toString(wordCount -1) +  "characters are bad words.\n");
					    		textArea.append(abusiveTweet +"\n\n");
				    		}
				    		
				    		tweetRead = true;
				    		username = tweet;
				    		badUserName = username + " is an appropriate username. ";
				    		shouted = " doesn't appear to be shouting.";
				    		wordCount = 0;
				    		badCount = 0;
				    		
				    		
				    		//for loop which checks if username contains a bad word
				    		for( int x=0; x< blacklist.size(); x++)
				    		{
				    			if( tweet.contains( blacklist.get(x)))
				    			{
				    				badUserName = username + " may be an offensive username. ";
				    				System.out.println( tweet );
				    			}//end if
				    		
				    		}

				    	}
				    	
				    	String shouting = tweet.toUpperCase();
				    	
				    	//if statement which checks if word is all caps
				    	if(shouting.equals(tweet) && tweet.length() >1)
				    	{
				    		shouted = " appears to be shouting.";
				    	}
				    					    	
				    	tweet = tweet.toLowerCase();
					    if( blacklist.contains( tweet ))
					    {
					    			System.out.println( tweet );
					    			badCount++;
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
				
				
				
				
				
			}//end if
			
			
			
			else if(e.getSource() == wordAdd)
				string = String.format("'%s' has been added to the blacklist.", e.getActionCommand());
			
			
			JOptionPane.showMessageDialog(null, string);
		}
		
	}//end thehandler

	//generated getters and setters
	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JTextField getWordAdd() {
		return wordAdd;
	}

	public void setWordAdd(JTextField wordAdd) {
		this.wordAdd = wordAdd;
	}

	public JButton getCompareFiles() {
		return compareFiles;
	}

	public void setCompareFiles(JButton compareFiles) {
		this.compareFiles = compareFiles;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public String getFound() {
		return found;
	}

	public void setFound(String found) {
		this.found = found;
	}

	public int getBadCount() {
		return badCount;
	}

	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}

	public String getShouted() {
		return shouted;
	}

	public void setShouted(String shouted) {
		this.shouted = shouted;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBadUserName() {
		return badUserName;
	}

	public void setBadUserName(String badUserName) {
		this.badUserName = badUserName;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public String getAbusiveTweet() {
		return abusiveTweet;
	}

	public void setAbusiveTweet(String abusiveTweet) {
		this.abusiveTweet = abusiveTweet;
	}

	public Boolean getTweetRead() {
		return tweetRead;
	}

	public void setTweetRead(Boolean tweetRead) {
		this.tweetRead = tweetRead;
	}

	public GridBagConstraints getGbc() {
		return gbc;
	}

	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//end of getters and setters
}
