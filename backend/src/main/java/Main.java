import framework.collectors.Collector;
import framework.collectors.ICollector;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;//Just a path
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.CollectData();

        System.out.println(System.currentTimeMillis() - start);



        /*
        * ICollector c = Collector.newInstance(() -> {}, () -> resource);
        *
        * */
    }
}
