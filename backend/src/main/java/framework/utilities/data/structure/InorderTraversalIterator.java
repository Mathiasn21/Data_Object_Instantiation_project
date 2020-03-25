package framework.utilities.data.structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static framework.utilities.data.structure.QuickTraversals.getBottomLeftChild;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T>
 */
public class InorderTraversalIterator<T> implements Iterator<Node<T>> {
    private Node<T> next;

    public InorderTraversalIterator(Node<T> rootNode) {
        next = rootNode;
        next = next != null ? getBottomLeftChild(rootNode) : null;
    }
    @Override
    public boolean hasNext() {return next != null;}

    @Override
    public Node<T> next() {
        if (!hasNext()) throw new NoSuchElementException();
        Node<T> r = next;

        if (next.hasRightChild()) {
            next = getBottomLeftChild(next.getRight());
            return r;
        }

        while (true) {
            if (!next.hasParent()) {
                next = null;
                return r;
            }
            if (next.getParent().getLeft() == next) {
                next = next.getParent();
                return r;
            }
            next = next.getParent();
        }
    }
}
