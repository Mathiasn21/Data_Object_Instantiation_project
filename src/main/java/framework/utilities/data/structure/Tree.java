package framework.utilities.data.structure;


  ///////////////////////////////////////////////
 //             Import statements             //
///////////////////////////////////////////////
import framework.errors.NotComparableError;
import framework.utilities.data.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import static framework.utilities.data.structure.QuickTraversals.getBottomLeftChild;

/**
 * A simple binary search tree and
 * By default root is null, comparator is null and it does not compress
 * duplicates, as this option is set to false.
 * Compression of duplicates is done by calling equals on T
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class Tree<T> implements ITree<T> {
    private Comparator<T> comparator;
    private final boolean compressDuplicates;
    private Node<T> rootNode;
    private Method method = null;
    private Comparator<Object> experimentalComparator;
    private Field fieldToutilize = null;
    private Method methodToUse = null;

    /**
     * Sets the current comparator to null.
     * Asserts that any T object implement comparable else
     * this will throw an error {@link NotComparableError}
     */
    @Contract(pure = true)
    public Tree() { this(null); }

    /**
     * @param compressDuplicates {@link Comparator}&lt;{@link T}&gt;
     */
    @Contract(pure = true)
    public Tree(boolean compressDuplicates) { this(null, compressDuplicates); }

    /**
     * @param comparator {@link Comparator}&lt;{@link T}&gt;
     */
    public Tree(Comparator<T> comparator) { this(comparator, false); }

    /**Main constructor for customizing this
     * @param comparator  {@link Comparator}&lt;{@link T}&gt;
     * @param compressDuplicates boolean
     */
    public Tree(Comparator<T> comparator, boolean compressDuplicates) {
        this.comparator = comparator;
        this.compressDuplicates = compressDuplicates;
        this.rootNode = null;
    }


      ///////////////////////////////////////////////
     //               GETTERS                     //
    ///////////////////////////////////////////////
    public Node<T> getRootNode() { return rootNode; }

    @Override
    public int getNumberOfLeaves() { return getNumberOfLeaves(rootNode); }

    @Override
    public int size() { return 0; }

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
    protected void insert(Node<T> thiz, Node<T> that) {
        if(rootNode == null){
            rootNode = that;
            return;
        }
        int compareRes = compare(thiz.t, that.t);


        boolean b = thiz.t.equals(that.t);
        //If oject exists and equals then increment counter
        if(compareRes == 0 && compressDuplicates){
            thiz.tCounter++;
            return;
        }
        boolean compare = compareRes > 0;

        if(compare && !thiz.hasLeftChild()){
            thiz.setLeftChild(that);
            that.parent = thiz;

        }else if (!(compare || thiz.hasRightChild())){
            thiz.setRightChild(that);
            that.parent = thiz;

        }else if (compare && thiz.hasLeftChild()){ insert(thiz.getLeft(), that);
        }else if (!compare && thiz.hasRightChild()){ insert(thiz.getRight(), that); }
    }

    protected final void setRootNode(Node<T> root) { rootNode = root; }


      ///////////////////////////////////////////////
     //               ITERATORS                   //
    ///////////////////////////////////////////////
    @Override
    public Iterator<Node<T>> inorderTraversal() { return new InorderTraversalIterator<>(rootNode); }
    @Override
    public Iterator<Node<T>> postorderTraversal() { return new PostorderTraversalIterator<>(rootNode); }
    @Override
    public Iterator<Node<T>> preorderTraversal() { return new PreorderTraversalIterator<>(rootNode); }
    @Override
    public Iterator<Node<T>> levelorderTraversal() { return new LevelOrderIterator<>(rootNode); }


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
            int compare = compare(t, res.t);
            if(compare == 0){ return res.t; }
            res = compare > 0 ? res.getRight() : res.getLeft();
        }
        return null;
    }

    protected Node<T> searchNode(T t) {
        Node<T> res = rootNode;
        while(res != null){
            int compare = compare(t, res.t);
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
    public ITree<T> copyToNewTree(){
        Tree<T> tree = new Tree<>(this.comparator);
        if (rootNode != null) {
            tree.insert(rootNode.t);
            appendNodesToCopy(rootNode, tree.getRootNode());
        }
        return tree;
    }

    private void appendNodesToCopy(Node<T> node, Node<T> copy){
        if(node.hasLeftChild()){ copy.setLeftChild(node.getLeft()); }
        if(node.hasRightChild()){ copy.setRightChild(node.getRight()); }
    }

    /**
     * Compares this with that
     * @param thiz {@link T}
     * @param that {@link T}
     * @return int
     */
    protected int compare(@NotNull T thiz, @NotNull T that){
        try {
            if(thiz instanceof Comparable){
                if(method == null){ setupComparableMethod(thiz); }

                //Guaranteed to return int by interface
                return (int) method.invoke(thiz, that);
            }

            if(comparator == null && experimentalComparator == null){
                tryToSetupComparator(thiz, that);
            }

            if(comparator != null){
                return comparator.compare(thiz, that);
            }else if (experimentalComparator != null){
                if(fieldToutilize != null){
                    return experimentalComparator.compare( fieldToutilize.get(thiz),fieldToutilize.get(that));
                }
                return experimentalComparator.compare(methodToUse.invoke(thiz), methodToUse.invoke(that));
            }
        } catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
        throw new Error();
    }

    //FIXME: cleanup this sick method...
    @SuppressWarnings("unchecked")//All instances are of type Object - guaranteed
    private void tryToSetupComparator(@NotNull T thiz, @NotNull T that) throws IllegalAccessException, InvocationTargetException {
        Field[] fields = thiz.getClass().getFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            if(Parser.isPrimitiveType(type) && Modifier.isPublic(field.getModifiers())){
                Object o = field.get(thiz);
                Object o2 = field.get(that);
                if(!o.equals(o2) || !o.toString().toUpperCase().equals(o2.toString())){
                    this.experimentalComparator = (Comparator<Object>) Parser.getComparatorForPrimitive(type);
                    this.fieldToutilize = field;
                    return;
                }
            }
        }

        Method[] methods = thiz.getClass().getMethods();
        for (Method method : methods) {
            Class<?> type = method.getReturnType();
            String name = method.getName();

            if(Parser.isPrimitiveType(type) && Modifier.isPublic(method.getModifiers()) &&
                    (name.startsWith("get") || name.equals(type.getName() + "Value")) &&
                    method.getParameters().length == 0){
                Object o = method.invoke(thiz);
                Object o2 = method.invoke(that);
                if(!o.equals(o2) || !compressDuplicates){
                    this.experimentalComparator = (Comparator<Object>) Parser.getComparatorForPrimitive(type);
                    this.methodToUse = method;
                    return;
                }
            }
        }
        //R.I.P
        throw new NotComparableError("Missing comparator or object is not comparable");
    }

    @SuppressWarnings("unchecked")//As this is guaranteed before this method
    private void setupComparableMethod(@NotNull T thiz) {
        try { method = ((Comparable<T>) thiz).getClass().getMethod("compareTo", thiz.getClass());
        } catch (NoSuchMethodException e) { e.printStackTrace(); }
    }
}
