package DOIFramework.utilities.collections;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** Class for handling of generic graph
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class Graph<T> {
    List<GraphNode> nodes;
    int nmbrOfNodes;

    public void insert(int uid, T number, int... connections) {
        nodes.add(new GraphNode(uid, connections));
        nmbrOfNodes++;
    }

    public boolean contains(T data) {
        return false;
    }

    public boolean contains(int uid) {
        return false;
    }

    public int size() {
        return nmbrOfNodes;
    }

    public void remove(T t) {
        nmbrOfNodes--;
    }

    public int numConnections(int uid) {
        return nodes.get(uid).getConnections().length;
    }

    public int numConnections(T t) {
        return 0;
    }

    public Iterator<GraphNode> DFS() {
        return null;
    }

    public int[] getConnections(int i) {
        return nodes.get(i).getConnections();
    }

    public T[] getConnections(T t) {
        return null;
    }

    public T get(T inf102) {
        return null;
    }

}
