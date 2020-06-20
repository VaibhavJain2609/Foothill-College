/**
 * @author Vaibhav Jain 20422729
 * File Name: Assignment 3
 * Professor: David Harden
 * Date: May 4, 2020
 * Foothill College Spring Quarter 2020
 * Description: Users are given a chance to enter the initial seed function for the input.
 * It consists of 2 classes: main and Automaton. Through the seed, we get to notice the 
 * growth of the seed function. 
 * 
 * https://github.com/jmscsedu/csedu/blob/master/labs/apcsa/03_cellular_automata/lab_cellular_automata.pdf // A lab I did in highschool for CS class
 */

import java.util.Scanner;

public class Assignment3
{
	public static void main(String[] args) {
		//Text Case
//		Scanner keyboard = new Scanner(System.in);
//		String userInput;
//		int rule;
//		do {
//			System.out.print("Enter Rule (0 - 255): ");
//			userInput = keyboard.nextLine();
//			rule = Integer.parseInt(userInput);
//		} while (rule < 0 || rule > 255);
//		Automaton aut = new Automaton(rule);
//		for (int k = 0; k < aut.MAX_DISPLAY_WIDTH; k++) {
//			System.out.println(aut.toStringCurrentGen());
//			aut.propagateNewGeneration();
//		}
//		keyboard.close();
//		
		Scanner keyboard = new Scanner(System.in);
		int rule=0;
		boolean valid = false;
		while(!valid) {
			while(!keyboard.hasNextInt()) {
				String skipped = keyboard.nextLine();
				System.out.println("Enter Rule (0 - 255): ");
			}
			rule = keyboard.nextInt();
			if(rule <0 || rule >255) {
				continue;
				
			}
			else {
				valid = true;
			}
		}
		Automaton aut = new Automaton(rule);
		for (int k = 0; k < aut.MAX_DISPLAY_WIDTH; k++) {
			System.out.println(aut.toStringCurrentGen());
			aut.propagateNewGeneration();
		}
		keyboard.close();	
	
	}
	
}

class Automaton{
   // class constants
   public final static int MAX_DISPLAY_WIDTH = 121;
   
   // private members
   private boolean rules[];  // allocate rules[8] in constructor!
   private String thisGen;   // same here
   String extremeBit; // bit, "*" or " ", implied everywhere "outside"
   int displayWidth;  // an odd number so it can be perfectly centered
   
   // public constructors, mutators, etc. (need to be written)
   public Automaton(int new_rule) {
	  thisGen = "*";
	  extremeBit = " ";
	  setDisplayWidth(79);
		resetFirstGen();
		setRule(new_rule);
   }
   
   
   
   
   
   // Resets the generation
   public void resetFirstGen() {
	   thisGen = "*";
		extremeBit = " ";
   }
   
   
   
   
   
   // Sets the rules
	   public boolean setRule(int new_rule) {
		   if (new_rule < 0 || new_rule > 255)
				return false;
	
			rules = new boolean[8];
			for (int i = 0; i < 8; i++) {
				rules[i] = (new_rule % 2 != 0);
				new_rule /= 2;
			}
	   return true;
	   }
//	for(int i = 0; i<rules.length; i++) {
//		if(Math.pow(2, 7-i)>new_rule) {
//			rules[i] = true;
//			new_rule -= Math.pow(2, 7-i);
//		}
//		else {
//			rules[i] = false;
//		}
//	}
//			
//			return true;
//	   }
//   
   
   
   
   
   //Sets the display width
   public boolean setDisplayWidth(int width) {
	   boolean display = !(width <0 || width > MAX_DISPLAY_WIDTH || width %2==0);
	   displayWidth = width;
	   return display;
   }
   
   
   
   
   
   //Prints out the current generation
   public String toStringCurrentGen() {
	   String str = new String(thisGen);
		int distance = 0;
		int end = 0;
		while (str.length() != displayWidth) {

			if (str.length() < displayWidth) {
				str += extremeBit;
				str = extremeBit + str;
			}

			if (str.length() > displayWidth) {
				distance = (str.length() - displayWidth) / 2;
				end = str.length() - distance;
				str = str.substring(distance, end);
			}
		}
		return str;
   }
   
   
   
   
   
   //Creates the next generation
   public void propagateNewGeneration() {
	   String nextGen = "";
		thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;

		for (int i = 1; i < thisGen.length() - 1; i++) {
			String num = "";
			for (int j = -1; j < 2; j++) {
				if (thisGen.charAt(j + i) == '*')
					num += 1;
				else
					num += 0;
			}
			int k = Integer.parseInt(num, 2);
			if (rules[k])
				nextGen += "*";
			else
				nextGen += " ";
		}

		if (rules[0])
			extremeBit = "*";

		thisGen = nextGen;
   }
   
   
   
   
   
   
}