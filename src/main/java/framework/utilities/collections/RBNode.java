package framework.utilities.collections;


import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class RBNode<T> extends Node<T> implements Cloneable {
    public boolean color = false;

    RBNode(T t, RBNode<T> parent) {
        super(t, parent);
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public final Object clone() {
        RBNode<T> t = null;
        try {
            t = (RBNode<T>) super.clone();
            t.color = this.color;
        } catch (CloneNotSupportedException e) { e.printStackTrace(); }
        return t;
    }
}