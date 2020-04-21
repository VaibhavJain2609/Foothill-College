import becker.robots.*;

/**
 * CS1A - Assignment 3 - "The Maze" <br>
 * Quarter: Winter 2020 <br>
 * The MazeBot class follows having 4 methods written into it, each serving a
 * completely different purpose. The 4 methods are the following: 1.
 * moveCount(): Keeps track of how many times the robot has moved and in which
 * direction 2. printEverything(): Prints the total number of moves for all
 * directions 3. isAtEndSpot(): returns a boolean value to confirm that the
 * robot has reached the end 4. navigateMaze(): Navigates throughout the maze.
 * The most important part of this program are the methods isAtEndSpot() and
 * navigateMaze(). In Navigate maze, the robot moves throughout the maze, trying
 * to reach the ending.
 * The robot always checks for the if the front is clear, it is clear, only then it allowed to move.
 * If the front is not clear, it turns right so the front is clear.
 *           Total Spaces the robot moves South In navigateMaze(), there is a
 *           statement which allows us to drop things at places where things are
 *           not dropped This allows us to be able to keep on going even without
 *           having any content in our backpack to drop.
 * 
 *           The Robot finds its way throughout the maze by moving, turning
 *           right, and if there is a wall, it would turn left and repeat the
 *           process The robot sticks to the wall to complete the maze
 *
 * @author Jingtong (Constance) Huang
 * @author Vaibhav Jain
 */
class MazeBot extends RobotSE
{
   // Instance Variables will be declared and initialized here
   // one each for totalMoves, movesWest, movesEast, movesSouth, and MovesNorth

   public MazeBot(City theCity, int str, int ave, Direction dir, int numThings)
   {
      super(theCity, str, ave, dir, numThings);
   }

   private int totalMoves;
   private int movesEast;
   private int movesWest;
   private int MovesNorth;
   private int movesSouth;

   // <-- BIG HINT: You might want to create a new method here called
   // movesCounted() that will count everything it is supposed to by adding to
   // the instance variables before moving, and then use that instead of move()
   // in the NavigateMaze() method
   /**
    * Got this idea from having a look at the example provided in the class and
    * Switch used in Make Spiral Method. This way, it keeps track of all the way
    * the robot has moved.
    */
   public void moveCount()
   {

      Direction dir = getDirection();
      // Uses the switch to collect and count the number of moves made for each move
      // that occurs
      // using the direction the robot is facing while moving
      switch (dir)
      {
      /**
       * This switch cases keeps track of all the moves made: depends on the direction
       * it faces and keeps track once it moves.
       */

      case EAST:
         movesEast++;
         totalMoves++;
         break;
      case WEST:
         movesWest++;
         totalMoves++;
         break;
      case SOUTH:
         movesSouth++;
         totalMoves++;
         break;
      case NORTH:
         MovesNorth++;
         totalMoves++;

      }

   }

   public void printEverything()// Or printTotalNumberOfSpacesMoved(),
   // whichever you decide
   {
      System.out.println("Total Spaces Moved: " + totalMoves);
      System.out.println("Total Spaces Moved East: " + movesEast);
      System.out.println("Total Spaces Moved South: " + movesSouth);
      System.out.println("Total Spaces Moved West: " + movesWest);
      System.out.println("Total Spaces Moved: North " + MovesNorth);
   }

   // The isAtEndSpot() method below is what's called a 'helper method' It
   // exists just to make another command (in this case, NavigateMaze) easier
   // to understand. It does this by replacing some code that otherwise would
   // be in NavigateMaze with it's name, and doing that work here, instead.
   // Declaring it "private" means that only the MazeBot is allowed to call
   // upon it.
   private boolean isAtEndSpot()
   {
      return getAvenue() == 9 && getStreet() == 10;
   }

