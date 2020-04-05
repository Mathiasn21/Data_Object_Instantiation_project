package framework.utilities.collections;


  ///////////////////////////////////////////////
 //             Import statements             //
///////////////////////////////////////////////
import framework.errors.NotComparableError;
import framework.utilities.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import static framework.utilities.collections.QuickTraversals.getBottomLeftChild;

/**
 * A simple binary search tree and
 * By default root is null, comparator is null and it does not compress
 * duplicates, as this option is set to false.
 * The compression will only count objects that gets a compare result = 0,
 * and are equal in terms of using the object method equal.
 *
 * If no comparator is specified it will try to find an appropriate one.
 * It will start by finding a primitive public field and using corresponding
 * primitive comparator. If no appropriate fields are found it will will then use a
 * public method that does return a primitive variable.
 *
 * This functionality is easily overridden either by sending in a custom comparator
 * or by implementing Comparable.
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class Tree<T> implements ITree<T> {
    private Comparator<T> comparator;
    private final boolean compressDuplicates;
    private Node<T> rootNode;
    private Comparator<Object> experimentalComparator;
    private Field fieldToUtilize = null;
    private Method method = null;
    private Method methodToUse = null;

    /**
     * The default constructor sets comparator to null.
     */
    @Contract(pure = true)
    public Tree() { this(null); }

    /**Toggles compression.
     * @param compressDuplicates {@link Comparator}&lt;{@link T}&gt;
     */
    @Contract(pure = true)
    public Tree(boolean compressDuplicates) { this(null, compressDuplicates); }

    /**Sets a custom made comparator.
     * @param comparator {@link Comparator}&lt;{@link T}&gt;
     */
    public Tree(Comparator<T> comparator) { this(comparator, false); }

    /**Sets custom comparator and toggles compression.
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
    /**Returns root node of this tree.
     * @return {@link Node}&lt;{@link T}&gt;
     */
    @Contract(pure = true)
    final Node<T> getRootNode() { return rootNode; }

    /**
     * @return int
     */
    @Override
    public int size() { return 0; }

    /**
     * @return int
     */
    @Override
    public final int getNumberOfLeaves() { return getNumberOfLeaves(rootNode); }

    /**
     * @param root {@link Node}&lt;{@link T}&gt;
     * @return int
     */
    private int getNumberOfLeaves(Node<T> root) {
        if(root == null){ return 0; }
        if(!(root.hasLeftChild() || root.hasRightChild())){ return 1; }
        return getNumberOfLeaves(root.getLeft()) + getNumberOfLeaves(root.getRight());
    }

    /**
     * @return int
     */
    @Override
    public final int getNumberOfNodesWithOneChild(){
        return getNumberOfNodesWithOneChild(rootNode);
    }

    /**
     * @param root {@link Node}&lt;{@link T}&gt;
     * @return int
     */
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

    /**
     * @return int
     */
    @Override
    public final int getNumberOfNodesWithTwoChild(){
        return getNumberOfNodesWithTwoChild(rootNode);
    }

    /**
     * @param root {@link Node}&lt;{@link T}&gt;
     * @return int
     */
    private int getNumberOfNodesWithTwoChild(Node<T> root) {
        if(root == null){return  0;}
        int sum = 0;
        if(root.hasRightChild() && root.hasLeftChild()){ sum++; }
        return sum + (getNumberOfNodesWithOneChild(root.getLeft()) + getNumberOfNodesWithOneChild(root.getRight()));
    }


      ///////////////////////////////////////////////
     //              SETTERS                      //
    ///////////////////////////////////////////////
    /**
     * @param data {@link T}
     */
    @Override
    public void insert(T data) {
        Node<T> newNode = new Node<>(data, null);
        insert(rootNode, newNode);
    }

    /**
     * @param thiz {@link Node}&lt;{@link T}&gt;
     * @param that {@link Node}&lt;{@link T}&gt;
     */
    protected final void insert(Node<T> thiz, Node<T> that) {
        if(rootNode == null){
            rootNode = that;
            return;
        }
        int compareRes = compare(thiz.t, that.t);


        //If object exists and equals then increment counter
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

        }else if (compare){ insert(thiz.getLeft(), that);
        }else { insert(thiz.getRight(), that); }
    }

    /**
     * @param root {@link Node}&lt;{@link T}&gt;
     */
    protected final void setRootNode(Node<T> root) { rootNode = root; }


      ///////////////////////////////////////////////
     //               ITERATORS                   //
    ///////////////////////////////////////////////

    /**
     * @return {@link Iterator}&lt;{@link Node}&lt;{@link T}&gt;&gt;
     */
    @NotNull
    @Contract(" -> new")
    @Override
    public final Iterator<Node<T>> inorderTraversal() { return new InorderTraversalIterator<>(rootNode); }

    /**
     * @return {@link Iterator}&lt;{@link Node}&lt;{@link T}&gt;&gt;
     */
    @NotNull
    @Contract(" -> new")
    @Override
    public final Iterator<Node<T>> postorderTraversal() { return new PostorderTraversalIterator<>(rootNode); }

    /**
     * @return {@link Iterator}&lt;{@link Node}&lt;{@link T}&gt;&gt;
     */
    @NotNull
    @Contract(" -> new")
    @Override
    public final Iterator<Node<T>> preorderTraversal() { return new PreorderTraversalIterator<>(rootNode); }

    /**
     * @return {@link Iterator}&lt;{@link Node}&lt;{@link T}&gt;&gt;
     */
    @NotNull
    @Contract(" -> new")
    @Override
    public final Iterator<Node<T>> levelorderTraversal() { return new LevelOrderIterator<>(rootNode); }


      ///////////////////////////////////////////////
     //              Utility Methods              //
    ///////////////////////////////////////////////
    /**
     * @param t {@link T}
     * @return boolean
     */
    @Override
    public final boolean contains(T t) { return search(t) == null; }

    /**
     * Returns T or null if none was found
     * @param t {@link T}
     * @return T {@link T}
     */
    @Nullable
    @Override
    public final T search(T t) {
        Node<T> res = rootNode;
        while(res != null){
            if(!(res.hasLeftChild() || res.hasRightChild())){ return null; }
            int compare = compare(t, res.t);
            if(compare == 0){ return res.t; }
            res = compare > 0 ? res.getRight() : res.getLeft();
        }
        return null;
    }

    /**
     * @param t {@link T}
     * @return {@link Node}&lt;{@link T}&gt;
     */
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
     * @return {@link Node}&lt;{@link T}&gt;
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

    /**Copies all the data from one tree, to a new tree, and returns new tree.
     * @return {@link ITree}&lt;{@link T}&gt;
     */
    @NotNull
    @Override
    public final ITree<T> copyToNewTree(){
        Tree<T> tree = new Tree<>(this.comparator);
        if (rootNode != null) {
            tree.insert(rootNode.t);
            appendNodesToCopy(rootNode, tree.getRootNode());
        }
        return tree;
    }

    /**
     * @param node {@link Node}&lt;{@link T}&gt;
     * @param copy {@link Node}&lt;{@link T}&gt;
     */
    private void appendNodesToCopy(@NotNull Node<T> node, @NotNull Node<T> copy){
        if(node.hasLeftChild()){ copy.setLeftChild(node.getLeft()); }
        if(node.hasRightChild()){ copy.setRightChild(node.getRight()); }
    }

    /**
     * Compares this with that
     * @param thiz {@link T}
     * @param that {@link T}
     * @return int
     */
    protected final int compare(@NotNull T thiz, @NotNull T that){
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
                if(fieldToUtilize != null){
                    return experimentalComparator.compare( fieldToUtilize.get(thiz), fieldToUtilize.get(that));
                }
                return experimentalComparator.compare(methodToUse.invoke(thiz), methodToUse.invoke(that));
            }
        } catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
        throw new Error();
    }

    /**
     * @param thiz {@link T}
     * @param that {@link T}
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     */
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
                    this.fieldToUtilize = field;
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

    /**
     * @param thiz {@link T}
     */
    @SuppressWarnings("unchecked")//As this is guaranteed before this method
    private void setupComparableMethod(@NotNull T thiz) {
        try { method = ((Comparable<T>) thiz).getClass().getMethod("compareTo", thiz.getClass());
        } catch (NoSuchMethodException e) { e.printStackTrace(); }
    }
}
