package framework.utilities.data.structure;


import framework.utilities.data.structure.utils.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
public class PostorderTraversalIterator<T> implements Iterator<Node<T>> {
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
