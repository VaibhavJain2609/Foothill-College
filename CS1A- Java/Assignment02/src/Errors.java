import becker.robots.*;

/* Assignment 2 Errors */

class RobotThatSpins extends Robot
{
    RobotThatSpins(City c, int st, int ave, Direction dir, int num)
    {
        super(c, st, ave, dir, num);
    }
    
    /**
     * Turn around five complete times (i.e., it spins 360 degrees, five times), then stops
     */
    public void spin()
    {
        int numberOfTurns = 0;
        while( numberOfTurns )
        {
            ian.turnLeft();
        }
    }
    
}

public class Errors extends Object
{
    public static void main(String[] args)
    {   
        CityFrame jetsburg = new CityFrame();
        RobotThatSpins christopher = new RobotThatSpins(jetsburg, 2, 3, Directions.WEST, 0);
        Robot ian = new Robot(jetsburg, 1, 2, Direction.EAST, 0);
                
        /* Christopher spins around if not standing beside ian*/
        // You may need to look up the getAvenue/avenue commands 
        // (either in your textbook, or online)
        if (christopher.getAvenue() != ian.avenue())
        {
            christopher.spin();
        }
    }
}