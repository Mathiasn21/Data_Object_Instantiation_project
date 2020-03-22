package framework.utilities.data.structure;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T>
 */
public class AVLNode<T extends Comparable<T>> extends Node<T> {
    public int height;
    public AVLNode(T t, AVLNode<T> parent) { super(t, parent); }
}
