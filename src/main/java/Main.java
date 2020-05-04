import DTOs.TrumpWord;
import doiframework.core.collectors.DataCollector;
import doiframework.core.collectors.IDataCollector;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.JSONHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/files/DTOJson.json" ;
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        IDataCollector collector = DataCollector.newCollector(dataSource, new JSONHandler()).build();
        collector.collectData();
        List<TrumpWord> list = collector.getAllObjects(TrumpWord.class);
    }
}
