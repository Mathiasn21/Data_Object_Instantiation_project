package framework.utilities.data.structure;

public class RBNode<T extends Comparable<T>> extends Node<T> implements Cloneable {
    public boolean color = false;

    RBNode(T t, RBNode<T> parent) {
        super(t, parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        RBNode<T> t = null;
        try {
            t = (RBNode<T>) super.clone();
            t.color = this.color;
        } catch (CloneNotSupportedException e) { e.printStackTrace(); }
        return t;
    }
}