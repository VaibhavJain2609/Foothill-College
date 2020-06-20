// Vaibhav Jain- 20422729
// Assignment 8
// Professor David Harden - CS1B Spring 2020
// June 8, 2020
// Description: Displays 0-9 and A-F using a 2d Display of arrays
public class foothill {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SevenSegmentImage ssi = new SevenSegmentImage();

	      System.out.println(
	         "Testing SevenSegmentImage ===================================");

	      ssi.setSize( 7, 9 );
	      ssi.turnOnCellsForSegment( 'a' );
	      ssi.display();
	      ssi.turnOnCellsForSegment( 'b' );
	      ssi.display();
	      ssi.turnOnCellsForSegment( 'c' );
	      ssi.display();
	      ssi.turnOnCellsForSegment( 'd' );
	      ssi.display();

	      ssi.clearImage();
	      ssi.turnOnCellsForSegment( 'e' );
	      ssi.display();
	      ssi.turnOnCellsForSegment( 'f' );
	      ssi.display();
	      ssi.turnOnCellsForSegment( 'g' );
	      ssi.display();

	      ssi.clearImage();
	      ssi.turnOnCellsForSegment( 'x' );
	      ssi.display();
	      ssi.turnOnCellsForSegment( '3' );
	      ssi.display();
	      
	      SevenSegmentDisplay  my7SegForCon = new SevenSegmentDisplay( 15, 13 );
	      int j;
	      
	      System.out.println(
	         "Testing SevenSegmentDisplay ===================================");

	      my7SegForCon.setSize( 7, 9 );
	      for ( j = 0; j < 16; j++ )
	      {
	         my7SegForCon.eval( j );
	         my7SegForCon.loadConsoleImage();
	         my7SegForCon.consoleDisplay();
	      }

	      for ( j = 5; j < 21; j += 4)
	      {
	         my7SegForCon.setSize( j, 2*j + 1 );
	         my7SegForCon.eval( 5 );
	         my7SegForCon.loadConsoleImage();
	         my7SegForCon.consoleDisplay();
	      }
	}

}

class SevenSegmentImage implements Cloneable {

	public static final int MIN_HEIGHT = 5;
	public static final int MIN_WIDTH = 5;
	public static final int MAX_HEIGHT = 65;
	public static final int MAX_WIDTH = 41;
	public static final int DEFAULT_HEIGHT = 32;

	public static final String DRAW_CHAR = "*";
	public static final String BLANK_CHAR = " ";

	private boolean[][] data;
	private int topRow, midRow, bottomRow, leftCol, rightCol;
	private int height1, width1;
	
	// Default Constuctor
	public SevenSegmentImage() {
		setSize(MIN_WIDTH, MIN_HEIGHT);
	}

	
	
	
	
	// OverLoaded Constructor
	public SevenSegmentImage(int width, int height) {
		// Always 0
		
		if (!validateSize(width, height)) {
			setSize(MIN_WIDTH, MIN_HEIGHT);
		}
		else setSize(width, height);

		clearImage();

	}

	
	
	
	
	// Clears the boolean Array to false
	public void clearImage() {
		
		for (boolean[] innerArray : this.data) {
			for (boolean val : innerArray) {
				val = false;
			}
		}
	}

	
	
	
	
	// Enables the Drawing of the shape
	public boolean turnOnCellsForSegment(char segment) {
		char letter = Character.toLowerCase(segment);
		switch (letter) {
		case 'a':
			drawHorizontal(topRow);
			break;
		case 'b':
			drawVertical(rightCol, topRow, midRow);
		case 'c':
			drawVertical(rightCol, midRow, bottomRow);
			break;
		case 'd':
			drawHorizontal(bottomRow);
			break;
		case 'e':
			drawVertical(leftCol, midRow, bottomRow);
			break;
		case 'f':
			drawVertical(leftCol, topRow, midRow);
			break;
		case 'g':
			drawHorizontal(midRow);
			break;
		default:
			return false;
		}
		return true;
	}

	
	
	
	
	//Sets the size and initializes the array
	public boolean setSize(int width, int height) {
		if (!validateSize(width, height)) {
			return false;
		}
		topRow = 0;
		bottomRow = height - 1;
		rightCol = width - 1;
		midRow = (height / 2);
		leftCol = 0;
		height1 = height;
		width1 = width;
		allocateCleanArray();
		return true;
	}

	public int getHeight() {
		return height1;
	}
	public int getWidth() {
		return width1;
	}
	
	
	
