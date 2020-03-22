package framework.utilities.data.structure;

class QuickTraversals{
    protected static <T extends Comparable<T>> Node<T> getBottomLeftChild(Node<T> node){
        var res = node;
        while(res.hasLeftChild()){ res = res.getLeft(); }
        return res;
    }

    protected static <T extends Comparable<T>> Node<T> getBottomRightChild(Node<T> node){
        var res = node;
        while(res.hasRightChild()){ res = res.getRight(); }
        return res;
    }

    protected static <T extends Comparable<T>> Node<T> getTopParent(Node<T> node){
        var res = node;
        while(res.hasParent()){ res = res.getParent(); }
        return res;
    }
}
