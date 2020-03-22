package framework.utilities.data.structure;

import java.util.Iterator;

public interface IBinaryTree<T extends Comparable<T>> {
    void insert(T t);
    Node<T> remove(T t);

    int getNumberOfNodesWithOneChild();
    int getNumberOfNodesWithTwoChild();
    int getNumberOfLeaves();

    boolean contains(T t);
    T search(T t);

    Iterator<Node<T>> inorderTraversal();
    Iterator<Node<T>> postorderTraversal();
    Iterator<Node<T>> preorderTraversal();
    Iterator<Node<T>> levelorderTraversal();

    IBinaryTree<T> copyToNewTree();
}
