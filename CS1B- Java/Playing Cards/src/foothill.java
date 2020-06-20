

// Name: Vaibhav Jain - 20422729
// Quarter: Spring 2020,Foothill College
// Professor David Harden
// Due Date: May 25, 2020
// Files: foothill.java
// Description" Created 4 classes: Node, Queue,CardNode, and Card Queue. 
// CardQueue extends Queue.
// This program creates a queue of Cards, which when empty, instead of throwing an exception, it would be caught and gives us our own value.

public class foothill {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardQueue cardQueue1 = new CardQueue();
		Card card1 = new Card('A', Card.Suit.spades);
		Card card2 = new Card('2', Card.Suit.hearts);
		Card card3 = new Card('J', Card.Suit.diamonds);
		Card card4 = new Card('T', Card.Suit.clubs);
		Card theCard;

		cardQueue1.addCard(card1);
		cardQueue1.addCard(card2);
		cardQueue1.addCard(card3);
		cardQueue1.addCard(card4);

		// toString queue
		System.out.println("Queue1: " + cardQueue1.toString());
		System.out.println();

		try {
			while ((theCard = cardQueue1.removeCard()) != null)
				System.out.println("removing..." + theCard.toString());
		} catch (QueueEmptyException e) {
			System.out.println("Queue empty");
		}
	}

}

class Node {
	// data (protected allows only certain other classes to access "next" directly)
	protected Node next;

	// constructor
	public Node() {
		next = null;
	}

	
	
	
	
	
	// console display
	public String toString() {
		String myStr = "(generic node) ";
		return myStr;
	}
}// end class Node







class Queue {
	// pointer to first node in stack
	private Node head, tail;

	// constructor
	public Queue() {
		head = null;
		tail = null;
	}

	
	
	
	
	// Adds a new node
	public void push_back(Node newNode) {
		if (newNode == null)
			return; // emergency return
		if (head == null) {
			head = tail = newNode;
		}
		tail.next = newNode;
		tail = newNode;
	}

	
	
	
	
	// Removes the card node
	public Node remove_card() {
		Node temp;

		temp = head;
		if (head == null)
			temp = null; // return null if queue is empty
		else {
			head = head.next;
			temp.next = null; // don't give client access to stack!
		}
		return temp;
	}

	
	
	
	
	
	// console display
	public String toString() {
		Node p;
		String myStr = "";

		// Display all the nodes in the stack
		for (p = head; p != null; p = p.next)
			myStr += p.toString();

		return myStr;
	}
}// end base class Queue


// Throws QueueEmptyException 
class QueueEmptyException extends Exception {
}






//derived class CardNode------------------------------------------------------
class CardNode extends Node {
	// additional data for subclass
	private Card theCard;

	
	
	
	
	
	// constructor for default Card Node
	public CardNode(Card card) {
		// super(); // constructor call to base class
		theCard = card;
	}

	// accessor for Card
	public Card getCard() {
		return theCard;
	}

	
	
	
	
	
	
	// overriding toString()
	public String toString() {
		String myStr = theCard.toString() + "/";
		return myStr;
	}
}// end CardNode






//derived class CardQueue----------------------------------------------------
class CardQueue extends Queue {
	public void addCard(Card theCard) {
		// create a new CardNode
		CardNode cn = new CardNode(theCard);

		// add the Node onto the queue (base class call)
		super.push_back(cn);
	}

	
	
	
	
	// Removes Card from Queue
	public Card removeCard() throws QueueEmptyException {
		// remove a node
		CardNode cn = (CardNode) remove_card();
		if (cn == null)
			throw new QueueEmptyException();
		else
			return cn.getCard();
	}
}// end class CardQueue

public class Card {
	// Beginning of Card Class and Phase 1
		// Public enum constant for the Suit of the Cards- Contains the 4 values of the
		// card
		public enum Suit {
			clubs, diamonds, hearts, spades
		};

	// Private values for the card
		private char value;
		private Suit suit;
		private boolean errorFlag;
	// Default Card Constructor
		Card() {
			value = 'A';
			suit = suit.spades;
			errorFlag = false;
		}
	
		
		
		
		
		
	// Over Loading constructor for Card
		Card(char value, Suit suit) {
			// sets the Given card, and if an invalid card, would return Illegal
			set(value, suit);
		}

		
		
		
		
		
	// Accessor for suit
		public Suit getSuit() {
			return suit;
		}
		
		
		
		
		
		
	// Accessor for value
		public char getValue() {
			return value;
		}
		
		
		
		
		
		
	// Accessor for errorFlag
		public boolean getErrorFlag() {
			return errorFlag;
		}		
		
		
		
		
		
		
	// Checks for the Validity of the Card
		private boolean isValid(char value, Suit suit) {
			value = Character.toUpperCase(value);
			// A = Ace, J = Jack, Q= Queen, K = King, and T = Ten- Checks for the special cases such as face cards
			if (value != 'A' && value != 'J' && value != 'Q' && value != 'K' && value != 'T'
					&& (value > '9' || value < '2'))
				return false;
			return true;
		}
		
		
		
		
		

	// Checks if 2 cards are equal
		public boolean equal(Card card) {
			boolean equal = true;
			if (card.getValue() == this.value && card.getSuit() == this.suit) {
				equal = true;
			} else
				equal = false;
			return equal;
		}
		
		
		
		
		

	// Updates the value of a pre-existing card, must be valid
		public boolean set(char value, Suit suit) {
			if (isValid(value, suit)) {
				errorFlag = false;
				this.value = value;
				this.suit = suit;
			} else {
				errorFlag = true;
			}
			return !errorFlag;
		}
		
		
		
		
		
		
	// Returns the type of Card
		public String toString() {
			String str = "";
			if (errorFlag) {
				return "[Invalid]";
			}
			str = value + " of ";
			switch (suit) {
			case spades:
				str += "Spades";
				break;
			case clubs:
				str += "Clubs";
				break;
			case hearts:
				str += "Hearts";
				break;
			case diamonds:
				str += "Diamonds";
				break;
			}
			return str;
		}
}

// Sample Output
//Queue1: A of Spades/2 of Hearts/J of Diamonds/T of Clubs/
//
//removing...A of Spades
//removing...2 of Hearts
//removing...J of Diamonds
//removing...T of Clubs
//Queue empty
