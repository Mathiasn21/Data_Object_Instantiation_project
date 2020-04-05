import framework.collectors.Collector;
import framework.collectors.CollectorPool;
import framework.collectors.ICollector;
import framework.collectors.ICollectorPool;
import framework.observer.EventObserver;
import framework.observer.events.CollectorFinishedEvent;
import framework.utilities.Parser;
import framework.resource.Resource;
import framework.utilities.handlers.CSVHandler;
import framework.utilities.handlers.JSONHandler;
import framework.utilities.collections.AVLTree;
import framework.utilities.collections.ITree;
import framework.utilities.collections.Node;
import framework.resource.commands.write.IWriteCommand;
import framework.resource.commands.write.WriteFileCommand;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Iterator;
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
        levelorder_traversal();

        String path = System.getProperty("user.dir") + "/files/simpleCSV.csv" ;
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new CSVHandler()).build();
        collector.collectData();


        System.out.println("Converting to value from string based on class: " + from_string_to_primitive_value());
    }

    private static void collectDataSingleColumnFromCSV() throws IOException {
        //Shows how to collect resource from a csv file
        String path = System.getProperty("user.dir") + "/files/trumpSpeeches.txt" ;
        Resource resource2 = Resource.newResource().fromFile(path).build();
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.setDelimiter("\\P{Alpha}+");
        csvHandler.isSingleColumn(true);
        csvHandler.skipEmptyLines(true);

        ICollector collector = Collector.newCollector(resource2, csvHandler).build();
        collector.setCompression(true);
        collector.collectData();
    }

    private static void collectDataWithPool() {
        //Showcasing more complex usage of the framework.
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        List<Resource> resources = Resource.newResource().fromFile(path).fromFile(path).buildAll();
        ICollectorPool collectorPool = CollectorPool.newCollectors(resources, new JSONHandler()).buildAll();
        collectorPool.collectAllDataAsync((ThreadPoolExecutor) Executors.newFixedThreadPool(2));
    }

    private static void collectFromJson() throws IOException {
        //Showcases how to collect resource from a json file
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        Resource resource = Resource.newResource().fromFile(path).build();//Will only build the first execute resource
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();//Data tries to be instantiated
    }

    //Shows how to read from a file
    @NotNull
    public static StringBuilder from_file_using_file() throws IOException {
        File path = new File(System.getProperty("user.dir") + "/files/DTOJson.json");
        Resource resource = Resource.newResource().fromFile(path).build();
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = resource.getData();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        return builder;
    }

    //Shows how to write to a file
    @NotNull
    public static StringBuilder to_file_using_string() throws IOException {
        String path = System.getProperty("user.dir") + "/files/writeToTest.txt";
        IWriteCommand writer = new WriteFileCommand(path);
        writer.execute("StringPath;");
        Resource resource = Resource.newResource().fromFile(path).build();
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = resource.getData();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        return builder;
    }

    public static double from_string_to_primitive_value(){
        String value = "22.34";
        return (double) Parser.toPrimitiveValueFromObject(Double.class, value);
    }

    public static boolean is_primitve_class() {
        String s = "Hei";
        return Parser.isPrimitiveType(s.getClass());

    }

    public static final int[]  scrambledFibonacci = {34, 1, 5, 1, 2, 13, 8, 3, 21};

    @NotNull
    private static ITree<Integer> genTree(){
        ITree<Integer> tree = new AVLTree<>();
        for(int numbers : scrambledFibonacci){ tree.insert(numbers); }
        return tree;
    }

    public static void levelorder_traversal(){
        var tree = genTree();
        for(int numbers : scrambledFibonacci) {
            tree.insert(numbers);
        }
        Iterator<Node<Integer>> iterator = tree.levelorderTraversal();
        System.out.println("Iterating over AVL-tree in level order:");
        while(iterator.hasNext()){
            System.out.println(iterator.next().getT());
        }
    }
}
