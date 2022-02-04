//importing the random class to help create random numbers used later on in the program
import java.util.Random;

//initializing a new class called player1Board, which creates a board for the person playing
class player1Board{

  //creating the playerboard by using a 2d list to represent the rows (7 rows) and coloums (7 coloums) to make 49 squares
  protected String playerboard[][] = new String[7][7];
  
  // creating colour variables that are going to be used to create the board
  protected String ANSI_BLUE = "\u001B[34m"; 
  protected String ANSI_RESET = "\u001B[0m";
  protected String ANSI_WHITE = "\u001B[37m";



  //this method is used to create a board by adding elements into a list to represent squares, this is a protected method as other files (such as the player1attackboard file and computerboard file) can access it
  protected void initializeBoard(){
    //when creating the board, there is a for loop inside of a for lop in order to create a space for each index of the 2d array of playerboard
    for (int i = 0; i < playerboard.length; i++){
      
      for (int j = 0; j < playerboard.length; j++){
        
        //if the loop reached the end of one of the rows it goes into here
        if (j == (playerboard.length) -1){
          
          playerboard[i][j] = (ANSI_BLUE + "O" + ANSI_RESET);//prints the final space in the row and resets the font to white 

        // if it hasn't reached the end of the row it goes into here 
        } else {
          playerboard[i][j] = (ANSI_BLUE + "O");// adds a blue circle (opened space)
        }
        
      
      }
    }

  }

  // this method is used to print the board back to the user whenever the method is called
  public void showBoard(){

    System.out.println("  1 2 3 4 5 6 7");

    //for loop inside of a for loop to iterate through each of the elements in the list  
    for (int i = 0; i < playerboard.length; i++){
      System.out.print((i + 1) + " ");  //prints the number before all of the squares
      for (int j = 0; j < playerboard.length; j++){
        System.out.print( playerboard[i][j] + " ");//prints the board position and a space
      }
      System.out.print("\n"); //once the second for loop has gone through one full iteration, a new space is entered for the next row

    }

  }

  //this method is used to place a ship onto a specific position on the players board
  public void placeship(int xPos, int yPos, player1Board p1board){

    //if the player had chosen a position outside of the board, the program goes into this part of the program and places a ship on a random tile
    if ((xPos > 7 )|| (xPos < 0) || (yPos > 7) || (yPos < 0)){
      
      System.out.println("You picked a position outside of the board,\nnow we will choose a random position on the board");
      
      //creating a loop to generate a random position for the players ship to be, this is in a loop since there is a possibility that the computer could generate a position where the player has already placed a ship
      while (true){
        //creating 2 random objects that will be used to generate a random x and y position
        Random randomx = new Random();
        Random randomy = new Random();

        // generating 2 random numbers to be the x and y position and storing it into a variable
        int newXPos = randomx.nextInt(6);
        int newYPos = randomy.nextInt(6);

        // if the random position generated does not have a player ship, then the code goes into here
        if (!(p1board.getPlayerBoard()[newYPos][newXPos].equals(ANSI_WHITE + "*" + ANSI_RESET))){
          System.out.println("The New Position of your ship is " + (newXPos + 1) + " (x position) "+ (newYPos + 1) + " (y position)");
          playerboard[newYPos][newXPos] = ANSI_WHITE + "*" + ANSI_RESET; //places the ship on random position generated
          break;//breaks out of the while loop
        }
      }
    // if they place a ship ontop of a place where they have already placed a ship, the code will go into here
    } else if (playerboard[yPos -1][xPos -1].equals(ANSI_WHITE + "*" + ANSI_RESET)){
      
      System.out.println("You already placed a ship there, \nnow we will choose a random position");
      //creating a loop to generate a random position for the players ship to be, this is in a loop since there is a possibility that the computer could generate a position where the player has already placed a ship
      while (true){
        //creating 2 random objects that will be used to generate a random x and y position
        Random randomx = new Random();
        Random randomy = new Random();

        // generating 2 random numbers to be the x and y position and storing it into a variable
        int newXPos = randomx.nextInt(6);
        int newYPos = randomy.nextInt(6);

        // if the random position generated does not have a player ship, then the code goes into here
        if (!(p1board.getPlayerBoard()[newYPos][newXPos].equals(ANSI_WHITE + "*" + ANSI_RESET))){
          System.out.println("The New Position of your ship is " + (newXPos + 1) + " (x position) "+ (newYPos + 1) + " (y position)");
          playerboard[newYPos][newXPos] = ANSI_WHITE + "*" + ANSI_RESET;//places the ship on random position generated
          break;// breaks out of the while loop
        }
      }
    // if the 2 conditions above are not met, that means the player entered in a valid position on the board, thus the code goes into this section of the program
    } else {
      p1board.changePlayerBoard(xPos -1, yPos -1, (ANSI_WHITE + "*" + ANSI_RESET), p1board);
      // playerboard[yPos -1][xPos -1] = ANSI_WHITE + "*" + ANSI_RESET;// changes the specific position the user entered to be the position of a battleship (*). 
    }
  } 
  
  // this is a getter method which is used to get the players board, it is used by the computerboard file in order to determine if it hit the players battleship and to change certain position on the board aswell
  public String[][] getPlayerBoard(){
    return playerboard;
  }

  //this method is used to change a certain position on the playerboard and is used when the computer is attacking, it takes on 4 perameters, the x and y position generated by the computer (where the computer is attacking), a string which is a character on if they sunk a battleship or if they missed a battleship, the final perameter is the player board (as it can be different everygame). 
  public void changePlayerBoard(int xpos, int ypos, String character,player1Board p1board){
    p1board.getPlayerBoard()[ypos][xpos] = character; 


  }



}