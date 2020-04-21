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
