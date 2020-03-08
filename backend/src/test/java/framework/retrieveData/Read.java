package framework.retrieveData;

import framework.collectors.Collector;
import framework.collectors.CollectorBuilder;
import framework.collectors.ICollector;
import framework.utilities.data.Resource;
import framework.utilities.data.ResourceBuilder;
import framework.utilities.data.handle.CSVHandler;
import framework.utilities.data.handle.IHandle;
import framework.utilities.data.write.IWriteData;
import framework.utilities.data.write.WriteData;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Read {
    @Test
    void data_from_file() throws IOException {
        Resource resource = new ResourceBuilder().fromFile("").build();

        IHandle dataHandler = new CSVHandler();
        CollectorBuilder builder = Collector.getBuilder()
                .setResource(resource)
                .setDataHandler(dataHandler);

        ICollector collector = builder.build();
        collector.loadData();

        IWriteData writeData = new WriteData();
        writeData.toFile().given("","");
    }
}
