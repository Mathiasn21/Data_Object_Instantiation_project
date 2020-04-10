package DOIFramework.utilities.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** Class for handling of generic graph
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class Graph<T> {
    List<GraphNode<T>> nodes;
    int nmbrOfNodes;

    public void insert(int uid, T number, int... connections) {
        GraphNode<T> node = new GraphNode<T>(uid, number, connections)
        nodes.add(node);
        nmbrOfNodes++;
    }

    public boolean contains(T data) {
        if(nodes.contains(data) == true){ //temporary pseudo code for checking of list contains the uid
            return true;
        }
        else{
            return false;
        }
    }

    public boolean contains(int uid) {
        if(nodes.contains(uid) == true){ //temporary pseudo code for checking of list contains the uid
            return true;
        }
        else{
            return false;
        }
    }

    public int size() {
        return nmbrOfNodes;
    }

    public void remove(T t) {
        nodes.remove(t); //incorrect, but its pseudo code, so it will be implemented later
        nmbrOfNodes--;
    }

    public int numConnections(int uid) {
        return nodes.get(uid).getConnections().length;
    }

    public int numConnections(T t) {
        return 0;
    }

    public Iterator<GraphNode<T>> DFS() { //iterator for distributed file system
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
