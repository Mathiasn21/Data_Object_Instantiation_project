package framework.utilities.data.structure;

import structure.utils.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static structure.QuickTraversals.getBottomLeftChild;

public class InorderTraversalIterator<T extends Comparable<T>> implements Iterator<Node<T>> {
    private final Stack<Node<T>> s = new Stack<>();
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
