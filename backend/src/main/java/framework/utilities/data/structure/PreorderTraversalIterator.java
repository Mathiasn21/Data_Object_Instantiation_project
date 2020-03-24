package framework.utilities.data.structure;

import framework.utilities.data.structure.utils.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PreorderTraversalIterator<T extends Comparable<T>> implements Iterator<Node<T>> {
    private final Stack<Node<T>> s = new Stack<>();

    public PreorderTraversalIterator(Node<T> rootNode) { traverse(rootNode); }

    private void traverse(Node<T> node) {
        if(node == null){return;}
        traverse(node.getRight());
        traverse(node.getLeft());
        s.push(node);
    }
    @Override
    public boolean hasNext() { return !s.isEmpty(); }

    @Override
    public Node<T> next() {
        if (!hasNext()) throw new NoSuchElementException();
        return s.pop();
    }
}
