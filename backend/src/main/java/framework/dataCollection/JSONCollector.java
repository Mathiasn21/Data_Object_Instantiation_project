package framework.dataCollection;

public class JSONCollector implements ICollector {
    private final String[] keys;

    public JSONCollector(String[] identifiers, String fileName) {
        this.keys = identifiers;
    }


    @Override
    public void getAllPrimaryColumns() {

    }

    @Override
    public void getAllFilledColumns() {

    }

    @Override
    public void getCategoryBy(String name) {

    }
}
