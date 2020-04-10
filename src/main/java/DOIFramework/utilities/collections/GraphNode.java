package DOIFramework.utilities.collections;

import java.util.ArrayList;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 * @param <T>
 */

public class GraphNode<T>{
    private int uid;
    private T number;
    private int[] connections;

    public GraphNode(int uid, T number, int... connections){
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