	//Displays the Table
	public void display() {


	       String grid = "";
	       for (int i = 0; i < height1; i++)
	       {
	          for (int j = 0; j < width1; j++)
	          {
	             if (data[i][j])
	                grid += DRAW_CHAR;
	             else
	                grid += BLANK_CHAR;
	          }

	          grid += "\n";
	       }
	       
	       System.out.println(grid);
	}

	
	
	
	
	
	// deep copy required
	public Object clone() throws CloneNotSupportedException {
			SevenSegmentImage ssiNew = (SevenSegmentImage)super.clone();
			ssiNew.rightCol = rightCol+1;
			ssiNew.bottomRow = bottomRow +1;
			ssiNew.data = new boolean[ssiNew.rightCol][ssiNew.bottomRow];
			for(int i =0; i<=bottomRow; i++) {
				for(int j = 0; j<rightCol; j++) {
					ssiNew.data[i][j] = this.data[i][j];
				}
			}
			return ssiNew;
	}

	
	
	
	
	//Checks if the size is valid
	private boolean validateSize(int width, int height) {
		if (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT || height % 2 == 0)
			return false;
		return true;
	}

	
	
	
	
	//Initializes the cleanArray of data[][]
	private void allocateCleanArray() {

		data = new boolean[height1][width1];
		clearImage();
	}

	
	
	
	
	
	// helpers - not required, but used by instructor
	// Sets the value Horizontally to be true
	void drawHorizontal(int row) {
		for (int i = leftCol; i < rightCol; i++) {
			data[row][i] = true;
		}
	}

	
	
	
	
	
// sets the Values Vertically to be true
	void drawVertical(int col, int startRow, int stopRow) {
		for (int i = startRow; i <= stopRow; i++) {
			data[i][col] = true;
		}
	}
}
class SevenSegmentDisplay  implements Cloneable
{
   private SevenSegmentImage theImage;
   private SevenSegmentLogic theDisplay;

   
   
   
   
   
   // Default Constructor
   public SevenSegmentDisplay()
   {
      theImage = new SevenSegmentImage();
      theDisplay = new SevenSegmentLogic();
   }
   
   
   
   
   
   
   // OverLoaded constructor which sets up the height and the width
   public SevenSegmentDisplay( int width, int height )
   {
      theImage = new SevenSegmentImage(width, height);
      theDisplay = new SevenSegmentLogic();
   }
   
   
   
   
   
   // Sets the size of the image: Calls for setSize from the sevenSegmentImage
   public boolean setSize( int width, int height )
   {
      return theImage.setSize(width, height);
   }
   
   
   
   
   
   
   //Sets up the values that are required to be true in the image
   public void loadConsoleImage()
   {
      char[] arr = {'a', 'b','c','d','e','f','g'};
      	for(int i = 0; i<arr.length; i++) {
      		if (theDisplay.getValOfSeg(i))
                theImage.turnOnCellsForSegment(arr[i]);
      	}
   }
   
   
   
   
   
   //Displays the console of the Image
   public void consoleDisplay()
   {
      theImage.display();
   }
   
   
   
   
   
   
   //Evaluates the display Function of the SevenSegmentLogic
   public void eval( int input )
   {
      theDisplay.eval(input);
   }

   
   
   
   
   
   //Deep Clone of the theImage and theDisplay
   public Object clone() throws CloneNotSupportedException
   {
	  SevenSegmentDisplay newSevenSegmentDisplay = (SevenSegmentDisplay)super.clone();

	  newSevenSegmentDisplay.theImage = (SevenSegmentImage) this.theImage.clone();
	  newSevenSegmentDisplay.theDisplay = (SevenSegmentLogic) this.theDisplay.clone();
	  return newSevenSegmentDisplay;

   }
}
class BooleanFunc implements Cloneable
{
    public static final int MAX_TABLE_FOR_CLASS = 65536;// 16 binary inputs
    public static final int DEFAULT_TABLE_SIZE = 16;
    public static final boolean DEFAULT_TABLE_VAL = false;
    public static final int MIN_TABLE_SIZE = 0;
    
    private int tableSize;
    private boolean[] truthTable;
    private boolean evalReturnIfError;
    private boolean state;
 
    public boolean getState(){return state;} //simple getter

