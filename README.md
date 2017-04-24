# OOP_Assignment

I decided to code the Abusive Language Detector Program.

-My program has 5 classes in total.

-My control class creates an instance of my GUI class 
 which then sets up a GUI using a GridBagLayout.
 
-All of the other classes are called through action listeners 
 and different functions are perfromed depending on which class is called. 
 
-My main function is in the File_compare class which opens two text files and 
 called badwords.txt and tweets.txt.
 -the bad words are then stored in an array list and it's compared against 
  the tweets using the .contains() function. 
  - In my program I check for a username, which is determined by an '@' symbol and 
    determine if it contains bad words. This is also used to reset counters so the rgram knows it's 
    on a new tweet.
  - I use multiple if statements to determine whether a tweet is abusive depending on character count 
    and whether the tweeter is shouting (ALL CAPS) 
  - MY program will tell you if a username is offensive, how many words are in their tweets, how many bad words
    are in their tweet , if they are shouting and whether the tweet is deemed abusive or not depending on my 
    constraints. The results of this scan will be displayed in a text area on the GUI.
   
-I've also included two more functions into the GUI.
-The user can enter a word in a text field,press enter and that word will be added to badwords.txt at
 the end of the file.
-The user can also enter a word to delete from badwords.txt which works by writing the text files contents
 into a temporary file, and replacing the entered string with "" when detected. The original file is then deleted 
 and the temporary file is renamed as badwords.txt.
 

   
