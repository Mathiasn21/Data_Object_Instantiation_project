package unitTests;

import DOIFramework.utilities.collections.*;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing RBTree and traversal of RBTree
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0.0
 */
public class RBTreeTest {
    ITree<Integer> tree = new RBTree<>(), tree2 = new RBTree<>();
    int[] fibonacci = {1, 1, 2, 3, 5, 8, 13, 21, 34}, scrambledFibonacci = {34, 1, 5, 1, 2, 13, 8, 3, 21};

    @Test
    void insertion_of_node(){
        assertEquals(0, tree.size()); //makes sure tree is empty beforehand

        for(int numbers : scrambledFibonacci){
            tree.insert(numbers);
        }

        Iterator<Node<Integer>> iterator = tree.inorderTraversal();

        for(int numbers : fibonacci){
            assertEquals(numbers, iterator.next().getT());
        }

        for(int numbers : scrambledFibonacci){
            tree.insert(numbers);
        }

        for(int numbers : fibonacci){
            assertEquals(numbers, iterator.next().getT());
        }
    }

    @Test
    void removal_of_node(){
        assertEquals(0, tree.size()); //makes sure tree is empty beforehand
        int[] fibonacciEightRemoved = {1, 1, 2, 3, 5, 13, 21, 34};

        for(int numbers : scrambledFibonacci){
            tree.insert(numbers);
            tree2.insert(numbers);
        }

        Iterator<Node<Integer>> iterator1 = tree.inorderTraversal(), iterator2 = tree2.inorderTraversal();

        for(int numbers : fibonacci){
            assertEquals(numbers, iterator1.next().getT()); //makes sure tree is filled with fibonacci numbers
        }

        tree2.remove(8);

        for(int numbers : fibonacciEightRemoved){
            assertEquals(numbers, iterator2.next().getT()); //makes sure tree has node removed
        }
    }

    @Test
    void levelorder_traversal(){
        assertEquals(0, tree.size()); //makes sure tree is empty beforehand
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
        assertEquals(0, tree.size()); //makes sure tree is empty beforehand
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
        assertEquals(0, tree.size()); //makes sure tree is empty beforehand
        int[] fibonacciPreordered = {5, 1, 1, 2, 3, 13, 8, 34, 21};

        for(int numbers : scrambledFibonacci) {
            tree.insert(numbers);
        }

        Iterator<Node<Integer>> iterator = tree.preorderTraversal();

        for(int numbers : fibonacciPreordered){
            assertEquals(numbers, iterator.next().getT());
        }
    }
}
