//Name: Vaibhav Jain - 20422729
// Spring 2020 Quarter, CS1B, Foothill College
// A5_GUI_Phase 2 and A5_GUI_Phase1 File
// Assignment 5
// Description: Displays Cards: Contains 2 Files for  Different Main and has the Card class in a Seperate File
// 

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

//MAIN FOR PHASE 2 MAIN FOR PHASE 2 // MAIN FOR PHASE 2 // MAIN FOR PHASE 2
public class A5_GUI_Phase2
{
    
    static int NUM_CARDS_PER_HAND = 7;
    static int  NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
    static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
    static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
    
    public static void main(String[] args)
    {
       int k;
       Icon tempIcon;
       GUICard.loadIcons();
       // establish main frame in which program will run
       CardTable myCardTable 
          = new CardTable("CS 1B CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
       myCardTable.setSize(800, 600);
       myCardTable.setLocationRelativeTo(null);
       myCardTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


       // CREATE LABELS ----------------------------------------------------
       for (k = 0; k < NUM_CARDS_PER_HAND; k++)
       {
           tempIcon = GUICard.getIcon(generateRandomCard());
           humanLabels[k] = new JLabel(tempIcon);
           computerLabels[k] = new JLabel(GUICard.getBackCardIcon());
           myCardTable.computerHand.add(computerLabels[k]);
           myCardTable.playerHand.add(humanLabels[k]);
       }
        
       for (k = 0; k < NUM_PLAYERS; k++)
       {
           tempIcon = GUICard.getIcon(generateRandomCard());
           playedCardLabels[k] = new JLabel(tempIcon);
           if (k > 0)
               playLabelText[k] = new JLabel("You", JLabel.CENTER);
           else
               playLabelText[k] = new JLabel("Player " + k + 1, JLabel.CENTER);
               
       }
       // ADD LABELS TO PANELS -----------------------------------------
       for (k = 0; k < NUM_CARDS_PER_HAND; k++)
       {
           myCardTable.computerHand.add(computerLabels[k]);
           myCardTable.playerHand.add(humanLabels[k]);

       }
       // and two random cards in the play region (simulating a computer/hum ply)
     //  code goes here ...
       for (k = 0 ; k < NUM_PLAYERS*2; k++)
       {
           if (k < NUM_PLAYERS)
               myCardTable.playing.add(playedCardLabels[k]);
           else
               myCardTable.playing.add(playLabelText[k - NUM_PLAYERS]);
       }
       // show everything to the user
       myCardTable.setVisible(true);
    }
    

    
    
    
    
    //generateRandomCard uses the random() function from the math library to
    //generate an int modulo 14 (cardVal) and another int modulo 4 (suitVal).
    //cardVal is then converted to a card value character ranging from 2-Joker
    //and suitVal is converted to a suit value using the enum type
    //Suit. Each of these tasks is accomplished using public static methods
    //of the GUICard class.
    public static Card generateRandomCard()
    {
        Card temp = new Card();
        int suitVal, cardVal;
        cardVal = (int) (Math.random()*1000 % 14);
        suitVal =  (int) (Math.random()*1000 % 4);
        temp.set(GUICard.turnIntIntoCardValueChar(cardVal), 
                 GUICard.turnIntIntoCardSuit(suitVal));
        
        return temp;
    }
    

}

class CardTable extends JFrame
{
    static int MAX_CARDS_PER_HAND = 57;
    static int MAX_PLAYERS = 2;
    static int DEFAULT_CARDS_PER_HAND = 5;
    static int DEFAULT_PLAYERS = 2;
    
    private int numCardsPerHand;
    private int numPlayers;
    
    public JPanel playerHand, computerHand, playing;
    
    //--method definitions---
    
    //--public instance methods--
    
