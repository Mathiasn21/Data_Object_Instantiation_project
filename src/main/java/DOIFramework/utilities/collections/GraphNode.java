package DOIFramework.utilities.collections;

public class GraphNode<T>{
    int uid;
    T number;
    int[] connections;

    public GraphNode(int uid, T number, int[] connections){
        this.uid = uid;
        this.number = number;
        this.connections = connections;
    }

    public int getUid() {
        return uid;
    }

    public T getNumber(){ return number; }

    public int[] getConnections(){ return connections; }
}
