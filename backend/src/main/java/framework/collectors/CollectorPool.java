package framework.collectors;

import framework.utilities.data.Resource;
import framework.utilities.data.handle.IHandle;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CollectorPool implements ICollectorPool{
    private byte numberOfThreads = 2;
    private final List<ICollector> collectors;

    CollectorPool(List<ICollector> collectors) { this.collectors = collectors; }

    @Override
    public void collectAllData() throws IOException {
        for (ICollector collector : collectors) { collector.collectData(); }
    }

    /**
     * Utilizes mutliple threads that does not block the main thread from exectuing.
     * By default this utlizes a default {@link ThreadPoolExecutor} with 2 threads
     * @throws IOException IOException {@link IOException}
     */
    @Override
    public void collectAllDataAsync() throws IOException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        collectAllDataAsync(pool);
    }


    /*
    TODO: Add more proper error handling.
     Like: Error has occoured boolean,
     and a map or list of stacktraces.
    */
    /**
     * @param pool {@link ThreadPoolExecutor}
     */
    @Override
    public void collectAllDataAsync(ThreadPoolExecutor pool){
        for (ICollector collector : collectors) {
            pool.submit(() -> {
                try { collector.collectData();
                } catch (IOException e) { e.printStackTrace(); }
                return null;
            });
        }
        pool.shutdown();
    }

    @Override
    public Iterator<ICollector> iterator() { return collectors.iterator(); }

    @Override
    public List<ICollector> getAllCollectors() { return Collections.unmodifiableList(collectors); }

    @Override
    public void setNumberOfThreads(byte number) { numberOfThreads = number; }

    /**
     * @param resources {@link List}&lt;{@link Resource}&gt;
     * @param dataHandler {@link IHandle}
     * @return {@link CollectorBuilder}
     */
    @NotNull
    @Contract("_, _ -> new")
    public static CollectorPoolBuilder newCollectors(List<Resource> resources, IHandle dataHandler) {
        return new CollectorPoolBuilder(resources, dataHandler);
    }
}