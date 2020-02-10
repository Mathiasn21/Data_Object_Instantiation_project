package framework.dataCollection;

/**
 * This class represents a general item
 * that exists in a dataset
 */
public class Item {
    private String[] row;
    private String name;
    public Item(String name, String[] row){
        this.name = name;
        this.row = row;
    }
}