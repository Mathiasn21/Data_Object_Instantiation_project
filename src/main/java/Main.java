import DTOs.News;
import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.JSONHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/files/newsfeed.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataCollector collector = DataCollector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();
        List<News> list = collector.getAllObjects(News.class);
        System.out.println(list.toString());
    }
}