    //Overloaded constructor for CardTable class. Takes a String, title, for
    //the title of the Jframe object, and two ints: numCardsPerHand and
    //numPlayers. Passes title into super with no filtering, then
    //checks for valid values of numCardsPerHand and numPlayers. If any of
    //these are incorrect, then the function sets the private member fields
    //with corresponding names to default values. Otherwise, they are set
    //to the passed in parameter. Adds 3 JPanels to the JFrame, each 
    //corresponding to a GUI aspect of the cardgame.
    public CardTable(String title, int numCardsPerHand, int numPlayers)
    {
        super(title);
        
        if (numCardsPerHand < 0 || numCardsPerHand > MAX_CARDS_PER_HAND)
            this.numCardsPerHand = DEFAULT_CARDS_PER_HAND;
        else
            this.numCardsPerHand = numCardsPerHand;
        
        if (numPlayers <= 0 || numPlayers > MAX_PLAYERS)
            this.numPlayers = DEFAULT_PLAYERS;
        else
            this.numPlayers = numPlayers;
        
        playerHand = new JPanel(new GridLayout(1,1,10,10));
        computerHand = new JPanel(new GridLayout(1,1,10,10));
        playing = new JPanel (new GridLayout (2,2,20,20));
        
        setLayout (new BorderLayout(10,10));
        add(computerHand,BorderLayout.NORTH);
        add(playing,BorderLayout.CENTER);
        add(playerHand,BorderLayout.SOUTH);
        
        computerHand.setBorder(new TitledBorder("Computer Hand"));
        playing.setBorder(new TitledBorder("Playing"));
        playerHand.setBorder(new TitledBorder("Player Hand"));
    }
    
    
    
    
    
    
    
    
    
    //Accessor for numPlayers
    public int getNumPlayers()
    {
        return numPlayers;
    }
    
    
    
    
    
    
    
    
    
    //Accessor for number of Cards in Hand
    public int getNumCardsPerHand()
    {
        return numCardsPerHand;
    }
}

class GUICard
{
    public static final int NUM_SUITS = 4;
    public static final int MAX_CARDVAL = 14;
    // 14 = A thru K (+ joker optional)
    private static Icon[][] iconCards = new ImageIcon[MAX_CARDVAL][NUM_SUITS]; 
    private static Icon iconBack;
    private static boolean iconsLoaded = false;
    
    private static String cardlValsConvertAssist = "23456789TJQKAX";
    private static String suitValsConvertAssist  = "CDHS";
    private static Card.Suit suitConvertAssist[] =
    {
       Card.Suit.clubs,
       Card.Suit.diamonds,
       Card.Suit.hearts,
       Card.Suit.spades
    };
    
    //---static methods
    
  // Technically the same method as provided in the assignment
    public static void loadIcons()
    {
        if (iconsLoaded)
            return;
        
        String imageFileName;
        int intSuit, intVal;

        for (intSuit = 0; intSuit < NUM_SUITS; intSuit++) {
           for (intVal = 0; intVal < MAX_CARDVAL; intVal++ )
           {
              imageFileName = "images/"
                    + turnIntIntoCardValueChar(intVal) 
                    + turnIntIntoCardSuitChar(intSuit)
                    + ".gif";
              iconCards[intVal][intSuit] = new ImageIcon(imageFileName);
           }
        imageFileName = "images/BK.gif";
        iconCards[MAX_CARDVAL-1][NUM_SUITS-1] = new ImageIcon(imageFileName);
        }
        iconBack = iconCards[MAX_CARDVAL-1][NUM_SUITS-1];
        iconsLoaded = true;
        
    }
    
    // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
    public static char turnIntIntoCardValueChar(int k)
    {
    
       if ( k < 0 || k > 13)
          return '?'; 
       return cardlValsConvertAssist.charAt(k);
    }
    
    
    
    
    
    
    
    
 // turns 0 - 3 into 'C', 'D', 'H', 'S'
    public static char turnIntIntoCardSuitChar(int k)
    {
       if ( k < 0 || k > 3)
          return '?'; 
       return suitValsConvertAssist.charAt(k);
    }
    
    
    
    // Takes an int, k, then uses suitConvertAssist[] as a reference
    //and returns the suit of the array corresponding to the index k
    //passed in.
    public static Card.Suit turnIntIntoCardSuit(int k)
    {
        if (k < 0 || k > 3)
            return Card.Suit.clubs;
        return suitConvertAssist[k];
    }
    
    
    
    //getIcon returns the Icon of the card in iconCards[][] corresponding to
    //the value of the Card card passed in. Uses two helper functions to
    //decode the card parameter into its constituents so that getIcon
    //can access the correct index of the array.
    public static Icon getIcon(Card card)
    {
       loadIcons(); // will not load twice, so no worries.
       return iconCards[valueAsInt(card)][suitAsInt(card)];
    }
    
    
    
    
    // Converts card value into Integer
    public static int valueAsInt(Card card)
    {
        return cardlValsConvertAssist.indexOf(card.getValue());
    }
    
    
    
    
    
    // Turns suit card into Integer
    public static int suitAsInt(Card card)
    {
        for (int i = 0; i < NUM_SUITS; i++) 
        {
            if (card.getSuit() == suitConvertAssist[i])
                return i;
        }
        
        return 0;
    }
    
    
    
    
    
    
    //returns Icon
    public static Icon getBackCardIcon()
    {
        return iconBack;
    }
    
}