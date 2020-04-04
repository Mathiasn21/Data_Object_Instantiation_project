package framework.utilities.data.structure;


/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class RBNode<T> extends Node<T> implements Cloneable {
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