   // THIS IS THE ONE MAIN METHOD WILL USE TO DO EVERYTHING (ALTHOUGH IT CAN USE
   // OTHER METHODS LIKE isAtEndSpot, ETC)
   public void navigateMaze()
   {
      // While your robot hasn't yet reached the 'end spot', keep navigating
      // through the Maze and doing its thing
      while (!isAtEndSpot())
      {
         /**
          * At the end of this method, we are turning right after it moves, but if a wall
          * is present, and tries to move again, it would crash, or if it turns right, it
          * would cause the robot to crash.
          */
         while (!frontIsClear())
         {
            turnLeft();
         }
         /**
          * Using this way to provide for a way for the robot to continue moving even if
          * the backpack has not enough items. This way, if for example a place already
          * has a thing put, it would not put the thing there again. Of course, this
          * would only be possible if the things have contents in it.
          */
         if (countThingsInBackpack() > 0)
         {
            if (!canPickThing())
            {
               putThing();
            }
         }
         move();
         /**
          * Keeps track of how much the robot has moved
          */
         moveCount();
         // Turns right everytime the robot moves, which then leads to the line 107 to
         // run
         turnRight();

         // The robot will navigate the maze until it reaches the end spot.
         // What will you have the robot do at each step?
      }
      /**
       * After the maze has reached an end, the amount of times the robot has moved
       * would be printed in the console for us
       */
      printEverything();

      // After completing Maze, print total number of spaces moved and how
      // many times robot moved East, South, West, North.
   }

}

// ###################################################################################################
// NO NEED TO TOUCH ANYTHING FROM HERE ON DOWN, EXCEPT TO CHANGE NUMBER OF
// THINGS IN BACKPACK IN MAIN AND ADDING JavaDoc
// The NavigateMaze() method is already set up and called by don the robot down
// in main
// ###################################################################################################
public class Maze extends Object
{
   private static void makeMaze(City theCity)
   {
      for (int i = 1; i < 11; i++)
      {
         // north wall
         new Wall(theCity, 1, i, Direction.NORTH);

         // Second to north wall
         if (i <= 9)
            new Wall(theCity, 1, i, Direction.SOUTH);

         // Third to north wall
         if (i >= 4)
            new Wall(theCity, 4, i, Direction.SOUTH);

         // south wall
         if (i != 9) // (9, 10, SOUTH), is where the 'exit' is
            new Wall(theCity, 10, i, Direction.SOUTH);

         // west wall
         if (i != 1) // (1, 1, WEST) is where the 'entrance' is
            new Wall(theCity, i, 1, Direction.WEST);

         // second to most western wall
         if (i >= 3 && i < 6)
            new Wall(theCity, i, 6, Direction.WEST);

         // east wall
         new Wall(theCity, i, 10, Direction.EAST);
      }

      // Cul de Sac
      new Wall(theCity, 3, 10, Direction.WEST);
      new Wall(theCity, 3, 10, Direction.SOUTH);

      new Wall(theCity, 2, 8, Direction.WEST);
      new Wall(theCity, 2, 8, Direction.SOUTH);

      new Wall(theCity, 10, 8, Direction.NORTH);
      new Wall(theCity, 10, 9, Direction.EAST);
      new Wall(theCity, 10, 9, Direction.NORTH);
      makeSpiral(theCity, 8, 9, 3);
      new Wall(theCity, 8, 10, Direction.SOUTH);

      makeSpiral(theCity, 10, 5, 4);
   }

   public static void makeSpiral(City theCity, int st, int ave, int size)
   {
      // We start out building the wall northward
      // the walls will be built on the east face of the current
      // intersection
      Direction facing = Direction.EAST;

      while (size > 0)
      {
         int spacesLeft = size;
         int aveChange = 0;
         int stChange = 0;
         switch (facing)
         {
         case EAST:
            stChange = -1;
            break;
         case NORTH:
            aveChange = -1;
            break;
         case WEST:
            stChange = 1;
            break;
         case SOUTH:
            aveChange = 1;
            break;
         }

         while (spacesLeft > 0)
         {
            new Wall(theCity, st, ave, facing);
            ave += aveChange;
            st += stChange;
            --spacesLeft;
         }
         // back up one space
         ave -= aveChange;
         st -= stChange;

         switch (facing)
         {
         case EAST:
            facing = Direction.NORTH;
            break;
         case NORTH:
            facing = Direction.WEST;
            size--;
            break;
         case WEST:
            facing = Direction.SOUTH;
            break;
         case SOUTH:
            facing = Direction.EAST;
            size--;
            break;
         }
      }
   }

   // ###########################################################################################
   // Main Method
   // ###########################################################################################
   public static void main(String[] args)
   {
      City calgary = new City(12, 12);
      MazeBot don = new MazeBot(calgary, 1, 1, Direction.EAST, 1000); // <-- YOU WILL NEED TO CHANGE THIS FROM ZERO

      Maze.makeMaze(calgary);

      don.navigateMaze(); // <-- HERE'S WHERE THE NavigateMaze() method is
      // called. NO NEED TO TOUCH AT ALL

   }
}