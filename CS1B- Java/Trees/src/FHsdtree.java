// Vaibhav Jain - 20422729
// Spring 2020, CS1B, June 16, 2020
// Proffessor David Harden
// 
public class FHsdtree<E> implements Cloneable
{
   protected int mSize;
   protected FHsdtreeNode<E> mRoot;
   private int n = 1;
   public FHsdtree() { clear(); }
   public boolean empty() { return (mSize == 0); }
   public int size() { return size(mRoot,0); }
   public void clear() { mSize = 0; mRoot = null; }
   
   public FHsdtreeNode<E> find(E x) { return find (mRoot, x, 0); }
   public boolean remove(E x) { return remove(mRoot, x); }
   public void  display()  { display(mRoot, 0); }
   public void displayPhysical() { displayPhysical(mRoot, 0);}
   public < F extends Traverser< ? super E > > 
   void traverse(F func)  { traverse(func, mRoot, 0); }
   
   public FHsdtreeNode<E> addChild( FHsdtreeNode<E> treeNode,  E x )
   {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0)
      {
         if (treeNode != null)
            return null; // error something's fishy.  treeNode can't right
         mRoot = new FHsdtreeNode<E>(x, null, null, null, false);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null;  // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      FHsdtreeNode<E> newNode = new FHsdtreeNode<E>(x, 
         treeNode.firstChild, null, treeNode, mRoot);  // sb, chld, prv, rt
      treeNode.firstChild = newNode;
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      ++mSize;
      return newNode;  
   }
   
   
   
   
   
   
   public FHsdtreeNode<E> find(FHsdtreeNode<E> root, E x, int level)
   {
      FHsdtreeNode<E> retval;

      if (mSize == 0 || root == null)
         return null;

      if (root.data.equals(x))
         return root;

      // otherwise, recurse.  don't process sibs if this was the original call
      if ( level > 0 && (retval = find(root.sib, x, level)) != null )
         return retval;
      return find(root.firstChild, x, ++level);
   }
   
   
   
   
   
   
   public boolean remove(FHsdtreeNode<E> root, E x)
   {
      FHsdtreeNode<E> tn = null;

      if (mSize == 0 || root == null)
         return false;
     
      if ( (tn = find(root, x, 0)) != null )
      {
         removeNode(tn);
         return true;
      }
      return false;
   }
   
   
   
   
   
   
   protected void removeNode(FHsdtreeNode<E> nodeToDelete )
   {
      if (nodeToDelete == null || mRoot == null)
         return;
      if (nodeToDelete.myRoot != mRoot)
         return;  // silent error, node does not belong to this tree

      // remove all the children of this node
      while (nodeToDelete.firstChild != null)
         removeNode(nodeToDelete.firstChild);

      if (nodeToDelete.prev == null)
         mRoot = null;  // last node in tree
      else if (nodeToDelete.prev.sib == nodeToDelete)
         nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
      else
         nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent

      // adjust the successor sib's prev pointer
      if (nodeToDelete.sib != null)
         nodeToDelete.sib.prev = nodeToDelete.prev;
      --mSize;
   }
   
   
   
   
   
   
   public Object clone() throws CloneNotSupportedException
   {
      FHsdtree<E> newObject = (FHsdtree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);
      
      return newObject;
   }
   
   
   
   
   
   
   private FHsdtreeNode<E> cloneSubtree(FHsdtreeNode<E> root)
   {
      FHsdtreeNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHsdtreeNode<E>
      (
         root.data, 
         cloneSubtree(root.sib), cloneSubtree(root.firstChild),
         null, false
      );
      
      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }
   
   
   
   
   
   
   // recursively sets all myRoots to mRoot
   private void setMyRoots(FHsdtreeNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      treeNode.myRoot = mRoot;
      setMyRoots(treeNode.sib);
      setMyRoots(treeNode.firstChild);
   }
   
   // define this as a static member so recursive display() does not need
   // a local version
   final static String blankString = "                                    ";
   
   // let be public so client can call on subtree
   public void  display(FHsdtreeNode<E> treeNode, int level) 
   {
      String indent;
              
      if (treeNode == null)
          return;

      // stop runaway indentation/recursion
      if  (level > (int)blankString.length() - 1)
      {
         System.out.println( blankString + " ... " );
         return;
      }

      indent = blankString.substring(0, level);

      // pre-order processing done here ("visit")
      System.out.println( indent + treeNode.data ) ;
      
      // recursive step done here
      display( treeNode.firstChild, level + 1 );
      if (level > 0 )
         display( treeNode.sib, level );
   }
      
   
   
   
   
   
   // often helper of typical public version, but also callable by on subtree
   public <F extends Traverser<? super E>> 
   void traverse(F func, FHsdtreeNode<E> treeNode, int level)
   {
      if (treeNode == null)
         return;

      func.visit(treeNode.data);
      
      // recursive step done here
      traverse( func, treeNode.firstChild, level + 1);
      if (level > 0 )
         traverse( func,  treeNode.sib, level);
   }
   
   
   
   
   
   // Returns the size for the physical tree
   public int sizePhysical()
   {
       return mSize;
   }

   
   
   
   
   
   // Dislays the physical tree
   protected void displayPhysical(FHsdtreeNode<E> treeNode, int level)
   {
       String indent;

       if(treeNode == null)
           return;
      
       if(level > (int) blankString.length() - 1)
       {
           System.out.println(blankString + " ---");
           return;
       }
      
       indent = blankString.substring(0, level);
      
       System.out.print(indent + treeNode.data);
       if(treeNode.deleted)
           System.out.print("Deleted");
       System.out.println();
       FHsdtreeNode<E> temp = treeNode.firstChild;
       while( temp != null) {
    	   displayPhysical(temp, level + 1);
    	   temp = temp.sib;
       }
   }
   
   
   
   
   
   // Goes through the Entire Tree to calculate the total nodes in the tree
   protected int size(FHsdtreeNode<E> treeNode, int level)
   {
       if(level == 0)
           n = 1;
      
       if(treeNode == null || treeNode.deleted)
           return 0;
       FHsdtreeNode<E> temp = treeNode.firstChild;
       while( temp != null)
       {	
    	   temp = temp.sib;
           n++;
           size(temp, level + 1);
       }
      
       return n;
   }
   
   
   
   
   
   // Collects Garbage to the desired level of the tree
   public boolean collectGarbage()
   {
       return collectGarbage(mRoot, 0);
   }
  
   
   
   
   
   
   //Removes the nodes that have been marked as false -- Does not affect that size
   public boolean collectGarbage(FHsdtreeNode<E> treeNode, int level)
   {
       boolean garb = false;

       for(FHsdtreeNode<E> temp = treeNode.firstChild; temp != null; temp = temp.sib)
       {
           if(temp.deleted)
           {
               removeNode(temp);
               garb = true;
           }
          
           collectGarbage(temp, level + 1);
       }
      
       return garb;
   }
}
