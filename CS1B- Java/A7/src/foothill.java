	// Vaibhav Jain - 20422729
	//Professor David Harden CS1B- Spring 2020
	// June 1, 2020
	// Description:  Constains 3 Classes: BooleanFunc, MultiSegmentLogic, and Seven Segment Logic
	// 
public class foothill {
	public static void main(String[] args) {
		BooleanFunc segA, segB, segC;

	      int  inputX, k;

	      int evenFunc[] = { 0, 2, 4, 6, 8, 10, 12, 14 };
	      int greater9Func[] = { 10, 11, 12, 13, 14, 15 };;
	      int greater3Func[] = { 0, 1, 2, 3 };
	      
	      segA = new BooleanFunc();
	      segB = new BooleanFunc( 13 );
	      segC = new BooleanFunc( 100, true );

	      segA.setTruthTableUsingTrue( evenFunc );
	      segB.setTruthTableUsingTrue( greater9Func );
	      segC.setTruthTableUsingFalse( greater3Func );

	      // testing class BooleanFunc
	      System.out.println( "before eval()" );
	      System.out.println(
	         "\n  A(x) = "
	         + segA.getState()
	         + "\n  B(x) = "
	         + segB.getState()
	         + "\n  C(x) = "
	         + segC.getState()
	         + "\n" );
	      System.out.println( "looping with eval()" );

	      for ( inputX = 0; inputX < 10; inputX++ )
	      {
	         segA.eval( inputX );
	         segB.eval( inputX );
	         segC.eval( inputX );
	         System.out.println(
	            "Input: " + inputX
	            + "\n  A(x) = "
	            + segA.getState()
	            + "\n  B(x) = "
	            + segB.getState()
	            + "\n  C(x) = "
	            + segC.getState()
	            + "\n" );
	      }
	      
	      SevenSegmentLogic my7Seg, myCopy;
	      
	      my7Seg = new SevenSegmentLogic();
	      
	      try
	      {
	         myCopy = (SevenSegmentLogic) my7Seg.clone();
	      }
	      catch  ( CloneNotSupportedException e )
	      {
	         System.out.println("** Clone Unsuccessful **");
	         myCopy = new SevenSegmentLogic();
	      }
	      
	      for ( inputX = 0; inputX < 16; inputX++ )
	      {
	         myCopy.eval( inputX );
	         System.out.print("\n| ");
	         for ( k = 0; k < 7; k++ )
	            System.out.print( myCopy.getValOfSeg( k ) + " | ");
	         System.out.println();
	      }
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

/*
Output
before eval()

  A(x) = false
  B(x) = false
  C(x) = true

looping with eval()
Input: 0
  A(x) = true
  B(x) = false
  C(x) = false

Input: 1
  A(x) = false
  B(x) = false
  C(x) = false

Input: 2
  A(x) = true
  B(x) = false
  C(x) = false

Input: 3
  A(x) = false
  B(x) = false
  C(x) = false

Input: 4
  A(x) = true
  B(x) = false
  C(x) = true

Input: 5
  A(x) = false
  B(x) = false
  C(x) = true

Input: 6
  A(x) = true
  B(x) = false
  C(x) = true

Input: 7
  A(x) = false
  B(x) = false
  C(x) = true

Input: 8
  A(x) = true
  B(x) = false
  C(x) = true

Input: 9
  A(x) = false
  B(x) = false
  C(x) = true


| true | true | true | true | true | true | false | 

| false | true | true | false | false | false | false | 

| true | true | false | true | true | false | true | 

| true | true | true | true | false | false | true | 

| false | true | true | false | false | true | true | 

| true | false | true | true | false | true | true | 

| true | false | true | true | true | true | true | 

| true | true | true | false | false | false | false | 

| true | true | true | true | true | true | true | 

| true | true | true | true | false | true | true | 

| true | true | true | false | true | true | true | 

| false | false | true | true | true | true | true | 

| true | false | false | true | true | true | false | 

| false | true | true | true | true | false | true | 

| true | false | false | true | true | true | true | 

| true | false | false | false | true | true | true | 

*/