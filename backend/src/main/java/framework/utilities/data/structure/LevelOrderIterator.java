package framework.utilities.data.structure;

import structure.utils.IQueue;
import structure.utils.MyQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LevelOrderIterator<T extends Comparable<T>> implements Iterator<Node<T>> {
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
