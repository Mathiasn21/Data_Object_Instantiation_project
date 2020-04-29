import doiframework.utilities.collections.ITree;
import doiframework.utilities.collections.Tree;

public class TreeMain {
    public static void main(String[] args) {
        int[] arr = {13, 4, 5, 14, 3, 6, 2, 3, 15, 7};
        ITree<Integer> tree = new Tree<>();
        for (int integer : arr) {
            tree.insert(integer);
        }

    }
}
