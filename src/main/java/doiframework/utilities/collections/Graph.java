package doiframework.utilities.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/** Class for handling of generic graph
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class Graph<T> {
    public static List<GraphNode> nodes;
    int nmbrOfNodes;

    public void insert(int uid, T number, ArrayList<GraphNode> connections) {
        GraphNode node = new GraphNode(uid, connections);
        nodes.add(node);
        nmbrOfNodes++;
    }

    public boolean contains(T data) {
        for (GraphNode gn : nodes) {
            if (gn.getData() == data) { // temporary code
                return true;
            }
        }
        return false;
    }

    public boolean contains(int uid) {
        for (GraphNode gn : nodes) {
            if (gn.getUid() == uid) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return nmbrOfNodes;
    } //will return the number of nodes

    public void remove(T t) {
        nodes.removeIf(gn -> gn == t);
    }

    public int numConnections(int uid) {
        return nodes.get(uid).getConnections().size();
    }

    public int numConnections(T t) {
        return 0;
    }

    public static void DFS(GraphNode startingNode) { //graph depth first search
        ArrayList<GraphNode> visitedNodes = new ArrayList<GraphNode>();
        Stack<GraphNode> stack= new Stack<GraphNode>();
        stack.push(startingNode);
        while (!stack.empty()){
            GraphNode currentNode = stack.pop();
            if(!visitedNodes.contains(currentNode)){
                visitedNodes.add(currentNode);
                ArrayList<GraphNode> connections = currentNode.getConnections();
                for(int i = connections.size(); i >= 0; i--){
                    stack.push(nodes.get(i));
                }
            }
        }
    }

    public ArrayList<GraphNode> getConnections(int i) {
        return nodes.get(i).getConnections();
    }

    public T[] getConnections(T t) {
        return null;
    }

    public T get(T inf102) {
        return null;
    }

    @NotNull
    public Iterator<GraphNode> DFS() {
        Iterator<GraphNode> iterator = new Iterator<GraphNode>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public GraphNode next() {
                return null;
            }
        };
        return iterator;
    }
}
