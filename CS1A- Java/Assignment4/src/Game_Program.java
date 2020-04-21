/**
 * CS1A, Assignment 4, "Guessing Game" <br>
 * Quarter: <br>
 * This is the class that allows us to play the game. We could keep on playing
 * the game as long as it is true. <br>
 * The
 * 
 * @author Vaibhav Jain
 * @author Jingtong 'Constance' Huang
 */
public class Game_Program extends Object
{
   public static void main(String[] args)
   {
      GuessingGame gg = new GuessingGame();
      boolean gameIsGoing = true;
      while (gameIsGoing)
      {
         gameIsGoing = gg.playGuessingGame();
      }
   }
}
