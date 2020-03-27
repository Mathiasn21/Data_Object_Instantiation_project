package framework.collectors;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

public interface ICollectorPool {
    void collectAllData() throws IOException;
    void collectAllDataAsync() throws IOException;
    void collectAllDataAsync(ThreadPoolExecutor threadPool) throws IOException;
}
