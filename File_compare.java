package com.assignment;

/******************
 * Class which compares the file 
 * with an array of bad words
 * and determines whether 
 * the tweets are offensive or not 
 * depending on rules in place. 
 ***********************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class File_compare extends GUI{
	
//file_compare variables
private static final long serialVersionUID = 1L;
private String file1;
private String file2;
private ArrayList<String> list = new ArrayList<String>();
	
	//File_compare constructor
	public File_compare(String fileName, String tweetFile) throws FileNotFoundException 
	{
		this.file1 = fileName;
		this.file2 = tweetFile;
	}
		
		
	public ArrayList<String> file_comp()
	{
		//delete text from previous results.
		getTextArea().setText(null);
		
		//try catch which will read from the file and 
		try 
		{	
			
			//set up scanner for badwords.txt
			File words = new File(file1);
			Scanner badWords = new Scanner(words);
			
			//set up scanner for tweets.txt
			File tweetFile = new File(file2);
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
		    	setWordCount(getWordCount() + 1);
		    	
		    	
		    	
		    	//nested if which checks usernames for profanity 
		    	//and resets counters for each tweet
		    	//also prints out results from each scan of a tweet.
		    	if(tweet.contains("@"))
		    	{
		    		
		    		
		    		
		    		
		    		//Only prints to text area if a tweet has been read first. 
		    		if(getTweetRead())
		    		{
		    			//if...else which determines whether tweet is abusive depending on character count
		    			//and whether the tweeter is shouting 
			    		if(getWordCount() >= 50 && getBadCount() > 3 || getWordCount() >= 30 && getBadCount()>= 2 || getWordCount() <30 && getBadCount() > 0)
			    		{
			    			setAbusiveTweet("Tweet appears to be abusive.");
			    		}
			    		else if(getShoutCount() >= 3)
			    		{
			    			setAbusiveTweet("Tweet appears to be abusive.");
			    		}
			    		else 
			    		{
			    			setAbusiveTweet("Tweet doesn't seem to be abusive.");
			    		}
			    		
			    		//writing to the textArea for results to be displayed on screen 
			    		list.add(getBadUserName() + "\n");
			    		list.add(getUsername() + getShouted() + "\n"); 
			    		list.add(Integer.toString(getBadCount()) + " out of " + Integer.toString(getWordCount() -1) +  "characters are bad words.\n");
			    		list.add(getAbusiveTweet() +"\n\n");
		    		}
		    		
		    		
		    		//resetting the tweet information after each tweet is read
		    		setTweetRead(true);
		    		setUsername(tweet);
		    		setBadUserName(getUsername() + " is an appropriate username. ");
		    		setShouted(" doesn't appear to be shouting.");
		    		setWordCount(0);
		    		setBadCount(0);
		    		setShoutCount(0);
		    		
		    		
		    		//for loop which checks if username contains a bad word
		    		for( int x=0; x< blacklist.size(); x++)
		    		{
		    			if( tweet.contains( blacklist.get(x)))
		    			{
		    				setBadUserName(getUsername() + " may be an offensive username. ");
		    				System.out.println( tweet );
		    			}//end if
		    		
		    		}

		    	}
		    	
		    	String shouting = tweet.toUpperCase();
		    	
		    	//if statement which checks if word is all caps and adds to shout count 
		    	if(shouting.equals(tweet) && tweet.length() >1)
		    	{
		    		setShouted(" appears to be shouting.");
		    		setShoutCount(getShoutCount()+1);
		    	}
		    	
		    	//counts number of bad words in tweet
		    	tweet = tweet.toLowerCase();
			    if( blacklist.contains( tweet ))
			    {
			    			System.out.println( tweet );
			    			setBadCount(getBadCount() + 1);
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
		
		return list;
		
	}//end file_comp()


	//Generated getters and setters
	public String getFile1() {
		return file1;
	}


	public void setFile1(String file1) {
		this.file1 = file1;
	}


	public String getFile2() {
		return file2;
	}


	public void setFile2(String file2) {
		this.file2 = file2;
	}


	public ArrayList<String> getList() {
		return list;
	}


	public void setList(ArrayList<String> list) {
		this.list = list;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
