package framework.utilities.data.structure;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class AVLNode<T> extends Node<T> {
    int height;
    AVLNode(T t, AVLNode<T> parent) { super(t, parent); }
}
