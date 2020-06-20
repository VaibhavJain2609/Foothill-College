import java.util.Scanner;

public class Messenger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Tests for message Class
		Message msg1, msg2;
		msg1 = new Message();
		msg2 = new Message("abc");
		System.out.println(msg1.toString());
		System.out.println(msg2.toString());
		msg2 = new Message("hello world");
		System.out.println(msg1.toString());
		System.out.println(msg2.toString());
		System.out.println("Testing Mutator");
		msg2.setMessage("abc");
		System.out.println(msg2.toString());
		System.out.println("Testing Mutator");
		msg2.setMessage("abcdefghijklmnopqrstuvwxyz");
		System.out.println(msg2.toString());
		msg2.setMessage("bbc");
		System.out.println(msg2.getMessage());
		// Tests for email class
		System.out.println("Email Class tests");
		Email mail1, mail2;
		mail1 = new Email();
		mail2 = new Email("abc", "jain@protonmail.com", "vjj@protonmail.com");
		System.out.println(mail1.toString());
		System.out.println(mail2.toString());
		mail2 = new Email("abcc", "sda@es.x", "ewas@fds.cs");
		System.out.println(mail1.toString());
		System.out.println(mail2.toString());
		mail2.setFromAddress("abc@fdsa.csa");
		mail2.setToAddress("mail@dsa.csa");
		System.out.println(mail2.toString());
		mail2.setFromAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		mail2.setToAddress("aaaaaaaa");
		System.out.println(mail2.toString());
		System.out.println(mail2.getMessage());
		// End of Email Tests and beginning of Shweet Tests;
		
		  Shweet shweet1, shweet2;
	      shweet1 = new Shweet();
	      shweet2 = new Shweet("kisses", "hello");
	   
	      System.out.println("Class Shwee Test ");
	      
	      //Show initial messages using toString()
	      System.out.println(
	                         "Shweet 1: \n" + shweet1.toString() +
	                         "Shweet 2: \n" + shweet2.toString());
	      
	      //Mutate some members then display all w/ toString()
	      shweet1.setMessage("Guacamole, we've got a shweet!");
	      shweet1.setID("N0_SP4CES");
	      shweet1.setID("No spaces allowed");
	      shweet2.setMessage("New valid shweet");
	      
	      System.out.println("---Mutated Shweets.Showing w/ toString(): \n\n" +
	                         "Shweet1 : \n" + shweet1.toString()  +
	                         "Shweet2 : \n" + shweet2.toString());
	      
	      //Testing accessor
	      System.out.println(shweet1.getID() + " " + shweet1.getMessage() + " " + shweet2.getID());
	      
	      //Testing mutator
	      System.out.println("---Testing shweet mutator: \n");
	      if (shweet1.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
	         System.out.println(shweet1.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	      
	      if (shweet1.setID("yayayay__"))
	         System.out.println(shweet1.setID("yayayay__"));
	      else
	         System.out.println(shweet1.setID("yayayay__"));
	      
	      if (shweet2.setMessage("Should be a valid Shweet!! Yay"))
	         System.out.println(shweet2.setMessage("Should be a valid Shweet!! Yay"));
	      else
	         System.out.println(shweet2.setMessage("Should be a valid Shweet!! Yay"));
	      
	      if (shweet2.setID("&&invalidcharacters"))
	          System.out.println(shweet2.setID("&&invalidcharacters"));
	      else
	          System.out.println(shweet2.setID("&&invalidcharacters"));
	      
	 	}

}

class Message {
	private String message;
	public final static int MAX_MSG_LENGTH = 15;
	public final static int MIN_MSG_LENGTH = 1;
	public final static String DEFAULT_MSG = "'Invalid'";
	Scanner keyboard;

	
	// default constructor for Message Class
	Message() {
		message = DEFAULT_MSG;
	}
	
	
	
	
	
	
	// Overload Constructor for Message Class
	Message(String a) {
		if (!validMessage(a))
			message = new String(DEFAULT_MSG);
		else
			message = new String(a);

	}

	
	
	
	
	
	// Accessor for Message
	public String getMessage() {
		return message;
	}

	
	
	
	
	
	// Returns string
	public String toString() {

		String str = "";
		if (validMessage(message))
			str += message;

		return str;
	}

	
	
	
	
	
	
	//Sets the Message if Valid
	public boolean setMessage(String msg) {
		if (validMessage(msg)) {
			message = msg;
			return true;
		} else
			message = "notValid";
			return false;
	}

	
	
	
	
	
	// Checks for Message Validity
	private static boolean validMessage(String str) {
		boolean valid = true;
		if (str.length() > MAX_MSG_LENGTH || str.length() < MIN_MSG_LENGTH) {
			return !valid;
		} else
			return valid;

	}
}
// Email Class- Accesses Message attributes
class Email extends Message {
	private String fromAddress;
	private String toAddress;
	public final static int MAX_EMAIL_LENGTH = 64;
	public final static int MIN_EMAIL_LENGTH = 6;
	public static final String DEFAULT_EMAIL_ADDRESS = "(undefined)@(N/A).com";

	
	
