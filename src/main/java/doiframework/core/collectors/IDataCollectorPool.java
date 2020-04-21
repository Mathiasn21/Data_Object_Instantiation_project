package doiframework.core.collectors;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public interface IDataCollectorPool {
    void collectAllData() throws IOException;
    void collectAllDataAsync();
    void collectAllDataAsync(ThreadPoolExecutor threadPool);

    Iterator<IDataCollector> iterator();
    List<IDataCollector> getAllCollectors();

    void setNumberOfThreads(byte number);
}
