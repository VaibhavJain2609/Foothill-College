// You may use the nested comments below as hints only. Remember, there is no one right or wrong way 
// to 'solve' this Assignment, so feel free to delete the comments if you find them distracting.

import java.util.Random;
import java.util.Scanner;

/**
 * CS1A, Assignment 4, "Guessing Game" <br>
 * Quarter: Winter, 2020 <br>
 * The code below is to allow users to play a guessing game with numbers <br>
 * It doesn't take in any other type of data other than integers You would get
 * hints from what your number is depending on your guess. If it is larger or
 * smaller would be told to you. <br>
 * 
 * @author Vaibhav Jain
 * @author Jingtong 'Constance' Huang
 */
public class GuessingGame extends Object
{

   private static final int MAX_POSSIBLE_GUESS = 50; // This is a constant for the max Value
   private static final int MIN_POSSIBLE_GUESS = -50; // For the lowest Value
   int guessesMade = 1; // Keeps track of the guesses made
   boolean play = true; // Helps with repeating the amount of guesses made
   public static final int MAX_GUESSES = 7;
   static int[] guesses = new int[MAX_GUESSES]; // Guesses List

   // Prints out the Instructions at the beginning of Each Game
   public void instructions()
   {
      System.out.println("Welcome");
      System.out.println("Rules");
      System.out.println("1) You have 7 guesses");
      System.out.println("2) If you guess the wrong number, a hint will be displayed");
      System.out.println("3) Your guess has to be between " + MIN_POSSIBLE_GUESS + " and " + MAX_POSSIBLE_GUESS);
   }

   // Generates Random Numbers
   public int genRandomNumber()
   {
      Random randomnum = new Random();
      int rand = randomnum.nextInt(MAX_POSSIBLE_GUESS - MIN_POSSIBLE_GUESS) + MIN_POSSIBLE_GUESS;
      return rand;

   }

   // Main method which allows us to play the game
   public boolean playGuessingGame()
   {
      instructions();
      int rand = genRandomNumber();
      for (int i = 0; i < guesses.length;)
      {

         System.out.print("Enter a whole number between -50 and 50: ");
         int numberGuessed = getInput();
         // Checks if the value is in Range and if it is not guessed already
         // If neither is true, the array stays in an infinite loop until set to true by
         // invoking the conditions
         if (valRange(numberGuessed) && !isNumberGuessed(numberGuessed))
         {
            System.out.println("You Guessed: " + numberGuessed);
            if (numberGuessed != rand)
            {
               guesses[i] = numberGuessed;

            }
            // Hint for if the guess was smaller or larger
            if (numberGuessed < rand)
            {
               System.out.println("Hint: The correct number is LARGER than you guessed");
               printArray(guesses);
               System.out.println("You have " + (MAX_GUESSES - guessesMade) + " guesses left.");
               guessesMade++;
               i++;
            } else if (numberGuessed > rand)
            {
               System.out.println("Hint: The correct number is SMALLER than you guessed.");
               printArray(guesses);
               System.out.println("You have " + (MAX_GUESSES - guessesMade) + " guesses left.");
               guessesMade++;
               i++;
            }
            // Tells if we have made enough guesses
            else if (guessesMade == 7)
            {
               System.out.println("You lost");
               replay();
            }
            // Won/ Asks if we want to replay the game
            else
            {
               System.out.println("You won!");
               replay();
               break;

            }
         }
      }

      return replay();
   }

   // Checks if the value is in range
   public boolean valRange(int val)
   {
      if (val < MIN_POSSIBLE_GUESS || val > MAX_POSSIBLE_GUESS)
      {
         return false;
      } else
         return true;
   }

   // Prints out the previous guesses
   public void printArray(int[] arr)
   {
      System.out.print("Previous Guess(es)");
      for (int i = 0; i < guessesMade; i++)
      {

         System.out.print(arr[i] + ", ");
      }
      System.out.println();
   }

   // The method to replay the game
   public boolean replay()
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Please enter 1 to play agin and  0 to exit");
      int setup = getInput();
      if (keyboard.hasNextInt())
      {
         int a = keyboard.nextInt();
         if (a == 1)
         {
            guessesMade = 1;
            for (int i = 0; i < guesses.length; i++)
            {
               guesses[i] = 0;
            }
            play = true;
            playGuessingGame();
         } else if (a == 0)
         {
            play = false;
            System.exit(0);
         } else
         {
            System.out.println("Please enter a valid Input");
            replay();
         }
      }
      return play;
   }

   // Checks if the number has been already guessed
   public boolean isNumberGuessed(int number)
   {
      for (int i = 0; i < guessesMade - 1; i++)
      {
         if (number == guesses[i])
         {
            System.out.println("You already tried this number");
            return true;
         }
      }
      return false;
   }

   // The method to check the input within the game
   public int getInput()
   {
      Scanner keyboard = new Scanner(System.in);
      while (!keyboard.hasNextInt())
      {
         String input = keyboard.nextLine();
         System.out.println("Enter a valid integer");
      }
      int val = keyboard.nextInt();
      return val;
   }
}