	// Default email Constructor
	Email() {
		fromAddress = DEFAULT_EMAIL_ADDRESS;
		toAddress = DEFAULT_EMAIL_ADDRESS;

	}

	
	
	
	
	
	// Overloaded Email Constructor with Parameters: message, fromAddress and toAddress
	public Email(String message, String fromAddress, String toAddress) {
		super(message);
		if (!setFromAddress(fromAddress))
			this.fromAddress = DEFAULT_EMAIL_ADDRESS;
		if (!setToAddress(toAddress))
			this.toAddress = DEFAULT_EMAIL_ADDRESS;
	}

	
	
	
	
	
	// Accessor for fromAddress
	public String getFromAddress() {
		return fromAddress;
	}

	
	
	
	
	
	//Accessor for toAddress
	public String getToAddress() {
		return toAddress;
	}

	
	
	
	
    //base class toString()
    public String toString()
    {
        String retString = super.toString();
        retString += "\nFrom: " + fromAddress + "\n" + "To: " +toAddress +"\n\n";
        
        return retString;
    }
    

    
    
    
    
	//Sets toAddress if Valid
	public boolean setToAddress(String mail) {
		if (!isValidEmailAddress(mail)) {
			toAddress = "fail";
			return false;
		} else {
			toAddress = mail;
			return true;
		}
	}

	
	
	
	
	
	// sets fromAddress if Valid
	public boolean setFromAddress(String mail) {
		if (!isValidEmailAddress(mail)) {
			fromAddress = "fail";
			return false;
		} else {
			fromAddress = mail;
			return true;
		}
	}

	
	
	
	
	
	// Checks if an email address is valid: requires to have '.' and '@'
	private static boolean isValidEmailAddress(String mail) {
		if (!mail.contains("@") || mail.length() < MIN_EMAIL_LENGTH || mail.length() > MAX_EMAIL_LENGTH
				|| !mail.contains(".")) {
			return false;
		}
		return true;

	}
}


// Shweet class that Accesses Message attributes
class Shweet extends Message {
	public final static int MAX_SHWITTER_ID_LENGTH = 15;
	public final static int MAX_SHWEET_LENGTH = 140;
	public final static int MIN_SHWEET_LENGTH = 1;
	public final static String DEFAULT_USER_ID = "'Invalid";
	private String fromId;

	
	// Shweet Default constructor
	Shweet() {
		fromId = DEFAULT_USER_ID;
	}

	
	
	
	
	
	//Shweet overloaded constructor
	Shweet(String shweetID, String shweet) {
		super(shweet);
		setMessage(shweet);

		if (!setID(shweetID)) {
			fromId = DEFAULT_USER_ID;
		} else
			fromId = shweetID;
	}

	
	
	
	
	
	// Sets message in shweet
	public boolean setMessage(String shweetTest) {
		if (!super.setMessage(shweetTest))
			return false;
		if (!isValidShweet(shweetTest))
			return false;

		super.setMessage(shweetTest);
		return true;
	}

	
	
	
	
	
	// Checks for a valid Shweet
	private static boolean isValidShweet(String shweet) {
		if (shweet.length() > MAX_SHWEET_LENGTH || shweet.length() < MIN_SHWEET_LENGTH) {
			return false;
		} else
			return true;
	}

	
	
	
	
	
	// Returns id
	public String getID() {
		return fromId;
	}

	
	
	
	
	
	// Sets id if Valid
	public boolean setID(String id) {
		if (!isValidShweeterID(id)) {
			return false;
		} else {
			fromId = id;
			return true;
		}
	}

	
	
	
	
	
	// Checks for Valid shweet iD
	private static boolean isValidShweeterID(String shweetID) {
		if (shweetID.length() > MAX_SHWITTER_ID_LENGTH) {
			return false;
		}
		return stringHasOnlyAlphaOrNumOrUnderscore(shweetID);
	}

	
	
	
	
	
	//  Checks shweetID for special Character Validation
	private static boolean stringHasOnlyAlphaOrNumOrUnderscore(String testString) {
		for (int i = 0; i < testString.length(); i++) {
			if (!Character.isLetterOrDigit(testString.charAt(i)) && testString.charAt(i) != '_')
				return false;
		}
		return true;
	}
	
	
	
	
	
	  public String toString()
	    {
	        String retString = "Shweet: @" + fromId + "\n";
	        retString += super.toString() + "\n";
	        
	        return retString;
	    }
}
