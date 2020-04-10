package DOIFramework.utilities.collections;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** Class for handling of generic graph
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class Graph<T> {
    List<GraphNode<T>> nodes;

    public void insert(int uid, T number, int... connections) {
        nodes.add(uid, new GraphNode<T>(number,connections));
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

    public int[] getConnections() {
        return node.getConnections();
    }

    public T[] getConnections(T t) {
        return null;
    }

    public T get(T inf102) {
        return null;
    }


//TODO: Implement generic graph and its functions
}
