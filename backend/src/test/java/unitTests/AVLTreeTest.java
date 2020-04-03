package unitTests;

import framework.utilities.data.structure.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing AVLTree
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0.0
 */
public class AVLTreeTest {
    private final int[] fibonacci = {1, 1, 2, 3, 5, 8, 13, 21, 34}, scrambledFibonacci = {34, 1, 5, 1, 2, 13, 8, 3, 21}, fibonacciEightRemoved = {1, 1, 2, 3, 5, 13, 21, 34};

    /*
    checklist for testing tree:
    * Same for Removing.
    *
    * */

    //TODO: IMPLEMENT TEST
    @Test
    void creation_of_tree(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void can_insert_first_value(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void can_insert_bigger_value(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void can_insert_smaller_value(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void insertion_causes_LL_rotation(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void insertion_causes_LR_rotation(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void insertion_causes_RL_rotation(){

    }

    //TODO: IMPLEMENT TEST
    @Test
    void insertion_causes_RR_rotation(){

    }

    @Test
    void insertion_of_node(){
        ITree<Integer> tree = genTree();
        Iterator<Node<Integer>> iterator = tree.inorderTraversal();

        for(int numbers : fibonacci){
            assertEquals(numbers, iterator.next().getT());
        }
        assertEquals(tree.size(), fibonacci.length);
    }

    @Test
    void removal_of_node(){
        var tree = genTree();
        for(int numbers : scrambledFibonacci){
            tree.insert(numbers);
        }
        tree.remove(8);
        assertFalse(tree.contains(8));
    }

    @Test
    void levelorder_traversal(){
        var tree = genTree();
        int[] fibonacciLevelordered = {5, 1, 13, 1, 2, 8, 34, 3, 21};

        for(int numbers : scrambledFibonacci) {
            tree.insert(numbers);
        }

        Iterator<Node<Integer>> iterator = tree.levelorderTraversal();
        for(int numbers : fibonacciLevelordered){
            assertEquals(numbers, iterator.next().getT());
        }
    }

    @Test
    void postorder_traversal(){
        var tree = genTree();
        int[] fibonacciPostordered = {1, 3, 2, 1, 8, 21, 34, 13, 5};

        for(int numbers : scrambledFibonacci) {
            tree.insert(numbers);
        }

        Iterator<Node<Integer>> iterator = tree.postorderTraversal();
        for(int numbers : fibonacciPostordered){
            assertEquals(numbers, iterator.next().getT());
        }
    }

    @Test
    void preorder_traversal(){
        var tree = genTree();
        int[] fibonacciPreordered = {5, 1, 1, 2, 3, 13, 8, 34, 21};

        for(int numbers : scrambledFibonacci) {
            tree.insert(numbers);
        }

        Iterator<Node<Integer>> iterator = tree.preorderTraversal();
        for(int numbers : fibonacciPreordered){
            assertEquals(numbers, iterator.next().getT());
        }
    }

    @NotNull
    private ITree<Integer> genTree(){
        ITree<Integer> tree = new AVLTree<>();
        for(int numbers : scrambledFibonacci){ tree.insert(numbers); }
        return tree;
    }
}
