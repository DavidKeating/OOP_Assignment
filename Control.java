/*****************************
 * 
 * Author: David Keating
 * Date modified: 02/04/2017
 * 
 * Description: Program which reads a file and informs the user whether 
 * said file contains bad language, shouting etc. and the severity of 
 * the language depending on the length of the file.
 * 
 * The user can also edit the list of bad words 
 * which it's being compared against.
 * 
 * 
 * 
 *****************************/

package com.assignment;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Control {
	

	public static void main(String[] args) {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		GUI menu = new GUI();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.pack();
		menu.setSize(new Dimension(500, 300));
		menu.setVisible(true);

	}

}
