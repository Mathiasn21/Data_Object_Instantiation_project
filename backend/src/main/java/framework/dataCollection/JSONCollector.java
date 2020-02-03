package framework.dataCollection;

import java.util.Collection;

public class JSONCollector extends Collector {
    private final String[] keys;

    public JSONCollector(String[] identifiers, String fileName) {
        this.keys = identifiers;
    }
}
