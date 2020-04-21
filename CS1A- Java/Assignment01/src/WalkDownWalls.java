import becker.robots.*;

// Create a new type of robot, named RobotThatCanWalkDownWalls on the next line(s)
class RobotThatCanWalkDownWalls extends Robot
{
   RobotThatCanWalkDownWalls(City c, int st, int ave, Direction dir, int num)
   {
      super(c, st, ave, dir, num);
   }

   public void turnAround()
   {
      this.turnLeft();
      this.turnLeft();
   }

   public void turnRigth()
   {
      this.turnAround();
      this.turnLeft();
   }
   /*
    * public void move3() { while(this.frontIsClear()) { this.move(); }
    * if(!this.frontIsClear()) { this.turnLeft(); if(this.canPickThing()) {
    * this.pickThing(); this.move(); } else { this.move(); } }
    * 
    * } public void walkDownWall() { this.move(); this.turnRigth();
    * 
    * this.move3(); // this.pickThing(); //this.move(); this.turnRigth();
    * this.move3();
    * 
    * // this.move(); this.putThing(); }
    */

   public void move3()
   {
      while (this.frontIsClear())
      {
         this.move();
      }
      this.turnLeft();
   }

   public void walkDownWall()
   {
      this.move();
      this.turnRigth();
      this.move3();

   }

   // You should create a new command named turnRight here:
   // The book likes to call commands 'services'. In programming lingo, a
   // command or service is called a 'method'

   // You should create a new service named walkDownWall here:

}

public class WalkDownWalls extends Object
{
   public static void main(String[] args)
   {
      City wallingford = new City();
      RobotThatCanWalkDownWalls jo = new RobotThatCanWalkDownWalls(wallingford, 0, 1, Direction.EAST, 0);
      // build the tower
      new Wall(wallingford, 0, 1, Direction.SOUTH);
      new Wall(wallingford, 1, 1, Direction.EAST);
      new Wall(wallingford, 2, 1, Direction.EAST);
      new Wall(wallingford, 3, 1, Direction.EAST);
      new Wall(wallingford, 3, 2, Direction.SOUTH);
      new Wall(wallingford, 4, 2, Direction.EAST);
      new Wall(wallingford, 5, 2, Direction.EAST);
      new Wall(wallingford, 6, 2, Direction.EAST);
      new Wall(wallingford, 6, 3, Direction.SOUTH);
      // Add a thing
      new Thing(wallingford, 3, 2);
      jo.walkDownWall();
      if (jo.canPickThing())
      {
         jo.pickThing();
      }
      jo.walkDownWall();
      jo.move();
      jo.putThing();
      // At this point, you should write code that uses new services (like
      // the walkDownWall method) along with the built in services
      // (like the pickThing method) that makes the robot
      // do the same thing as the flowchart in the in class exercises.

   }
}