# Scenarios

## Collecting from a single dataSource:

### Data used:
Data used for this page.
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
import doiframework.*;

public class ReportMain {
    public static void main(String[] args) {
        //Showcases how to collect dataSource from a json file
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        Resource dataSource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();//Data tries to be collected
    }
}
```


#### Data formatted as CSV:
```java
import doiframework.*;

public class ReportMain {
    public static void main(String[] args) {
        //Showcases how to collect dataSource from a json file
        String path = System.getProperty("user.dir") + "/files/finalCountdownCSV.csv";//Just a path
        Resource dataSource = Resource.newResource().fromFile(path).build();
        ICollector collector = Collector.newCollector(dataSource, new CSVHandler()).build();
        collector.collectData();//Data tries to be collected
    }
}
```

It is possible to substitute string path with an actual file class.

### Collecting from URL:
Collecting data from a URL is just as easy as collecting from a file. Do note that this just uses
a simple URL connection. If one wishes to alter this behaviour then you must create you're own reader.

#### Data formatted as JSON:
```java
import doiframework.*;

public class ReportMain {
    public static void main(String[] args) {
        String url = "https://someAPI.com";
        Resource dataSource = Resource.newResource().fromURL(url).build();
        ICollector collector = Collector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();
    }
}
```

#### Data formatted as CSV:
```java
import doiframework.*;

public class ReportMain {
    public static void main(String[] args) {
        String url = "https://someAPI.com";
        Resource dataSource = Resource.newResource().fromURL(url).build();
        ICollector collector = Collector.newCollector(dataSource, new CSVHandler()).build();
        collector.collectData();
    }
}
```
It is possible to substitute string url with an actual URL class from the java.net package.

**Note** the usage of **new CSVHandler** to indicate that this data is formatted as **CSV**.
And **new JSONHandler** to indicate that it is in **JSON**.