package DOIFramework.utilities.collections;

import DOIFramework.utilities.collections.utils.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @param <T> T
 */
final class PreorderTraversalIterator<T> implements Iterator<Node<T>> {
    private final Stack<Node<T>> s = new Stack<>();

    PreorderTraversalIterator(Node<T> rootNode) { traverse(rootNode); }

    private void traverse(Node<T> node) {
        if(node == null){return;}
        traverse(node.getRight());
        traverse(node.getLeft());
        s.push(node);
    }

    @Override
    public final boolean hasNext() { return !s.isEmpty(); }

    @Override
    public final Node<T> next() {
        if (!hasNext()) throw new NoSuchElementException();
        return s.pop();
    }
}
