package coll;

import framework.collectors.Collector;
import framework.collectors.Item;
import java.io.IOException;

public class MyCollector extends Collector {
    @Override
    public String[][] getAllColumns() {
        return new String[0][];
    }

    @Override
    public void loadAndReadFile() throws IOException {

    }

    @Override
    public Item[] getCategoryBy(String name) {
        return new Item[0];
    }

    @Override
    public String[] getColumnBy(String columnName) {
        return new String[0];
    }
}