package doiframework.utilities.collections;

import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

/**Generic AVL tree. It extends binary tree.
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class AVLTree<T> extends Tree<T> {
    public AVLTree(Comparator<T> comparator) { super(comparator); }
    public AVLTree() { super(); }

    ///////////////////////////////////////////////
     //              SETTERS                      //
    ///////////////////////////////////////////////
    @Override
    public final void insert(T data) {
        AVLNode<T> node = new AVLNode<>(data, null);
        insert(getRootNode(), node);//Calls super method

        if(node.parent == null){
            size++;
            return;//No need to re balance
        }

        node = (AVLNode<T>)node.parent;
        while(node != null){
            reBalanceTree(node);
            node = (AVLNode<T>)node.parent;
        }
        size++;
    }


      ///////////////////////////////////////////////
     //              Utility Methods              //
    ///////////////////////////////////////////////
    @Override
    public final Node<T> remove(T t) {
        Node<T> removedNode = super.remove(t);//Calls super method;
        propagateReBalancing(removedNode);
        return removedNode;
    }

    /**
     * Traverse upwards and re balance on need
     * @param root Node
     */
    private void propagateReBalancing(Node<T> root) {
        while(root != null){
            reBalanceTree((AVLNode<T>) root);
            root = root.parent;
        }
    }

    private void reBalanceTree(AVLNode<T> root){
        updateNodeHeight(root);
        int balance = calcBalance(root);

        if(balance >= 2){
            //Balance == 2
            //RightHeight > LeftHeight --> Rotate Left with root Z
            //RightHeight <= LeftHeight --> Rotate Right with root Y, then Left with root Z
            if (getHeight((AVLNode<T>) root.right.right) <= getHeight((AVLNode<T>) root.right.left)) {
                rotateRight((AVLNode<T>) root.right);
            }
            rotateLeft(root);

            //Balance == -2
            //LeftHeight > RightHeight --> Rotate Right with root Z
            //LeftHeight <= RightHeight --> Rotate Left with root Y, then Right with root Z
        }else if(balance <= -2){
            if(getHeight((AVLNode<T>)root.left.left) <= getHeight((AVLNode<T>) root.left.right)){
                rotateLeft((AVLNode<T>) root.left);
            }
            rotateRight(root);
        }
    }

    int calcBalance(AVLNode<T> node) {
        return (node == null) ? 0 : getHeight((AVLNode<T>) node.right) - getHeight((AVLNode<T>) node.left);
    }

    /**Rotate right execute root node from subtree
     * @param root AVLNode&lt;T&gt;
     */
    private void rotateRight(AVLNode<T> root){
        if(root == null){ return; }//pivot is root...

        AVLNode<T> pivot = (AVLNode<T>) root.left;
        Node<T> pRightChild = pivot.right;
        Node<T> pGrandParent = root.parent;

        root.left = pRightChild;
        root.parent = pivot;
        pivot.right = root;
        updateParentalReferences(root, pivot, pRightChild, pGrandParent);
        updateNodeHeight(pivot);
        updateNodeHeight(root);
    }

    /**Rotate right execute root node from subtree
     * @param root AVLNode&lt;T&gt;
     */
    private void rotateLeft(AVLNode<T> root){
        if(root == null){ return; }//pivot is root...

        AVLNode<T> pivot = (AVLNode<T>) root.right;
        Node<T> pLeftChild = pivot.left;
        Node<T> pGrandParent = root.parent;

        root.right = pLeftChild;
        root.parent = pivot;
        pivot.left = root;
        updateParentalReferences(root, pivot, pLeftChild, pGrandParent);
        updateNodeHeight(pivot);
        updateNodeHeight(root);
    }

    /**
     * @param node AVLNode
     */
    private void updateNodeHeight(@NotNull AVLNode<T> node){
        node.height = 1 + Math.max(getHeight((AVLNode<T>) node.left), getHeight((AVLNode<T>) node.right));
    }

    /**
     * @param node AVLNode
     * @return int
     */
    private int getHeight(AVLNode<T> node) {
        return node == null ? -1 : node.height;
    }
}
