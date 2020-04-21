
public class Hand {
	// Cards that the player holds currently in their hand
	private Card[] myCards;
	// Number of cards the player holds
	private int numCards;
	// Max amount of cards a player can hold
	int maxCards = 10;
	// Default constructor for hand class= Initializes everything as an empty class
	Hand() {
		myCards = new Card[maxCards];
		numCards = 0;
		for (int i = 0; i < maxCards; i++)
			myCards[i] = new Card();
	}
// Accessor for numCards in Hand
	public int getNumCards() {
		return numCards;
	}

// Resets number of cards in players hand
	public void resetHand() {
		numCards = 0;
	}

// Returns the Cards currently in player Hands
	public String toString() {
		String str = "";
		for (int i = 0; i < numCards; i++) {
			str += myCards[i].toString() + "\n";
		}

		return str;
	}

// Takes in a new card into the hand; Unable to take in more if the number of cards in hand is already max
	public boolean takeCard(Card card) {
		boolean available = false;
		if (numCards >= maxCards) {
			return available;
		}
		if (!card.getErrorFlag()) {
			myCards[numCards] = card;
			numCards++;
			available = true;
		}

		return available;

	}

// Plays the latest card that had been added to your hand
	public Card playCard() {
		if (numCards <= 0)
			return new Card('F', Card.Suit.spades);

		Card temp = myCards[numCards - 1];
		numCards--;

		return temp;
	}

// Inspect the card the requested Index Value; If no card is present, it would return Invalid
	public Card inspectCard(int k) {
		if (k < 0 || (k > numCards - 1) || k >= maxCards)
			return new Card('C', Card.Suit.spades);
		return myCards[k];
	}

}
