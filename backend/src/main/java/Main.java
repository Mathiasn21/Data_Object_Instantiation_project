import framework.collectors.CSVCollector;
import framework.collectors.ICollector;
import framework.collectors.JSONCollector;
import framework.collectors.Setting;
import framework.datastructure.AVLTree;
import framework.annotations.DTO;
import framework.collectors.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        testCollectors();
        Collector c = Collector.fromFileExtension("csv");
        CBuilder b = new CBuilder();
        Collector c2 = b
                .setFileExtension()
                .setMaxMemory(200, "MB")
                .buildCollector();
        testAVL();
    }

    private static  void testAVL() throws IOException{
        System.out.println("\n\nAVL-TREE:");
        AVLTree tree = new AVLTree();
        AVLTree.Node node = new AVLTree.Node(5);
        tree.insert(node,11);
        tree.insert(node,2);
        tree.insert(node,4);
        tree.insert(node,7);
        tree.insert(node,9);
        tree.insert(node,1);
        System.out.println("No rotations/deletions:");
        tree.print(node);


        /* //DELETES NODE 2
        tree.deleteNode(node,2);
        System.out.println("Deleted node 2:");
        tree.print(node);
        */


        /* // ROTATES THE TREE RIGHT
        tree.rightRotate(node);
        System.out.println("Right rotation:");
        tree.print(node);
        */


        /* // ROTATES THE TREE LEFT
        tree.leftRotate(node);
        System.out.println("Left rotations:");
        tree.print(node);
        */
    }

    private static void testCollectors() throws IOException {
        ICollector collector = new CSVCollector("testingFileCSV.csv");
        collector.setSetting(Setting.DELIMITER, ";");
        collector.loadAndReadFile();

        System.out.println(collector);
        System.out.println(Arrays.toString(collector.getAllPrimaryColumns()));
        String[][] columns = collector.getAllColumns();
        for(String[] column : columns){
            if(column.length == 0)continue;
            System.out.println(Arrays.toString(column));
        }


        System.out.println("\n\n");
        String[] primaryKeys = {"Institusjonskode", "Institusjonsnavn"};
        ICollector collector2 = new JSONCollector("20200210-123-Registrerte studenter.json", primaryKeys);
        collector2.loadAndReadFile();

        String primaryJSONColumns = Arrays.toString(collector2.getAllPrimaryColumns());
        String[][] primaryColumns = collector2.getAllColumns();

        for(String[] column : primaryColumns){
            if(column.length == 0)continue;
            System.out.println(Arrays.toString(column));
        }
        System.out.println(primaryJSONColumns);
    }
}
