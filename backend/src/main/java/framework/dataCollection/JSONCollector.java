package framework.dataCollection;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class JSONCollector extends Collector {
    private final String[] keys;

    public JSONCollector(String[] identifiers, String fileName) {
        this.keys = identifiers;
    }

    @Override
    public String[][] getAllColumns() {
        return new String[0][];
    }

    @Override
    public void loadAndReadFile() throws IOException {

    }

}
