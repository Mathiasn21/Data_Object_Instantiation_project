import framework.collectors.Collector;
import framework.collectors.CollectorPool;
import framework.collectors.ICollector;
import framework.collectors.ICollectorPool;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //Showcasing usage of the framework.
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        Resource resource = Resource.newResource().fromFile(path).build();//Will only build the first given resource
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();

        //Showcasing more complex usage of the framework.
        String path2 = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        List<Resource> resources = Resource.newResource().fromFile(path2).fromFile(path2).buildAll();
        ICollectorPool collectorPool = CollectorPool.newCollectors(resources, new JSONHandler()).buildAll();
        collectorPool.collectAllData();
    }
}