    //Default constructor, takes no parameters. It sets tableSize
    //and evalReturnIfError to their default values and initializes the 
    //truthTable data member as well as state. 
    public BooleanFunc()
    {
        tableSize = DEFAULT_TABLE_SIZE;
        state = evalReturnIfError = DEFAULT_TABLE_VAL;
        initTable(tableSize);
    }
    
    
    
    
    
    
    //Overloaded class constructor which takes in a single int parameter,
    //tableSize. It checks for valid tableSize and fixes tableSize if the
    //tableSize is invalid. Afterward, sets the private data member 
    //tableSize to the parameter tableSize and allocates the truthTable
    //member with the helper function. Finally, sets state and 
    //evalReturnIfError to their default values.
    public BooleanFunc(int tableSize)
    {
        if(!validTableSize(tableSize))
            tableSize = DEFAULT_TABLE_SIZE;
        
        this.tableSize = tableSize;
        initTable(tableSize);
        state = evalReturnIfError = DEFAULT_TABLE_VAL;
        
    }
    
    
    
    
    
    
    
    //Overloaded class constructor which takes in anint parameter tableSize and
    //boolean evalReturnIfError. It checks for valid tableSize and fixes param 
    //tableSize if it is invalid. Afterward, sets the private data member 
    //tableSize to the parameter tableSize and allocates the truthTable
    //member with the helper function. Finally, sets state and 
    //evalReturnIfError to the parameter evalReturnIfError value.
    public BooleanFunc(int tableSize, boolean evalReturnIfError)
    {
        if (!validTableSize(tableSize))
            tableSize = DEFAULT_TABLE_SIZE;
        this.tableSize = tableSize;
        
        initTable(tableSize);
        this.evalReturnIfError = evalReturnIfError;
        this.state = evalReturnIfError;
        
    }
    
    
    
    
    
    
    
    
    
    //initTable takes in a single int, tableSize, then uses tableSize to 
    //properly initialize the truthTable data member array. First, it checks
    //for valid tableSize. If an invalid size is detected, it returns FALSE.
    //Otherwise, the truthTable member is set accordingly and returns
    //TRUE.
    private boolean initTable(int tableSize)
    {
        if (!validTableSize(tableSize))
            return false;
        
        this.tableSize = tableSize;
        truthTable = new boolean[tableSize];
        
        setTableDefault(DEFAULT_TABLE_VAL);
        return true;
        
    }
    
    
    
    
    
   //Sets the entire passed array to true if Valid
    public boolean setTruthTableUsingTrue(int inputsThatProduceTrue[])
    {
        if (!validArraySize(inputsThatProduceTrue.length))
            return false;
        
        setTableDefault(false);
        
        for (int i = 0; i < inputsThatProduceTrue.length; i++)
        {
            if (inputsThatProduceTrue[i] < tableSize &&
                inputsThatProduceTrue[i] >= 0)
                truthTable[inputsThatProduceTrue[i]] = true;
        }
        
        return true;
    }
    
    
    
    
    
    
    
    
 // Sets the entire passed array to false if Valid
    public boolean setTruthTableUsingFalse(int inputsThatProduceFalse[])
    {
        if (!validArraySize(inputsThatProduceFalse.length))
            return false;
        
        setTableDefault(true);
        
        for (int i = 0; i < inputsThatProduceFalse.length; i++)
        {
            if (inputsThatProduceFalse[i] < tableSize &&
                inputsThatProduceFalse[i] >= 0)
                truthTable[inputsThatProduceFalse[i]] = false;
        }
        
        return true;
    }
    
    
    
    
    
    
    
    //sets the value of state depending on the truthTable
    public boolean eval(int input)
    {
        if (input < 0 || input >= tableSize)
            state = evalReturnIfError;
        else
            state = truthTable[input];
        
        return state;
    }
    
    
    
    
    
    
    //Copy constructor. Assures deep data of tableSize is copied between
    //objects.
    public BooleanFunc clone() throws CloneNotSupportedException
    {

       BooleanFunc newBooleanFunc = (BooleanFunc)super.clone();
       newBooleanFunc.truthTable = new boolean[this.tableSize];
       
       for (int i = 0; i < tableSize; i++)
           newBooleanFunc.truthTable[i] = this.truthTable[i];
       
       return newBooleanFunc;
    }
    //---private helper functions
    
   // Checks for the validity of the array
    boolean validArraySize(int arraySize)
    {
        if (arraySize < MIN_TABLE_SIZE || arraySize > tableSize)
            return false;
        
        return true;
    }
    
    
    
    
    
    
    
   // Checks the validity of the Table Size
    private boolean validTableSize(int tableSize)
    {
        if (tableSize < MIN_TABLE_SIZE || tableSize > MAX_TABLE_FOR_CLASS)
            return false;
        
        return true;
    }
    
    
    
    
    
    
    
    
    //sets the default table to be equal to the tableVal variable
    private void setTableDefault(boolean tableVal)
    {
        for (int k = 0; k < tableSize; k++)
            truthTable[k] = tableVal;
    }
    
}

