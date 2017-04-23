package com.assignment;
/************************
 * 
 * Class which is used to add
 * a word to the blacklist.
 * 
 ********************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WordAdd{

	private String file;
	private String newWord;
	
	//function which adds a new word to the blacklist
	public WordAdd(String filename,String word) throws IOException 
	{
		this.file= filename;
		this.newWord = word;
		
		Writer output;
		output = new BufferedWriter(new FileWriter(file,true));  //clears file every time
		output.append("\r\n" + newWord);
		output.close();
	}

}
