package framework.utilities.collections;

/**
 * A tree utility class for ease of traversals
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
final class QuickTraversals{
    protected final static <T> Node<T> getBottomLeftChild(Node<T> node){
        var res = node;
        while(res.hasLeftChild()){ res = res.getLeft(); }
        return res;
    }

    protected final static <T> Node<T> getBottomRightChild(Node<T> node){
        var res = node;
        while(res.hasRightChild()){ res = res.getRight(); }
        return res;
    }
}
