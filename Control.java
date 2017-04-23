/**********************************************************************
 * Author: David Keating
 * Student Number:C14357031
 * Date last modified: 24/04/2017
 * 
 * Description: Program which reads a file and informs the user whether 
 * said file contains bad language, shouting etc. and the severity of 
 * the language depending on the length of the file.
 * 
 * The user can also edit the list of bad words 
 * which it's being compared against.
 * This is all done through a GUI.
 * 
 * A list of detected bad words
 * are also displayed in the console log.
 * 
 **************************************************************************/

package com.assignment;
import java.io.FileNotFoundException;
import javax.swing.JFrame;


public class Control {
	

	public static void main(String[] args) throws FileNotFoundException {
	
		//set up GUI
		JFrame.setDefaultLookAndFeelDecorated(true);
		GUI menu = new GUI();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.pack();
		menu.setVisible(true);

	}

}
