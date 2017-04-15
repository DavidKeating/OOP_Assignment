package com.assignment;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JTextField wordAdd;
	private JButton compareFiles;
	
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
		
		JLabel addVal = new JLabel("Enter a word to add to blacklist:"); 
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(addVal,gbc);
		
		wordAdd = new JTextField("",15); 
		gbc.gridx = 3;
		gbc.gridy = 2;
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
			
			if(e.getSource() == compareFiles)
				string = String.format("field 1: %s", e.getActionCommand());
			else if(e.getSource() == wordAdd)
				string = String.format("field 1: %s", e.getActionCommand());
			
			
			JOptionPane.showMessageDialog(null, string);
		}
	}
	
	

}
