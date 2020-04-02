package unitTests;

import framework.utilities.data.structure.AVLTree;
import framework.utilities.data.structure.ITree;
import framework.utilities.data.structure.Node;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing AVLTree
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @version 1.0.0
 */
public class AVLTreeTest {
    ITree<Integer> tree = new AVLTree<>(), tree2 = new AVLTree<>();
    int[] fibonacci = {1, 1, 2, 3, 5, 8, 13, 21, 34}, scrambledFibonacci = {34, 1, 5, 1, 2, 13, 8, 3, 21}, fibonacciEightRemoved = {1, 1, 2, 3, 5, 13, 21, 34};

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

        System.out.println("Insertion into AVLTree is possible");
    }

    @Test
    void removal_of_node(){
        assertEquals(0, tree.size()); //makes sure tree is empty beforehand

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

        System.out.println("Removal of node in AVLTree is possible");
    }
}
