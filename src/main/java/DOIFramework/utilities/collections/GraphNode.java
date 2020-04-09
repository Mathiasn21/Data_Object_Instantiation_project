package DOIFramework.utilities.collections;

import java.util.ArrayList;

public class GraphNode<T>{
    T data;
    ArrayList<T> edges = new ArrayList<>(); //array list that will contain edges and (if necessary) their weight
    public GraphNode(T data, ArrayList<T> edges){
        this.data = data;
        this.edges = edges;
    }
}
