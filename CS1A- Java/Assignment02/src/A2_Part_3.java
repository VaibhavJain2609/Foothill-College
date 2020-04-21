import becker.robots.*;
import java.util.Random;
// Made by Jingtong Huang and Vaibhav Jain- CS1A, Winter 2020, Assignment 2 Part 3
public class A2_Part_3 extends Object
{
   public static void main(String[] args)
   {
      City wallville = new City(6, 12);
      advRobot rob = new advRobot(wallville, 1, 2, Direction.EAST, 0);

      A2_Part_3.BuildCity(wallville); // this calls the "BuildCity" method, below

      // TODO: * * * Your code to race around the race track goes here! * * *
      // HINT: Several while loops would work well for this particular exercise.
      // One possible solution would be to start with a while loop (for checking the
      // count)
      // that contains two nested while loops used to check whether the front is clear
      // before performing some actions. Jumping through this initial while loop might
      // use additional while loops to continue checking whether the front is clear
      // before performing other actions in order to complete the tasks of the maze.
      /*
       * int i=0; while ( i<4) { while(rob.frontIsClear()) { rob.move();
       * if(rob.canPickThing()) { rob.pickThing(); rob.turnAround(); }
       * 
       * } if(!rob.frontIsClear()) { rob.newLane(); } i++; }
       * 
       * while(rob.countThingsInBackpack()>0) { rob.move(); rob.putThing();
       * if(!rob.frontIsClear()) { rob.turnAround(); }
       * 
       * }
       */
      rob.doEverything();
   }

   /////////////////////////////////////////////////////////////////////////////////////////
   // No need to touch any of the code below.
   // All it does is construct the maze in the city.
   /////////////////////////////////////////////////////////////////////////////////////////
   public static void BuildCity(City wallville)
   {
      // Width and height must be at least 2 (each)
      // Feel free to change these numbers, and see how your racetrack changes

      Random randomNumberGenerator = new Random();
      int top = 1;
      int left = 2;
      int height = 4;
      int width = 7 + randomNumberGenerator.nextInt(4);

      int streetNumber = top;
      while (streetNumber <= height)
      {
         if (streetNumber == 1)
         {
            // the topmost line:
            new Wall(wallville, streetNumber, left, Direction.NORTH);
         } else if (streetNumber == height)
         {
            // generate the 'holding spot' thing at the bottom:

            // the corner:
            new Wall(wallville, streetNumber + 1, left, Direction.WEST);
            new Wall(wallville, streetNumber + 1, left, Direction.SOUTH);
            int spotNum = left + 1;
            int counter = 0;
            while (counter < height)
            {
               new Wall(wallville, streetNumber + 1, spotNum, Direction.NORTH);
               new Wall(wallville, streetNumber + 1, spotNum, Direction.SOUTH);
               // Uncomment the next line for a 'final state' picture (i.e., the second picture
               // in the assignment)
               // new Thing(wallville, streetNumber + 1, spotNum);
               ++spotNum;
               ++counter;
            }
            new Wall(wallville, streetNumber + 1, spotNum, Direction.WEST);
         }

         // the most western, vertical line:
         new Wall(wallville, streetNumber, left, Direction.WEST);
         // the most eastern, vertical line:
         new Wall(wallville, streetNumber, width, Direction.EAST);
         // the Thing at the end of the tunnel
         new Thing(wallville, streetNumber, width);

         int aveNum = left + 1;
         while (aveNum <= width)
         {
            new Wall(wallville, streetNumber, aveNum, Direction.NORTH);
            new Wall(wallville, streetNumber, aveNum, Direction.SOUTH);
            ++aveNum;
         }

         ++streetNumber;
      }
   }
}

class advRobot extends Robot
{

   public advRobot(City arg0, int arg1, int arg2, Direction arg3, int items)
   {
      super(arg0, arg1, arg2, arg3);
      // TODO Auto-generated constructor stub
   }

   // turns the robot around
   public void turnAround()
   {
      this.turnLeft();
      this.turnLeft();
   }

   // turns the robot right
   public void turnRight()
   {
      this.turnAround();
      this.turnLeft();
   }

   // Helps switch the lane
   public void newLane()
   {
      this.turnLeft();
      this.move();
      this.turnLeft();
   }

   // moves the robot down the wall
   public void movetoWall()
   {
      while (this.frontIsClear())
      {
         this.move();
      }
   }

   // master method
   public void doEverything()
   {
      // Initializing a counter variable to assist us to switch lanes
      int counter = 0;
      // Loop that switches the lane
      while (counter < 4)
      {

         movetoWall();
         this.pickThing();
         // As the thing is at the end of the lane, we could assume that it picks thing
         // up
         this.turnAround();
         this.movetoWall();
         this.newLane();
         // Without this, this loop would run infinite amount of times,
         counter++;

      }
      // Puts things down while moving- Moves and drops
      while (this.countThingsInBackpack() > 0)
      {
         this.move();
         this.putThing();

      }
      // Leading the robot to it's original location
      this.returnToStart();
   }

   // Final steps to return the robot to it's original location
   public void returnToStart()
   {
      this.turnAround();
      this.movetoWall();
      this.turnRight();
      this.movetoWall();
      this.turnRight();
   }

}
