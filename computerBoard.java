//importing the random class which is used to generate random numbers
import java.util.Random;

class computerBoard extends player1Board{// initializing the computerboard class which extends the player1Board class as it uses similar methods for some objects

  // similar to the player1Board, the computerBoard is also a 2d array of 7 rows and 7 columns
  protected String compboard[][] = new String[7][7];
  private String ANSI_RED = "\u001B[31m";


  // this method is used to create the computer board and give each space in the 2d array a value
  public void initializeCompBoard(){

    // using a for loop inside of a for loop, I am able to trace through each of the elements in the list and add a value 
    for (int i = 0; i < compboard.length; i++){
      
      for (int j = 0; j < compboard.length; j++){
          
        compboard[i][j] = ("O");// adding a value at each index of the list
  
      }
    }

  }

  //the computer board is different from the player board as it has to generate random positions each time it is created, thus the program goes into here
  public void initializePossibleBoatPositions(){
    // creating 2 objects from the random class which will be used to generate numbers 
    Random randomx = new Random();
    Random randomy = new Random();

    // using a for loop to add a certain amount of boats at randomly generated positions by the computer 
    for (int i = 0; i < 8; i++){
      // these 2 lines of code generate a 2 random numbers between 0 - 6 in order to add a boat to the computer board
      int newXPos = randomx.nextInt(6);
      int newYPos = randomy.nextInt(6);

      // if the random space generated by the computer does not already have a battleship, the code goes into here
      if (compboard[newYPos][newXPos] != "*"){
      
        compboard[newYPos][newXPos] =  "*";// adds a battleship to the randomly generated index value 
      
      // if the computer generates a random number and has already placed a boat in that position, then the code goes into here 
      } else {

        i = i - 1;// subtracts 1 from the number of times the loop has run, allowing the computer to generate another random position and see whether a battleship was already placed in a specific position. This part of the program is to ensure that the computer will always place 8 ships on the computer board


      }
    }
  }

  //this method was mainly used to see the computerboard in order to check if certain elements of the game worked (such as attacking at a certain position), it works the same as the showplayerboard function found in the player1board file  
  public void showCompBoard(){

    System.out.println("  1 2 3 4 5 6 7");
    
    for (int i = 0; i < compboard.length; i++){
      System.out.print((i + 1) + " ");  
      for (int j = 0; j < compboard.length; j++){
        System.out.print( compboard[i][j] + " ");
      }
      System.out.print("\n");

    }

  }

  //this boolean method is used to determine if the computer has hit the players battleship, taking on the perameter of the players battleship board in order to determine the position the computer attacks is a real position
  public boolean computerAttack(player1Board regularplayerboard){
    boolean hit = false;// setting a boolean value to determine whether or not it hit a battleship 
    //creating 2 objects from the random class that is used to generate 2 random numbers
    Random randomx = new Random();
    Random randomy = new Random();

    // while loop used to constantly generate random positions that way the computer does not attack the same position twice
    while (true){
      //creating 2 different variables that store the value of a random number
      int newXPos = randomx.nextInt(6);
      int newYPos = randomy.nextInt(6);

      //using the playerboard as well as the getplayerboard function, this if statement determines if the generated position from the computer has already been attacked (this way it doesn't attack the same position multiple times), if it is a new position, then the program goes into this if statement      
      if (!(regularplayerboard.getPlayerBoard()[newYPos][newXPos].equals(ANSI_RED + "*")) && !(regularplayerboard.getPlayerBoard()[newYPos][newXPos].equals(ANSI_RED + "X")) ){
        System.out.println("The computer attacked the position " + (newXPos + 1) + " (x position) "+ (newYPos + 1) + " (y position)");
        
        //checks to see if the position generated by the computer is the same position as one of the players battlerships, if it is, the program goes into here
        if (regularplayerboard.getPlayerBoard()[newYPos][newXPos].equals(ANSI_WHITE + "*" + ANSI_RESET)){
          hit = true;// changes the boolean value of hit to true, meaning that it hit one of the battleships
          changePlayerBoard(newXPos, newYPos, (ANSI_RED+"*"), regularplayerboard);//calls the changePlayerBoard function which changes a position on the players board to show that it was attacked
          return hit;//returns the value of hit

        //if the statement above is not true, that means that the computer missed the battleship and hit an empty space, thus the program will go into here           
        } else {
          changePlayerBoard(newXPos, newYPos, (ANSI_RED+"X"), regularplayerboard);// calls the changePlayerBoard function to change the open space to a missed space
          return hit;// returns the value of hit
        }

      }

    }  

  }

  //this getter method is used to simply return the computerboard 2d array (the computerboard) so that the program can determine if the player hit a battleship on the computers board
  public String[][] getCompBoard(){
    return compboard;
  }

  //getter method which gets a specific position from the computerboard object, this method takes on 2 perameters which is the x and y position entered in from the user, it then returns that postion on the computer board. This function is mainly used in the player1AttackBoard file to determine if the player hit one of the computers battleships
  public String computerPosition(int attackX, int attackY){
    return compboard[attackY-1][attackX-1];
  }
}