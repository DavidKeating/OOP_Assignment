package com.assignment;

/*******************************
 * Class which is used to 
 * delete a word from a blacklist.
 ****************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class DeleteWord extends File_compare {

	
	
	private static final long serialVersionUID = 1L;

	public DeleteWord(String fileName, String tweetFile,String userinput) throws IOException
	{
		super(fileName, tweetFile);
		// TODO Auto-generated constructor stub
	
		File file = new File(fileName);
		File temp = File.createTempFile("file", ".txt", file.getParentFile());
		
		String delete = userinput;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp)));
		
		for (String line; (line = reader.readLine()) != null;) {
		    // ...
			
			if (line.equals(delete))
			{
				line = line.replaceAll(delete , "");
			}
			writer.println(line);
			
		}
		
		
		
	
		reader.close();
		writer.close();

		
		file.delete();
		temp.renameTo(file);
	}

}
