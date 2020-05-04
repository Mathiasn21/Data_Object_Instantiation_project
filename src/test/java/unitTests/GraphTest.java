package unitTests;

import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.CSVHandler;
import doiframework.utilities.collections.Graph;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    void insert_into_graph() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_1.txt";
        var initArgs = getInfo(path);
        Graph<Integer> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 3];

            int uid = (int) args[0];
            int number = (int) args[1];

            for (int j = 0, argument = 3; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            //graph.insert(uid, number, connections);
            assertTrue(graph.contains(uid));
            assertTrue(graph.contains(number));
        }
        assertEquals(8, graph.size());
        int[][] connections = {
                {5,6},
                {3,5,6,7},
                {6,4},
                {5},
                {9,10},
                {8,9,10},
                {8,12},
                {},
                {},
                {12},
                {11},
                {},
                {}
        };

        for(int i = 0; i < connections.length; i++){
            assertEquals(connections[i].length, graph.numConnections(i));
            ArrayList conns = graph.getConnections(i);
            assertTrue(conns.size() != 0);

            for(int j = 0; j < connections[i].length; j++){
                assertEquals(connections[i][j], conns.get(j));
            }
        }
    }

    @Test
    void insert_into_graph_generic() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_2.txt";
        var initArgs = getInfo(path);
        Graph<String> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 3];

            int uid = (int) args[0];
            String str = (String) args[1];

            for (int j = 0, argument = 3; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            //graph.insert(uid, str, connections);
        }
        assertEquals(8, graph.size());

        int[][] connections = {
                {3,4},
                {3},
                {4,7},
                {5,6,7},
                {6},
                {},
                {},
                {}
        };
        for(int i = 0; i < connections.length; i++){
            assertEquals(connections[i].length, graph.numConnections(i));
            ArrayList conns = graph.getConnections(i);
            assertTrue(conns.size() != 0);

            for(int j = 0; j < connections[i].length; j++){
                assertEquals(connections[i][j], conns.get(j));
            }
        }
    }

    @Test
    void remove_from_graph() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_1.txt";
        var initArgs = getInfo(path);
        Graph<Integer> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 3];

            int uid = (int) args[0];
            int number = (int) args[1];

            for (int j = 0, argument = 3; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            //graph.insert(uid, number, connections);
            assertTrue(graph.contains(uid));
        }
        assertEquals(8, graph.size());
        graph.remove(5);
        assertFalse(graph.contains(5));
        assertEquals(7, graph.size());

        assertNull(graph.get(5));

        assertEquals(2, graph.numConnections(3));
        assertEquals(2, graph.numConnections(Integer.valueOf(11)));
    }

    @Test
    void remove_from_graph_generic() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_2.txt";
        var initArgs = getInfo(path);
        Graph<String> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 3];

            int uid = (int) args[0];
            String str = (String) args[1];

            for (int j = 0, argument = 3; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            //graph.insert(uid, str, connections);
            assertTrue(graph.contains(uid));
        }
        assertEquals(8, graph.size());

        graph.remove("INF102");
        assertEquals(7, graph.size());
        assertFalse(graph.contains("INF102"));
        assertNull(graph.get("INF102"));
    }


    @Test
    void breadth_first_search() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_2.txt";
        var initArgs = getInfo(path);

        Graph<String> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] readConnections = new int[args.length - 3];

            int uid = (int) args[0];
            String str = (String) args[1];

            for (int j = 0, argument = 3; j < readConnections.length; j++, argument++) {
                readConnections[j] = (int) args[argument];
            }
            //graph.insert(uid, str, readConnections);
            assertTrue(graph.contains(uid));
        }
        assertEquals(8, graph.size());

        Iterator iterator = graph.DFS();
        assertNotNull(iterator);
    }


    @Test
    void deph_first_search() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_2.txt";
        var initArgs = getInfo(path);
        Graph<String> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 3];

            int uid = (int) args[0];
            String str = (String) args[1];

            for (int j = 0, argument = 2; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            //graph.insert(uid, str, connections);
            assertTrue(graph.contains(uid));
        }
        assertEquals(8, graph.size());
    }


    @NotNull
    private List<Object[]> getInfo(String path) throws IOException {
        var csvHandler = new CSVHandler();
        csvHandler.skipEmptyLines(true);
        csvHandler.setSampleEachLine(true);
        csvHandler.setDelimiter("\\s+");

        var resource = DataSource.newResource().fromFile(path).build();
        return csvHandler.handle(resource.getData());
    }
}
