package DOIFramework.utilities.collections;

import java.util.Iterator;

/** Class for handling of generic graph
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class Graph<T> {
    public void insert(int uid, T number, int ...connections) {
        GraphNode<T> node = new GraphNode<>(uid, number, connections);

    }

    public boolean contains(T data) {
        return false;
    }
    public boolean contains(int uid) {
        return false;
    }

    public int size() {
        return 0;
    }

    public void remove(T t) {
    }

    public int numConnections(int uid) {
        return 0;
    }

    public int numConnections(T t) {
        return 0;
    }

    public Iterator<GraphNode> DFS() {
        return null;
    }

    public int[] getConnections(int i) {
        return new int[0];
    }

    public T[] getConnections(T t) {
        return null;
    }

    public T get(T inf102) {
        return null;
    }


//TODO: Implement generic graph and its functions
}
