
/**
 * A node for a binary tree.
 * @author CS261
 * @version 2011.10.27
 * @version 2012.10.30 Improved documentation
 */
public class BinNode<E>
{
    private E data;              // the data in the node
    private BinNode<E> left;     // the root of the left subtree
    private BinNode<E> right;    // the root of the right subtee
    /**
     * Constructor for objects of class BinNode
     * @param data the data to store in this node
     */
    public BinNode (E data)
    {
       this.data = data;
       left = null;
       right = null;
    }
    /** 
     * Set a new data value in this node
     * @param data the new data for this node
     */
    public void setData(E data)
    {
        this.data = data;
    }
    
    /** 
     * Get the data from this node.
     * @return the data stored in this node
     */
    public E getData()
    {
        return data;
    }

    /**
     * Set pointer to the left subtree
     * @param l pointer to a BinNode which is the root of the left subtree
     */
    public void setLeft(BinNode<E> l)
    {
        left = l;
    }
    
     /**
     * Set pointer to the right subtree
     * @param r pointer to a BinNode which is the root of the right subtree
     */
    public void setRight(BinNode<E> r)
    {
        right = r;
    }
    
     /**
     * Get left pointer.
     * @return pointer to the root of left subtree
     */
    public BinNode<E> getLeft()
    {
        return left;
    }
    
     /**
     * Get right pointer.
     * @return pointer to the root of the right subtree
     */
    public BinNode<E> getRight()
    {
        return right;
    }
}
