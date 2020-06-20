
/**
 * 
 * @author Vaibhav Jain - 20422729
 * @Class Foothill College CS1B Online
 * @Quarter Spring, 2020
 * @instructor Professor Harden
 * Name of File: Assignment 1 
 * Classes included: Card and Hand
 * Description: The Card class simulates a scenario of real life playing cards- Excluding joker
 * 				meanwhile the hand class portrays the hands of playing cards- meaning the cards a player holds
 *				In our main, we test for the functionality for each classes, making sure each card is valid
 *				and then only can it be added to the hand
 *
 */
import java.util.Scanner;

public class Assignment1 {
	public static void main(String[] args) {

//		// Phase 1 tests
//		// Initialising 3 cards for Phase 1 tests
//		Card test1, test2, test3;
//		test1 = new Card();
//		test2 = new Card('X', Card.Suit.clubs);
//		test3 = new Card('J', Card.Suit.clubs);
//		System.out.println(test1.toString());
//		System.out.println(test2.toString());
//		System.out.println(test3.toString());
//		test2.set('J', Card.Suit.clubs);
//		test3.set('X', Card.Suit.clubs);
//		System.out.println(test2.toString());
//		System.out.println(test3.toString());
//		System.out.println(test2.equal(test3));
//		// Phase 1 test ends
//
//		// Phase 2 Test Starts
//		// Initializing 5 cards for phase 2 tests
//		Card card1, card2, card3, card4, card5;
//		card1 = new Card();
//		card2 = new Card('A', Card.Suit.clubs);
//		card3 = new Card('T', Card.Suit.diamonds);
//		card4 = new Card('K', Card.Suit.hearts);
//		card5 = new Card('Q', Card.Suit.spades);
//		// Initializing a Hand for phase 2 tests
//		Hand hand = new Hand();
//		while (hand.getNumCards() < hand.maxCards) {
//			hand.takeCard(card1);
//			hand.takeCard(card2);
//			hand.takeCard(card3);
//			hand.takeCard(card4);
//			hand.takeCard(card5);
//		}
//		System.out.println(hand.toString());
//		while (hand.getNumCards() != 0) {
//			System.out.println(hand.playCard());
//		}
//		System.out.println(hand.toString());
//		card5.set('X', Card.Suit.clubs);
//		card4.set('J', Card.Suit.clubs);
//		hand.takeCard(card4);
//		System.out.println(hand.inspectCard(0));
//
//		// Phase 2 tests end
		// Phase 1
		Deck black = new Deck();
		Scanner keyboard = new Scanner(System.in);
		for (int i = black.getNumCards() - 1; i >= 0; i--) {
			System.out.print(black.getTopCard().toString() + " / ");
		}
		System.out.println();

		black.restock(1);
		black.reorder();
		for (int i = black.getNumCards() - 1; i >= 0; i--) {
			System.out.print(black.getTopCard().toString() + " / ");
		}
		System.out.println();
		black.restock(3);
		for (int i = black.getNumCards() - 1; i >= 0; i--) {
			System.out.print(black.getTopCard().toString() + " / ");
		}
		System.out.println();
		black.restock(3);
		black.reorder();

		for (int i = black.getNumCards() - 1; i >= 0; i--) {
			System.out.print(black.getTopCard().toString() + " / ");
		}
		System.out.println();
		System.out.print("How many hands? (1 - 12, please):");

//Phase 2
		boolean validInput = false;
		int numHands = 0;
//Checks for valid user input
		while (!validInput) {
			System.out.print("Choose a number of players (1-12) : ");
			while (!keyboard.hasNextInt()) {
				System.out.print("Please enter a a valid integer: ");
				keyboard.nextLine();
			}

			numHands = keyboard.nextInt();

			if (numHands < 1 || numHands > 12)
				continue;
			else
				validInput = true;
		}
		Deck hand2 = new Deck();
		int size = hand2.getNumCards();
// Sets up new Hands
		Hand[] hands = new Hand[numHands];
		for (int i = 0; i < numHands; i++) {
			hands[i] = new Hand();
		}
// Gives hands cards
		for (int i = 0; i < size; i++) {
			hands[i % numHands].takeCard(hand2.getTopCard());
		}
// returns cards in hand
		for (int k = 0; k < numHands; k++)
			System.out.println("\n Hand " + (k + 1) + " \n" + hands[k].toString());
		for (int i = 0; i < numHands; i++) {
			hands[i].resetHand();
		}
		hand2.restock(1);
		hand2.reorder();

//Dealing deck to players
		for (int i = 0; i < size; i++)
			hands[i % numHands].takeCard(hand2.getTopCard());
		for (int k = 0; k < numHands; k++)
			System.out.println("\n Hand " + (k + 1) + " \n" + hands[k].toString());
//with 3 packs
		hand2.restock(1);
		for (int i = 0; i < size; i++) {
			hands[i % numHands].takeCard(hand2.getTopCard());
		}
// returns cards in hand
		for (int k = 0; k < numHands; k++)
			System.out.println("\n Hand " + (k + 1) + " \n" + hands[k].toString());
		for (int i = 0; i < numHands; i++) {
			hands[i].resetHand();
		}
		hand2.restock(3);
		hand2.reorder();

//Dealing deck to players
		for (int i = 0; i < size; i++)
			hands[i % numHands].takeCard(hand2.getTopCard());
		for (int k = 0; k < numHands; k++)
			System.out.println("\n Hand " + (k + 1) + " \n" + hands[k].toString());
	}

}