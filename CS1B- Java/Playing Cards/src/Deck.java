import java.util.Random;
public class Deck {
private static final int  NUM_CARDS_PER_PACK = 52;
private static final int MAX_PACKS = 6;
private static final int MAX_CARDS_PER_DECK = NUM_CARDS_PER_PACK * MAX_PACKS;
static Card[] staticPack;
private  Card[] cards;
private int numCards;
private int numPacks;
private static boolean initialStaticPack = false;
Card invalid = new Card('X', Card.Suit.clubs);

public int getNumCards() {
	return numCards;
}
public Deck() {
	numPacks = 1;
	if(!initialStaticPack)
		allocateStaticPack();
	restock(numPacks);
}
public Deck(int numPacks) {
	this.numPacks = numPacks;
	if(!initialStaticPack)
	allocateStaticPack();
	restock(numPacks);
}

private static void allocateStaticPack() {
	 char[] val = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};

	Card.Suit[] suits = {Card.Suit.clubs, Card.Suit.diamonds, Card.Suit.hearts, Card.Suit.spades};
	int index = 0;
	staticPack = new Card[NUM_CARDS_PER_PACK];
	for(int i = 0; i<suits.length; i++) {
		for (int j = 0; j<val.length; j++) {
			staticPack[index] = new Card(val[j], suits[i]);
			index++;
		}
		
	}
	initialStaticPack = true;
	}
public boolean restock(int numPacks) {
	numCards = numPacks * NUM_CARDS_PER_PACK;
	cards = new Card[numCards];
	boolean valid = true;
	if(numPacks > MAX_PACKS || numPacks <1) valid = false;
	for(int i = 0; i < numPacks; i++)
    {
        for(int k = 0; k < NUM_CARDS_PER_PACK; k++)
        {
            cards[k + (52*i)] = staticPack[k];
            
        }
}
	return valid;
}
public Card getCard(int k) {
	return (k>cards.length || k<0 || k>= MAX_CARDS_PER_DECK) ? invalid : cards[k];
	
}
public void reorder() {
	Random random = new Random();
	// Implemented the Fischer-Yates Algorithm to Swap Cards
	for(int i = cards.length-1; i >0; i--) {
		int j = random.nextInt(cards.length-1);
		Card temp = cards[j];
		cards[j] = cards[i];
		cards[i] = temp;
	}
}
public Card getTopCard() {
	if (numCards <= 0)
		return invalid;

	Card temp = cards[numCards - 1];
	numCards--;

	return temp;
}
}
