import framework.collectors.Collector;
import framework.collectors.CollectorPool;
import framework.collectors.ICollector;
import framework.collectors.ICollectorPool;
import framework.observer.EventObserver;
import framework.observer.events.CollectorFinishedEvent;
import framework.utilities.data.Parser;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.JSONHandler;
import framework.utilities.data.structure.AVLTree;
import framework.utilities.data.structure.ITree;
import framework.utilities.data.structure.Node;
import framework.utilities.data.write.IWriteCommand;
import framework.utilities.data.write.WriteFileWriteCommand;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
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
        System.out.println(from_string_to_primitive_value());
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

    //Shows how to read from a file
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
        IWriteCommand writer = new WriteFileWriteCommand(path, "StringPath;");
        writer.execute();
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
        return (double) Parser.classToValueFromObject(Double.class, value);
    }

    public static boolean is_primitve_class() {
        String s = "Hei";
        return Parser.isPrimitiveType(s.getClass());
    }

    public final int[] fibonacci = {1, 1, 2, 3, 5, 8, 13, 21, 34}, scrambledFibonacci = {34, 1, 5, 1, 2, 13, 8, 3, 21};
    @NotNull
    private ITree<Integer> genTree(){
        ITree<Integer> tree = new AVLTree<>();
        for(int numbers : scrambledFibonacci){ tree.insert(numbers); }
        return tree;
    }
    public List<Integer> levelorder_traversal(){
        var tree = genTree();
        int[] fibonacciLevelordered = {5, 1, 13, 1, 2, 8, 34, 3, 21};

        for(int numbers : scrambledFibonacci) {
            tree.insert(numbers);
        }
        Iterator<Node<Integer>> iterator = tree.levelorderTraversal();
        List<Integer> num = new ArrayList<>();
        for(int numbers : fibonacciLevelordered){
            num.add(numbers);
        }
        return num;
    }
}
