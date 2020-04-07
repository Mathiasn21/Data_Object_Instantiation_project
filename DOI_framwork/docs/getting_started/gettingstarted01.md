# Scenarios:

## Collect data from a resource:

### Data used:
These are actual data used for this page.

JSON:

    [{
      "string": "thing"
    },{
      "string": "thing"
    }]

CSV - With **default delimiter(,)**:

    "dwada",5.5,5
    "dwada",5.5,5
    "dwada",5.5,5
    
**Note:** Assumes all dataobject classes has already been setup correctly beforehand.

### Collecting from file:

#### Data formatted as JSON:
```java
import DOIFramework.*;

public class Main {
    public static void main(String[] args) {
        //Showcases how to collect resource from a json file
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();//Data tries to be collected
    }
}
```


#### Data formatted as CSV:
```java
import DOIFramework.*;

public class Main {
    public static void main(String[] args) {
        //Showcases how to collect resource from a json file
        String path = System.getProperty("user.dir") + "/files/simpleCSV.csv";//Just a path
        Resource resource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(resource, new CSVHandler()).build();
        collector.collectData();//Data tries to be collected
    }
}
```

### Collecting from URL:

#### Data formatted as JSON:
```java
import DOIFramework.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://someAPI.com";
        Resource resource = Resource.newResource().fromURL(url).build();
        ICollector collector = Collector.newCollector(resource, new JSONHandler()).build();
        collector.collectData();
    }
}
```

#### Data formatted as CSV:
```java
import DOIFramework.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://someAPI.com";
        Resource resource = Resource.newResource().fromURL(url).build();
        ICollector collector = Collector.newCollector(resource, new CSVHandler()).build();
        collector.collectData();
    }
}
```


**Note** the usage of **new CSVHandler** to indicate that this data is formatted as **CSV**.
And **new JSONHandler** to indicate that it is in **JSON**.