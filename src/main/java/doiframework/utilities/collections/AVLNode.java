package doiframework.utilities.collections;

/** Node utilized in the {@link AVLTree}.
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class AVLNode<T> extends Node<T> {
    protected int height;

    AVLNode(T t, AVLNode<T> parent) {
        super(t, parent);
    }
}
