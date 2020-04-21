## Collecting from multiple dataSources:

### Data used:
Data used for this page.
JSON:

    [{
      "string": "thing"
    },{
      "string": "thing"
    }]

**Note:** Assumes all dataobject classes has already been setup correctly beforehand.

### Collecting from file:

#### Define dataSources multiple times:
```java
import doiframework.*;

public class Main {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        List<Resource> dataSources = Resource.newResource().fromFile(path).fromFile(path).buildAll();
        ICollectorPool collectorPool = CollectorPool.newCollectors(dataSources, new JSONHandler()).buildAll();
        collectorPool.collectAllDataAsync();
    }
}
```
Note the chained invocations of fromFile().
 
#### Define multiple dataSources once
```java
import doiframework.*;

public class Main {
    public static void main(String[] args) { 
        String path = System.getProperty("user.dir") + "/files/DTOJson.json";//Just a path
        String[] paths = {path, path, path}; //Multiple paths
        List<Resource> dataSources = Resource.newResource().fromFile(paths).buildAll();
        ICollectorPool collectorPool = CollectorPool.newCollectors(dataSources, new JSONHandler()).buildAll();
        collectorPool.collectAllDataAsync();
    }
}
```
It is both possible to do the same whenever defining URL dataSources. In accordance with the single dataSource scenario,
one is allowed to utilize the respective classes: File and URL, instead of strings for defining paths/urls.

**Note** the invocation of **buildAll()** for building a list of dataSources.


With many dataSource one must utilize a pool of collectors. these are instantiated by 
using the corresponding builder like this: 

    ICollectorPool collectorPool = CollectorPool.newCollectors(dataSources, new JSONHandler()).buildAll();
    
A collector pool collects data either async or sync by invoking respective methods.
Further said pool allows one to define their own executor service if needed. Like this:

    collectorPool.collectAllDataAsync((ThreadPoolExecutor) Executors.newFixedThreadPool(2));
    
 