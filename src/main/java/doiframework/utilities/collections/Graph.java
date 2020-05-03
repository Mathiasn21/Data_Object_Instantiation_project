package DOIFramework.utilities.collections;

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
            /*if (gn.getData() == data) { // temporary code
                return true;
            }*/
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
        return nodes.get(t).getConnections().size(); //incorrect, but temporery pseudocode
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
        T[] neighbors = T;
        if (neighbors != null) {
            return null;
        } else {
            return neighbors;
        } //incorrect, but temporery pseudocode
    }

    public T get(T inf102) {
        return DFS(inf102); //incorrect, but temporery pseudocode
    }

    @NotNull
    public Iterator<GraphNode> DFS() {
        return new Iterator<GraphNode>() {
            private final Set<T> visited = new HashSet<>();
            private final Stack<Iterator<T>> stack = new Stack<>();
            private T next;
            private Graph<GraphNode> graph;

            @Override
            public boolean hasNext() {
                return this.next() != null;
            }

            @Override
            public GraphNode next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                try {
                    this.visited.add(this.next);
                    return (GraphNode) this.next;
                } finally {
                    this.advance();
                }
            }

            private void advance() {
                Iterator<T> neighbors = this.stack.peek();
                do {
                    while (!neighbors.hasNext()) {  // No more nodes -> back out a level
                        this.stack.pop();
                        if (this.stack.isEmpty()) { // All done!
                            this.next = null;
                            return;
                        }
                        neighbors = this.stack.peek();
                    }

                    this.next = neighbors.next();
                } while (this.visited.contains(this.next));
                this.stack.push(this.graph.getConnections(this.next).iterator());
            }
        };
    }

}
class GraphNode{
    private int uid;
    ArrayList<GraphNode> connections; //array list that contains the neighbours/edges of the node

    public GraphNode(int uid, ArrayList<GraphNode> connections){
        this.uid = uid;
        this.connections = connections;
    }

    public int getUid() {
        return uid;
    }

    public ArrayList<GraphNode> getConnections(){ return connections; }
}
