package doiframework.utilities.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/** Class for handling of generic graph
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public class Graph<T> {
    public List<GraphNode<T>> nodes;
    int nmbrOfNodes;

    public void insert(int uid, T number, ArrayList<GraphNode<T>> connections) {
        GraphNode<T> node = new GraphNode<>(uid, number, connections);
        nodes.add(node);
        nmbrOfNodes++;
    }

    public boolean contains(T data) {
        for (GraphNode<T> gn : nodes) {
            if (gn.getData() == data) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(int uid) {
        for (GraphNode<T> gn : nodes) {
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
        for (GraphNode<T> gn: nodes) {
            if(gn.getData() == t){
                return gn.getConnections().size();
            }
        }
        return 1;
    }

    public void DFS(GraphNode<T> startingNode) { //graph depth first search
        ArrayList<GraphNode<T>> visitedNodes = new ArrayList<>();
        Stack<GraphNode<T>> stack= new Stack<>();
        stack.push(startingNode);
        while (!stack.empty()){
            GraphNode<T> currentNode = stack.pop();
            if(!visitedNodes.contains(currentNode)){
                visitedNodes.add(currentNode);
                ArrayList<GraphNode<T>> connections = currentNode.getConnections();
                for(int i = connections.size(); i >= 0; i--){
                    stack.push(nodes.get(i));
                }
            }
        }
    }

    public ArrayList<GraphNode<T>> getConnections(int i) {
        return nodes.get(i).getConnections();
    }

    public T[] getConnections(T t) {
        for (GraphNode<T> gn: nodes) {
            if(gn.getData() == t){
                return (T[]) gn.getConnections().toArray();
            }
        }
        return null;
    }

    public T get(T inf102) {
        for (GraphNode<T> gn: nodes) {
            if(gn.getData() == inf102){
                return gn.getData();
            }
        }
        return null;
    }

    @NotNull
    public Iterator<GraphNode<T>> DFS() {
        return new Iterator<>() {
            private final Set<T> visited = new HashSet<>();
            private final Stack<Iterator<T>> stack = new Stack<>();
            private T next;
            private Graph<GraphNode<T>> graph;

            @Override
            public boolean hasNext() {
                    return this.next() != null;
            }

            @Override
            public GraphNode<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                try {
                    this.visited.add(this.next);
                    return (GraphNode<T>) this.next;
                } finally {
                    this.advance();
                }
            }

            private void advance() {
                Iterator<T> neighbors = this.stack.peek();
                do {
                    while (!neighbors.hasNext()) {
                        this.stack.pop();
                        if (this.stack.isEmpty()) {
                            this.next = null;
                            return;
                        }
                        neighbors = this.stack.peek();
                    }
                    this.next = neighbors.next();
                } while (this.visited.contains(this.next));
                    this.stack.push(this.graph.getConnections((GraphNode<T>)this.next).iterator()); // ???
            }
        };
    }

}
class GraphNode<T>{
    private final int uid;
    private final T data;
    ArrayList<GraphNode<T>> connections; //array list that contains the neighbours/edges of the node

    public GraphNode(int uid, T number, ArrayList<GraphNode<T>> connections){
        this.uid = uid;
        data = number;
        this.connections = connections;
    }

    public int getUid() {
        return uid;
    }

    public T getData() { return data; }

    public ArrayList<GraphNode<T>> getConnections(){ return connections; }
}