class MultiSegmentLogic implements Cloneable
{
    public static final int DEFAULT_NUM_SEGS = 0;
    
    protected int numSegs;
    protected BooleanFunc segs[];
    
    // Default Constructor
    public MultiSegmentLogic()
    {
        setNumSegs(DEFAULT_NUM_SEGS);
    }
    
    
    
    
    
    
    
    
    // Overloaded Constructor that checks for validity.
    public MultiSegmentLogic(int numSegs)
    {
        if (!setNumSegs(numSegs))
            setNumSegs(DEFAULT_NUM_SEGS);
        
    }
    
    
    
    
    
    
    
    // Checks for validity and sets numSegs
    public boolean setNumSegs(int numSegs)
    {
        if (numSegs < 0)
            return false;
        
        initSegArray(numSegs);
        this.numSegs = numSegs;
        
        return true;
    }
    
    
    
    
    
    
    
    
    //Takes two parameters, int segNum and a BooleanFunc funcForThisSeg.
    //If the segNum parameter is invalid, returns FALSE immediately.
    //Otherwise, updates the index of the segs array corresponding to the
    //passed param segNum with funcForThisSeg via direct assignment and returns
    //TRUE.
    public boolean setSegment(int segNum, BooleanFunc funcForThisSeg)
    {
        if (segNum < 0 || segNum >= numSegs)
            return false;
        
        segs[segNum] = funcForThisSeg;
        return true;
    }
    
    
    
    
    
    
    
    
    
    //eval takes a single int parameter, input, and simply goes through the
    //segs[] array passing each individual BooleanFunc's eval method
    //with the passed parameter input.
    public void eval(int input)
    {
        for (int i = 0; i < numSegs; i++)
            segs[i].eval(input);
    }
    
    
    
    
    
    
    
    
    //Copy constructor that assures deep data of segs is copied between
    //objects.
    public MultiSegmentLogic clone() throws CloneNotSupportedException
    {

       MultiSegmentLogic newMSL = (MultiSegmentLogic)super.clone();
       newMSL.segs = new BooleanFunc[this.numSegs];
       
       for (int i = 0; i < numSegs; i++)
           newMSL.segs[i] = this.segs[i];
       
       return newMSL;
    }
    
    
    
    
    
    
    
    //--helper functions
    
    //Initializes the segs array. Takes in a single parameter, numSegs, then 
    //sets the segs[] array to be the size of numSegs.
    protected void initSegArray(int numSegs)
    {
        segs = new BooleanFunc[numSegs];
    }




    
}

class SevenSegmentLogic extends MultiSegmentLogic {
    private static boolean sevenSegmentsLoaded = false;
    private static BooleanFunc sevenSegBoolFuncs[];

    
    
    
    
    
    // Default Constructor
	SevenSegmentLogic() {
		super(7);
		loadSevenSegmentBooleans();
		
	}
	
	
	
	
	
	
	public boolean getValOfSeg(int seg)
    {
        if (seg < 0 || seg >= numSegs)
            return false;
        
        return segs[seg].getState();
        
    }
    
    
    private void loadSevenSegmentBooleans()
    {
        if (!sevenSegmentsLoaded)
        {
           sevenSegBoolFuncs = new BooleanFunc[7];
           for (int i = 0; i < sevenSegBoolFuncs.length; i++)
               sevenSegBoolFuncs[i] = new BooleanFunc();
           //set with false
           int aFunc[] = { 1, 4, 11, 13 };
           int bFunc[] = { 5, 6, 11, 12, 14, 15 };
           int cFunc[] = { 2, 12, 14, 15 };
           int dFunc[] = { 1, 4, 7, 10, 15};
           int eFunc[] = { 1, 3, 4, 5, 7, 9 };
           int fFunc[] = { 1, 2, 3, 7, 13 };
           int gFunc[] = { 0, 1, 7, 12 };

           sevenSegBoolFuncs[0].setTruthTableUsingFalse(aFunc);
           sevenSegBoolFuncs[1].setTruthTableUsingFalse(bFunc);
           sevenSegBoolFuncs[2].setTruthTableUsingFalse(cFunc);
           sevenSegBoolFuncs[3].setTruthTableUsingFalse(dFunc);
           sevenSegBoolFuncs[4].setTruthTableUsingFalse(eFunc);
           sevenSegBoolFuncs[5].setTruthTableUsingFalse(fFunc);
           sevenSegBoolFuncs[6].setTruthTableUsingFalse(gFunc);

           sevenSegmentsLoaded = true;
        }

        for (int i = 0; i < 7; i++)
           setSegment(i, sevenSegBoolFuncs[i]);
    }

}