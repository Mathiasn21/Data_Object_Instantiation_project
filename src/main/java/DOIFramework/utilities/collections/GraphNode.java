package DOIFramework.utilities.collections;

import java.util.ArrayList;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @param <T>
 */

public class GraphNode{
    private int uid;
    private int[] connections;

    public GraphNode(int uid, int... connections){
        this.uid = uid;
        this.connections = connections;
    }

    public int getUid() {
        return uid;
    }
    public int[] getConnections(){ return connections; }
}
