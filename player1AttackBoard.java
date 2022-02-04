// creating the class player1attackboard that extends computerboard since it uses similar functions and instance data stored in the computer board
class player1AttackBoard extends computerBoard{
  //importing colours that are used in this program
  private String ANSI_RED = "\u001B[31m";
  protected String ANSI_WHITE = "\u001B[37m";

  //method (hit) used to detect if player1 hit a ship, takes on 3 perameters, the x position the player attacked, the y position the player attacked and the computer board (since the computer board is different every game)
  public int hit (int attackX, int attackY, computerBoard compboard){
    int directHit = 0; //since there are many siuations that can occur, I used a integer to represent all of the different situations
    
    // if they attacked out of bounds (outside of the board), then there is a certain situation for that
    if (attackX > 7 || attackX < 0 || attackY > 7 || attackY < 0){
      
      directHit = 1;//setting the value of directhit to 1
    
    //if the current player board has already been attacked, it goes into this section that way they don't attack the same position twice
    } else if (getPlayerBoard()[attackY - 1][attackX -1].equals(ANSI_WHITE+"X") || getPlayerBoard()[attackY - 1][attackX - 1].equals(ANSI_RED + "*")){
      
      directHit = 2;//sets the value of directhit to 2
        
    //if the position they attacked happened to be where the computers battleship is, then the code goes into here 
    } else if (compboard.computerPosition(attackX, attackY) == ("*")){

      directHit = 3;//sets the value of directhit to 3
    
    //if the position the player attacked didn't have a computer battleship (empty space) the code goes into here
    } else if (compboard.computerPosition(attackX, attackY) == ("O")){
      
      directHit = 0;// sets the value of the directHit variable to 0
        
    }

    return directHit; //value of hit is then returned to the main file which impact what happens in the game

  }

  //this method is used to replace a position on the players attack board, taking on the perameters of where they attacked on the x axis (attackX), where they attacked on the Y axis (attackY), and whether they hit the computers ship or missed the comptuers ship
  public void replacePosition(int attackX, int attackY, int hitvalue){
    //if the hit value is zero, that means that they missed the computers battleship
    if (hitvalue == 0){
      
      getPlayerBoard()[attackY -1][attackX - 1] = (ANSI_WHITE+ "X");//replaces the position on the player1attackboard with a white X
      
    // if the hit value is 3, that means that they hit the computers battleship    
    } else if (hitvalue == 3){
      
      getPlayerBoard()[attackY -1][attackX - 1] = (ANSI_RED+ "*");//replaces the position on the player1attackboard witha red * to represent they hit and sunk one of the computers battleships
    
    }

  }


}