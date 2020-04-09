package unitTests;

import DOIFramework.utilities.collections.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing AVLTree
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public class AVLTreeTest {
    private final int[] numbers = {20,4,3,2,9,7,11,26,21,30};// size = 10
    @Test
    void creation_of_tree(){
        assertDoesNotThrow(() -> {
            ITree<Integer> tree = new AVLTree<>();
            assertTrue(tree.isEmpty());
        });
    }

    @Test
    void insertion_with_comparator_set(){
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
    void insertion_without_comparator_set(){
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

    @Test
    void insertion_of_node(){
        ITree<Integer> tree = genTree();
        assertFalse(tree.isEmpty());
        assertEquals(tree.size(), numbers.length);
    }

    @Test
    void removal_of_node(){
        ITree<Integer> tree = genTree();
        tree.remove(20);
        tree.remove(11);

        assertEquals(tree.size(), numbers.length - 2);
        assertFalse(tree.contains(20));
        assertFalse(tree.contains(11));
    }

    @NotNull
    private ITree<Integer> genTree(){
        ITree<Integer> tree = new AVLTree<>();
        for(int numbers : numbers){ tree.insert(numbers); }
        return tree;
    }

    @NotNull
    private int[] getArrInorder(@NotNull ITree<Integer> tree){
        int[] arr = new int[tree.size()];
        var iterator = tree.inorderTraversal();

        int i = 0;
        while(iterator.hasNext()){
            arr[i] = iterator.next().getT();
            i++;
        }
        return arr;
    }
}
