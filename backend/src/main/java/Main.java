import framework.collectors.Collector;
import framework.collectors.CollectorPool;
import framework.collectors.ICollector;
import framework.collectors.ICollectorPool;
import framework.observer.EventObserver;
import framework.observer.events.CollectorFinishedEvent;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.JSONHandler;

import java.io.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws IOException {
        //Showcasing usage of the framework.
        EventObserver.subscribeToEvent(event -> System.out.println("Got event: " + event), CollectorFinishedEvent.class);
        collectFromJson();
        collectDataWithPool();
        collectDataSingleColumnFromCSV();

    }

    private static void collectDataSingleColumnFromCSV() throws IOException {
        //Shows how to collect data from a csv file
        String path = System.getProperty("user.dir") + "/files/trumpSpeeches.txt" ;
        Resource resource2 = Resource.newResource().fromFile(path).build();
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.setDelimiter("\\P{Alpha}+");
        csvHandler.isSingleColumn(true);
        csvHandler.setSkipEmptyLines(true);
        ICollector collector2 = Collector.newCollector(resource2, csvHandler).build();
        collector2.setCompressionOn(true);
        collector2.collectData();
    }

    private static void collectDataWithPool() {
        //Showcasing more complex usage of the framework.
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        List<Resource> resources = Resource.newResource().fromFile(path).fromFile(path).buildAll();
        ICollectorPool collectorPool = CollectorPool.newCollectors(resources, new JSONHandler()).buildAll();
        collectorPool.collectAllDataAsync((ThreadPoolExecutor) Executors.newFixedThreadPool(2));
    }

    private static void collectFromJson() throws IOException {
        //Showcases how to collect data from a json file
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        Resource resource = Resource.newResource().fromFile(path).build();//Will only build the first execute resource
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();//Data tries to be instantiated
    }
}
