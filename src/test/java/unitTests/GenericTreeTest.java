package unitTests;

import framework.utilities.collections.ITree;
import framework.utilities.collections.Node;
import framework.utilities.collections.Tree;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class GenericTreeTest {
    @Test
    void insertion_with_comparator_set_using_Tree(){
        int[] res = {2, 3, 4, 5, 6, 7, 13, 14, 15};
        int[] arr = {13, 4, 5, 14, 3, 6, 2, 3, 15, 7};

        ITree<Integer> tree = new Tree<>(Integer::compareTo, true);
        for(int integer : arr) { tree.insert(integer); }

        Iterator<Node<Integer>> iterator = tree.inorderTraversal();
        assertDoesNotThrow(() -> {
            for(int integer : res){ assertEquals(integer, (int) iterator.next().getT()); }
            assertFalse(iterator.hasNext());
        });
    }

    @Test
    void insertion_without_comparator_set_using_Tree(){
        int[] res = {2, 3, 3, 4, 5, 6, 7, 13, 14, 15};
        int[] arr = {13, 4, 5, 14, 3, 6, 2, 3, 15, 7};

        ITree<Integer> tree = new Tree<>();
        for(int integer : arr) { tree.insert(integer); }
        Iterator<Node<Integer>> iterator = tree.inorderTraversal();

        assertDoesNotThrow(() -> {
            for(int integer : res){ assertEquals(integer, (int) iterator.next().getT()); }
            assertFalse(iterator.hasNext());
        });
    }
}
