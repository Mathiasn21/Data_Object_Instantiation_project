package framework.utilities.data.structure;
  ///////////////////////////////////////////////
 //             Import statements             //
///////////////////////////////////////////////

import java.util.Iterator;

import static framework.utilities.data.structure.QuickTraversals.getBottomLeftChild;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T>{
    private Node<T> rootNode;
    public BinaryTree() { rootNode = null; }


      ///////////////////////////////////////////////
     //               GETTERS                     //
    ///////////////////////////////////////////////
    public Node<T> getRootNode() { return rootNode; }

    @Override
    public int getNumberOfLeaves() { return getNumberOfLeaves(rootNode); }
    private int getNumberOfLeaves(Node<T> root) {
        if(root == null){ return 0; }
        if(!(root.hasLeftChild() || root.hasRightChild())){ return 1; }
        return getNumberOfLeaves(root.getLeft()) + getNumberOfLeaves(root.getRight());
    }

    @Override
    public int getNumberOfNodesWithOneChild(){
        return getNumberOfNodesWithOneChild(rootNode);
    }
    private int getNumberOfNodesWithOneChild(Node<T> root) {
        if(root == null){return  0;}

        int sum = 0;
        if(root.hasRightChild() && !root.hasLeftChild()){
            sum++;
        }
        if(root.hasLeftChild() && !root.hasRightChild()){
            sum++;
        }
        sum += getNumberOfNodesWithOneChild(root.getLeft()) + getNumberOfNodesWithOneChild(root.getRight());
        return sum;
    }

    @Override
    public int getNumberOfNodesWithTwoChild(){
        return getNumberOfNodesWithTwoChild(rootNode);
    }
    private int getNumberOfNodesWithTwoChild(Node<T> root) {
        if(root == null){return  0;}
        int sum = 0;
        if(root.hasRightChild() && root.hasLeftChild()){ sum++; }
        return sum + (getNumberOfNodesWithOneChild(root.getLeft()) + getNumberOfNodesWithOneChild(root.getRight()));
    }


      ///////////////////////////////////////////////
     //              SETTERS                      //
    ///////////////////////////////////////////////
    @Override
    public void insert(T data) {
        Node<T> newNode = new Node<>(data, null);
        insert(rootNode, newNode);
    }
    protected void insert(Node<T> currentNode, Node<T> node) {
        if(rootNode == null){
            rootNode = node;
            return;
        }

        int compareRes = currentNode.compareTo(node);

        //If node exists increment counter and return
        if(compareRes == 0){
            currentNode.tCounter++;
            return;
        }
        boolean compare = compareRes > 0;

        if(compare && !currentNode.hasLeftChild()){
            currentNode.setLeftChild(node);
            node.parent = currentNode;

        }else if (!(compare || currentNode.hasRightChild())){
            currentNode.setRightChild(node);
            node.parent = currentNode;

        }else if (compare && currentNode.hasLeftChild()){ insert(currentNode.getLeft(), node);
        }else if (!compare && currentNode.hasRightChild()){ insert(currentNode.getRight(), node); }
    }

    protected final void setRootNode(Node<T> root) { rootNode = root; }


      ///////////////////////////////////////////////
     //               ITERATORS                   //
    ///////////////////////////////////////////////
    @Override
    public Iterator<Node<T>> inorderTraversal() {
     return new InorderTraversalIterator<>(rootNode);
    }

    @Override
    public Iterator<Node<T>> postorderTraversal() {
        return new PostorderTraversalIterator<>(rootNode);
    }

    @Override
    public Iterator<Node<T>> preorderTraversal() {
        return new PreorderTraversalIterator<>(rootNode);
    }

    @Override
    public Iterator<Node<T>> levelorderTraversal() {
        return new LevelOrderIterator<>(rootNode);
    }


      ///////////////////////////////////////////////
     //              Utility Methods              //
    ///////////////////////////////////////////////
    @Override
    public boolean contains(T t) { return search(t) == null; }

    /**
     * Returns T or null if none was found
     * @param t T
     * @return T
     */
    @Override
    public T search(T t) {
        Node<T> res = rootNode;
        while(res != null){
            if(!(res.hasLeftChild() || res.hasRightChild())){ return null; }
            int compare = t.compareTo(res.t);
            if(compare == 0){ return res.t; }
            res = compare > 0 ? res.getRight() : res.getLeft();
        }
        return null;
    }

    protected Node<T> searchNode(T t) {
        Node<T> res = rootNode;
        while(res != null){
            int compare = t.compareTo(res.t);
            if(compare == 0){ return res; }
            if(!(res.hasLeftChild() || res.hasRightChild())){ return null; }
            res = compare > 0 ? res.getRight() : res.getLeft();
        }
        return null;
    }


    /**
     * Removes the node with T object if it exists in tree.
     * It returns the removed node or null if none.
     * @param t T
     * @return Node&lt;T&gt;
     */
    @Override
    @SuppressWarnings("unchecked")
    public Node<T> remove(T t) {
        if (t == null){ return null; }
        Node<T> node = searchNode(t);
        if(node == null){ return null; }
        Node<T> orgNode = null;

        try { orgNode  = (Node<T>) node.clone();
        } catch (CloneNotSupportedException e) {e.printStackTrace();}

        boolean isRoot = rootNode.equals(node);

        //Case 1: Node is a leaf
        if(node.isLeaf()){
            Node<T> parent = node.parent;

            if(isRoot){ rootNode = null;
            } else if (parent.left == node) { parent.left = null;
            } else { parent.right = null; }

            //Case 2: Node has a single subtree
        }else if(node.getNumOfChildren() == 1){
            Node<T> parent = node.parent;
            Node<T> child = node.getLonelyChild();
            child.parent = parent;

            if(isRoot){
                rootNode = child;
                child.parent = null;
            } else if (parent.left == node) { parent.left = child;
            } else { parent.right = child; }

            //Case 3: Node has two subtrees
        }else if(node.getNumOfChildren() == 2) {
            Node<T> smallestNode = getBottomLeftChild(node.right);
            Node<T> parent = smallestNode.parent;

            //Replaces vital node information
            node.t = smallestNode.t;
            node.tCounter = smallestNode.tCounter;

            //Updates the references
            if(parent.right == smallestNode){ parent.right = null; }
            if(parent.left == smallestNode){ parent.left = null; }

            if(smallestNode.right != null){
                smallestNode.right.parent = node;
                node.right = smallestNode.right;
            }
        }
        return orgNode;
    }

    @Override
    public IBinaryTree<T> copyToNewTree(){
        BinaryTree<T> tree = new BinaryTree<>();
        if (rootNode != null) {
            tree.insert(rootNode.t);
            appendNodesToCopy(rootNode, tree.getRootNode());
        }
        return tree;
    }

    private void appendNodesToCopy(Node<T> node, Node<T> copy){
        if(node.hasLeftChild()){copy.setLeftChild(node.getLeft());}
        if(node.hasRightChild()){copy.setRightChild(node.getRight());}
    }
}
