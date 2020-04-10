package DOIFramework.utilities.collections;

public class GraphNode<T>{
    T number;
    int[] connections; //array list that will contain this node's connections to other nodes

    public GraphNode(T number, int ... connections){
        this.number = number;
        this.connections = connections;
    }

    public int[] getConnections() {
        return connections;
    }

    public T getNumber() {
        return number;
    }
}
