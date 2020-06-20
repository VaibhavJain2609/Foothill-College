public class FHsdtreeNode<E>
{
   // use protected access so the tree, in the same package, 
   // or derived classes can access members 
   protected FHsdtreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHsdtreeNode<E> myRoot;  // needed to test for certain error
   protected boolean deleted;

   public FHsdtreeNode( E d, FHsdtreeNode<E> sb, FHsdtreeNode<E> chld, FHsdtreeNode<E> prv, boolean delete )
   {
      firstChild = chld; 
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
      deleted = delete;
   }
   
   public FHsdtreeNode()
   {
      this(null, null, null, null, false);
      
   }
   
   public E getData() { return data; }

   // for use only by FHtree (default access)
   protected FHsdtreeNode( E d, FHsdtreeNode<E> sb, FHsdtreeNode<E> chld, 
      FHsdtreeNode<E> prv, FHsdtreeNode<E> root )
   {
      this(d, sb, chld, prv, false);
      myRoot = root;
   }
}
