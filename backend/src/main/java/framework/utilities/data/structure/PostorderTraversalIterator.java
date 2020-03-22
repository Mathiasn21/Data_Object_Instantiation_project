package framework.utilities.data.structure;

import structure.utils.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PostorderTraversalIterator<T extends Comparable<T>> implements Iterator<Node<T>> {
    private final Stack<Node<T>> s = new Stack<>();

    public PostorderTraversalIterator(Node<T> rootNode) { traverse(rootNode); }

    private void traverse(Node<T> node) {
        if(node == null){return;}
        s.push(node);
        traverse(node.getRight());
        traverse(node.getLeft());
    }

    @Override
    public boolean hasNext() { return !s.isEmpty(); }

    @Override
    public Node<T> next() {
        if (!hasNext()) throw new NoSuchElementException();
        return s.pop();
    }
}
