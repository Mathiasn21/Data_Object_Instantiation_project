package DOIFramework.utilities.collections;

public class GraphNode{
    int uid;
    int[] connections;

    public GraphNode(int uid, int[] connections){
        this.uid = uid;
        this.connections = connections;
    }

    public int getUid() {
        return uid;
    }

    public int[] getConnections(){ return connections; }
}
