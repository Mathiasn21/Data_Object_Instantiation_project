package framework.utilities.collections;

import framework.utilities.collections.utils.IQueue;
import framework.utilities.collections.utils.MyQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public final class LevelOrderIterator<T> implements Iterator<Node<T>> {
    private final IQueue<Node<T>> q = new MyQueue<>();
    private Node<T> next;

    LevelOrderIterator(Node<T> root) {
        this.next = root;
    }

    @Override
    public final boolean hasNext() { return next != null; }

    @Override
    public final Node<T> next() {
        if (!hasNext()) throw new NoSuchElementException();

        if(next.hasLeftChild()){q.enqueue(next.left);}
        if(next.hasRightChild()){q.enqueue(next.right);}

        Node<T> r = next;
        next = q.isEmpty() ? null : q.dequeue();
        return r;
    }
}
