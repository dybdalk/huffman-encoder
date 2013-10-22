
/**
 * A binary tree
 * For CS261F12Lab10
 * @author Chuck Hommel 
 * @version 2012.10.20
 * @version 2012.10.30 Modified for lab 10
 */
public class BinTree<E> 
{
    protected BinNode<E> root;    // the root of the tree

    /**
     * Constructor for objects of class BinTree
     */
    public BinTree()
    {
        root = null;
    }

    /**
     * Constructor
     * @param root root for this binary tree
     */
    public BinTree(BinNode<E> root)
    {
        this.root = root;
    }

    /**
     * Constructor
     * @param data data for root node
     * @param leftTree root of tree to be put on left
     * @param rightTree root of tree to be put on right
     * 
     */
    public BinTree(E data, BinTree<E> leftTree, BinTree<E> rightTree)
    {
        root = new BinNode<E>(data);
        if (leftTree != null)
        {
            root.setLeft(leftTree.root);
        }
        if (rightTree != null)
        {
            root.setRight(rightTree.root);
        }
    }

    /**
     * Checks whether the root of this tree is a leaf
     * @return true if this is a leaf, false if not
     */ 
    public boolean isLeaf()
    {
        return (root == null ||(root.getLeft() == null && root.getRight() == null));
    }

    /**
     * Returns the data in the root node.
     * @return the data in the root node
     */
    public E getData()
    {
        if (root == null)
        {
            return null;
        }
        return root.getData();
    }

    /** 
     * returns a reference to the left subTree
     * @return left subTree
     */
    public BinTree<E> getLeftSubtree()
    {
        if (root != null)
        {
            return new BinTree<E>(root.getLeft()) ;
        }
        else
        {
            return new BinTree<E>();
        }
    }

    /** 
     * returns a reference to the right subTree
     * @return right subTree
     */
    public BinTree<E> getRightSubtree()
    {
        if (root != null)
        {
            return new BinTree<E>(root.getRight()) ;
        }
        else
        {
            return new BinTree<E>();
        }
    }

    /**
     * prints on the terminal a preorder traversal of the tree
     */ 
    public void preOrderTraversal()
    {
        preOrderTraversal(root);
        
    }
    private void preOrderTraversal(BinNode<E> localRoot)
    {
        if (localRoot == null)
        {
            return;
        }
        else
        {
            System.out.println(localRoot.getData());
            preOrderTraversal(localRoot.getLeft());
            preOrderTraversal(localRoot.getRight());
        }
    }

    /**
     * prints on the terminal a postorder traversal of the tree
     */ 
    public void postOrderTraversal()
    {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * prints on the terminal an inorder traversal of the tree
     */ 
    public void inOrderTraversal()
    {
         throw new UnsupportedOperationException("Not implemented");
    }

}
