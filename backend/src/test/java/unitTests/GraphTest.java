package unitTests;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.structure.Graph;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
    @Test
    void insert_into_graph() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_1.txt";
        var initArgs = getInfo(path);
        Graph<Integer> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 2];

            int uid = (int) args[0];
            int number = (int) args[1];

            for (int j = 0, argument = 2; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            graph.insert(uid, number, connections);
            assertTrue(graph.contains(uid));
        }
        assertEquals(8, graph.size());
    }

    @Test
    void insert_into_graph_generic() throws IOException {
        var path = System.getProperty("user.dir") + "/files/graf_topsort_2.txt";
        var initArgs = getInfo(path);
        Graph<String> graph = new Graph<>();
        for (Object[] args : initArgs) {
            int[] connections = new int[args.length - 2];

            int uid = (int) args[0];
            String str = (String) args[1];

            for (int j = 0, argument = 2; j < connections.length; j++, argument++) {
                connections[j] = (int) args[argument];
            }
            graph.insert(uid, str, connections);
            assertTrue(graph.contains(uid));
        }
        assertEquals(8, graph.size());
    }

    @NotNull
    private List<Object[]> getInfo(String path) throws IOException {
        var csvHandler = new CSVHandler();
        csvHandler.setSkipEmptyLines(true);
        csvHandler.setSampleEachLine(true);
        csvHandler.setDelimiter("\\s+");

        var resource = Resource.newResource().fromFile(path).build();
        return csvHandler.handle(resource.getData());
    }
}
