import java.lang.Thread;
import java.util.Scanner;


class Main {
  public static void main(String[] args) {
    
    //Creating different colour variables to use later on in the code, this is primarily used to change the colour of the text for different circumstances
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";

    
    //creating a scanner object that is used to get the users input
    Scanner scan = new Scanner(System.in);

    // creating a player 1 board where he will place his own battleships
    player1Board p1board = new player1Board();
    
    //creating a read file object that is used get information from a text file and potentially change that information 
    readFile r = new readFile();
    r.openFile();//calls the open file method which is used to open the file 
    String highscore = r.gethighscore();//creating a string variable to store the highscore variable in the highscore textfile



    String WelcomeMessage = ANSI_BLUE + "Welcome to Battleship!" + ANSI_RESET;




    //Prints out the welcome message as well as the board used to play the battleship game
    System.out.println("\t\t\t\t\t\t" + WelcomeMessage + "\n");



    //prints the board to the console 
    System.out.println("    1 2 3 4 5 6 7");
    
    for (int i = 1; i < 8; i++){
      
      System.out.print(i + " | O O O O O O O |");//prints out a row of "posistions" for Battleship

      //printing out certain messages depending on how many times the loop has iterated
      if (i == 1){//if the loop iterated the first time, it will print a certain message
        
        System.out.println(" The rules of the game are simple:");
      
      } else if (i == 4){//if the loop iterated the forth time, it will print a certain message
      
        System.out.println(" Try and sink your opponents ships"); 
      
      } else if (i == 6){//if the loop interated the sixth time, it will print a certain message
      
        System.out.println(" Before they sink yours using the least amount of moves!");
      
      } else {// if none of the conditions are met, then it will print a new line
      
        System.out.print("\n");
      
      }
    }

    //prints out many new lines
    System.out.println("\n\n");

    //When using the thread class, there is a possibility that the program can be interupted and cause an error when using the thread class, thus whenever the word thread is used in this code it is in this try section
    try {
      


      Thread.sleep(6000);//creates a delay in the program which makes the program wait a certain amount of time (in milliseconds) before continuing on with the code
      
      System.out.println("Today you will be playing against....... \n");

      Thread.sleep(4000);

      System.out.println(ANSI_RED + "The C0MPUTER" + ANSI_RESET);

      Thread.sleep(4000);

      System.out.print("\033[H\033[2J");//in order to make everything very clean, this line of code is able to clear the console and allow the user to easily see and understand what is happening in the game
      

      Thread.sleep(3000);
      
      System.out.println("The current highscore (lowest moves required to win) is "+ highscore + " moves\nGuess all the battleship posistions in the least amount of moves to get on the leaderboard!! ");// tells the user the current highscore

      Thread.sleep(6000);

      System.out.println("First, lets start off by placing all 8 of your battleships");

      
      Thread.sleep(2000);

      //using the player1Board object created, a board is initialized and shown to the player
      p1board.initializeBoard();
      p1board.showBoard();

      //this loop allows the player to enter in the position of 8 battleships onto their board
      for (int i = 0; i < 8; i++){
        int xpos, ypos;
        
        System.out.println("Where would you like the x position of your ship to be");
        xpos = scan.nextInt();

        System.out.println("Where would you like the y position of your ship to be");
        ypos = scan.nextInt();

        p1board.placeship(xpos, ypos, p1board);//using the user input of the x and y position along with their board, the program places a battleship on the space the user entered using the placeship function
        Thread.sleep(4000);
        
        System.out.print("\033[H\033[2J");

        p1board.showBoard();//clears the console and shows the board to the user to show where they kept their battleship

      }


      System.out.println("Now that you have placed all of your ships we can begin the game, since you are the guest, you will go first! ");

      Thread.sleep(5000);
        
      System.out.print("\033[H\033[2J");
      computerBoard comp = new computerBoard();//creating a new computerboard object which has very similar methods to the playerboard 
      
      comp.initializeCompBoard();//initializes all the possible positions/spaces for the computer board
      
      comp.initializePossibleBoatPositions();//calls the initializePossibleBoatPositions method which generates random positions for the computer board
      
      // comp.showCompBoard(); //this method was mainly used in testing to determine if certain elements of the program worked (example: skinging a computer battleship once you guessed the right position), it is not required in the final program but is helpful for testing

      
      //creating an attack board object which has very similar methods to the regular player board
      player1AttackBoard p1attackboard = new player1AttackBoard();
      p1attackboard.initializeBoard();// initializing the regular player board

      
      //setting game variables to keep the game running such as turn (to determine if it is the players turn or the computers turn), the number of player and computer ships, and the total amount of turns, along with that, there is a boolean variable created to track if the player won or lost
      int computerShips = 8, playerShips = 8, turn = 0, totalPlayer1Turns = 0;
      boolean winner = false;

      Thread.sleep(3000);
      //main game loop, which will keep on running until the player or the computer does not have any more battle ships 
      while (computerShips!= 0 && playerShips !=0){
        //if the value of turn is equal to 1, that means its player 1's turn
        if (turn == 0){
          System.out.println("It's player 1's turn");

          int attackX, attackY, playerAttack;
          
          p1attackboard.showBoard();
          System.out.println("Where would you like to attack on the x axis: ");
          attackX = scan.nextInt();

          System.out.println("Where would you like to attack on the y axis: ");
          attackY = scan.nextInt();
          
          playerAttack = p1attackboard.hit(attackX, attackY, comp);// after telling the user that it is player 1's turn, it gets the input on where they would like to attack on the x and y position of the board, from there a method is used to determine if it hits a computer battleship, taking on the perameters of the x position attack, y position attack and the computer board object
          
          if (playerAttack == 3){//if the value returned in the method above is the integer 3, that means that the player hit the computers battleship and the code goes into here 
            
            Thread.sleep(3000);
            System.out.println(ANSI_GREEN + "You sunk the computer battleship" + ANSI_RESET);

            p1attackboard.replacePosition(attackX, attackY, playerAttack);//after telling the player they sunk the computers battleship, the attack board uses the replace method 
            totalPlayer1Turns++;// adds a turn to the total turns
            
            computerShips--;//subtracts the total amount of computer battleships
            
            if (computerShips == 0){ // if the computer has no more battleships left, that means the player won, meaning the code goes into here
            
              System.out.println(ANSI_GREEN + "You eliminated all of the computers battleships " + ANSI_RESET);
              winner = true;// sets the value of winner to true, telling the computer that the player won the game
            
            } else { //otherwise,if the computer still has battleships, the code goes into this part of the program and tells the user they get to go again
              System.out.println("Because of that, you now get to go again");
            }
            
            Thread.sleep(3000);
            System.out.print("\033[H\033[2J");


          } else if (playerAttack == 1){// if the value from the method above returns 1, that means that the player attacked outside of the boundries and the code goes into here
            
            Thread.sleep(1000);
            System.out.println("You went outside of the boundries, please enter in a valid input");
            Thread.sleep(3000);
            System.out.print("\033[H\033[2J");

          } else if (playerAttack == 2){//if the value from the method above returns 2, that means that the player attacked the same square twice, meaning that the code will go into here
            
            Thread.sleep(1000);
            System.out.println("you attacked the same position, please enter in a valid input");
            Thread.sleep(3000);
            System.out.print("\033[H\033[2J");


          } else if (playerAttack == 0){//if the value from the method above returns 3, that means the player had missed the computers battleship, making the code go into here

            Thread.sleep(1000);

            System.out.println("You missed the computers battleship");
            p1attackboard.replacePosition(attackX, attackY, playerAttack);
            totalPlayer1Turns++;
            Thread.sleep(3000);
            turn = 1;
            System.out.print("\033[H\033[2J");

            //after telling the player, that they missed the computers battleship, a method is then used to change the attacked space on the players attack board, from there, it accumulates the total amount of turns and makes it the computers turn
          }

        //if the value of turn is 1, that means that it is the computers turn, making the code go into here          
        } else if (turn == 1){
          System.out.println("Its the computer's turn");
          
          

          p1board.showBoard();// shows the player board to show that they are under attack
          boolean attack = comp.computerAttack(p1board); // this line of code creates a boolean variable which makes the computer attack a random position on the players board, it takes on the perameter of a player board in order to determine if it attacked the position of a battleship
          Thread.sleep(5000);
          
          if (attack == true){// if the value of the attack variable (boolean variable) is true, that means that the computer hit one of the players battleships and the program goes into here
            
            playerShips--;
            p1board.showBoard();
            Thread.sleep(2000);
            //subtracts one of the players battleships and shows where the computer attacked

            if (playerShips == 0){ // if the amount of player ships is zero, that means that the computer has sunk all of the computers battleships, thus the code will go into here, telling the user that the computer sunk all of the battleships
              System.out.println("The computer has sunk all of your battleships");
              Thread.sleep(3000);

            } else {// otherwise, if the statement above is false, that means the computer has not sunk all of the players battleships and tells the user that they will go again
              System.out.println("The computer sunk one of your battleships\nnow he gets to go again");
              Thread.sleep(3000);
            }
            turn = 1;// changes the turn back to one allowing the computer to go again

          } else { // if the condition above is false, this means that the computer has missed the players battleship
            
            System.out.println("The Computer missed your battleship");
            p1board.showBoard();
            Thread.sleep(3000);
            turn = 0;

            //once the code goes into here, it shows where the computer attacked on the players board then switches the turn back to the player
          }

          System.out.print("\033[H\033[2J");
        
        }


      }

      //closing the scanner object and clearning the console
      scan.close();
      System.out.print("\033[H\033[2J");
      
      if (winner){//if the player won the game the code goes into this part of the program to determine if they beat the highscore
        
        System.out.println("Congratulations, you won the game :), your total amount of moves is " + totalPlayer1Turns);
        
        int integerHighscore = Integer.valueOf(highscore);//since the value of the highscore is a string(Due to it being stored in a textfile), this variable is able to change the string value to an integer
        
        Thread.sleep(5000);
        
        if (totalPlayer1Turns < integerHighscore){// using the highscore integer, the program checks to see if the total amount of player moves was less than the high score. If it was, then the player beat the highscore and the highscore must change
        
          r.changeHighScore(totalPlayer1Turns);// changes the value of the highscore by calling the change highscore method, writing a new highscore into the text file
          
          System.out.println("You beat the highscore!!, the new highscore is now " + totalPlayer1Turns);
          Thread.sleep(5000);

        } else if (totalPlayer1Turns == integerHighscore){ // if the player tied the highscore, the program will go into here
          System.out.println("You tied the highscore!!");
          Thread.sleep(5000);

        } else { // if the first 2 statements are false, that means that the player did not beat the high score, nor tie the highscore, thus thr program goes into here, telling the user that they didn't beat the highscore
        
          System.out.println("Unfortunately, you didn't beat the highscore");
          Thread.sleep(5000);
        }

      } else {//otherwise if the statement above is not true, that means the player lost, thus their score was not recorded and they could not beat the high score
        System.out.println("Since you lost your score was not recorded :(");
        Thread.sleep(5000);

      }
      
      System.out.print("\033[H\033[2J");
      System.out.println("Thank you for playing battleship! :)");//clears the console and prints a final message




    } catch (InterruptedException e){//when using the thread class, if there are any problems that interupt the execussion of this program, then the program will immediately go into here, stoping the program and printing the issue
      System.out.println("The game stopped, please launch again");
      System.err.print(e.getMessage());

    } 

  }
}

