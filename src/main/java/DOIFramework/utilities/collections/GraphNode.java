package DOIFramework.utilities.collections;

import java.util.ArrayList;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @param <T>
 */

public class GraphNode{
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
