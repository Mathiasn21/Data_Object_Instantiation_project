package framework.utilities.data.structure;

import java.util.Objects;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class Node<T> implements Cloneable{
    protected int tCounter = 1;
    protected T t;
    protected Node<T> left, right, parent;

    public Node(T t, Node<T> parent) {
        this.t = t;
        left = null; right = null; this.parent = parent;
    }

    public T getT() { return t; }

    protected boolean hasLeftChild(){ return left != null;}
    protected boolean hasRightChild(){ return right != null;}
    protected boolean hasParent(){ return parent != null;}
    protected boolean isLeaf(){ return left == null && right == null;}

    protected void setLeftChild(Node<T> node) { left = node; }
    protected void setRightChild(Node<T> node) { right = node; }

    protected Node<T> getLeft() { return left; }
    protected Node<T> getRight() { return right; }
    protected Node<T> getParent() { return parent; }
    protected Node<T> getLonelyChild(){ return left == null ? right : left; }

    protected int getNumOfChildren(){
        short num = 0;
        if(hasLeftChild()){num++;}
        if(hasRightChild()){num++;}
        return num;
    }

    public int getTCounter(){ return tCounter; }

    @Override
    public String toString() { return t.toString(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node<?> node = (Node<?>) o;
        if (!Objects.equals(t, node.t)) return false;
        if (!Objects.equals(left, node.left)) return false;
        if (!Objects.equals(right, node.right)) return false;
        return Objects.equals(parent, node.parent);
    }

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
