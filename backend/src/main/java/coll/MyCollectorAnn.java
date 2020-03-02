package coll;

import coll.test.Collector;
import coll.test.Loader;
import framework.annotations.DataObject;

@Collector(file = "FileName", fileType = "FileName", dataClass = DataObject.class)
public class MyCollectorAnn {
    public MyCollectorAnn() {
    }

    @Loader
    public final String[][] loadAndReadData(){
        return new String[0][0];
    }
}
