package doiframework.utilities.collections;

/**
 * A tree utility class for traversing to either bottom of given node.
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
final class QuickTraversals{
    protected static <T> Node<T> getBottomLeftChild(Node<T> node){
        var res = node;
        while(res.hasLeftChild()){ res = res.getLeft(); }
        return res;
    }

    protected static <T> Node<T> getBottomRightChild(Node<T> node){
        var res = node;
        while(res.hasRightChild()){ res = res.getRight(); }
        return res;
    }
}
