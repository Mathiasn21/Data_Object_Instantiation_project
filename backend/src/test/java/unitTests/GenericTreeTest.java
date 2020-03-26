package unitTests;

import framework.utilities.data.structure.ITree;
import framework.utilities.data.structure.Node;
import framework.utilities.data.structure.Tree;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

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

        ITree<Integer> tree = new Tree<>(false);
        for(int integer : arr) { tree.insert(integer); }
        Iterator<Node<Integer>> iterator = tree.inorderTraversal();

        assertDoesNotThrow(() -> {
            for(int integer : res){ assertEquals(integer, (int) iterator.next().getT()); }
            assertFalse(iterator.hasNext());
        });
    }
}
