package framework.collectors;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface ICollectorPool {
    void collectAllData() throws IOException;
    void collectAllDataAsync() throws IOException;
    void collectAllDataAsync(ThreadPoolExecutor threadPool) throws IOException;

    Iterator<ICollector> iterate();
    List<ICollector> getAllCollectors();
}
