package DOIFramework.utilities.collections;

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
        if(nodes.contains(data)){ //temporary pseudo code for checking of list contains the data
            return true;
        }
        else{
            return false;
        }
    }

    public boolean contains(int uid) {
        if(nodes.contains(uid)){ //temporary pseudo code for checking of list contains the uid
            return true;
        }
        else{
            return false;
        }
    }

    public int size() {
        return nmbrOfNodes;
    } //will return the number of nodes

    public void remove(T t) {
        nodes.remove(t); //incorrect, but its pseudo code, so it will be implemented later
        nmbrOfNodes--;
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

}
