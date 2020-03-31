package framework.utilities.data.structure;

import framework.utilities.data.structure.utils.IQueue;
import framework.utilities.data.structure.utils.MyQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class LevelOrderIterator<T> implements Iterator<Node<T>> {
    private final IQueue<Node<T>> q = new MyQueue<>();
    private Node<T> next;

    public LevelOrderIterator(Node<T> root) {
        this.next = root;
    }

    @Override
    public boolean hasNext() { return next != null; }

    @Override
    public Node<T> next() {
        if (!hasNext()) throw new NoSuchElementException();

        if(next.hasLeftChild()){q.enqueue(next.left);}
        if(next.hasRightChild()){q.enqueue(next.right);}

        Node<T> r = next;
        next = q.isEmpty() ? null : q.dequeue();
        return r;
    }
}
