package DOIFramework.utilities.collections;

import java.util.ArrayList;

public class GraphNode<T>{
    int uid;
    T number;
    int[] connections; //array list that will contain this node's connections to other nodes

    public GraphNode(int uid, T number, int ... connections){
        this.uid = uid;
        this.number = number;
        this.connections = connections;
    }
}
