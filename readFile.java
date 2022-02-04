//importing classes used to open the textfile 
import java.io.*;
import java.util.*;

class readFile{ //creating the class readfile
  private Scanner x;// initializing a private scanner object variable used to read and write information to a file

  //this open file method is used to open the text file stored in the game
  public void openFile(){
    //when opening files, potential errors can occur, therefore we use the try and 
    try {
      x = new Scanner(new File("HighScore.txt"));// sets the value of the variable x (part of the scanner class) to a file in order to scan the file
    } catch (Exception e){// This line will catch any exceptions that occur throughout the code, meaning if the textfile could not be opened, the program will go into this portion of the code
      System.out.println("Could not open file");//tells the user that the text file couldn't be opened
    }
  }

  //this method is able to get the integer value stored in the text file, however it is a string method as everything in the textfile is seen as a string
  public String gethighscore(){
    return x.next();// gets the value of the first instance in the text function 
  }
  
  //this method is used to close the text file
  public void closehighscorefile(){
    x.close();//closes the file and the scanner object
  }

  // If the user beats the highscore than this method is executed where it changed the highscore in the text file
  public void changeHighScore(int highscore){
    
    // Since we are writing to a file, there are potential errors that could occur (such as the file being corrupted or not found), thus I used try here in order to get this code to work
    try {
      FileWriter fw = new FileWriter("HighScore.txt");//creating a new object called file writer which can write to different files (such as text files), it also required 1 perameter which is the file it is writing to
      String newHighscore = String.valueOf(highscore);// Converts the passed integer to a string 
      fw.write(newHighscore);//writes the new highscore value in the text file
      fw.close();//closes the file writer 

    // if an IOException occurs (cannot find the file), then the code goes into here and tells the user the computer cannot update the score
    }catch (IOException e){
      System.out.println("Could not update file");
    }


  }
}