package framework.utilities.data.structure;


import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class Node<T> implements Cloneable{
    protected int tCounter = 1;
    protected T t;
    protected Node<T> left, right, parent;

    Node(T t, Node<T> parent) {
        this.t = t;
        left = null; right = null; this.parent = parent;
    }

    public final T getT() { return t; }

    protected final boolean hasLeftChild(){ return left != null;}
    protected final boolean hasRightChild(){ return right != null;}
    protected final boolean hasParent(){ return parent != null;}
    protected final boolean isLeaf(){ return left == null && right == null;}

    protected final void setLeftChild(Node<T> node) { left = node; }
    protected final void setRightChild(Node<T> node) { right = node; }

    protected final Node<T> getLeft() { return left; }
    protected final Node<T> getRight() { return right; }
    protected final Node<T> getParent() { return parent; }
    protected final Node<T> getLonelyChild(){ return left == null ? right : left; }

    protected final int getNumOfChildren(){
        short num = 0;
        if(hasLeftChild()){num++;}
        if(hasRightChild()){num++;}
        return num;
    }

    public final int getTCounter(){ return tCounter; }

    @Override
    public String toString() { return t.toString(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node<?> node = (Node<?>) o;
        if (!t.equals(node.t)) return false;
        if (left != node.left) return false;
        if (right != node.right) return false;
        return parent == node.parent;
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public Object clone() throws CloneNotSupportedException {
        Node<T> t = (Node<T>) super.clone();

        t.parent = this.parent;
        t.left = this.left;
        t.right = this.right;
        t.tCounter = this.tCounter;
        t.t = this.t;
        return t;
    }
}
