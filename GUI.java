/*****************************
 * 
 * GUI setup and calling 
 * functions from other classes 
 * using action listeners.
 * 
 ******************************/

package com.assignment;

import java.awt.Color;
import java.awt.Container;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;



public class GUI extends JFrame {
	
	
	//creation of variables
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JLabel del;
	private JTextField wordAdd;
	private JButton compareFiles;
	private JTextArea textArea;
	private JTextField wordDelete;
	
	private String found = "";
	private int badCount = 0;
	private String shouted ;
	private String username;
	private String badUserName;
	private int wordCount = 0;
	private int shoutCount =0;
	private String abusiveTweet;
	private Boolean tweetRead = false;
	private String string1;
	
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
		
		del = new JLabel("Enter a word to delete from the blacklist:"); 
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(del,gbc);
		
		wordDelete = new JTextField("",15); 
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(wordDelete,gbc);
		
		//adding action listeners
		thehandler handler = new thehandler(); 
		wordAdd.addActionListener(handler);
		compareFiles.addActionListener(handler);
		wordDelete.addActionListener(handler);
		
	}//end of GUI layout
	
	//handler which calls other classes depending on user choice
	private class thehandler implements ActionListener 
	{
	
		public void actionPerformed(ActionEvent e)
		{
			
			//If user clicks button to compare tweets function will be called
			if(e.getSource() == compareFiles)
			{
				try
				{
					File_compare compare = new File_compare("badwords.txt","tweets.txt");
					ArrayList<String> array = compare.file_comp();
					for (int i=0; i<array.size(); i++) 
					{
						textArea.append(array.get(i));
					}
				}
				catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				string1 = String.format("Tweets have finished being scanned.");
				
			}//end if
			
			//If the user enters a word and presses enter that word will be added to the blacklist
			else if(e.getSource() == wordAdd)
			{
				try
				{
					WordAdd append = new WordAdd("badwords.txt", e.getActionCommand());
					string1 = String.format("'%s' has been added to the blacklist.", e.getActionCommand());
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
			}
			
			//if the user types a word and presses enter that word will be deleted
			else if(e.getSource() == wordDelete)
			{
				try 
				{
					DeleteWord delete = new DeleteWord("badwords.txt", "tweets.txt",e.getActionCommand());
					string1 = String.format("'%s' has been deleted from the blacklist.", e.getActionCommand());
				} 
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			
			//prints string as a message
			JOptionPane.showMessageDialog(null, string1);
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

	public int getShoutCount() {
		return shoutCount;
	}

	public void setShoutCount(int shoutCount) {
		this.shoutCount = shoutCount;
	}

	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}
	
	
	//end of getters and setters
}
