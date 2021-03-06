package doiframework.utilities.collections;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static doiframework.utilities.collections.QuickTraversals.getBottomLeftChild;

/**
 * A red black tree has the following rules:
 * -Each node is either red or black.
 * -The root is black. This rule is sometimes omitted.
 * -All leaves (NIL) are black.
 * -If a node is red, then both its children are black.
 * -Every path from a execute node, to any of its descendant NIL nodes, goes through the same number of black nodes.
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class RBTree<T> extends Tree<T> {
    public RBTree(Comparator<T> comparator) { super(comparator); }
    public RBTree(Comparator<T> comparator, boolean compression) { super(comparator, compression); }
    public RBTree() { super(); }


      ///////////////////////////////////////////////
     //                 SETTERS                   //
    ///////////////////////////////////////////////
    @Override
    public final void insert(T data) {
        RBNode<T> node = new RBNode<>(data, null);
        insert(getRootNode(), node);//Calls super method

        //No need to re balance - as node is root
        if(node.parent == null){
            node.color = false;
            return;
        }
        node.color = true;
        reBalanceAfterInsertion(node);
        ((RBNode<T>) getRootNode()).color = false;//Set root black
        size++;
    }

    /**Re balances the tree after a insertion
     * @param node RBNode&lt;T&gt;
     */
    private void reBalanceAfterInsertion(@NotNull RBNode<T> node){
        RBNode<T> parent = (RBNode<T>) node.parent;
        RBNode<T> parentSibling = getSiblingFrom(parent);
        RBNode<T> grandParent = getGrandparentFrom(node);

        if(!parent.color){ return; }

        //If it is red then recolor
        if(isRed(parentSibling)){
            recolor(parent, false);
            recolor(parentSibling, false);
            recolor(grandParent, true);

            if(grandParent != getRootNode() && grandParent != null){
                grandParent.color = true;
                reBalanceAfterInsertion(grandParent);
            }

        } else if(parent == getLeftOf(grandParent)){
            if(node == getRightOf(parent)){
                rotateLeft((RBNode<T>) grandParent.left);
                recolor(node, true);
            }
            recolor(parent, false);
            recolor(grandParent, true);

            rotateRight(grandParent);

        } else if(parent == getRightOf(grandParent)){
            if(node == getLeftOf(parent)){
                rotateRight((RBNode<T>) grandParent.right);
                recolor(node, true);
            }
            recolor(parent, true);
            recolor(grandParent, true);

            rotateLeft(grandParent);
        }
    }


    ///////////////////////////////////////////////
    //              Utility Methods              //
    ///////////////////////////////////////////////
    /**The actual deletion algorithm is found here:
     * https://github.com/alenachang/red-black-deletion-steps/blob/master/RedBlackDeletionSteps.pdf
     * @param t T
     * @return Node or null if none was removed
     */
    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public final Node<T> remove(T t) {
        if (t == null){ return null; }
        RBNode<T> node = (RBNode<T>) searchNode(t);
        if(node == null){ return null; }
        RBNode<T> orgNode = (RBNode<T>) node.clone();
        boolean isRoot = getRootNode().equals(node);

        Map<String, RBNode<T>> res = binaryRemoval(node, isRoot);
        RBNode<T> x = res.get("x");
        RBNode<T> replacement = res.get("replacement");
        RBNode<T> parent = res.get("xParent");

        //Initiate recovery from removal
        if(orgNode.color){
            if(isRedOrNil(replacement)){
                return orgNode;
            }
            node.color = true;
        }else{
            if(isRed(replacement)){
                replacement.color = false;
                return orgNode;
            }
        }

        RBNode<T> w = x == null ? (RBNode<T>) parent.getLonelyChild() : getSiblingFrom(x);
        pickRemovalCase(x, w, parent);
        size--;
        return orgNode;
    }

    /**
     * @param node RBNode&lt;T&gt;
     * @param isRoot boolean
     * @return Map&lt;String, RBNode&lt;T&gt;&gt;
     */
    @NotNull
    private Map<String, RBNode<T>> binaryRemoval(RBNode<T> node, boolean isRoot) {
        Map<String, RBNode<T>> results = new HashMap<>();

        RBNode<T> parent;
        RBNode<T> replacement = null, x = null, xParent = getParentFrom(node);

        //Case 1: Node is a leaf
        if(node.isLeaf()){
            parent = getParentFrom(node);

            if(isRoot){ setRootNode(null);
            } else if (getLeftOf(parent) == node) { parent.left = null;
            } else { parent.right = null; }

            //Case 2: Node has a single subtree
        }else if(node.getNumOfChildren() == 1){
            parent = getParentFrom(node);
            replacement = (RBNode<T>) node.getLonelyChild();
            replacement.parent = parent;

            if(isRoot){
                setRootNode(replacement);
                replacement.parent = null;
            } else if (getLeftOf(parent) == node) { parent.left = replacement;
            } else { parent.right = replacement; }
            x = replacement;
            xParent = getParentFrom(x);

            //Case 3: Node has two subtrees
        }else if(node.getNumOfChildren() == 2){
            replacement = (RBNode<T>) getBottomLeftChild(node.right);
            x = getRightOf(replacement);
            parent = getParentFrom(replacement);
            replace(node, replacement);
            node.color = replacement.color;

            //Updates the references
            updateReferences(node, replacement, parent);
            replacement = node;
            xParent = parent;
        }
        results.put("x", x);
        results.put("replacement", replacement);
        results.put("xParent", xParent);

        return results;
    }

    private void pickRemovalCase(RBNode<T> x, RBNode<T> w, RBNode<T> parent) {
        //Case 0
        if(isRed(x)) {
            removalCase0(x);

        //Case 1
        }else if(isBlack(x) && isRed(w)){
            removalCase1(x, w, parent, parent.left == x);

        //Case 2
        }else if(isBlack(x) && isBlack(w) && isBlack(getLeftOf(w)) && isBlack(getRightOf(w))){
            removalCase2(w, parent);
        }else if(isBlack(x) && isBlack(w)){

            //Case 3
            if(parent.left == x && isRed(getLeftOf(w)) && isBlack(getRightOf(w))){
                removalCase3(w, parent, true);
            }else if(parent.right == x && isRed(getRightOf(w)) && isBlack(getLeftOf(w))){
                removalCase3(w, parent, false);
            }

            //Case 4
            if(parent.left == x && isRed(getRightOf(w))){
                removalCase4(w, parent, true);
            }else if(parent.right == x && isRed(getLeftOf(w))){
                removalCase4(w, parent, false);
            }
        }
    }

    private void removalCase0(@NotNull RBNode<T> x) { x.color = false; }

    private void removalCase1(RBNode<T> x, @NotNull RBNode<T> w, @NotNull RBNode<T> xParent, boolean xIsLeft) {
        //step 1 - color w black
        //step 2 - color x parent red
        w.color = false;
        xParent.color = true;

        //step 3 - perform a rotation
        //step 4 - alter w
        if(xIsLeft){
            rotateLeft(xParent);
            w = getRightOf(xParent);
        } else{
            rotateRight(xParent);
            w = getLeftOf(xParent);
        }
        pickRemovalCase(x, w, xParent);
    }

    private void removalCase2(@NotNull RBNode<T> w, RBNode<T> xParent) {
        w.color = true;
        if(isRed(xParent)){
            xParent.color = false;
            return;
        }
        pickRemovalCase(xParent, w, xParent);
    }

    private void removalCase3(@NotNull RBNode<T> w, RBNode<T> xParent, boolean xIsLeft) {
        w.color = true;
        if(xIsLeft){
            getLeftOf(w).color = false;
            rotateRight(w);
            w = getRightOf(xParent);

        }else{
            getRightOf(w).color = false;
            rotateLeft(w);
            w = getLeftOf(xParent);
        }
        removalCase4(w, xParent, xIsLeft);
    }

    private void removalCase4(@NotNull RBNode<T> w, @NotNull RBNode<T> xParent, boolean xIsLeft) {
        w.color = xParent.color;
        xParent.color = false;

        if(xIsLeft){
            getRightOf(w).color = false;
            rotateLeft(xParent);
        }else {
            getLeftOf(w).color = false;
            rotateRight(xParent);
        }
    }


    /**Rotate right, execute root node from subtree
     * @param root RBNode&lt;T&gt;
     */
    private void rotateRight(RBNode<T> root){
        if(root == null){ return; }//pivot is root...

        RBNode<T> pivot = (RBNode<T>) root.left;
        RBNode<T> pRightChild = (RBNode<T>) pivot.right;
        RBNode<T> pGrandParent = (RBNode<T>) root.parent;

        root.left = pRightChild;
        root.parent = pivot;
        pivot.right = root;
        updateParentalReferences(root, pivot, pRightChild, pGrandParent);
    }

    /**Rotate right, execute root node from subtree
     * @param root RBNode&lt;T&gt;
     */
    private void rotateLeft(RBNode<T> root){
        if(root == null){ return; }//pivot is root...

        RBNode<T> pivot = (RBNode<T>) root.right;
        RBNode<T> pLeftChild = (RBNode<T>) pivot.left;
        RBNode<T> pGrandParent = (RBNode<T>) root.parent;

        root.right = pLeftChild;
        root.parent = pivot;
        pivot.left = root;
        updateParentalReferences(root, pivot, pLeftChild, pGrandParent);
    }

    /**Replaces vital resource from this node with that node...
     * @param thiz RBNode&lt;T&gt;
     * @param that RBNode&lt;T&gt;
     */
    private void replace(@NotNull RBNode<T> thiz, @NotNull RBNode<T> that) {
        thiz.tCounter = that.tCounter;
        thiz.t = that.t;
    }


      ///////////////////////////////////////////////
     //              Helper Methods               //
    ///////////////////////////////////////////////
    //All of these really cut down on NP checking
    private RBNode<T> getParentFrom(RBNode<T> node) {
        return node != null && node.hasParent() ? (RBNode<T>) node.parent : null;
    }

    private RBNode<T> getSiblingFrom(RBNode<T> node) {
        if(node == null){ return null; }
        RBNode<T> parent = (RBNode<T>) node.parent;
        if(parent == null){ return null; }
        return parent.getNumOfChildren() == 2 ? (RBNode<T>) (parent.left.equals(node) ? parent.right : parent.left) : null;
    }

    @Nullable
    @Contract(pure = true)
    private RBNode<T> getGrandparentFrom(@NotNull RBNode<T> node){
        return node.parent != null ? (RBNode<T>) node.parent.parent : null;
    }

    private RBNode<T> getLeftOf(RBNode<T> parent){
        return parent != null && parent.hasLeftChild() ? (RBNode<T>) parent.left : null;
    }

    private RBNode<T> getRightOf(RBNode<T> parent){
        return  parent != null && parent.hasRightChild() ? (RBNode<T>) parent.right : null;
    }

    private boolean isRed(RBNode<T> node){
        return node != null && node.color;
    }

    private boolean isBlack(RBNode<T> node){
        return node == null || !node.color;
    }


    private boolean isRedOrNil(RBNode<T> node) {
        return node == null || node.color;
    }

    private void recolor(RBNode<T> node, boolean color){
        if(node == null){return;}
        node.color = color;
    }

    @Override
    public String toString() {
        return inorderTraversal().toString();
    }
}
